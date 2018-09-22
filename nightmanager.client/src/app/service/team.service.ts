import {Injectable} from '@angular/core';
import {Team} from '../model/Team';
import {HttpProxyService} from '../service/http-proxy.service';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  constructor(private http: HttpProxyService) {
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
}
