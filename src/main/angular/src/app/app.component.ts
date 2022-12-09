import {Component, OnChanges, OnInit} from '@angular/core';
import {RoleService} from './service/role.service';
import {Router} from '@angular/router';
import {ChatService} from './service/chat.service';
import {HallService} from './service/hall.service';
import {SnackbarService} from './service/snackbar.service';
import {Subscription, takeUntil} from "rxjs";
import {untilDestroyed} from "@ngneat/until-destroy";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnChanges, OnInit {

  isReferee = false;
  lastChatCheckedDate: Date = new Date(Date.now());
  private subscription: Subscription;

  constructor(
    private roleService: RoleService,
    private router: Router,
    private chatService: ChatService,
    private hallService: HallService,
    private snackbarService: SnackbarService,
  ) {
    this.isReferee = this.roleService.getRole() && this.roleService.getRole().name === 'REFEREE';
  }

  ngOnChanges() {
    this.isReferee = this.roleService.getRole() && this.roleService.getRole().name === 'REFEREE';
    console.log(this.isReferee);
  }

  ngOnInit() {
    this.roleService.role$.subscribe(role => {
      this.subscription?.unsubscribe()
      this.isReferee = role && role.name === 'REFEREE';
      if (this.isReferee || role?.name === 'ADMIN') {
        this.subscription = this.chatService.chatChanges$.subscribe(async () => {
          const hallIds: number[] = this.hallService.isHallSet()
            ? [(await this.hallService.getCurrentHall()).id]
            : [1, 2];
          for (const hallId of hallIds) {
            const chats = await this.chatService.getChatsByHall(hallId)
            if (chats.some(chat =>
              role.name !== chat.creator
              && chat.createdDate
              && new Date(chat.createdDate).getTime() > this.lastChatCheckedDate.getTime())) {
              this.snackbarService.showMessage('Neue Nachricht', 60000);
            }
            this.lastChatCheckedDate = new Date(Date.now());
          }
        });
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
