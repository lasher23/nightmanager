import {Component, OnInit} from '@angular/core';
import {GameService} from '../../../service/game.service';
import {Game} from '../../../model/Game';
import {HallService} from '../../../service/hall.service';
import {Hall} from '../../../model/Hall';
import {SnackbarService} from '../../../service/snackbar.service';
import {AdminConfirmDialogComponent} from '../admin-generation/admin-confirm-dialog/admin-confirm-dialog.component';
import {MatDialog} from '@angular/material';
import {timeInterval} from 'rxjs/operators';
import {interval} from 'rxjs';

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
  interval;
  miliseconds = 0;


  constructor(private gameService: GameService, private  hallService: HallService, private snackbarService: SnackbarService, public dialog: MatDialog) {
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

  ngOnInit() {
    this.setup();
    interval(10000).subscribe(() => this.setup());
  }

  setup() {
    this.gamesHallAssingments = [];
    this.gameService.getAllGames().then(games => this.games = games).then(() => this.initHalls());
  }

  private initHalls() {
    this.hallService.getAllHalls().then(halls => halls.forEach(hall => {
      const gamesPerHall = this.games.filter(game => game.hall.id === hall.id);
      this.gamesHallAssingments.push({hall: hall, games: gamesPerHall});
    })).then(() => this.widthPerHallTable = (100 / this.gamesHallAssingments.length - 2) + '%');
  }

  resetGame(game: Game) {
    this.dialog.open(AdminConfirmDialogComponent).afterClosed().subscribe(result => {
      if (result) {
        this.gameService.resetGame(game).then(resettedGame => {
          this.setup();
          this.snackbarService.showMessage('Spiel Zurückgesetzt');
        });
      }
    });
  }
}
