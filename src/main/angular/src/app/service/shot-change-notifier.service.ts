import {Injectable} from '@angular/core';
import {map} from "rxjs/operators";
import {StompService} from "../stomp.service";
import {interval, merge, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ShotChangeNotifierService {
  shotChanges$: Observable<any>;

  constructor(
    private stompService: StompService,
  ) {
    this.shotChanges$ = merge(this.stompService.watch('/topic/nightmanager-shot-change').pipe(map(() => undefined)), interval(30000))
  }
}
