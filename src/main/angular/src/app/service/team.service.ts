import {Injectable} from '@angular/core';
import {Team} from '../model/Team';
import {HttpProxyService} from '../service/http-proxy.service';
import {Category, CategoryState} from "../model/Category";
import {CategoryService} from "./category.service";
import {GameService} from "./game.service";
import {Game} from "../model/Game";

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  constructor(private http: HttpProxyService, private categoryService: CategoryService, private gameService: GameService) {
  }

  getAll(): Promise<Array<Team>> {
    return this.http.get<Array<Team>>('teams');
  }

  getTeam(id: number): Promise<Team> {
    return this.http.get<Team>('teams/' + id);
  }

  getAllByCategory(categoryId: number) {
    return this.http.get<Array<Team>>('teams', {categoryId: categoryId});
  }

  getAllByCategorySorted(categoryId: number) {
    return Promise.all([
        this.categoryService.getById(categoryId),
        this.http.get<Array<Team>>('teams', {categoryId: categoryId}),
        this.gameService.getAllGamesByCategory(categoryId),
      ]
    ).then(([category, teams, games]) => this.sortTeams(teams, category, games))

  }

  save(team: Team) {
    return this.http.put<Team>('teams', team);
  }

  private sortTeams(teams: Array<Team>, category: Category, games: Game[]): Array<Team> {
    if (category.state === CategoryState.FINISHED) {
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
          return this.compareEqualPointedTeams(a, b, games);
        }
      });
    }
  }

  compareEqualPointedTeams(a: Team, b: Team, games: Game[]): number {
    const aGoalDifference = a.goalsShot - a.goalsGotten;
    const bGoalDifference = b.goalsShot - b.goalsGotten;
    if (aGoalDifference > bGoalDifference) {
      return -1;
    } else if (aGoalDifference < bGoalDifference) {
      return 1;
    } else {
      return this.compareEqualTeamsWithEqualGoalDifferenceAndPoints(a, b, games);
    }
  }

  compareEqualTeamsWithEqualGoalDifferenceAndPoints(a: Team, b: Team, games: Game[]): number {
    if (a.goalsShot > b.goalsShot) {
      return -1;
    } else if (a.goalsShot < b.goalsShot) {
      return 1;
    } else {
      return this.compareDirectOpponent(a, b, games);
    }
  }

  private compareDirectOpponent(a: Team, b: Team, allCategoryGames: Game[]) {
    const games = allCategoryGames.filter(game =>
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
