import {Component, inject, OnChanges, OnInit} from '@angular/core';
import {RoleService} from './service/role.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ChatService} from './service/chat.service';
import {HallService} from './service/hall.service';
import {SnackbarService} from './service/snackbar.service';
import {Subscription} from "rxjs";
import {SwUpdate} from "@angular/service-worker";
import {OneSignal} from "onesignal-ngx";
import {StompService} from "./stomp.service";
import {map} from "rxjs/operators";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  isReferee = false;
  lastChatCheckedDate: Date = new Date(Date.now());
  private subscription: Subscription;

  private gameStartSubscription: Subscription;
  constructor(
    private roleService: RoleService,
    private router: Router,
    private chatService: ChatService,
    private hallService: HallService,
    private snackbarService: SnackbarService,
    private oneSignal: OneSignal,
    private sw: SwUpdate,
    private stompService: StompService,
  ) {
    sw.checkForUpdate().catch(console.error)
    this.oneSignal.init({
      appId: location.href.includes('localhost') ? 'acd8ce34-dd03-467d-8745-153dbf05a0d3' : 'a4c31416-21df-4dec-ad3b-202580f32eca',
    }).catch(console.error)
    this.isReferee$.subscribe(referee => {
      if (referee) {
        this.gameStartSubscription = this.stompService.watch('/topic/game-started').subscribe(() => this.snackbarService.showMessage("Game Started"));
      } else {
        this.gameStartSubscription?.unsubscribe()
      }
    })
    if (this.isReferee) {

    }
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

  protected readonly location = location;
  protected readonly document = document;
  isLoggedIn$ = inject(RoleService).role$.pipe(map(role => !!role));
  isReferee$ = inject(RoleService).role$.pipe(map(role => role?.name === 'REFEREE'));
  isAdmin$ = inject(RoleService).role$.pipe(map(role => role?.name === 'ADMIN'));
}
