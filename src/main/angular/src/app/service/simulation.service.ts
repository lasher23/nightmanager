import {Injectable} from '@angular/core';
import {HttpProxyService} from './http-proxy.service';
import {Tournament} from '../model/Tournament';
import {SimulationCategory, SimulationCategorySpec, SimulationTournamentSummary} from '../model/Simulation';

@Injectable({providedIn: 'root'})
export class SimulationService {

  constructor(private http: HttpProxyService) {
  }

  listTournaments(): Promise<SimulationTournamentSummary[]> {
    return this.http.get<SimulationTournamentSummary[]>('simulation/tournaments');
  }

  createTournament(name: string): Promise<Tournament> {
    return this.http.post<Tournament>('simulation/tournaments', {name});
  }

  deleteTournament(id: number): Promise<void> {
    return this.http.delete<void>(`simulation/tournaments/${id}`);
  }

  getCategories(tournamentId: number): Promise<SimulationCategory[]> {
    return this.http.get<SimulationCategory[]>(`simulation/tournaments/${tournamentId}/categories`);
  }

  addCategories(tournamentId: number, specs: SimulationCategorySpec[]): Promise<SimulationCategory[]> {
    return this.http.post<SimulationCategory[]>(`simulation/tournaments/${tournamentId}/categories`, specs);
  }
}
