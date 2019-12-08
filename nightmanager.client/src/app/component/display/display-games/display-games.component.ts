import {Component, Input, OnInit} from '@angular/core';
import {Game} from '../../../model/Game';
import {GameService} from '../../../service/game.service';
import {HallService} from '../../../service/hall.service';
import {Hall} from '../../../model/Hall';

export interface HallGamesAssignement {
  hall: Hall;
  games: Array<Game>;
}

@Component({
  selector: 'app-display-games',
  templateUrl: './display-games.component.html',
  styleUrls: ['./display-games.component.scss']
})
export class DisplayGamesComponent implements OnInit {
  _games: Array<Game>;
  gamesHallAssingments: Array<HallGamesAssignement> = [];
  displayedColumns: Array<String> = [
    'time',
    'category',
    'teamHome',
    'goalsTeamHome',
    'placeholder',
    'goalsTeamGuest',
    'teamGuest',
  ];
  widthPerHallTable = '100%';

  @Input()
  set games(games: Array<Game>) {
    this._games = games;
    this.initHalls();
  }

  constructor(private gameService: GameService, private hallService: HallService) {
  }

  ngOnInit() {
  }

  private initHalls() {
    this.gamesHallAssingments = [];
    this.hallService.getAllHalls().then(halls => halls.forEach(hall => {
      const gamesPerHall = this._games.filter(game => game.hall.id === hall.id);
      this.gamesHallAssingments.push({hall: hall, games: gamesPerHall});
    })).then(() => this.widthPerHallTable = (100 / this.gamesHallAssingments.length - 2) + '%');
  }
}
