import {BrowserModule} from '@angular/platform-browser';
import {ErrorHandler, NgModule, isDevMode} from '@angular/core';
import {A11yModule} from '@angular/cdk/a11y';
import {BidiModule} from '@angular/cdk/bidi';
import {ObserversModule} from '@angular/cdk/observers';
import {OverlayModule} from '@angular/cdk/overlay';
import {PlatformModule} from '@angular/cdk/platform';
import {PortalModule} from '@angular/cdk/portal';
import {CdkStepperModule} from '@angular/cdk/stepper';
import {CdkTableModule} from '@angular/cdk/table';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {AuthenticationService} from './service/authentication.service';
import {AuthenticationGuard} from './authentication.guard';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpProxyService} from './service/http-proxy.service';
import {GlobalErrorHandler} from './global-error-handler';
import {SnackbarService} from './service/snackbar.service';
import {LoginComponent} from './component/login/login.component';
import {HomeComponent} from './component/home/home.component';
import {LoginService} from './service/login.service';
import {TeamService} from './service/team.service';
import {RoleService} from './service/role.service';
import {CommonModule} from '@angular/common';
import {RefereeGamesComponent} from './component/referee/referee-games/referee-games.component';
import {RefereeTeamsComponent} from './component/referee/referee-teams/referee-teams.component';
import {RefereeTeamComponent} from './component/referee/referee-teams/referee-team/referee-team.component';
import {RefereeHallsComponent} from './component/referee/referee-halls/referee-halls.component';
import {GameService} from './service/game.service';
import {RefereeGameComponent} from './component/referee/referee-games/referee-game/referee-game.component';
import {
  RefereeGameDialogComponent
} from './component/referee/referee-games/referee-game/referee-game-dialog/referee-game-dialog.component';
import {
  RefereeGameConfirmDialogComponent
} from './component/referee/referee-games/referee-game/referee-game-confirm-dialog/referee-game-confirm-dialog.component';
import {DisplayHomeComponent} from './component/display/display-home/display-home.component';
import {DisplayCategoryComponent} from './component/display/display-category/display-category.component';
import {DisplayGamesComponent} from './component/display/display-games/display-games.component';
import {AdminGameOverviewComponent} from './component/admin/admin-game-overview/admin-game-overview.component';
import {AdminHomeComponent} from './component/admin/admin-home/admin-home.component';
import {AdminGenerationComponent} from './component/admin/admin-generation/admin-generation.component';
import {
  DisplayCategoryOverviewComponent
} from './component/display/display-category-overview/display-category-overview.component';
import {
  DisplayCategoryDetailsComponent
} from './component/display/display-category-details/display-category-details.component';
import {
  AdminConfirmDialogComponent
} from './component/admin/admin-generation/admin-confirm-dialog/admin-confirm-dialog.component';
import {AdminTeamsComponent} from './component/admin/admin-teams/admin-teams.component';
import {SidenavComponent} from './component/sidenav/sidenav.component';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatTableModule} from '@angular/material/table';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatDialogModule} from '@angular/material/dialog';
import {MatIconModule} from '@angular/material/icon';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatTabsModule} from '@angular/material/tabs';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatCardModule} from '@angular/material/card';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatChipsModule} from '@angular/material/chips';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatListModule} from '@angular/material/list';
import {MatMenuModule} from '@angular/material/menu';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatRadioModule} from '@angular/material/radio';
import {MatNativeDateModule, MatRippleModule} from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatSliderModule} from '@angular/material/slider';
import {MatStepperModule} from '@angular/material/stepper';
import {MatTooltipModule} from '@angular/material/tooltip';
import {DisplayLiveGameComponent} from './component/display/display-live-game/display-live-game.component';
import {DisplayChooseHallComponent} from './component/display/display-choose-hall/display-choose-hall.component';
import {RefereeChatComponent} from './component/referee/referee-chat/referee-chat.component';
import {AdminChatComponent} from './component/admin/admin-chat/admin-chat.component';
import {TimerComponent} from './component/timer/timer.component';
import {AddComponent} from './component/display/add/add.component';
import { AdminNotificationsOverviewComponent } from './component/admin/admin-notifications-overview/admin-notifications-overview.component';
import { AdminSendNotificationsComponent } from './component/admin/admin-send-notifications/admin-send-notifications.component';
import { RefereeGameDetailComponent } from './v2/pages/referee/referee-game-detail/referee-game-detail.component';
import { ServiceWorkerModule } from '@angular/service-worker';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RefereeTeamsComponent,
    HomeComponent,
    RefereeTeamComponent,
    RefereeGamesComponent,
    RefereeHallsComponent,
    RefereeGameComponent,
    RefereeGameDialogComponent,
    RefereeGameConfirmDialogComponent,
    DisplayHomeComponent,
    DisplayCategoryComponent,
    DisplayGamesComponent,
    AdminGameOverviewComponent,
    AdminHomeComponent,
    AdminGenerationComponent,
    DisplayCategoryOverviewComponent,
    DisplayCategoryDetailsComponent,
    AdminConfirmDialogComponent,
    AdminTeamsComponent,
    SidenavComponent,
    DisplayLiveGameComponent,
    DisplayChooseHallComponent,
    RefereeChatComponent,
    AdminChatComponent,
    TimerComponent,
    AddComponent,
    AdminNotificationsOverviewComponent,
    AdminSendNotificationsComponent,
    RefereeGameDetailComponent,
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    FormsModule,
    MatButtonModule,
    MatInputModule,
    MatTableModule,
    MatSnackBarModule,
    MatDialogModule,
    MatIconModule,
    MatProgressSpinnerModule,
    MatTabsModule,
    CdkTableModule,
    MatSlideToggleModule,
    MatToolbarModule,
    MatAutocompleteModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatDatepickerModule,
    MatDialogModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatListModule,
    MatMenuModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSlideToggleModule,
    MatSliderModule,
    MatSnackBarModule,
    MatStepperModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    MatNativeDateModule,
    A11yModule,
    BidiModule,
    ObserversModule,
    OverlayModule,
    PlatformModule,
    PortalModule,
    CdkStepperModule,
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: !isDevMode(),
      // Register the ServiceWorker as soon as the application is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    }),
  ],
  providers: [
    LoginService,
    AuthenticationService,
    AuthenticationGuard,
    {
      provide: ErrorHandler,
      useClass: GlobalErrorHandler
    },
    TeamService,
    HttpProxyService,
    SnackbarService,
    RoleService,
    GameService,
    // {
    //   provide: StompService, useFactory: () => {
    //     const stompService = new StompService();
    //     stompService.configure(stompConf)
    //     stompService.activate()
    //     return stompService
    //   }
    // }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
