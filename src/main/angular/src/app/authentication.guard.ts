import {Injectable} from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import {Observable} from 'rxjs';
import {AuthenticationService} from './service/authentication.service';
import {RoleService} from './service/role.service';

@Injectable()
export class AuthenticationGuard  {
  constructor(private router: Router, private rootService: AuthenticationService, private roleService: RoleService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.rootService.isLoggedIn() && !this.rootService.isTokenExpired()) {
      return true;
    }

    this.router.navigate(['/login'], {queryParams: {returnUrl: state.url}});
    return undefined;
  }
}
