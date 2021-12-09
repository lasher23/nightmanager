import { Hall } from './Hall';

export interface Chat {
  id?: number;
  hall: Hall;
  message: string;
  createdDate?: string;
  creator: string;
}
