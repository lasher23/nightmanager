import {Component, OnInit} from '@angular/core';
import {HallService} from '../../../service/hall.service';
import {Hall} from '../../../model/Hall';
import {Router} from '@angular/router';

@Component({
    selector: 'app-referee-halls',
    templateUrl: './referee-halls.component.html',
    styleUrls: ['./referee-halls.component.scss'],
    standalone: false
})
export class RefereeHallsComponent implements OnInit {
  halls: Array<Hall>;

  constructor(private hallService: HallService, private router: Router) {
  }

  ngOnInit() {
    this.hallService.getAllHalls().then(x => this.halls = x);
  }

  onHallChoose(hall: Hall) {
    this.hallService.setHall(hall);
    this.router.navigate(['referee/games']);
  }
}
