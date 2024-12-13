import {Routes} from "@angular/router";


export const routes: Routes = [
  {
    path: "",
    loadComponent: () => import("./public-categories/public-categories.component").then(c => c.PublicCategoriesComponent)
  },
  {
    path: "categories",
    loadComponent: () => import("./public-categories/public-categories.component").then(c => c.PublicCategoriesComponent)
  },
  {
    path: "catgories/:categoryId/teams",
    loadComponent: () => import("./public-category-teams/public-category-teams.component").then(c => c.PublicCategoryTeamsComponent)
  },
  {
    path: "games",
    loadComponent: () => import("./public-games/public-games.component").then(c => c.PublicGamesComponent)
  },
  {
    path: "rules",
    loadComponent: () => import("./public-rules/public-rules.component").then(c => c.PublicRulesComponent)
  },
  {
    path: "categories/:categoryId/ranking",
    loadComponent: () => import("./public-ranking/public-ranking.component").then(c => c.PublicRankingComponent)

  }
]
