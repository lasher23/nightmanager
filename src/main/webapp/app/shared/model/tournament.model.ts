import { ICategory } from 'app/shared/model/category.model';

export interface ITournament {
  id?: number;
  name?: string;
  active?: boolean;
  categories?: ICategory[];
}

export class Tournament implements ITournament {
  constructor(public id?: number, public name?: string, public active?: boolean, public categories?: ICategory[]) {
    this.active = this.active || false;
  }
}
