import { Injectable } from '@angular/core';
import { HttpProxyService } from './http-proxy.service';
import { Hall } from '../model/Hall';
import { Chat } from '../model/Chat';
import { IMessage } from '@stomp/stompjs/esm5/i-message';
import { NotifierService } from './notifier.service';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  chat$ = new Subject();
  initialized = false;

  constructor(private http: HttpProxyService, private notifierService: NotifierService,) {
  }

  getChatsByHall(hallId: Hall['id']): Promise<Array<Chat>> {
    return this.http.get<Array<Chat>>(`chats/${hallId}`);
  }

  save(chat: Chat): Promise<any> {
    return this.http.post('chats', chat);
  }

  async getChatEvents(): Promise<Observable<any>> {
    if (!this.initialized) {
      this.initialized = true;
      const client = await this.notifierService.getClient();
      if (client.connected) {
        client.subscribe('/topic/nightmanager-chat-change', () => this.chat$.next());
        return;
      }
      client.onConnect = param => {
        client.subscribe('/topic/nightmanager-chat-change', () => this.chat$.next());
      };
    }
    return this.chat$;
  }
}
