import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'nm-user-management',
  standalone: true,
  imports: [CommonModule, RouterModule],
  template: `
    <div class="max-w-6xl mx-auto p-4">
      <h1 class="text-3xl font-bold mb-6">User Management</h1>
      <div class="tabs tabs-boxed mb-6">
        <a class="tab" routerLink="users" routerLinkActive="tab-active">Users</a>
        <a class="tab" routerLink="roles" routerLinkActive="tab-active">Roles</a>
        <a class="tab" routerLink="otp-users" routerLinkActive="tab-active">OTP Users</a>
      </div>
      <router-outlet />
    </div>
  `
})
export class UserManagementComponent {}
