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
    return this.http.get<Array<Game>>('games', {hall: hall.id, state: 'OPEN'});
  }

  getGameById(id: number): Promise<Game> {
    return this.http.get<Game>('games/' + id);
  }

  updateGame(game: Game): Promise<Game> {
    return this.http.post<Game>('games/complete', game);
  }
}
