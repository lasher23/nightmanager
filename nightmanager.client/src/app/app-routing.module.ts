import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AuthenticationGuard} from './authentication.guard';
import {LoginComponent} from './component/login/login.component';
import {HomeComponent} from './component/home/home.component';
import {RefereeTeamsComponent} from './component/referee/referee-teams/referee-teams.component';
import {RefereeTeamComponent} from './component/referee/referee-teams/referee-team/referee-team.component';
import {RefereeHallsComponent} from './component/referee/referee-halls/referee-halls.component';
import {RefereeGamesComponent} from './component/referee/referee-games/referee-games.component';
import {RefereeGameComponent} from './component/referee/referee-games/referee-game/referee-game.component';
import {DisplayHomeComponent} from './component/display/display-home-component/display-home.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'referee/teams',
    component: RefereeTeamsComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: 'referee/teams/:id',
    component: RefereeTeamComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: 'referee/halls',
    component: RefereeHallsComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: 'referee/games',
    component: RefereeGamesComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: 'referee/games/:id',
    component: RefereeGameComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: 'referee',
    component: RefereeGamesComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: 'display',
    component: DisplayHomeComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: '',
    component: HomeComponent,
    canActivate: [AuthenticationGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
