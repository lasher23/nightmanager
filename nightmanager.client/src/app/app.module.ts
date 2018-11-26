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
import {
  MatDialogModule,
  MatIconModule,
  MatProgressSpinnerModule,
  MatSnackBarModule,
  MatTableModule,
  MatTabsModule
} from '@angular/material';
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
import {RefereeGameConfirmDialogComponent}
  from './component/referee/referee-games/referee-game/referee-game-confirm-dialog/referee-game-confirm-dialog.component';
import {DisplayHomeComponent} from './component/display/display-home/display-home.component';
import {DisplayCategoryComponent} from './component/display/display-category/display-category.component';
import {DisplayGamesComponent} from './component/display/display-games/display-games.component';
import {AdminGameOverviewComponent} from './component/admin/admin-game-overview/admin-game-overview.component';
import {AdminHomeComponent} from './component/admin/admin-home/admin-home.component';
import {AdminGenerationComponent} from './component/admin/admin-generation/admin-generation.component';
import {CdkTableModule} from '@angular/cdk/table';
import { DisplayCategoryOverviewComponent } from './component/display/display-category-overview/display-category-overview.component';
import { DisplayCategoryDetailsComponent } from './component/display/display-category-details/display-category-details.component';
import { AdminConfirmDialogComponent } from './component/admin/admin-generation/admin-confirm-dialog/admin-confirm-dialog.component';

@NgModule({
  entryComponents: [
    RefereeGameDialogComponent,
    RefereeGameConfirmDialogComponent,
    AdminConfirmDialogComponent
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
    DisplayHomeComponent,
    DisplayCategoryComponent,
    DisplayGamesComponent,
    AdminGameOverviewComponent,
    AdminHomeComponent,
    AdminGenerationComponent,
    DisplayCategoryOverviewComponent,
    DisplayCategoryDetailsComponent,
    AdminConfirmDialogComponent
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
