import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {RoleService} from '../../service/role.service';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss'],
    standalone: false
})
export class HomeComponent implements OnInit {

  constructor(private router: Router, private roleService: RoleService) { }

  ngOnInit() {
    this.router.navigateByUrl(this.roleService.getDefaultRoute());
  }

}
