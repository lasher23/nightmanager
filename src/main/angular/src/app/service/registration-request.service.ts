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

  /**
   * Sends (or re-sends) the "registration confirmed" email as the acting admin via Microsoft Graph.
   * Safe to call again if it previously failed — it does not change the request's status.
   */
  sendApprovalEmail(id: number): Promise<void> {
    return this.http.post<void>(`registration-requests/${id}/send-approval-email`, {});
  }

  reject(id: number): Promise<RegistrationRequest> {
    return this.http.patch<RegistrationRequest>(`registration-requests/${id}/reject`, {});
  }
}
