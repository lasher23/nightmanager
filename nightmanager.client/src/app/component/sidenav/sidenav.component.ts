import {Component, OnInit} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {map} from 'rxjs/operators';
import {Router} from '@angular/router';
import {RoleService} from '../../service/role.service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {
  mode = 'side';
  opened = false;
  layoutGap = '64';
  fixedInViewport = true;
  isReferee = false;

  constructor(private bpo: BreakpointObserver, public roleService: RoleService) {
  }

  public ngOnInit(): void {
    this.roleService.role$.subscribe(role => this.isReferee = role.name === 'REFEREE');
    const breakpoints = Object.keys(Breakpoints).map(key => Breakpoints[key]);
    this.bpo.observe(breakpoints)
      .pipe(map(bst => bst.matches))
      .subscribe(matched => {
        this.determineSidenavMode();
        this.determineLayoutGap();
      });
  }

  private determineSidenavMode(): void {
    if (
      this.isExtraSmallDevice() ||
      this.isSmallDevice()
    ) {
      this.fixedInViewport = false;
      this.mode = 'over';
      this.opened = false;
      return;
    }

    this.fixedInViewport = true;
    this.mode = 'side';
  }

  private determineLayoutGap(): void {
    if (this.isExtraSmallDevice() || this.isSmallDevice()) {
      this.layoutGap = '0';
      return;
    }

    this.layoutGap = '64';
  }

  public isExtraSmallDevice(): boolean {
    return this.bpo.isMatched(Breakpoints.XSmall);
  }

  public isSmallDevice(): boolean {
    return this.bpo.isMatched(Breakpoints.Small);
  }

  logout() {
    localStorage.clear();
    window.location.href = '';
  }
}
