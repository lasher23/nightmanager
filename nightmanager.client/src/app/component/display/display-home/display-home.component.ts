import {Component, OnDestroy, OnInit} from '@angular/core';
import {CategoryService} from '../../../service/category.service';
import {Category, CategoryState} from '../../../model/Category';
import {GameService} from '../../../service/game.service';
import {Game} from '../../../model/Game';

export enum DisplayType {
  CATEGORY, GAMES
}

export interface Displayable {
  type: DisplayType;
  data: any;
}

@Component({
  selector: 'app-display-home-component',
  templateUrl: './display-home.component.html',
  styleUrls: ['./display-home.component.scss']
})
export class DisplayHomeComponent implements OnInit, OnDestroy {
  private id: any;
  private displayables: Array<Displayable> = [];
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

  constructor(private categoryService: CategoryService, private gameService: GameService) {
  }

  ngOnInit(): void {
    this.changeComponent();
    this.gameService.getAllGames().then(games => this.games = games);
    this.id = setInterval(() => this.changeComponent(), 10000);
  }


  private setDisplayData() {
    const categoryPromise = this.categoryService.getAll().then(x => this.displayables.push(...x.filter(y => !y.name.includes('DUMMY'))
      .sort((a: Category, b: Category) => {
        if (a.id < b.id) {
          return -1;
        } else if (a.id > b.id) {
          return 1;
        } else {
          return 0;
        }
      }).map(y => <Displayable>{type: DisplayType.CATEGORY, data: y})));
    const gamePromise = this.gameService.getAllGames().then(x => {
      this.displayables.push(<Displayable>{
        type: DisplayType.GAMES,
        data: this.gameService.getClosestGamesToNow(x, 6, 6)
      });
    });
    return Promise.all([
      gamePromise,
      categoryPromise]);
  }

  ngOnDestroy(): void {
    clearInterval(this.id);
  }

  private changeComponent() {
    let current = this.count % this.displayables.length;
    if (Number.isNaN(current)) {
      current = 0;
    }
    this.count++;
    this.setDisplayData().then(() => {
      let currentDisplayable = this.displayables[current];
      while (currentDisplayable.type === DisplayType.CATEGORY && currentDisplayable.data.state === CategoryState.DISABLED) {
        this.count++;
        current = this.count % this.displayables.length;
        currentDisplayable = this.displayables[current];
      }
      this.currentDisplayble = this.displayables[current];
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

  private sortGames(games: Array<Game>) {
    games.sort((game1, game2) => {
      const diff = new Date(game1.startDate).getTime() - new Date(game2.startDate).getTime();
      if (diff === 0) {
        return game1.hall.id - game2.hall.id;
      } else {
        return diff;
      }
    });
    return undefined;
  }
}
