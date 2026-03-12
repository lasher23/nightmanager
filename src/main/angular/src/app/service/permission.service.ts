import {Injectable} from '@angular/core';
import {AuthService} from '../auth/auth.service';
import {map, Observable} from 'rxjs';

/**
 * Reads the 'permissions' claim from the OIDC access token and exposes
 * helper methods / observables used by the UI to show/hide features.
 */
@Injectable({providedIn: 'root'})
export class PermissionService {

  /** Emits the current flat permission set whenever the user changes. */
  permissions$: Observable<Set<string>>;

  constructor(private authService: AuthService) {
    this.permissions$ = this.authService.user$.pipe(
      map(user => {
        if (!user?.profile) return new Set<string>();
        const perms = (user.profile as any)['permissions'];
        return new Set<string>(Array.isArray(perms) ? perms : []);
      })
    );
  }

  /** Returns an observable that emits true when the user has the given permission. */
  has$(permission: string): Observable<boolean> {
    return this.permissions$.pipe(map(perms => perms.has(permission)));
  }

  /** Synchronous check — use only where a snapshot is sufficient. */
  has(permission: string): boolean {
    const user = this.authService.currentUser;
    if (!user?.profile) return false;
    const perms = (user.profile as any)['permissions'];
    return Array.isArray(perms) && perms.includes(permission);
  }
}
