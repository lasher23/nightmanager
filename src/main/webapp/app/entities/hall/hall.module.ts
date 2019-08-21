import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';

import { NightmanagerSharedModule } from 'app/shared';
import {
  HallComponent,
  HallDetailComponent,
  HallUpdateComponent,
  HallDeletePopupComponent,
  HallDeleteDialogComponent,
  hallRoute,
  hallPopupRoute
} from './';

const ENTITY_STATES = [...hallRoute, ...hallPopupRoute];

@NgModule({
  imports: [NightmanagerSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [HallComponent, HallDetailComponent, HallUpdateComponent, HallDeleteDialogComponent, HallDeletePopupComponent],
  entryComponents: [HallComponent, HallUpdateComponent, HallDeleteDialogComponent, HallDeletePopupComponent],
  providers: [{ provide: JhiLanguageService, useClass: JhiLanguageService }],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NightmanagerHallModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
