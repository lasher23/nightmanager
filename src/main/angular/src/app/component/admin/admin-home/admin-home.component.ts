import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.scss']
})
export class AdminHomeComponent implements OnInit {
  interval;
  miliseconds = 0;

  constructor() { }

  ngOnInit() {
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
