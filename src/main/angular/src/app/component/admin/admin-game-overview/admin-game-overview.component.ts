import {Component, OnInit} from '@angular/core';
import {GameService} from '../../../service/game.service';
import {Game} from '../../../model/Game';
import {HallService} from '../../../service/hall.service';
import {Hall} from '../../../model/Hall';
import {SnackbarService} from '../../../service/snackbar.service';
import {AdminConfirmDialogComponent} from '../admin-generation/admin-confirm-dialog/admin-confirm-dialog.component';
import {timeInterval} from 'rxjs/operators';
import {interval} from 'rxjs';
import {MatDialog} from '@angular/material/dialog';
import {GameChangeNotifierService} from "../../../service/game-change-notifier.service";
import {UntilDestroy, untilDestroyed} from "@ngneat/until-destroy";

export interface HallGamesAssignement {
  hall: Hall;
  games: Array<Game>;
}

@UntilDestroy()
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
    'notifyButton',
  ];
  widthPerHallTable = '100%';

  constructor(
    private gameService: GameService,
    private hallService: HallService,
    private snackbarService: SnackbarService,
    public dialog: MatDialog,
    private gameChangeNotifierService: GameChangeNotifierService,
  ) {
  }

  ngOnInit() {
    this.setup();
    this.gameChangeNotifierService.gameChanges$.pipe(untilDestroyed(this)).subscribe(() => this.setup())
  }

  setup() {
    this.gameService.getAllGames().then(games => this.games = games).then(() => this.initHalls());
  }

  private initHalls() {
    this.hallService.getAllHalls().then(halls => {
        halls.forEach(hall => {
            const gamesPerHall = this.games.filter(game => game.hall.id === hall.id);
            this.gamesHallAssingments = [...this.gamesHallAssingments.filter(x => x.hall.id != hall.id), {
              hall: hall,
              games: gamesPerHall
            }]
          }
        );
      }
    ).then(() => this.widthPerHallTable = (100 / this.gamesHallAssingments.length - 2) + '%');
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

  notifyGame(game: Game) {
    this.dialog.open(AdminConfirmDialogComponent).afterClosed().subscribe(result => {
      if (result) {
        this.gameService.notifyGame(game).then(() => {
          this.snackbarService.showMessage("Successfully notified");
        })
      }
    })
  }
}
