import {AfterViewChecked, Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material';
import {RefereeGameDialogComponent} from './referee-game-dialog/referee-game-dialog.component';
import {ActivatedRoute, Route, Router} from '@angular/router';
import {GameService} from '../../../../service/game.service';
import {Game} from '../../../../model/Game';
import {SnackbarService} from '../../../../service/snackbar.service';
import {RoleService} from '../../../../service/role.service';
import {RefereeGameConfirmDialogComponent} from './referee-game-confirm-dialog/referee-game-confirm-dialog.component';

@Component({
  selector: 'app-referee-game',
  templateUrl: './referee-game.component.html',
  styleUrls: ['./referee-game.component.scss']
})
export class RefereeGameComponent implements OnInit {
  game: Game;

  constructor(public dialog: MatDialog, route: ActivatedRoute, private gameService: GameService, private snackbarService: SnackbarService,
              private router: Router, private roleService: RoleService) {
    route.params.subscribe(x => this.gameService.getGameById(Number(x['id'])).then(y => {
      this.game = y;
      this.showPopup(y);
    }));
  }

  ngOnInit() {
  }

  addGoalHome() {
    this.game.goalsTeamHome = this.game.goalsTeamHome + 1;
  }

  minusGoalHome() {
    if (this.game.goalsTeamHome > 0) {
      this.game.goalsTeamHome = this.game.goalsTeamHome - 1;
    }
  }

  addGoalGuest() {
    this.game.goalsTeamGuest = this.game.goalsTeamGuest + 1;
  }

  minusGoalGuest() {
    if (this.game.goalsTeamGuest > 0) {
      this.game.goalsTeamGuest = this.game.goalsTeamGuest - 1;
    }
  }

  completeGame() {
    Promise.resolve().then(() => this.dialog.open(RefereeGameConfirmDialogComponent, {
      width: '250px',
      data: {text: 'Spiel abschliessen?', onConfirmation: () => this.completeGameFinally()}
    }));
  }

  completeGameFinally() {
    this.gameService.updateGame(this.game).then(x => this.snackbarService.dismiss());
    this.router.navigateByUrl(this.roleService.getDefaultRoute());
  }

  private showPopup(x: Game) {
    if (x.category.remark && x.category.remark !== '') {
      Promise.resolve().then(() => this.dialog.open(RefereeGameDialogComponent, {
        width: '250px',
        data: {text: x.category.remark}
      }));
    }
  }
}
