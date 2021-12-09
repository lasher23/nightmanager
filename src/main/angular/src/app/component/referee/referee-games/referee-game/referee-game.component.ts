import { AfterViewChecked, Component, OnInit } from '@angular/core';
import { RefereeGameDialogComponent } from './referee-game-dialog/referee-game-dialog.component';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { GameService } from '../../../../service/game.service';
import { Game } from '../../../../model/Game';
import { SnackbarService } from '../../../../service/snackbar.service';
import { RoleService } from '../../../../service/role.service';
import { RefereeGameConfirmDialogComponent } from './referee-game-confirm-dialog/referee-game-confirm-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-referee-game',
  templateUrl: './referee-game.component.html',
  styleUrls: ['./referee-game.component.scss']
})
export class RefereeGameComponent implements OnInit {
  game: Game;
  swapped = false;

  constructor(public dialog: MatDialog, route: ActivatedRoute, private gameService: GameService,
              private snackbarService: SnackbarService,
              private router: Router, private roleService: RoleService) {
    route.params.subscribe(x => this.gameService.getGameById(Number(x['id'])).then(y => {
      this.game = y;
      this.showPopup(y);
      this.setGameAsLiveGame(y);
    }));
  }

  ngOnInit() {
  }

  addGoalHome() {
    this.game.goalsTeamHome = this.game.goalsTeamHome + 1;
    this.updateGame();
  }

  minusGoalHome() {
    if (this.game.goalsTeamHome > 0) {
      this.game.goalsTeamHome = this.game.goalsTeamHome - 1;
      this.updateGame();
    }
  }

  addGoalGuest() {
    this.game.goalsTeamGuest = this.game.goalsTeamGuest + 1;
    this.updateGame();
  }

  minusGoalGuest() {
    if (this.game.goalsTeamGuest > 0) {
      this.game.goalsTeamGuest = this.game.goalsTeamGuest - 1;
      this.updateGame();
    }
  }

  updateGame() {
    this.gameService.updateGame(this.game)
      .then(game => this.game = game);
  }

  completeGame() {
    Promise.resolve().then(() => this.dialog.open(RefereeGameConfirmDialogComponent, {
      width: '250px',
      data: { text: 'Spiel abschliessen?', game: this.game, onConfirmation: () => this.completeGameFinally() }
    }));
  }

  completeGameFinally() {
    this.gameService.completeGame(this.game).then(x => this.snackbarService.dismiss());
    this.router.navigateByUrl(this.roleService.getDefaultRoute());
  }

  private showPopup(x: Game) {
    if (x.category.remark && x.category.remark !== '') {
      Promise.resolve().then(() => this.dialog.open(RefereeGameDialogComponent, {
        width: '250px',
        data: { text: x.category.remark }
      }));
    }
  }

  swap() {
    this.swapped = !this.swapped;
  }

  private setGameAsLiveGame(game: Game) {
    game.live = true;
    this.gameService.updateGameAsLive(game);
  }
}
