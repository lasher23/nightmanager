import {Injectable} from '@angular/core';
import {Role} from '../model/Role';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  roles: Array<Role> = [
    {name: 'Schiedsrichter', defaultRoute: '/referee'},
    {name: 'Admin', defaultRoute: '/admin'},
    {name: 'Anzeige', defaultRoute: '/display'}
  ];

  constructor() {
  }

  getAllRoles(): Array<Role> {
    return this.roles;
  }

  setRole(role: Role) {
    localStorage.setItem('role', role.name);
  }

  getRole(): Role {
    return this.roles.filter(x => x.name === localStorage.getItem('role'))[0];
  }

  getDefaultRoute(): string {
    return this.getRole().defaultRoute;
  }
}
