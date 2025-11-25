import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { RoleService } from './role.service';

@Injectable({ providedIn: 'root' })
export class RoleGuard implements CanActivate {
  constructor(private roleService: RoleService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    const requiredRole = route.data?.role as string;
    const current = this.roleService.getRole();

    if (!requiredRole) {
      return true;
    }

    if (current && current.name === requiredRole) {
      return true;
    }

    // not authorized -> redirect to public home
    this.router.navigate(['/v2/public']);
    return false;
  }
}
