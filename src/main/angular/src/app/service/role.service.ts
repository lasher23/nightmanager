import {Injectable} from '@angular/core';
import {Role} from '../model/Role';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  roles: Array<Role> = [
    {name: 'REFEREE', defaultRoute: '/referee'},
    {name: 'ADMIN', defaultRoute: '/admin'},
  ];
  public role$ = new Subject<Role>();

  constructor() {
    setTimeout(() => this.role$.next(this.getRole()));
  }

  getAllRoles(): Array<Role> {
    return this.roles;
  }

  setRole(role: string) {
    localStorage.setItem('role', role);
    this.role$.next(this.getRole());
  }

  getRole(): Role {
    return this.roles.filter(x => x.name === localStorage.getItem('role'))[0];
  }

  getDefaultRoute(): string {
    return this.getRole().defaultRoute;
  }
}
