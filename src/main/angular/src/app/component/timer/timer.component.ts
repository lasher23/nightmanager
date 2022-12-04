import {Component} from '@angular/core';

@Component({
  selector: 'app-timer',
  templateUrl: './timer.component.html',
  styleUrls: ['./timer.component.css']
})
export class TimerComponent {
  interval;
  miliseconds = 0;

  start() {
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
    this.stop()
    this.miliseconds = 0;
  }
}
