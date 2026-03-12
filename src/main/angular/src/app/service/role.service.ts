import {Injectable} from '@angular/core';
import {AuthService} from '../auth/auth.service';
import {map, Observable} from 'rxjs';

/**
 * Derives the user's display role from their permission set.
 * The 'role' concept is kept for UI labelling only (e.g. showing "Schirri" menu).
 * Access control is done purely via permissions.
 */
@Injectable({
  providedIn: 'root'
})
export class RoleService {

  readonly isAdmin$: Observable<boolean>;
  readonly isReferee$: Observable<boolean>;
  readonly isShotMaster$: Observable<boolean>;
  readonly isLoggedIn$: Observable<boolean>;

  constructor(private authService: AuthService) {
    const perms$ = this.authService.user$.pipe(
      map(user => {
        const perms = (user?.profile as any)?.['permissions'];
        return new Set<string>(Array.isArray(perms) ? perms : []);
      })
    );

    this.isAdmin$      = perms$.pipe(map(p => p.has('game:manage')));
    this.isReferee$    = perms$.pipe(map(p => p.has('game:update') && !p.has('game:manage')));
    this.isShotMaster$ = perms$.pipe(map(p => p.has('shot:update') && !p.has('game:manage')));
    this.isLoggedIn$   = this.authService.user$.pipe(map(u => u != null && !u.expired));
  }

  getDefaultRoute(): string {
    const perms = this.authService.getPermissions();
    const permSet = new Set(perms);
    if (permSet.has('game:manage'))  return '/admin';
    if (permSet.has('game:update'))  return '/referee/games';
    if (permSet.has('shot:update'))  return '/v2/shot-master';
    return '/v2/public';
  }
}

