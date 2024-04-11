import {Team} from './Team';
import {Hall} from './Hall';
import {Category} from './Category';

export interface Game {
  id: number;
  teamHome: Team;
  teamGuest: Team;
  goalsTeamGuest: number;
  goalsTeamHome: number;
  state: string;
  placeholder: boolean;
  hall: Hall;
  startDate: Date;
  category: Category;
  type: string;
  live: boolean;
  swappedLiveDisplay: boolean;
  swappedReferee: boolean;
}
