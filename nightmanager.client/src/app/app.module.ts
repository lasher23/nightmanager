import {BrowserModule} from '@angular/platform-browser';
import {ErrorHandler, NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {AuthenticationService} from './service/authentication.service';
import {AuthenticationGuard} from './authentication.guard';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatDialogModule, MatIconModule, MatProgressSpinnerModule, MatSnackBarModule, MatTableModule} from '@angular/material';
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
import {RefereeGameDialogComponent} from './component/referee/referee-games/referee-game/referee-game-dialog/referee-game-dialog.component';
import {RefereeGameConfirmDialogComponent} from './component/referee/referee-games/referee-game/referee-game-confirm-dialog/referee-game-confirm-dialog.component';
import { DisplayHomeComponent } from './component/display/display-home-component/display-home.component';

@NgModule({
  entryComponents: [
    RefereeGameDialogComponent,
    RefereeGameConfirmDialogComponent,
  ],
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
    DisplayHomeComponent
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
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
