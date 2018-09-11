import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

export interface DialogData {
  text: string;
  onConfirmation: Function;
}

@Component({
  selector: 'app-referee-game-confirm-dialog',
  templateUrl: './referee-game-confirm-dialog.component.html',
  styleUrls: ['./referee-game-confirm-dialog.component.scss']
})
export class RefereeGameConfirmDialogComponent implements OnInit {
  text: string;
  onConfirmation: Function;

  constructor(public dialogRef: MatDialogRef<RefereeGameConfirmDialogComponent>, @Inject(MAT_DIALOG_DATA) data: DialogData) {
    this.text = data.text;
    this.onConfirmation = data.onConfirmation;
  }

  ngOnInit() {
  }

  confirm() {
    this.dialogRef.close();
    this.onConfirmation.apply(null);
  }

  cancel() {
    this.dialogRef.close();
  }
}
