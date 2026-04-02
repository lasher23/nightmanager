import {Injectable} from '@angular/core';
import {HttpProxyService} from './http-proxy.service';
import {Category, CategoryState} from '../model/Category';
import {TournamentStore} from './tournament-store.service';


@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpProxyService, private tournamentStore: TournamentStore) {
  }

  getAll(): Promise<Array<Category>> {
    const tournamentId = this.tournamentStore.getActiveTournamentId();
    return this.http.get<Array<Category>>('categories', tournamentId ? {tournamentId} : {});
  }

  async getAllActive(): Promise<Array<Category>> {
    const categories = await this.getAll();
    return categories.filter(category => category.state !== CategoryState.DISABLED);
  }

  getById(id: number): Promise<Category> {
    return this.http.get<Category>('categories/' + id);
  }
}
