import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NightmanagerSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [NightmanagerSharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [NightmanagerSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NightmanagerSharedModule {
  static forRoot() {
    return {
      ngModule: NightmanagerSharedModule
    };
  }
}
