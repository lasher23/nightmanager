import {Component, OnInit} from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-admin-confirm-dialog',
  templateUrl: './admin-confirm-dialog.component.html',
  styleUrls: ['./admin-confirm-dialog.component.scss']
})
export class AdminConfirmDialogComponent implements OnInit {

  constructor(private matdialogRef: MatDialogRef<boolean>) {
  }

  ngOnInit() {
  }

  ok() {
    this.matdialogRef.close(true);
  }

  cancel() {
    this.matdialogRef.close(false);
  }

}
