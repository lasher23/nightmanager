import {Component, Input, OnInit} from '@angular/core';
import {Category} from '../../../model/Category';
import {ActivatedRoute} from '@angular/router';
import {CategoryService} from '../../../service/category.service';
import {TeamService} from '../../../service/team.service';
import {Team} from '../../../model/Team';
import {GameService} from '../../../service/game.service';
import {Game} from '../../../model/Game';

@Component({
  selector: 'app-display-category',
  templateUrl: './display-category.component.html',
  styleUrls: ['./display-category.component.scss']
})
export class DisplayCategoryComponent implements OnInit {
  @Input() category: Category;
  teams: Array<Team> = [];
  displayedColumnsTeams = [
    'index',
    'name',
    'goals',
  ];
  games: Array<Game> = [];
  displayedColumnsGames: Array<String> = [
    'time',
    'teamHome',
    'goalsTeamHome',
    'placeholder',
    'goalsTeamGuest',
    'teamGuest',
    'hall',
  ];

  constructor(private route: ActivatedRoute,
              private categoryService: CategoryService,
              private teamService: TeamService,
              private gameService: GameService) {
  }

  ngOnInit() {
    if (!this.category) {
      this.route.params.subscribe(x => this.categoryService.getById(x['id'])
        .then(category => this.category = category)
        .then(() => this.initTeams()
          .then(() => this.initGames())));
    } else {
      this.initTeams();
    }
  }

  private initTeams(): Promise<any> {
    return this.teamService.getAllByCategory(this.category.id).then(teams => this.teams = this.sortTeams(teams));
  }

  private sortTeams(teams: Array<Team>): Array<Team> {
    return teams.sort((a, b) => {
      const aGoalDifference = a.goalsShot - a.goalsGotten;
      const bGoalDifference = b.goalsShot - b.goalsGotten;
      if (aGoalDifference > bGoalDifference) {
        return 1;
      } else if (aGoalDifference < bGoalDifference) {
        return -1;
      }
      return 0;
    });
  }

  private initGames() {
    this.gameService.getAllGamesByCategory(this.category.id, 5, 10).then(games => this.games = games);
    return undefined;
  }
}
