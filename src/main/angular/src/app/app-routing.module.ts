import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AuthGuard} from './auth/auth.guard';
import {OidcCallbackComponent} from './auth/oidc-callback.component';
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
    path: 'oidc/callback',
    component: OidcCallbackComponent,
  },
  {
    path: 'referee/teams',
    component: RefereeTeamsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'referee/teams/:id',
    component: RefereeTeamComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'referee/halls',
    component: RefereeHallsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'referee/games',
    component: RefereeGamesComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'referee/chats',
    component: RefereeChatComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'referee/games/:id',
    component: RefereeGameComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'referee',
    component: RefereeGamesComponent,
    canActivate: [AuthGuard]
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
    canActivate: [AuthGuard]
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
    canActivate: [AuthGuard]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {enableTracing: false})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
