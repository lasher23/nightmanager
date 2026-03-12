import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

export interface OtpUserDto {
  id: number;
  name: string;
  token: string;
  validUntil: string;
  createdAt: string;
  roles: string[];
  expired: boolean;
}

export interface CreateOtpUserRequest {
  name: string;
  validUntil: string; // ISO instant
  roles: string[];
}

@Injectable({ providedIn: 'root' })
export class OtpUserService {
  private http = inject(HttpClient);

  getAll(): Observable<OtpUserDto[]> {
    return this.http.get<OtpUserDto[]>('/api/otp-users');
  }

  create(req: CreateOtpUserRequest): Observable<OtpUserDto> {
    return this.http.post<OtpUserDto>('/api/otp-users', req);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`/api/otp-users/${id}`);
  }

  /** Returns the full login URL that should be encoded in the QR code. */
  loginUrl(token: string): string {
    return `${window.location.origin}/login?otp_code=${encodeURIComponent(token)}`;
  }
}
