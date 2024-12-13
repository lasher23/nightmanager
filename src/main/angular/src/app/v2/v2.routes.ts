import {Routes} from "@angular/router";

export const routes: Routes = [
  {
    path: "display",
    loadChildren: () => import("./pages/display/display.routes").then(d => d.routes)
  },
  {
    path: "public",
    loadChildren: () => import("./pages/public/public.routes").then(d => d.routes)
  }
]
