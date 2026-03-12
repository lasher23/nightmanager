import { Component, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserManagementService, RoleDto } from './user-management.service';

export const ALL_PERMISSIONS = [
  'category:list', 'category:get', 'category:create',
  'chat:list', 'chat:create',
  'game:list', 'game:get', 'game:update', 'game:manage', 'game:notify',
  'generation:create',
  'hall:list', 'hall:get',
  'notification:list', 'notification:create',
  'shot:list', 'shot:update',
  'team:list', 'team:get', 'team:create', 'team:update',
  'user:list', 'user:get', 'user:update',
  'role:list', 'role:get', 'role:create', 'role:update', 'role:delete',
];

@Component({
  selector: 'nm-role-edit',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="max-w-xl">
      <button class="btn btn-ghost btn-sm mb-4" (click)="back()">← Back</button>

      @if (loading()) {
        <div class="flex justify-center py-8"><span class="loading loading-spinner loading-lg"></span></div>
      } @else {
        <div class="card bg-base-100 shadow">
          <div class="card-body gap-4">
            <h2 class="card-title">{{ isNew ? 'New Role' : 'Edit Role: ' + name }}</h2>

            @if (error()) {
              <div class="alert alert-error">{{ error() }}</div>
            }

            <div class="form-control">
              <label class="label"><span class="label-text">Name</span></label>
              <input class="input input-bordered" type="text" [(ngModel)]="name" placeholder="e.g. TIMEKEEPER" />
            </div>

            <div class="form-control">
              <label class="label"><span class="label-text">Permissions</span></label>
              <div class="grid grid-cols-2 gap-1">
                @for (perm of allPerms; track perm) {
                  <label class="flex items-center gap-2 cursor-pointer text-sm">
                    <input type="checkbox" class="checkbox checkbox-sm"
                      [checked]="selectedPerms().has(perm)"
                      (change)="togglePerm(perm, $any($event.target).checked)" />
                    <span>{{ perm }}</span>
                  </label>
                }
              </div>
            </div>

            <div class="form-control">
              <label class="label"><span class="label-text">Inherits from (child roles)</span></label>
              <div class="flex flex-wrap gap-2">
                @for (role of otherRoles(); track role.name) {
                  <label class="flex items-center gap-2 cursor-pointer">
                    <input type="checkbox" class="checkbox"
                      [checked]="selectedChildren().has(role.name)"
                      (change)="toggleChild(role.name, $any($event.target).checked)" />
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
                {{ isNew ? 'Create' : 'Save' }}
              </button>
            </div>
          </div>
        </div>
      }
    </div>
  `
})
export class RoleEditComponent {
  private service = inject(UserManagementService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  readonly allPerms = ALL_PERMISSIONS;
  isNew = false;
  roleId: number | null = null;
  originalName = '';

  name = '';
  selectedPerms = signal<Set<string>>(new Set());
  selectedChildren = signal<Set<string>>(new Set());
  otherRoles = signal<RoleDto[]>([]);

  loading = signal(true);
  saving = signal(false);
  error = signal<string | null>(null);
  saveError = signal<string | null>(null);
  successMsg = signal<string | null>(null);

  constructor() {
    const idParam = this.route.snapshot.paramMap.get('id');
    this.isNew = idParam === 'new';
    this.roleId = this.isNew ? null : Number(idParam);

    if (this.isNew) {
      this.service.getRoles().subscribe({
        next: roles => { this.otherRoles.set(roles); this.loading.set(false); },
        error: () => { this.error.set('Failed to load roles'); this.loading.set(false); }
      });
    } else {
      Promise.all([
        this.service.getRole(this.roleId!).toPromise(),
        this.service.getRoles().toPromise()
      ]).then(([role, roles]) => {
        this.name = role!.name;
        this.originalName = role!.name;
        this.selectedPerms.set(new Set(role!.permissions));
        this.selectedChildren.set(new Set(role!.childRoles));
        this.otherRoles.set(roles!.filter(r => r.id !== this.roleId));
        this.loading.set(false);
      }).catch(() => { this.error.set('Failed to load role'); this.loading.set(false); });
    }
  }

  togglePerm(perm: string, checked: boolean) {
    const s = new Set(this.selectedPerms());
    checked ? s.add(perm) : s.delete(perm);
    this.selectedPerms.set(s);
  }

  toggleChild(name: string, checked: boolean) {
    const s = new Set(this.selectedChildren());
    checked ? s.add(name) : s.delete(name);
    this.selectedChildren.set(s);
  }

  save() {
    if (!this.name.trim()) { this.saveError.set('Name is required'); return; }
    this.saving.set(true);
    this.saveError.set(null);
    this.successMsg.set(null);
    const req = {
      name: this.name.trim().toUpperCase(),
      permissions: [...this.selectedPerms()],
      childRoleNames: [...this.selectedChildren()]
    };
    const call = this.isNew
      ? this.service.createRole(req)
      : this.service.updateRole(this.roleId!, req);
    call.subscribe({
      next: saved => {
        this.saving.set(false);
        if (this.isNew) {
          this.router.navigate(['/v2/user-management/roles', saved.id]);
        } else {
          this.successMsg.set('Saved successfully');
        }
      },
      error: () => { this.saveError.set('Failed to save'); this.saving.set(false); }
    });
  }

  back() {
    this.router.navigate(['/v2/user-management/roles']);
  }
}
