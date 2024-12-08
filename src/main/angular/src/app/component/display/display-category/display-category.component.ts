import {Component, Input, OnInit} from '@angular/core';
import {Category, CategoryState} from '../../../model/Category';
import {ActivatedRoute} from '@angular/router';
import {CategoryService} from '../../../service/category.service';
import {TeamService} from '../../../service/team.service';
import {Team} from '../../../model/Team';
import {GameService} from '../../../service/game.service';
import {Game} from '../../../model/Game';
import {DisplayConfig, DisplayConfigService} from "../../../service/display-config.service";

@Component({
  selector: 'app-display-category',
  templateUrl: './display-category.component.html',
  styleUrls: ['./display-category.component.scss']
})
export class DisplayCategoryComponent implements OnInit {
  _category: Category;
  categoryName = '';
  teams: Array<Team> = [];
  displayedColumnsTeams = [
    'index',
    'name',
    'points',
    'goals'
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
  private gamesForSorting: Array<Game> = [];

  constructor(private route: ActivatedRoute,
              private categoryService: CategoryService,
              private teamService: TeamService,
              private gameService: GameService,
              private displayConfigService: DisplayConfigService,
              ) {
  }

  @Input() set category(category: Category) {
    this._category = category;
    this.categoryName = category.name
      .replace("Kat. A", "Plausch")
      .replace("Kat. A Winner", "Plausch Winner")
      .replace("Kat. A Loser", "Plausch Loser")
      .replace("Kat. B", "Guggen und Firmen")
      .replace("Kat. B 1", "Guggen und Firmen 1")
      .replace("Kat. B 2", "Guggen und Firmen 2")
      .replace("Kat. C", "Mixed")
      .replace("Kat. C 1", "Mixed 1")
      .replace("Kat. C 2", "Mixed 2")
      .replace("Kat. 1", "Jungs und Mädels 1. - 3. Kl.")
      .replace("Kat. 2", "Jungs und Mädels 4. - 6. Kl.")
      .replace("Kat. 3", "Jungs und Mädels 7. Kl. - 16 J.")
    ;
    this.initTeams();
    this.initGames();
  }

  ngOnInit() {
    if (!this._category) {
      this.route.params.subscribe(x => this.categoryService.getById(x['id'])
        .then(category => this._category = category)
        .then(() => {
          this.initTeams();
          this.initGames();
        }));
    } else {
      this.initTeams();
      this.initGames();
    }
  }

  private initTeams(): Promise<any> {
    return this.teamService.getAllByCategory(this._category.id)
      .then(teams => this.teams = this.sortTeams(teams.filter(x => !x.placeholder))).then(() => {
        return this.gameService.getAllGames()
          .then(games => this.gamesForSorting = games)
          .then(() => this.teams = this.sortTeams(this.teams.filter(x => !x.placeholder)));
      });
  }

  private sortTeams(teams: Array<Team>): Array<Team> {
    if (this._category.state === CategoryState.FINISHED) {
      return teams.sort((team1, team2) => {
        if (team1.rank < team2.rank) {
          return -1;
        } else {
          return 1;
        }
      });
    } else {
      return teams.sort((a, b) => {
        if (a.points > b.points) {
          return -1;
        } else if (a.points < b.points) {
          return 1;
        } else {
          return this.compareEqualPointedTeams(a, b);
        }
      });
    }
  }

  compareEqualPointedTeams(a: Team, b: Team): number {
    const aGoalDifference = a.goalsShot - a.goalsGotten;
    const bGoalDifference = b.goalsShot - b.goalsGotten;
    if (aGoalDifference > bGoalDifference) {
      return -1;
    } else if (aGoalDifference < bGoalDifference) {
      return 1;
    } else {
      return this.compareEqualTeamsWithEqualGoalDifferenceAndPoints(a, b);
    }
  }

  compareEqualTeamsWithEqualGoalDifferenceAndPoints(a: Team, b: Team): number {
    if (a.goalsShot > b.goalsShot) {
      return -1;
    } else if (a.goalsShot < b.goalsShot) {
      return 1;
    } else {
      return this.compareDirectOpponent(a, b);
    }
  }

  private initGames() {
    return this.gameService.getAllGamesByCategory(this._category.id).then(games => {
      this.games = this.gameService.getClosestGamesToNow(games, this.displayConfigService.getConfig().categoryGamesView.before, this.displayConfigService.getConfig().categoryGamesView.after);
    });
  }

  private compareDirectOpponent(a: Team, b: Team) {
    const games = this.gamesForSorting.filter(game =>
      (game.teamHome.id === a.id || game.teamGuest.id === a.id || game.teamHome.id === b.id || game.teamGuest.id === b.id)
      && game.type === 'GROUP_STAGE');
    if (games.length > 0) {
      const game1 = games[0];
      if (game1.teamHome.id === a.id) {
        return game1.goalsTeamHome - game1.goalsTeamGuest;
      } else {
        return game1.goalsTeamGuest - game1.goalsTeamHome;
      }
    }
    return 0;
  }
}
