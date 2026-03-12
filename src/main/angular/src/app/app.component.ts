import {Component, inject, OnInit} from '@angular/core';
import {RoleService} from './service/role.service';
import {Router} from '@angular/router';
import {ChatService} from './service/chat.service';
import {HallService} from './service/hall.service';
import {SnackbarService} from './service/snackbar.service';
import {Subscription} from "rxjs";
import {SwUpdate} from "@angular/service-worker";
import {OneSignal} from "onesignal-ngx";
import {StompService} from "./stomp.service";
import { NotificationService } from './v2/pages/notifications/notification.service';
import {AuthService} from './auth/auth.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss'],
    standalone: false
})
export class AppComponent implements OnInit {
  navbarCollapsed = false;

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
    private authService: AuthService,
    private stompService: StompService,
  ) {
    sw.checkForUpdate().catch(console.log);
    this.oneSignal.init({
      appId: location.href.includes('localhost') ? 'acd8ce34-dd03-467d-8745-153dbf05a0d3' : 'a4c31416-21df-4dec-ad3b-202580f32eca',
    })
    .then(() => this.notificationService.triggerOneSignalReady())
    .catch(console.error);

    this.isReferee$.subscribe(referee => {
      if (referee) {
        this.gameStartSubscription = this.stompService.watch('/topic/game-started').subscribe(() => this.snackbarService.showMessage("Game Started"));
      } else {
        this.gameStartSubscription?.unsubscribe();
      }
    });
  }

  ngOnInit() {
    try {
      this.navbarCollapsed = localStorage.getItem('navbarCollapsed') === 'true';
    } catch (e) {
      this.navbarCollapsed = false;
    }
    // Subscribe to chat changes for referees and admins
    this.isReferee$.subscribe(referee => {
      this.isReferee = referee;
    });
    this.isAdmin$.subscribe(isAdmin => {
      this.subscription?.unsubscribe();
      if (this.isReferee || isAdmin) {
        this.subscription = this.chatService.chatChanges$.subscribe(async () => {
          const hallIds: number[] = this.hallService.isHallSet()
            ? [(await this.hallService.getCurrentHall()).id]
            : [1, 2];
          for (const hallId of hallIds) {
            const chats = await this.chatService.getChatsByHall(hallId);
            if (chats.some(chat =>
              chat.createdDate &&
              new Date(chat.createdDate).getTime() > this.lastChatCheckedDate.getTime())) {
              this.snackbarService.showMessage('Neue Nachricht', 60000);
            }
            this.lastChatCheckedDate = new Date(Date.now());
          }
        });
      }
    });
  }

  toggleNavbar() {
    this.navbarCollapsed = !this.navbarCollapsed;
    try {
      localStorage.setItem('navbarCollapsed', this.navbarCollapsed ? 'true' : 'false');
    } catch (e) {}
  }

  logout() {
    this.authService.logout();
  }

  login() {
    this.authService.login();
  }

  openChat() {
    this.router.navigateByUrl('referee/chats');
  }

  openGames() {
    this.router.navigateByUrl('referee/games');
  }

  protected readonly location = location;
  protected readonly document = document;

  private _roleService = inject(RoleService);
  isLoggedIn$   = this._roleService.isLoggedIn$;
  isReferee$    = this._roleService.isReferee$;
  isAdmin$      = this._roleService.isAdmin$;
  isShotMaster$ = this._roleService.isShotMaster$;

  notificationService = inject(NotificationService);
  unreadNotificationCount$ = this.notificationService.unreadNotificationCount$;
}

