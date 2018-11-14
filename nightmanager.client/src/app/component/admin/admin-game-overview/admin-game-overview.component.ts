import {Component, OnInit} from '@angular/core';
import {GameService} from '../../../service/game.service';
import {Game} from '../../../model/Game';
import {HallService} from '../../../service/hall.service';
import {Hall} from '../../../model/Hall';
import {SnackbarService} from '../../../service/snackbar.service';

export interface HallGamesAssignement {
  hall: Hall;
  games: Array<Game>;
}

@Component({
  selector: 'app-admin-game-overview',
  templateUrl: './admin-game-overview.component.html',
  styleUrls: ['./admin-game-overview.component.scss']
})
export class AdminGameOverviewComponent implements OnInit {
  games: Array<Game>;
  gamesHallAssingments: Array<HallGamesAssignement> = [];
  displayedColumns: Array<String> = [
    'time',
    'teamHome',
    'goalsTeamHome',
    'placeholder',
    'goalsTeamGuest',
    'teamGuest',
    'resetButton',
  ];
  widthPerHallTable = '100%';


  constructor(private gameService: GameService, private  hallService: HallService, private snackbarService: SnackbarService) {
  }


  ngOnInit() {
    this.gamesHallAssingments = [];
    this.gameService.getGames().then(games => this.games = games).then(() => this.initHalls());
  }

  private initHalls() {
    this.hallService.getAllHalls().then(halls => halls.forEach(hall => {
      const gamesPerHall = this.games.filter(game => game.hall.id === hall.id);
      this.gamesHallAssingments.push({hall: hall, games: gamesPerHall});
    })).then(() => this.widthPerHallTable = (100 / this.gamesHallAssingments.length - 2) + '%');
  }

  resetGame(game: Game) {
    this.gameService.resetGame(game).then(resettedGame => {
      this.ngOnInit();
      this.snackbarService.showMessage('Spiel Zurückgesetzt');
    });
  }
}
