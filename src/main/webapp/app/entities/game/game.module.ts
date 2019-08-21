import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { NightmanagerSharedModule } from 'app/shared';
import {
  GameComponent,
  GameDetailComponent,
  GameUpdateComponent,
  GameDeletePopupComponent,
  GameDeleteDialogComponent,
  gameRoute,
  gamePopupRoute
} from './';

const ENTITY_STATES = [...gameRoute, ...gamePopupRoute];

@NgModule({
  imports: [NightmanagerSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [GameComponent, GameDetailComponent, GameUpdateComponent, GameDeleteDialogComponent, GameDeletePopupComponent],
  entryComponents: [GameComponent, GameUpdateComponent, GameDeleteDialogComponent, GameDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NightmanagerGameModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
