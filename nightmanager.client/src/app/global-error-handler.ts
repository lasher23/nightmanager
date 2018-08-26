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
    if (error instanceof HttpErrorResponse) {
      const errorResponse = <Error>(<HttpErrorResponse>error).error;
      const responseText = <string>errorResponse.error;
      try {
        this.snackBarService.showMessage(responseText);
      } catch (ex) {
        console.log(ex);
      }
    } else {
      console.dir(error);
      this.snackBarService.showMessage('Unexpected Error, Please call your Support');
    }
  }
}
