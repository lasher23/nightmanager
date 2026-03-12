import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { UserManagementService, UserDto } from './user-management.service';

@Component({
  selector: 'nm-user-list',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div>
      @if (loading()) {
        <div class="flex justify-center py-8"><span class="loading loading-spinner loading-lg"></span></div>
      } @else if (error()) {
        <div class="alert alert-error">{{ error() }}</div>
      } @else {
        <div class="overflow-x-auto">
          <table class="table table-zebra w-full">
            <thead>
              <tr>
                <th>Email</th>
                <th>Username</th>
                <th>Roles</th>
                <th>Status</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              @for (user of users(); track user.id) {
                <tr>
                  <td>{{ user.email }}</td>
                  <td>{{ user.username }}</td>
                  <td>
                    <div class="flex flex-wrap gap-1">
                      @for (role of user.roles; track role) {
                        <span class="badge badge-outline">{{ role }}</span>
                      }
                    </div>
                  </td>
                  <td>
                    <span class="badge" [class.badge-success]="user.enabled" [class.badge-error]="!user.enabled">
                      {{ user.enabled ? 'Active' : 'Disabled' }}
                    </span>
                  </td>
                  <td>
                    <button class="btn btn-sm btn-outline" (click)="edit(user)">Edit</button>
                  </td>
                </tr>
              }
            </tbody>
          </table>
        </div>
      }
    </div>
  `
})
export class UserListComponent {
  private service = inject(UserManagementService);
  private router = inject(Router);

  users = signal<UserDto[]>([]);
  loading = signal(true);
  error = signal<string | null>(null);

  constructor() {
    this.service.getUsers().subscribe({
      next: users => { this.users.set(users); this.loading.set(false); },
      error: e => { this.error.set('Failed to load users'); this.loading.set(false); }
    });
  }

  edit(user: UserDto) {
    this.router.navigate(['/v2/user-management/users', user.id]);
  }
}
