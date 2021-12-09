import { Injectable } from '@angular/core';
import { CompatClient, IFrame, Stomp, StompSubscription } from '@stomp/stompjs';
import { IMessage } from '@stomp/stompjs/esm5/i-message';
import { NotifierService } from './notifier.service';

interface Subscription {
  unsubscribe: () => void;
}

@Injectable({
  providedIn: 'root'
})
export class GameChangeNotifierService {

  constructor(private notifierService: NotifierService,) { }

  async subscribe(callback: (message: IMessage) => void) {
    const client = await this.notifierService.getClient();
    if (client.connected) {
      client.subscribe('/topic/nightmanager-game-change', callback);
      return;
    }
    client.onConnect = param => {
      client.subscribe('/topic/nightmanager-game-change', callback);
    };
  }
}
