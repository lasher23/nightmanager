import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable, map, take } from 'rxjs';
import { PermissionService } from './permission.service';

@Injectable({ providedIn: 'root' })
export class RoleGuard implements CanActivate {
  constructor(private permissionService: PermissionService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    const requiredPermission = route.data?.['permission'] as string;

    if (!requiredPermission) {
      return new Observable(obs => { obs.next(true); obs.complete(); });
    }

    return this.permissionService.has$(requiredPermission).pipe(
      take(1),
      map(has => {
        if (!has) {
          this.router.navigate(['/v2/public']);
        }
        return has;
      })
    );
  }
}
