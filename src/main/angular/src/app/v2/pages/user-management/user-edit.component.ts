import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserManagementService, UserDto, RoleDto } from './user-management.service';

@Component({
  selector: 'nm-user-edit',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="max-w-xl">
      <button class="btn btn-ghost btn-sm mb-4" (click)="back()">← Back</button>

      @if (loading()) {
        <div class="flex justify-center py-8"><span class="loading loading-spinner loading-lg"></span></div>
      } @else if (error()) {
        <div class="alert alert-error mb-4">{{ error() }}</div>
      } @else if (user()) {
        <div class="card bg-base-100 shadow">
          <div class="card-body gap-4">
            <h2 class="card-title">{{ user()!.email }}</h2>

            <div class="form-control">
              <label class="label"><span class="label-text">Username</span></label>
              <input class="input input-bordered" type="text" [(ngModel)]="editUsername" />
            </div>

            <div class="form-control">
              <label class="label cursor-pointer justify-start gap-3">
                <input type="checkbox" class="toggle toggle-success" [(ngModel)]="editEnabled" />
                <span class="label-text">Enabled</span>
              </label>
            </div>

            <div class="form-control">
              <label class="label"><span class="label-text">Roles</span></label>
              <div class="flex flex-wrap gap-2">
                @for (role of allRoles(); track role.name) {
                  <label class="flex items-center gap-2 cursor-pointer">
                    <input type="checkbox" class="checkbox"
                      [checked]="editRoles().has(role.name)"
                      (change)="toggleRole(role.name, $any($event.target).checked)" />
                    <span>{{ role.name }}</span>
                  </label>
                }
              </div>
            </div>

            @if (successMsg()) {
              <div class="alert alert-success py-2">{{ successMsg() }}</div>
            }
            @if (saveError()) {
              <div class="alert alert-error py-2">{{ saveError() }}</div>
            }

            <div class="card-actions justify-end">
              <button class="btn btn-primary" [disabled]="saving()" (click)="save()">
                @if (saving()) { <span class="loading loading-spinner loading-sm"></span> }
                Save
              </button>
            </div>
          </div>
        </div>
      }
    </div>
  `
})
export class UserEditComponent {
  private service = inject(UserManagementService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  user = signal<UserDto | null>(null);
  allRoles = signal<RoleDto[]>([]);
  loading = signal(true);
  saving = signal(false);
  error = signal<string | null>(null);
  saveError = signal<string | null>(null);
  successMsg = signal<string | null>(null);

  editUsername = '';
  editEnabled = true;
  editRoles = signal<Set<string>>(new Set());

  constructor() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    Promise.all([
      this.service.getUser(id).toPromise(),
      this.service.getRoles().toPromise()
    ]).then(([user, roles]) => {
      this.user.set(user!);
      this.allRoles.set(roles!);
      this.editUsername = user!.username;
      this.editEnabled = user!.enabled;
      this.editRoles.set(new Set(user!.roles));
      this.loading.set(false);
    }).catch(() => {
      this.error.set('Failed to load user');
      this.loading.set(false);
    });
  }

  toggleRole(name: string, checked: boolean) {
    const s = new Set(this.editRoles());
    checked ? s.add(name) : s.delete(name);
    this.editRoles.set(s);
  }

  save() {
    this.saving.set(true);
    this.saveError.set(null);
    this.successMsg.set(null);
    this.service.updateUser(this.user()!.id, {
      username: this.editUsername,
      enabled: this.editEnabled,
      roles: [...this.editRoles()]
    }).subscribe({
      next: updated => {
        this.user.set(updated);
        this.successMsg.set('Saved successfully');
        this.saving.set(false);
      },
      error: () => {
        this.saveError.set('Failed to save');
        this.saving.set(false);
      }
    });
  }

  back() {
    this.router.navigate(['/v2/user-management/users']);
  }
}
