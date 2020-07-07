import {Component, Inject, OnInit} from '@angular/core';
import {Game} from '../../../../../model/Game';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

export interface DialogData {
  text: string;
  game: Game;
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
  team1Confirms = false;
  team2Confirms = false;

  game: Game;

  constructor(public dialogRef: MatDialogRef<RefereeGameConfirmDialogComponent>, @Inject(MAT_DIALOG_DATA) data: DialogData) {
    this.text = data.text;
    this.game = data.game;
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
