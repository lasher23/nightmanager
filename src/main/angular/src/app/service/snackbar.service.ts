import {Injectable, Injector, NgZone} from '@angular/core';
import {MatSnackBar, MatSnackBarRef, SimpleSnackBar} from '@angular/material/snack-bar';

@Injectable()
export class SnackbarService {
  private openSnackbarRef: MatSnackBarRef<SimpleSnackBar>;

  constructor(private snackBar: MatSnackBar, private ngZone: NgZone) {
  }

  public showMessage(message: string, duration: number = 2000): void {
    this.ngZone.run(() => this.openSnackbarRef = this.snackBar.open(message, 'Dismiss', {
      duration: duration,
      verticalPosition: "top"
    }));
  }

  public dismiss(): void {
    if (this.openSnackbarRef) {
      this.openSnackbarRef.dismiss();
    }
  }
}
