import {Injectable} from '@angular/core';
import {Role} from '../model/Role';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  roles: Array<Role> = [
    {name: 'REFEREE', defaultRoute: '/referee'},
    {name: 'ADMIN', defaultRoute: '/admin'},
  ];

  constructor() {
  }

  getAllRoles(): Array<Role> {
    return this.roles;
  }

  setRole(role: string) {
    localStorage.setItem('role', role);
  }

  getRole(): Role {
    return this.roles.filter(x => x.name === localStorage.getItem('role'))[0];
  }

  getDefaultRoute(): string {
    return this.getRole().defaultRoute;
  }
}
