import {Routes} from "@angular/router";

export const routes: Routes = [
  {
    path: "display",
    loadChildren: () => import("./pages/display/display.routes").then(d => d.routes)
  },
  {
    path: "shot-master",
    loadComponent: () => import("./pages/shot-master/shot-master.component").then(c => c.ShotMasterComponent),
  },
  {
    path: "notifications",
    loadComponent: () => import("./pages/notifications/notifications.component").then(c => c.NotificationsComponent)
  },
  {
    path: "public",
    loadChildren: () => import("./pages/public/public.routes").then(d => d.routes)
  },
  {
    path: "user-management",
    loadChildren: () => import("./pages/user-management/user-management.routes").then(r => r.routes)
  },
  {
    path: "tournaments",
    loadComponent: () => import("./pages/tournaments/tournaments.component").then(c => c.TournamentsComponent)
  },
  {
    path: "tournaments/registrations",
    loadComponent: () => import("./pages/tournaments/tournament-registrations.component").then(c => c.TournamentRegistrationsComponent)
  },
  {
    path: "tournaments/generate/:groupId",
    loadComponent: () => import("./pages/tournaments/tournament-generate.component").then(c => c.TournamentGenerateComponent)
  }
]
