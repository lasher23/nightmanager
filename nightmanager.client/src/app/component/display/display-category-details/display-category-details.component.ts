import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {TeamService} from '../../../service/team.service';
import {Team} from '../../../model/Team';
import {GameService} from '../../../service/game.service';
import {Game} from '../../../model/Game';
import {CategoryState} from '../../../model/Category';

@Component({
  selector: 'app-display-category-details',
  templateUrl: './display-category-details.component.html',
  styleUrls: ['./display-category-details.component.scss']
})
export class DisplayCategoryDetailsComponent implements OnInit {
  teams: Array<Team>;
  games: Array<Game>;
  displayedColumnsTeams = [
    'index',
    'name',
    'points',
    'goals'
  ];
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

  constructor(route: ActivatedRoute, private teamService: TeamService, private gameService: GameService) {
    route.params.subscribe(x => this.initData(x['id']));
  }

  ngOnInit() {
  }

  private initData(categoryId: number) {
    this.gameService.getAllGames().then(games => this.gamesForSorting = games)
      .then(() => this.teamService.getAllByCategory(categoryId)
        .then(teams => this.teams = this.sortTeams(teams.filter(x => !x.placeholder))));
    this.gameService.getAllGamesByCategory(categoryId).then(games => this.games = games);
  }

  private sortTeams(teams: Array<Team>): Array<Team> {
    if (teams[0].category.state === CategoryState.FINISHED) {
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
    }
    return this.compareDirectOpponent(a, b);
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
