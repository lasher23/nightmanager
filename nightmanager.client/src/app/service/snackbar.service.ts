import {Injectable, Injector, NgZone} from '@angular/core';
import {MatSnackBar} from '@angular/material';

@Injectable()
export class SnackbarService {

  constructor(private snackBar: MatSnackBar, private ngZone: NgZone) {
  }

  public showMessage(message: string, duration: number = 2000): void {
    if (this.snackBar === undefined) {
      return;
    }
    this.ngZone.run(() => this.snackBar.open(message, 'Dismiss', {duration: duration}));
  }
}
