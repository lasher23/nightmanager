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
    return this.http.get<Array<Game>>('games');
  }

  getAllGamesByCategory(categoryId: number): Promise<Array<Game>> {
    return this.http.get<Array<Game>>('games', {categoryId: categoryId});
  }

  updateGame(game: Game): Promise<Game> {
    return this.http.post<Game>('games/complete', game);
  }

  resetGame(game: Game): Promise<Game> {
    return this.http.post<Game>('games/reset', game);
  }

  getClosestGamesToNow(games: Array<Game>, beforeNow: number, afterNow: number) {
    const gamessorted = games.map(game => {
      game.startDate = new Date(game.startDate);
      return game;
    }).sort((game1, game2) => game1.startDate.getTime() - game2.startDate.getTime());
    const now = new Date().getTime();
    const gamesbefore = gamessorted.filter(game => game.startDate.getTime() - now < 0);
    const gamesafter = gamessorted.filter(game => game.startDate.getTime() - now >= 0);
    return gamesbefore.splice(gamesbefore.length - beforeNow)
      .concat(gamesafter.splice(0, afterNow));
  }
}
