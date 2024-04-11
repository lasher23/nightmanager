import {Injectable} from '@angular/core';
import {HttpProxyService} from './http-proxy.service';
import {Category} from '../model/Category';


@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpProxyService) {
  }

  getAll(): Promise<Array<Category>> {
    return this.http.get<Array<Category>>('categories');
  }

  getById(id: number): Promise<Category> {
    return this.http.get<Category>('categories/' + id);
  }
}
