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
  @Input() games: Array<Game>;
  gamesHallAssingments: Array<HallGamesAssignement> = [];
  displayedColumns: Array<String> = [
    'time',
    'teamHome',
    'goalsTeamHome',
    'placeholder',
    'goalsTeamGuest',
    'teamGuest',
  ];
  widthPerHallTable = '100%';

  constructor(private gameService: GameService, private hallService: HallService) {
  }

  ngOnInit() {
    if (!this.games) {
      this.gameService.getAllGames().then(x => this.games = x).then(() => this.initHalls());
    } else {
      this.initHalls();
    }
  }

  private initHalls() {
    this.hallService.getAllHalls().then(halls => halls.forEach(hall => {
      const gamesPerHall = this.games.filter(game => game.hall.id === hall.id);
      this.gamesHallAssingments.push({hall: hall, games: gamesPerHall});
    })).then(() => this.widthPerHallTable = (100 / this.gamesHallAssingments.length - 2) + '%');
  }
}
