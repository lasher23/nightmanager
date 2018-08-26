import {Injectable} from '@angular/core';
import {HttpProxyService} from './http-proxy.service';
import {Hall} from '../model/Hall';

@Injectable({
  providedIn: 'root'
})
export class HallService {

  constructor(private http: HttpProxyService) {
  }

  getAllHalls(): Promise<Array<Hall>> {
    return this.http.get<Array<Hall>>('halls');
  }

  getHall(id: number): Promise<Hall> {
    return this.http.get<Hall>('halls/' + id);
  }

  setHall(hall: Hall) {
    localStorage.setItem('hall', '' + hall.id);
  }

  getCurrentHall(): Promise<Hall> {
    const hallId = localStorage.getItem('hall');
    return this.getHall(Number(hallId));
  }

  isHallSet(): boolean {
    return localStorage.getItem('hall') != undefined;
  }
}
