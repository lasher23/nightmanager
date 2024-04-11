import {Injectable} from '@angular/core';
import {map} from "rxjs/operators";
import {StompService} from "../stomp.service";
import {interval, merge, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GameChangeNotifierService {
  gameChanges$: Observable<any>;

  constructor(
    private stompService: StompService,
  ) {
    this.gameChanges$ = merge(this.stompService.watch('/topic/nightmanager-game-change').pipe(map(() => undefined)), interval(60000))
  }
}
