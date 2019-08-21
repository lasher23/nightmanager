export interface ITeam {
  id?: number;
  name?: string;
  placeholder?: boolean;
  rank?: number;
  paid?: boolean;
  categoryId?: number;
}

export class Team implements ITeam {
  constructor(
    public id?: number,
    public name?: string,
    public placeholder?: boolean,
    public rank?: number,
    public paid?: boolean,
    public categoryId?: number
  ) {
    this.placeholder = this.placeholder || false;
    this.paid = this.paid || false;
  }
}
