import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthenticationService} from './service/authentication.service';
import {RoleService} from './service/role.service';

@Injectable()
export class AuthenticationGuard implements CanActivate {
  constructor(private router: Router, private rootService: AuthenticationService, private roleService: RoleService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if (this.rootService.isLoggedIn() && !this.rootService.isTokenExpired() && this.roleService.getRole()) {
      return true;
    }

    this.router.navigate(['/login'], {queryParams: {returnUrl: state.url}});
    return undefined;
  }
}
