import {Component, NgZone, OnDestroy, OnInit} from '@angular/core';
import {CategoryService} from '../../../service/category.service';
import {Category, CategoryState} from '../../../model/Category';
import {GameService} from '../../../service/game.service';
import {Game} from '../../../model/Game';
import * as _ from 'lodash';
import {MatDialog} from '@angular/material/dialog';
import {DisplayChooseHallComponent} from '../display-choose-hall/display-choose-hall.component';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import {Hall} from '../../../model/Hall';
import {ActivatedRoute, Router} from '@angular/router';
import {filter} from 'rxjs/operators';
import {GameChangeNotifierService} from '../../../service/game-change-notifier.service';
import {DisplayConfig, DisplayConfigService} from "../../../service/display-config.service";

export enum DisplayType {
  CATEGORY, GAMES,
  ADD,
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
  addCount = 0;
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
  displayConfig: DisplayConfig;
  appTimerActivated = false;


  constructor(
    private categoryService: CategoryService,
    private gameService: GameService,
    private matDialog: MatDialog,
    private router: Router,
    private gameChangeNotifierService: GameChangeNotifierService,
    private ngZone: NgZone,
    private activatedRoute: ActivatedRoute,
    private displayConfigService: DisplayConfigService,
  ) {
  }


  ngOnInit(): void {
    this.changeComponent();
    this.updateGames()
    this.id = setInterval(() => this.changeComponent(), 10000);
    this.gameChangeNotifierService.gameChanges$.pipe(untilDestroyed(this)).subscribe(() => this.updateGames())
    this.activatedRoute.queryParams.pipe(untilDestroyed(this)).subscribe(params => {
      if (params.displayMode) {
        this.displayMode = true
      }
      this.appTimerActivated = params.timer
    })
    this.displayConfig = this.displayConfigService.getConfig()
  }

  updateGames() {
    this.gameService.getAllGames().then(games => this.games = games);
  }

  ngOnDestroy(): void {
    clearInterval(this.id);
  }

  private async getDisplayables(): Promise<Array<Displayable> | never> {
    const categoryDisplayables = (await this.categoryService.getAll())
      .filter(category => category.state != CategoryState.DISABLED && !!category.showOnDisplay)
      .sort(this.sortCategory)
      .map(category => <Displayable>{
        type: DisplayType.CATEGORY,
        data: category
      });
    const gamesDisplayable = [<Displayable>{
      type: DisplayType.GAMES,
      data: this.gameService.getClosestGamesToNow(this.games, this.displayConfig.totalGamesView.before, this.displayConfig.totalGamesView.after)
        .map(game => ({...game, category: {...game.category, name: game.category.name.replace("Schüler Kategorie", "Kat.").replace("Night ", "").replace("Guuggenmusigen und Firmen", "Guggen + Firmen")}}))
    }];
    const addDisplayable = <Displayable>{
      type: DisplayType.ADD,
    }
    const displayables = [gamesDisplayable, categoryDisplayables, addDisplayable];
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
    this.router.navigate(["/v2/display"])
  }

  displayLiveGame() {
    let matDialogRef = this.matDialog.open(DisplayChooseHallComponent);
    matDialogRef.afterClosed().pipe(untilDestroyed(this), filter(hall => hall))
      .subscribe(hall => this.openDisplayLiveGame(hall));
  }

  private openDisplayLiveGame(hall: Hall) {
    this.router.navigate(['display/livegame'], {queryParams: {hallId: hall.id}});
  }

  isAdd() {
    return this.currentDisplayble && this.currentDisplayble.type === DisplayType.ADD;
  }

  incrementCount() {
    setTimeout(() => this.addCount++, 11000)
  }
}
