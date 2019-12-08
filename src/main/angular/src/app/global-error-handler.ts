import {ErrorHandler, Injectable, Injector} from '@angular/core';
import {HttpErrorResponse} from '@angular/common/http';
import {MatSnackBar} from '@angular/material';
import {SnackbarService} from './service/snackbar.service';
import {Error} from '../app/model/Error';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {

  constructor(private snackBarService: SnackbarService) {
  }

  public handleError(error: any): void {
    try {
      const httpError = error;
      const errorResponse = <Error>httpError.rejection.error;
      const responseText = <string>errorResponse.error;
      this.snackBarService.showMessage(responseText);
    } catch (ex) {
      this.snackBarService.showMessage('Unerwarter Fehler');
    }
    console.error(error);
  }
}
