import {Injectable} from "@angular/core";
import {RxStomp, RxStompConfig} from "@stomp/rx-stomp";
import {stompConf} from "./stomp.conf";


@Injectable({
  providedIn: "root"
})
export class StompService extends RxStomp {
  constructor() {
    super();
    this.configure(stompConf)
    this.activate()
  }
}
