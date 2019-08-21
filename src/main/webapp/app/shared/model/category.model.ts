import { IGame } from 'app/shared/model/game.model';
import { ITeam } from 'app/shared/model/team.model';

export const enum CategoryType {
  YETIS_CUP = 'YETIS_CUP',
  DOUBLE = 'DOUBLE',
  SINGLE = 'SINGLE'
}

export const enum CategoryState {
  GROUP_STAGE = 'GROUP_STAGE',
  SEMI_FINAL = 'SEMI_FINAL',
  FINAL = 'FINAL'
}

export interface ICategory {
  id?: number;
  name?: string;
  type?: CategoryType;
  state?: CategoryState;
  remark?: string;
  tournamentId?: number;
  parentCategoryId?: number;
  games?: IGame[];
  teams?: ITeam[];
}

export class Category implements ICategory {
  constructor(
    public id?: number,
    public name?: string,
    public type?: CategoryType,
    public state?: CategoryState,
    public remark?: string,
    public tournamentId?: number,
    public parentCategoryId?: number,
    public games?: IGame[],
    public teams?: ITeam[]
  ) {}
}
