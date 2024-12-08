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
import {DisplayHomeComponent} from './component/display/display-home/display-home.component';
import {DisplayGamesComponent} from './component/display/display-games/display-games.component';
import {DisplayCategoryComponent} from './component/display/display-category/display-category.component';
import {AdminHomeComponent} from './component/admin/admin-home/admin-home.component';
import {
  DisplayCategoryDetailsComponent
} from './component/display/display-category-details/display-category-details.component';
import {DisplayLiveGameComponent} from './component/display/display-live-game/display-live-game.component';
import {RefereeChatComponent} from './component/referee/referee-chat/referee-chat.component';

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
    path: 'referee/chats',
    component: RefereeChatComponent,
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
  },
  {
    path: 'display/games',
    component: DisplayGamesComponent,
  },
  {
    path: 'display/categories/:id',
    component: DisplayCategoryComponent,
  },
  {
    path: 'display/details/categories/:id',
    component: DisplayCategoryDetailsComponent,
  },
  {
    path: 'display/livegame',
    component: DisplayLiveGameComponent,
  },
  {
    path: 'admin',
    component: AdminHomeComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: 'v2',
    loadChildren: () => import("./v2/v2.routes").then(r => r.routes)
  },
  {
    path: '',
    redirectTo: "v2/public",
    pathMatch: "full"
  },
  {
    path: '**',
    component: HomeComponent,
    canActivate: [AuthenticationGuard]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
