import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHall } from 'app/shared/model/hall.model';

@Component({
  selector: 'jhi-hall-detail',
  templateUrl: './hall-detail.component.html'
})
export class HallDetailComponent implements OnInit {
  hall: IHall;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ hall }) => {
      this.hall = hall;
    });
  }

  previousState() {
    window.history.back();
  }
}
