import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {HallService} from '../../../service/hall.service';
import {GameService} from '../../../service/game.service';
import {Game} from '../../../model/Game';

@Component({
  selector: 'app-referee-games',
  templateUrl: './referee-games.component.html',
  styleUrls: ['./referee-games.component.scss']
})
export class RefereeGamesComponent implements OnInit {
  games: Array<Game>;
  displayedColumns = ['startDate', 'teamHome', 'teamGuest'];

  constructor(private router: Router, private hallService: HallService, private gameService: GameService) {
  }

  ngOnInit() {
    if (!this.hallService.isHallSet()) {
      this.router.navigateByUrl('referee/halls');
    } else {
      this.hallService.getCurrentHall().then(x => this.gameService.getAllGamesByHallAndNotCompleted(x).then(y => {
        this.games = y;
      }));
    }
  }

  onGameClick(game: Game) {
    this.router.navigateByUrl('referee/games/' + game.id);
  }
}
