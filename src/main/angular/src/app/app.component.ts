import { Component, OnChanges, OnInit } from '@angular/core';
import { RoleService } from './service/role.service';
import { Router } from '@angular/router';
import { ChatService } from './service/chat.service';
import { HallService } from './service/hall.service';
import { SnackbarService } from './service/snackbar.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnChanges, OnInit {

  isReferee = false;
  lastChatCheckedDate: Date = new Date(Date.now());

  constructor(private roleService: RoleService, private router: Router, private chatService: ChatService,
              private hallService: HallService, private snackbarService: SnackbarService,) {
    this.isReferee = this.roleService.getRole() && this.roleService.getRole().name === 'REFEREE';
  }

  ngOnChanges() {
    this.isReferee = this.roleService.getRole() && this.roleService.getRole().name === 'REFEREE';
    console.log(this.isReferee);
  }

  ngOnInit() {
    this.roleService.role$.subscribe(role => {
      this.isReferee = role && role.name === 'REFEREE';
      if (this.isReferee || role?.name === 'ADMIN') {
        this.chatService.getChatEvents().then(events => events.subscribe(() => {
          let hallPromise;
          if (this.hallService.isHallSet()) {
            hallPromise = this.hallService.getCurrentHall().then(hall => [hall.id]);
          } else {
            hallPromise = Promise.resolve([1, 2]);
          }
          hallPromise.then(hallIds => {
            hallIds.forEach(hallId => {
              this.chatService.getChatsByHall(hallId)
                .then(chats => {
                  if (chats.some(chat => chat.createdDate && new Date(chat.createdDate).getTime()
                    > this.lastChatCheckedDate.getTime())) {
                    this.snackbarService.showMessage('Neue Nachricht');
                  }
                  this.lastChatCheckedDate = new Date(Date.now());
                });
            });
          });
        }));
      }
    });
  }

  logout() {
    localStorage.clear();
    window.location.href = '';
  }

  openChat() {
    this.router.navigateByUrl('referee/chats');
  }

  openGames() {
    this.router.navigateByUrl('referee/games');
  }
}
