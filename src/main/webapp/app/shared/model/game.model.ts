import { Moment } from 'moment';

export const enum GameState {
  OPEN = 'OPEN',
  DONE = 'DONE'
}

export const enum GameType {
  GROUP_STAGE = 'GROUP_STAGE',
  SEMI_FINAL = 'SEMI_FINAL',
  FINAL = 'FINAL'
}

export interface IGame {
  id?: number;
  goalsHome?: number;
  goalsGuest?: number;
  state?: GameState;
  placeholder?: boolean;
  start?: Moment;
  duration?: number;
  type?: GameType;
  homeId?: number;
  guestId?: number;
  categoryId?: number;
  hallId?: number;
}

export class Game implements IGame {
  constructor(
    public id?: number,
    public goalsHome?: number,
    public goalsGuest?: number,
    public state?: GameState,
    public placeholder?: boolean,
    public start?: Moment,
    public duration?: number,
    public type?: GameType,
    public homeId?: number,
    public guestId?: number,
    public categoryId?: number,
    public hallId?: number
  ) {
    this.placeholder = this.placeholder || false;
  }
}
