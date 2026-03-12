import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

export interface UserDto {
  id: number;
  email: string;
  username: string;
  enabled: boolean;
  createdAt: string;
  roles: string[];
}

export interface UpdateUserRequest {
  username?: string;
  enabled?: boolean;
  roles?: string[];
}

export interface RoleDto {
  id: number;
  name: string;
  permissions: string[];
  childRoles: string[];
}

export interface RoleRequest {
  name?: string;
  permissions?: string[];
  childRoleNames?: string[];
}

@Injectable({ providedIn: 'root' })
export class UserManagementService {
  private http = inject(HttpClient);

  getUsers(): Observable<UserDto[]> {
    return this.http.get<UserDto[]>('/api/users');
  }

  getUser(id: number): Observable<UserDto> {
    return this.http.get<UserDto>(`/api/users/${id}`);
  }

  updateUser(id: number, req: UpdateUserRequest): Observable<UserDto> {
    return this.http.patch<UserDto>(`/api/users/${id}`, req);
  }

  getRoles(): Observable<RoleDto[]> {
    return this.http.get<RoleDto[]>('/api/roles');
  }

  getRole(id: number): Observable<RoleDto> {
    return this.http.get<RoleDto>(`/api/roles/${id}`);
  }

  createRole(req: RoleRequest): Observable<RoleDto> {
    return this.http.post<RoleDto>('/api/roles', req);
  }

  updateRole(id: number, req: RoleRequest): Observable<RoleDto> {
    return this.http.patch<RoleDto>(`/api/roles/${id}`, req);
  }

  deleteRole(id: number): Observable<void> {
    return this.http.delete<void>(`/api/roles/${id}`);
  }
}
