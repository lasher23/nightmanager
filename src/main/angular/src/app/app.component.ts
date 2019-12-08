import {Component, OnChanges, OnInit} from '@angular/core';
import {RoleService} from './service/role.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnChanges, OnInit {

  isReferee = false;

  constructor(private roleService: RoleService) {
    this.isReferee = this.roleService.getRole() && this.roleService.getRole().name === 'REFEREE';
  }

  ngOnChanges() {
    this.isReferee = this.roleService.getRole() && this.roleService.getRole().name === 'REFEREE';
    console.log(this.isReferee);
  }

  ngOnInit() {
    this.roleService.role$.subscribe(role => this.isReferee = role && role.name === 'REFEREE');
  }

  logout() {
    localStorage.clear();
    window.location.href = '';
  }
}
