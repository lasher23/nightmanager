import {Component, OnInit} from '@angular/core';
import {untilDestroyed} from "@ngneat/until-destroy";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss']
})
export class AdminHomeComponent implements OnInit {
  interval;
  miliseconds = 0;

  fullMode = false;

  constructor(private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.activatedRoute.queryParams.pipe(untilDestroyed(this)).subscribe(params => {
      if (params.fullMode) {
        this.fullMode = true
      }
    })
  }


  start() {
    console.log(this.interval);
    if (!this.interval) {
      this.interval = setInterval(() => this.miliseconds += 1000, 1000);
    }
  }

  stop() {
    if (this.interval) {
      clearInterval(this.interval);
      this.interval = null;
    }
  }

  reset() {
    if (this.interval) {
      clearInterval(this.interval);
      this.interval = null;
    }
    this.miliseconds = 0;
  }

}
