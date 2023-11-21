import {Injectable} from '@angular/core';

const CONFIG_KEY = "displayConfig"

export interface DisplayConfig {
  margin: {
    top?: number
    right?: number
    bottom?: number
    left?: number
  }
}

@Injectable({
  providedIn: 'root'
})
export class DisplayConfigService {

  constructor() {
  }

  save(config: DisplayConfig) {
    window.localStorage.setItem(CONFIG_KEY, JSON.stringify(config))

  }

  getConfig(): DisplayConfig {
    const config = window.localStorage.getItem(CONFIG_KEY);

    return config ? JSON.parse(config) : {margin: {}}
  }
}
