import {Category} from './Category';

export interface Team {
  id: number;
  name: string;
  placeholder: boolean;
  goalsShot: number;
  goalsGotten: number;
  category: Category;
  rank: number;
  points: number;
}
