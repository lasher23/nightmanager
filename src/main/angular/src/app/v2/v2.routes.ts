import {Routes} from "@angular/router";
import { RoleGuard } from '../service/role.guard';

export const routes: Routes = [
  {
    path: "display",
    loadChildren: () => import("./pages/display/display.routes").then(d => d.routes)
  },
  {
    path: "shot-master",
    loadComponent: () => import("./pages/shot-master/shot-master.component").then(c => c.ShotMasterComponent),
    canActivate: [RoleGuard],
    data: { role: 'SHOT_MASTER' }
  },
  {
    path: "public",
    loadChildren: () => import("./pages/public/public.routes").then(d => d.routes)
  }
]
