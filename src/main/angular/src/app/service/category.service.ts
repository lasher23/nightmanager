import {Injectable} from '@angular/core';
import {HttpProxyService} from './http-proxy.service';
import {Category, CategoryState} from '../model/Category';


@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpProxyService) {
  }

  getAll(): Promise<Array<Category>> {
    return this.http.get<Array<Category>>('categories');
  }

  async getAllActive(): Promise<Array<Category>> {
    const categories = await this.http.get<Array<Category>>('categories');
    return categories.filter(category => category.state !== CategoryState.DISABLED);
  }

  getById(id: number): Promise<Category> {
    return this.http.get<Category>('categories/' + id);
  }
}
