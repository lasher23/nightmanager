import {Injectable} from '@angular/core';
import {Hall} from '../model/Hall';
import {HttpProxyService} from './http-proxy.service';
import {Game} from '../model/Game';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  constructor(private http: HttpProxyService) {
  }

  getAllGamesByHallAndNotCompleted(hall: Hall): Promise<Array<Game>> {
    return this.http.get<Array<Game>>('games', {hallId: hall.id, state: 'OPEN'});
  }

  getGameById(id: number): Promise<Game> {
    return this.http.get<Game>('games/' + id);
  }

  getAllGames(): Promise<Array<Game>> {
    return this.http.get<Array<Game>>('games').then(games => this.sortGames(games));
  }

  getAllGamesByCategory(categoryId: number): Promise<Array<Game>> {
    return this.http.get<Array<Game>>('games', {categoryId: categoryId});
  }

  completeGame(game: Game): Promise<Game> {
    return this.http.post<Game>('games/complete', game);
  }

  updateGame(game: Game): Promise<Game> {
    return this.http.put<Game>(`games/${game.id}`, game);
  }

  resetGame(game: Game): Promise<Game> {
    return this.http.post<Game>('games/reset', game);
  }

  getClosestGamesToNow(games: Array<Game>, beforeNow: number, afterNow: number) {
    if (!games) {
      return [];
    }
    const gamesSorted = this.sortGames(games);
    const now = new Date().getTime();
    const gamesBefore = gamesSorted.filter(game => game.startDate.getTime() - now < 0);
    const gamesAfter = gamesSorted.filter(game => game.startDate.getTime() - now >= 0);
    const countFromBefore = afterNow > gamesAfter.length ? beforeNow + afterNow - gamesAfter.length : beforeNow
    const countFromAfter = beforeNow > gamesAfter.length ? afterNow + beforeNow - gamesBefore.length : afterNow
    return gamesBefore.splice(Math.max(gamesBefore.length - countFromBefore, 0))
      .concat(gamesAfter.splice(0, Math.min(afterNow, gamesAfter.length)));
  }

  private sortGames(games: Array<Game>) {
    return games.map(game => {
      game.startDate = new Date(game.startDate);
      return game;
    }).sort((game1, game2) => game1.startDate.getTime() - game2.startDate.getTime() || (game1.hall?.id ?? 0) - (game2.hall?.id ?? 0));
  }

  updateGameAsLive(game: Game) {
    return this.http.patch<Game>(`games/${game.id}/live`, {live: true});
  }

  getLiveGameByHall(hallId: number) {
    return this.http.get<Array<Game>>('games', {hallId: hallId, live: true});
  }

  getNextGame(game: Game): Promise<Game> {
    return this.http.get<Game>(`games/${game.id}/next`)
  }
}
