import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Hall } from '../../../model/Hall';
import { HallService } from '../../../service/hall.service';

@Component({
    selector: 'app-display-choose-hall',
    templateUrl: './display-choose-hall.component.html',
    styleUrls: ['./display-choose-hall.component.scss'],
    standalone: false
})
export class DisplayChooseHallComponent implements OnInit {
  halls: Array<Hall>;

  constructor(public dialogRef: MatDialogRef<any, Hall>, private hallService: HallService) { }

  ngOnInit(): void {
    this.hallService.getAllHalls()
      .then(halls => this.halls = halls);
  }

  onHallClick(hall: Hall) {
    this.dialogRef.close(hall);
  }
}
