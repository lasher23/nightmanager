import { IGame } from 'app/shared/model/game.model';

export interface IHall {
  id?: number;
  name?: string;
  games?: IGame[];
}

export class Hall implements IHall {
  constructor(public id?: number, public name?: string, public games?: IGame[]) {}
}
