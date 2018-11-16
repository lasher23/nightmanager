import {Team} from './Team';
import {Hall} from './Hall';
import {Time} from '@angular/common';

export interface Game {
  id: number;
  teamHome: Team;
  teamGuest: Team;
  goalsTeamGuest: number;
  goalsTeamHome: number;
  state: string;
  placeholder: boolean;
  hall: Hall;
  startDate: string;
}
