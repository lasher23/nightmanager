import {Injectable} from '@angular/core';
import {HttpProxyService} from './http-proxy.service';
import {Hall} from '../model/Hall';
import {Chat} from '../model/Chat';
import {interval, merge, Observable, Subject, timer} from 'rxjs';
import {StompService} from "../stomp.service";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  readonly chatChanges$: Observable<any>;

  constructor(
    private http: HttpProxyService,
    private stompService: StompService,
  ) {
    this.chatChanges$ = merge(this.stompService.watch('/topic/nightmanager-chat-change').pipe(map(() => undefined)), interval(60000))
  }

  getChatsByHall(hallId: Hall['id']): Promise<Array<Chat>> {
    return this.http.get<Array<Chat>>(`chats/${hallId}`);
  }

  save(chat: Chat): Promise<any> {
    return this.http.post('chats', chat);
  }
}
