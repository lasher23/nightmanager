import { Injectable } from '@angular/core';
import { CompatClient, IFrame, Stomp, StompSubscription } from '@stomp/stompjs';
import { IMessage } from '@stomp/stompjs/esm5/i-message';

interface Subscription {
  unsubscribe: () => void;
}

@Injectable({
  providedIn: 'root'
})
export class NotifierService {
  private client: CompatClient;
  private isSetup = false;

  constructor() { }

  async getClient(): Promise<CompatClient> {
    if (this.isSetup) {
      return this.client;
    }
    this.client = Stomp.client(
      `${window.location.protocol.includes('https') ? 'wss' : 'ws'}://${window.location.host}/nightmanager-websockets`);
    this.client.activate();
    this.isSetup = true;
    return this.client;
  }

  subscribe(callback: (message: IMessage) => void) {
    if (!this.isSetup) {
      this.setup(callback);
      this.isSetup = true;
    } else {
      this.client.subscribe('/topic/nightmanager-game-change', callback);
    }
  }

  private setup(callback: (message: IMessage) => void) {
    this.client = Stomp.client(
      `${window.location.protocol.includes('https') ? 'wss' : 'ws'}://${window.location.host}/nightmanager-websockets`);
    this.client.onConnect = () => this.client.subscribe('/topic/nightmanager-game-change',
      (message) => callback(message));
    this.client.activate();
  }
}
