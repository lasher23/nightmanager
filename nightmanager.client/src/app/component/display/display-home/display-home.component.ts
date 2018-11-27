import {Component, ComponentFactoryResolver, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {CategoryService} from '../../../service/category.service';
import {Category} from '../../../model/Category';
import {GameService} from '../../../service/game.service';
import {assertNumber} from '@angular/core/src/render3/assert';
import {createAotUrlResolver} from '@angular/compiler';
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
  private id: number;
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
    this.gameService.getGames().then(games => this.games = games);
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
    const gamePromise = this.gameService.getAllGames(10, 20).then(x => {
      this.displayables.push(<Displayable>{
        type: DisplayType.GAMES,
        data: x
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
    this.setDisplayData().then(() => this.currentDisplayble = this.displayables[current]);
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
}
