import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { UserManagementService, RoleDto } from './user-management.service';

@Component({
  selector: 'nm-role-list',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div>
      <div class="flex justify-end mb-4">
        <button class="btn btn-primary btn-sm" (click)="create()">+ New Role</button>
      </div>

      @if (loading()) {
        <div class="flex justify-center py-8"><span class="loading loading-spinner loading-lg"></span></div>
      } @else if (error()) {
        <div class="alert alert-error">{{ error() }}</div>
      } @else {
        <div class="space-y-3">
          @for (role of roles(); track role.id) {
            <div class="card bg-base-100 shadow">
              <div class="card-body py-3">
                <div class="flex items-center justify-between">
                  <h3 class="font-bold text-lg">{{ role.name }}</h3>
                  <div class="flex gap-2">
                    <button class="btn btn-sm btn-outline" (click)="edit(role)">Edit</button>
                    <button class="btn btn-sm btn-error btn-outline" (click)="confirmDelete(role)">Delete</button>
                  </div>
                </div>
                @if (role.childRoles.length > 0) {
                  <div class="flex flex-wrap gap-1 mt-1">
                    <span class="text-sm text-gray-500 mr-1">Inherits:</span>
                    @for (child of role.childRoles; track child) {
                      <span class="badge badge-outline badge-sm">{{ child }}</span>
                    }
                  </div>
                }
                @if (role.permissions.length > 0) {
                  <div class="flex flex-wrap gap-1 mt-1">
                    @for (perm of role.permissions; track perm) {
                      <span class="badge badge-ghost badge-sm">{{ perm }}</span>
                    }
                  </div>
                }
              </div>
            </div>
          }
        </div>
      }

      <!-- Delete confirmation modal -->
      @if (deleteTarget()) {
        <dialog class="modal modal-open">
          <div class="modal-box">
            <h3 class="font-bold text-lg">Delete role "{{ deleteTarget()!.name }}"?</h3>
            <p class="py-2 text-sm text-gray-500">This cannot be undone. Users with this role will lose its permissions.</p>
            <div class="modal-action">
              <button class="btn btn-ghost" (click)="deleteTarget.set(null)">Cancel</button>
              <button class="btn btn-error" [disabled]="deleting()" (click)="doDelete()">
                @if (deleting()) { <span class="loading loading-spinner loading-sm"></span> }
                Delete
              </button>
            </div>
          </div>
          <div class="modal-backdrop" (click)="deleteTarget.set(null)"></div>
        </dialog>
      }
    </div>
  `
})
export class RoleListComponent {
  private service = inject(UserManagementService);
  private router = inject(Router);

  roles = signal<RoleDto[]>([]);
  loading = signal(true);
  error = signal<string | null>(null);
  deleteTarget = signal<RoleDto | null>(null);
  deleting = signal(false);

  constructor() {
    this.load();
  }

  load() {
    this.loading.set(true);
    this.service.getRoles().subscribe({
      next: roles => { this.roles.set(roles); this.loading.set(false); },
      error: () => { this.error.set('Failed to load roles'); this.loading.set(false); }
    });
  }

  create() {
    this.router.navigate(['/v2/user-management/roles/new']);
  }

  edit(role: RoleDto) {
    this.router.navigate(['/v2/user-management/roles', role.id]);
  }

  confirmDelete(role: RoleDto) {
    this.deleteTarget.set(role);
  }

  doDelete() {
    this.deleting.set(true);
    this.service.deleteRole(this.deleteTarget()!.id).subscribe({
      next: () => {
        this.deleting.set(false);
        this.deleteTarget.set(null);
        this.load();
      },
      error: () => { this.deleting.set(false); }
    });
  }
}
