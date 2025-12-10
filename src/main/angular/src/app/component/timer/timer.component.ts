import {Component, EventEmitter, Output} from '@angular/core';
import Tock from 'tocktimer';
@Component({
    selector: 'app-timer',
    templateUrl: './timer.component.html',
    styleUrls: ['./timer.component.css'],
    standalone: false
})
export class TimerComponent {
  @Output()
  startClicked = new EventEmitter<void>();
  timer = new Tock({
    callback: () => this.miliseconds = this.timer.lap()
  });
  miliseconds = 0;

  start() {
    this.timer.start()
    this.startClicked.emit(undefined)
  }

  stop() {
    this.timer.stop()
  }

  reset() {
    this.timer.reset()
    this.miliseconds = 0;
  }
}
