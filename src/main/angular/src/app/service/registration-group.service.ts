import {Injectable} from '@angular/core';
import {HttpProxyService} from './http-proxy.service';
import {RegistrationGroup} from '../model/RegistrationGroup';

@Injectable({providedIn: 'root'})
export class RegistrationGroupService {

  constructor(private http: HttpProxyService) {}

  getByTournament(tournamentId: number): Promise<RegistrationGroup[]> {
    return this.http.get<RegistrationGroup[]>('registration-groups', {tournamentId});
  }

  getById(id: number): Promise<RegistrationGroup> {
    return this.http.get<RegistrationGroup>(`registration-groups/${id}`);
  }

  create(tournamentId: number, group: Partial<RegistrationGroup>): Promise<RegistrationGroup> {
    return this.http.post<RegistrationGroup>(`registration-groups?tournamentId=${tournamentId}`, group);
  }

  update(id: number, group: Partial<RegistrationGroup>): Promise<RegistrationGroup> {
    return this.http.put<RegistrationGroup>(`registration-groups/${id}`, group);
  }

  delete(id: number): Promise<void> {
    return this.http.delete<void>(`registration-groups/${id}`);
  }

  propose(groupId: number, count: number): Promise<number[][]> {
    return this.http.get<number[][]>(`registration-groups/${groupId}/propose`, {count});
  }

  generate(groupId: number, categories: {name: string; requestIds: number[]}[]): Promise<any[]> {
    return this.http.post<any[]>(`registration-groups/${groupId}/generate`, {categories});
  }
}
