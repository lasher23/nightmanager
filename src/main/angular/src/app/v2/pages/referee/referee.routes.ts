import {Routes} from "@angular/router";


export const routes: Routes = [
  {
    path: "games/:gameId",
    loadComponent: () => import("./referee-game-detail/referee-game-detail.component").then(c => c.RefereeGameDetailComponent)
  },
]
