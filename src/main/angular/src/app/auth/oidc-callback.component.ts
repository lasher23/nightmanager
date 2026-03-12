import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from './auth.service';
import {RoleService} from '../service/role.service';

@Component({
  selector: 'app-oidc-callback',
  template: '<p style="text-align:center; margin-top: 2rem;">Logging in...</p>',
  standalone: true
})
export class OidcCallbackComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router,
    private roleService: RoleService
  ) {
  }

  async ngOnInit() {
    try {
      await this.authService.handleCallback();
      this.router.navigateByUrl(this.roleService.getDefaultRoute());
    } catch (e) {
      console.error('OIDC callback error', e);
      this.router.navigateByUrl('/v2/public');
    }
  }
}

