import { ErrorHandler, Injectable } from '@angular/core';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {

  constructor() {
  }

  public handleError(error: any): void {
    console.error(error);
  }
}
