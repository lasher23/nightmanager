import {Injectable} from '@angular/core';
import {Hall} from '../model/Hall';
import {HttpProxyService} from './http-proxy.service';
import {Game, GameState} from '../model/Game';

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

  getAllGames(beforeNow: number, afterNow: number): Promise<Array<Game>> {
    return this.http.get<Array<Game>>('games', {beforeNow: beforeNow, afterNow: afterNow});
  }

  getGames(): Promise<Array<Game>> {
    return this.http.get<Array<Game>>('games');
  }

  getAllGamesByCategory(categoryId: number, beforeNow: number, afterNow: number): Promise<Array<Game>> {
    return this.http.get<Array<Game>>('games', {categoryId: categoryId, beforeNow: beforeNow, afterNow: afterNow});
  }

  updateGame(game: Game): Promise<Game> {
    return this.http.post<Game>('games/complete', game);
  }

  resetGame(game: Game): Promise<Game> {
    return this.http.post<Game>('games/reset', game);
  }
}
