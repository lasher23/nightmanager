import { browser, by, element } from 'protractor';

export class AppPage {
  navigateTo() {
    return browser.get('/display');
  }

  getParagraphText() {
    return element(by.css('app-display-home-component mat-tab-header div')).getText();
  }
}
