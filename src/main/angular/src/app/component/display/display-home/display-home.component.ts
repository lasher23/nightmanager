import {Component, OnDestroy, OnInit} from '@angular/core';
import {CategoryService} from '../../../service/category.service';
import {Category, CategoryState} from '../../../model/Category';
import {GameService} from '../../../service/game.service';
import {Game} from '../../../model/Game';
import * as _ from 'lodash';
import {interval, takeUntil} from 'rxjs';
import {MatDialog} from '@angular/material/dialog';
import {DisplayChooseHallComponent} from '../display-choose-hall/display-choose-hall.component';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import {Hall} from '../../../model/Hall';
import {Router} from '@angular/router';
import {filter} from 'rxjs/operators';
import {GameChangeNotifierService} from '../../../service/game-change-notifier.service';

export enum DisplayType {
  CATEGORY, GAMES
}

export interface Displayable {
  type: DisplayType;
  data: any;
}

@UntilDestroy()
@Component({
  selector: 'app-display-home-component',
  templateUrl: './display-home.component.html',
  styleUrls: ['./display-home.component.scss']
})
export class DisplayHomeComponent implements OnInit, OnDestroy {
  private id: any;
  private count = 0;
  currentDisplayble: Displayable;
  displayMode = false;
  displayedColumns: Array<String> = [
    'time',
    'teamHome',
    'goalsTeamHome',
    'placeholder',
    'goalsTeamGuest',
    'teamGuest',
    'hall',
  ];

  games: Array<Game>;


  constructor(private categoryService: CategoryService, private gameService: GameService, private matDialog: MatDialog,
              private router: Router, private gameChangeNotifierService: GameChangeNotifierService) {
  }

  ngOnInit(): void {
    this.changeComponent();
    this.updateGames()
    this.id = setInterval(() => this.changeComponent(), 10000);
    this.gameChangeNotifierService.gameChanges$.pipe(untilDestroyed(this)).subscribe(() => this.updateGames())
  }

  updateGames() {
    this.gameService.getAllGames().then(games => this.games = games);
  }

  ngOnDestroy(): void {
    clearInterval(this.id);
  }

  private async getDisplayables(): Promise<Array<Displayable> | never> {
    const categoryDisplayables = (await this.categoryService.getAll())
      .filter(category => category.state != CategoryState.DISABLED)
      .sort(this.sortCategory)
      .map(category => <Displayable>{
        type: DisplayType.CATEGORY,
        data: category
      });
    const gamesDisplayable = [<Displayable>{
      type: DisplayType.GAMES,
      data: this.gameService.getClosestGamesToNow(this.games, 20, 20)
    }];
    const displayables = [gamesDisplayable, categoryDisplayables];
    return Promise.all(displayables).then(x => _.flatten(x));
  }

  private sortCategory(a: Category, b: Category) {
    if (a.id < b.id) {
      return -1;
    } else if (a.id > b.id) {
      return 1;
    } else {
      return 0;
    }
  }

  private changeComponent() {
    this.getDisplayables().then(displayables => {
      console.warn(displayables);
      console.log(displayables.length);
      let current = this.count % displayables.length;
      if (Number.isNaN(current)) {
        current = 0;
      }
      this.count++;
      this.currentDisplayble = displayables[current];
    });
  }

  isCategory(): boolean {
    return this.currentDisplayble && this.currentDisplayble.type === DisplayType.CATEGORY;
  }

  isGames(): boolean {
    return this.currentDisplayble && this.currentDisplayble.type === DisplayType.GAMES;
  }

  enterDisplayMode() {
    this.displayMode = true;
  }

  displayLiveGame() {
    let matDialogRef = this.matDialog.open(DisplayChooseHallComponent);
    matDialogRef.afterClosed().pipe(untilDestroyed(this), filter(hall => hall))
      .subscribe(hall => this.openDisplayLiveGame(hall));
  }

  private openDisplayLiveGame(hall: Hall) {
    this.router.navigate(['display/livegame'], {queryParams: {hallId: hall.id}});
  }
}
