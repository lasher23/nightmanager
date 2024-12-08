import {Component} from '@angular/core';
import Tock from 'tocktimer';
@Component({
  selector: 'app-timer',
  templateUrl: './timer.component.html',
  styleUrls: ['./timer.component.css']
})
export class TimerComponent {
  timer = new Tock({
    callback: () => this.miliseconds = this.timer.lap()
  });
  miliseconds = 0;

  start() {
    this.timer.start()
  }

  stop() {
    this.timer.stop()
  }

  reset() {
    this.timer.reset()
    this.miliseconds = 0;
  }
}
