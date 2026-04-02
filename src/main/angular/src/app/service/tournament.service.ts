import {Injectable} from '@angular/core';
import {HttpProxyService} from './http-proxy.service';
import {Tournament} from '../model/Tournament';

@Injectable({
  providedIn: 'root'
})
export class TournamentService {

  constructor(private http: HttpProxyService) {
  }

  getAll(): Promise<Tournament[]> {
    return this.http.get<Tournament[]>('tournaments');
  }

  getById(id: number): Promise<Tournament> {
    return this.http.get<Tournament>(`tournaments/${id}`);
  }

  create(tournament: Partial<Tournament>): Promise<Tournament> {
    return this.http.post<Tournament>('tournaments', tournament);
  }

  update(tournament: Tournament): Promise<Tournament> {
    return this.http.put<Tournament>('tournaments', tournament);
  }

  delete(id: number): Promise<void> {
    return this.http.delete<void>(`tournaments/${id}`);
  }
}
