import {Routes} from "@angular/router";


export const routes: Routes = [
  {
    path: "",
    loadComponent: () => import("./home/display-home-v2.component").then(c => c.DisplayHomeV2Component)
  },
]
