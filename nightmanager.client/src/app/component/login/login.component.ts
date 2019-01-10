import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../service/login.service';
import {FormControl, FormGroup} from '@angular/forms';
import {MatFormFieldControl} from '@angular/material/form-field';
import {User} from '../../model/User';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../service/authentication.service';
import {RoleService} from '../../service/role.service';
import {Role} from '../../model/Role';
import {SnackbarService} from '../../service/snackbar.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user: User = {password: '', username: ''};
  roleChoosing = false;
  roles: Array<Role>;

  constructor(private loginService: LoginService,
              private router: Router,
              private authService: AuthenticationService,
              private roleService: RoleService,
              private snackbarService: SnackbarService) {
  }

  ngOnInit() {
    this.roles = this.roleService.getAllRoles();
    if (this.authService.isLoggedIn()) {
      this.router.navigateByUrl('/');
    }
  }

  onLogin() {
    this.loginService.login(this.user).then(user => {
      this.roleService.setRole(user.roles[0]);
      this.router.navigateByUrl(this.roleService.getDefaultRoute());
    });
  }
}
