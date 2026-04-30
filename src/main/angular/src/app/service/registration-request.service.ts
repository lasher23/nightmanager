import {Injectable} from '@angular/core';
import {HttpProxyService} from './http-proxy.service';
import {RegistrationRequest} from '../model/RegistrationRequest';

@Injectable({providedIn: 'root'})
export class RegistrationRequestService {

  constructor(private http: HttpProxyService) {}

  create(groupId: number, request: Partial<RegistrationRequest>): Promise<RegistrationRequest> {
    return this.http.post<RegistrationRequest>(`registration-requests?groupId=${groupId}`, request);
  }

  getByGroup(groupId: number): Promise<RegistrationRequest[]> {
    return this.http.get<RegistrationRequest[]>('registration-requests', {groupId});
  }

  getByTournament(tournamentId: number): Promise<RegistrationRequest[]> {
    return this.http.get<RegistrationRequest[]>('registration-requests', {tournamentId});
  }

  approve(id: number): Promise<RegistrationRequest> {
    return this.http.patch<RegistrationRequest>(`registration-requests/${id}/approve`, {});
  }

  reject(id: number): Promise<RegistrationRequest> {
    return this.http.patch<RegistrationRequest>(`registration-requests/${id}/reject`, {});
  }
}
