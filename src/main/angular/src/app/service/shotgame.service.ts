import {Injectable} from '@angular/core';
import {HttpProxyService} from './http-proxy.service';
import {ShotGame} from '../model/ShotGame';

@Injectable({providedIn: 'root'})
export class ShotGameService {

  constructor(private http: HttpProxyService) {
  }

  getRanking(): Promise<Array<ShotGame>> {
    return this.http.get<Array<ShotGame>>('shots/ranking');
  }

  increment(teamId: number, count: number = 1): Promise<ShotGame> {
    return this.http.post<ShotGame>('shots/' + teamId + '/increment', {count: count});
  }

}
