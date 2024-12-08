import {Injectable} from '@angular/core';

const CONFIG_KEY = "displayConfig"

export interface DisplayConfig {
  margin: {
    top?: number
    right?: number
    bottom?: number
    left?: number
  },
  totalGamesView: {
    before?: number
    after?: number
  },
  categoryGamesView: {
    before?: number
    after?: number
  }
}

const defaultConfig = {margin: {}, totalGamesView: {before: 20, after: 20}, categoryGamesView: {before: 6, after: 6}};

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

    return {...defaultConfig, ...(JSON.parse(config) || {})};
  }
}
