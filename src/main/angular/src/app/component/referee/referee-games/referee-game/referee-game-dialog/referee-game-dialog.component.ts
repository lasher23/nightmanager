import {Component, Inject, OnInit} from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

export interface DialogData {
  text: string;
}

@Component({
  selector: 'app-referee-game-dialog',
  templateUrl: './referee-game-dialog.component.html',
  styleUrls: ['./referee-game-dialog.component.scss']
})
export class RefereeGameDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<RefereeGameDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: DialogData) {
  }

  ngOnInit() {
  }

  close() {
    this.dialogRef.close();
  }
}
