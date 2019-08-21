import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'tournament',
        loadChildren: () => import('./tournament/tournament.module').then(m => m.NightmanagerTournamentModule)
      },
      {
        path: 'hall',
        loadChildren: () => import('./hall/hall.module').then(m => m.NightmanagerHallModule)
      },
      {
        path: 'category',
        loadChildren: () => import('./category/category.module').then(m => m.NightmanagerCategoryModule)
      },
      {
        path: 'team',
        loadChildren: () => import('./team/team.module').then(m => m.NightmanagerTeamModule)
      },
      {
        path: 'game',
        loadChildren: () => import('./game/game.module').then(m => m.NightmanagerGameModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NightmanagerEntityModule {}
