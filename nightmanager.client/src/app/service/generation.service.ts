import {Injectable} from '@angular/core';
import {HttpProxyService} from './http-proxy.service';
import {Category} from '../model/Category';

@Injectable({
  providedIn: 'root'
})
export class GenerationService {

  constructor(private http: HttpProxyService) {
  }

  generate(category: Category) {
    this.http.post('generation', category);
  }
}
