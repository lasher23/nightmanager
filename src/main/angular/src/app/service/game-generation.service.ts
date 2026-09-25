import {Injectable} from '@angular/core';
import {HttpProxyService} from './http-proxy.service';
import {Category} from '../model/Category';
import {GameGenerationProposal, ProposalSummary, ProposeRequest} from '../model/GameGenerationProposal';

@Injectable({providedIn: 'root'})
export class GameGenerationService {

  constructor(private http: HttpProxyService) {
  }

  getEligibleCategories(tournamentId: number): Promise<Category[]> {
    return this.http.get<Category[]>('game-generation/categories', {tournamentId});
  }

  listProposals(tournamentId: number): Promise<ProposalSummary[]> {
    return this.http.get<ProposalSummary[]>('game-generation/proposals', {tournamentId});
  }

  getProposal(id: number): Promise<GameGenerationProposal> {
    return this.http.get<GameGenerationProposal>(`game-generation/proposals/${id}`);
  }

  propose(tournamentId: number, request: ProposeRequest): Promise<GameGenerationProposal> {
    return this.http.post<GameGenerationProposal>(`game-generation/propose?tournamentId=${tournamentId}`, request);
  }

  updateProposal(id: number, proposal: GameGenerationProposal): Promise<GameGenerationProposal> {
    return this.http.put<GameGenerationProposal>(`game-generation/proposals/${id}`, proposal);
  }

  deleteProposal(id: number): Promise<void> {
    return this.http.delete<void>(`game-generation/proposals/${id}`);
  }

  commit(id: number): Promise<Category[]> {
    return this.http.post<Category[]>(`game-generation/proposals/${id}/commit`, {});
  }
}
