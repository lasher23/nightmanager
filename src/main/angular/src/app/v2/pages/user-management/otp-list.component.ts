import { Component, inject, signal, AfterViewChecked } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { OtpUserService, OtpUserDto, CreateOtpUserRequest } from './otp-user.service';
import { UserManagementService, RoleDto } from './user-management.service';
import QRCode from 'qrcode';

@Component({
  selector: 'nm-otp-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div>
      <div class="flex justify-end mb-4">
        <button class="btn btn-primary btn-sm" (click)="openCreate()">+ New OTP User</button>
      </div>

      @if (loading()) {
        <div class="flex justify-center py-8"><span class="loading loading-spinner loading-lg"></span></div>
      } @else if (error()) {
        <div class="alert alert-error">{{ error() }}</div>
      } @else if (otpUsers().length === 0) {
        <div class="text-center text-base-content/50 py-8">No OTP users yet. Create one to generate a QR code login.</div>
      } @else {
        <div class="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 gap-4">
          @for (user of otpUsers(); track user.id) {
            <div class="card bg-base-100 shadow border-2"
                 [class.border-success]="!user.expired"
                 [class.border-error]="user.expired"
                 [class.opacity-60]="user.expired">
              <div class="card-body p-4">
                <div class="flex items-start justify-between gap-2">
                  <div>
                    <h3 class="font-bold text-lg">{{ user.name }}</h3>
                    <div class="flex flex-wrap gap-1 mt-1">
                      @for (role of user.roles; track role) {
                        <span class="badge badge-outline badge-sm">{{ role }}</span>
                      }
                    </div>
                    <p class="text-xs text-base-content/50 mt-1">
                      Valid until: {{ user.validUntil | date:'dd.MM.yyyy HH:mm' }}
                    </p>
                    @if (user.expired) {
                      <span class="badge badge-error badge-sm mt-1">Expired</span>
                    } @else {
                      <span class="badge badge-success badge-sm mt-1">Active</span>
                    }
                  </div>
                  <div class="flex flex-col gap-1 shrink-0">
                    <button class="btn btn-xs btn-outline" (click)="printCard(user)">🖨 Print</button>
                    <button class="btn btn-xs btn-error btn-outline" (click)="confirmDelete(user)">Delete</button>
                  </div>
                </div>

                <!-- QR Code -->
                <div class="flex flex-col items-center mt-3 gap-2">
                  <canvas [attr.data-token]="user.token" class="otp-qr-canvas rounded border border-base-300" width="180" height="180"></canvas>
                  <p class="text-xs text-base-content/40 break-all text-center">{{ user.token }}</p>
                  <button class="btn btn-xs btn-ghost" (click)="copyToken(user.token)">📋 Copy token</button>
                </div>
              </div>
            </div>
          }
        </div>
      }

      <!-- Create modal -->
      @if (showCreateModal()) {
        <dialog class="modal modal-open">
          <div class="modal-box max-w-md">
            <h3 class="font-bold text-lg mb-4">New OTP User</h3>

            @if (createError()) {
              <div class="alert alert-error text-sm mb-3">{{ createError() }}</div>
            }

            <div class="form-control w-full mb-3">
              <label class="label"><span class="label-text">Name <span class="text-error">*</span></span></label>
              <input type="text" [(ngModel)]="newName" placeholder="e.g. Referee Station 1"
                     class="input input-bordered w-full" />
              <label class="label"><span class="label-text-alt text-base-content/50">Must be unique</span></label>
            </div>

            <div class="form-control w-full mb-3">
              <label class="label"><span class="label-text">Valid until <span class="text-error">*</span></span></label>
              <input type="datetime-local" [(ngModel)]="newValidUntil"
                     class="input input-bordered w-full" />
            </div>

            <div class="form-control w-full mb-3">
              <label class="label"><span class="label-text">Roles</span></label>
              @if (rolesLoading()) {
                <div class="flex items-center gap-2 py-2">
                  <span class="loading loading-spinner loading-xs"></span>
                  <span class="text-sm text-base-content/50">Loading roles…</span>
                </div>
              } @else {
                <div class="border border-base-300 rounded-lg p-2 max-h-48 overflow-y-auto flex flex-col gap-1">
                  @for (role of availableRoles(); track role.name) {
                    <label class="flex items-center gap-2 cursor-pointer hover:bg-base-200 rounded px-2 py-1">
                      <input type="checkbox" class="checkbox checkbox-sm"
                             [checked]="selectedRoles.has(role.name)"
                             (change)="toggleRole(role.name, $event)" />
                      <span class="text-sm font-medium">{{ role.name }}</span>
                    </label>
                  }
                </div>
                @if (availableRoles().length === 0) {
                  <p class="text-xs text-base-content/40 mt-1">No roles found.</p>
                }
              }
            </div>

            <div class="modal-action">
              <button class="btn btn-ghost" (click)="closeCreate()">Cancel</button>
              <button class="btn btn-primary" [disabled]="creating()" (click)="doCreate()">
                @if (creating()) { <span class="loading loading-spinner loading-sm"></span> }
                Create
              </button>
            </div>
          </div>
          <div class="modal-backdrop" (click)="closeCreate()"></div>
        </dialog>
      }

      <!-- Delete confirmation modal -->
      @if (deleteTarget()) {
        <dialog class="modal modal-open">
          <div class="modal-box">
            <h3 class="font-bold text-lg">Delete OTP user "{{ deleteTarget()!.name }}"?</h3>
            <p class="py-2 text-sm text-base-content/60">
              This will immediately invalidate the QR code and token for this user.
            </p>
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
export class OtpListComponent implements AfterViewChecked {
  private service     = inject(OtpUserService);
  private userMgmtSvc = inject(UserManagementService);

  otpUsers = signal<OtpUserDto[]>([]);
  loading  = signal(true);
  error    = signal<string | null>(null);

  showCreateModal  = signal(false);
  creating         = signal(false);
  createError      = signal<string | null>(null);
  newName          = '';
  newValidUntil    = '';
  availableRoles   = signal<RoleDto[]>([]);
  rolesLoading     = signal(false);
  selectedRoles    = new Set<string>();

  deleteTarget = signal<OtpUserDto | null>(null);
  deleting     = signal(false);

  private renderedTokens = new Set<string>();

  constructor() {
    this.load();
  }

  ngAfterViewChecked() {
    const canvases = document.querySelectorAll<HTMLCanvasElement>('canvas.otp-qr-canvas');
    canvases.forEach(canvas => {
      const token = canvas.getAttribute('data-token');
      if (token && !this.renderedTokens.has(token)) {
        this.renderedTokens.add(token);
        const url = this.service.loginUrl(token);
        QRCode.toCanvas(canvas, url, { width: 180, margin: 1 }).catch(console.error);
      }
    });
  }

  private load() {
    this.loading.set(true);
    this.service.getAll().subscribe({
      next: users => {
        this.otpUsers.set(users);
        this.loading.set(false);
        this.renderedTokens.clear();
      },
      error: () => { this.error.set('Failed to load OTP users'); this.loading.set(false); }
    });
  }

  openCreate() {
    this.newName = '';
    this.newValidUntil = '';
    this.selectedRoles = new Set<string>(['USER']);
    this.createError.set(null);
    this.showCreateModal.set(true);
    this.rolesLoading.set(true);
    this.userMgmtSvc.getRoles().subscribe({
      next: roles => { this.availableRoles.set(roles); this.rolesLoading.set(false); },
      error: ()    => { this.availableRoles.set([]);   this.rolesLoading.set(false); }
    });
  }

  closeCreate() { this.showCreateModal.set(false); }

  toggleRole(name: string, event: Event) {
    if ((event.target as HTMLInputElement).checked) {
      this.selectedRoles.add(name);
    } else {
      this.selectedRoles.delete(name);
    }
  }

  doCreate() {
    if (!this.newName.trim()) { this.createError.set('Name is required'); return; }
    if (!this.newValidUntil)  { this.createError.set('Valid-until date is required'); return; }

    const req: CreateOtpUserRequest = {
      name: this.newName.trim(),
      validUntil: new Date(this.newValidUntil).toISOString(),
      roles: Array.from(this.selectedRoles)
    };

    this.creating.set(true);
    this.createError.set(null);
    this.service.create(req).subscribe({
      next: () => {
        this.creating.set(false);
        this.showCreateModal.set(false);
        this.load();
      },
      error: err => {
        this.creating.set(false);
        this.createError.set(err.error?.message ?? err.error ?? 'Failed to create OTP user');
      }
    });
  }

  confirmDelete(user: OtpUserDto) { this.deleteTarget.set(user); }

  doDelete() {
    const target = this.deleteTarget();
    if (!target) return;
    this.deleting.set(true);
    this.service.delete(target.id).subscribe({
      next: () => {
        this.deleting.set(false);
        this.deleteTarget.set(null);
        this.load();
      },
      error: () => { this.deleting.set(false); this.deleteTarget.set(null); }
    });
  }

  copyToken(token: string) {
    navigator.clipboard.writeText(token).catch(() => {});
  }

  async printCard(user: OtpUserDto) {
    const url = this.service.loginUrl(user.token);
    const dataUrl = await QRCode.toDataURL(url, { width: 300, margin: 2, color: { dark: '#000000', light: '#ffffff' } });
    const validUntil = new Date(user.validUntil).toLocaleString('de-CH', { dateStyle: 'short', timeStyle: 'short' });
    const rolesHtml = user.roles.map(r => `<span class="badge">${r}</span>`).join(' ');
    const statusLabel = user.expired ? '<span class="expired">Expired</span>' : '<span class="active">Active</span>';

    const win = window.open('', '_blank', 'width=500,height=650');
    if (!win) return;
    win.document.write(`<!DOCTYPE html>
<html lang="de">
<head>
  <meta charset="UTF-8">
  <title>OTP Login – ${user.name}</title>
  <style>
    * { box-sizing: border-box; margin: 0; padding: 0; }
    body {
      font-family: system-ui, sans-serif;
      background: #fff;
      color: #111;
      display: flex;
      justify-content: center;
      align-items: flex-start;
      padding: 32px 24px;
    }
    .card {
      width: 360px;
      border: 2px solid #d1d5db;
      border-radius: 12px;
      padding: 24px;
      text-align: center;
    }
    .app-title {
      font-size: 13px;
      text-transform: uppercase;
      letter-spacing: .1em;
      color: #6b7280;
      margin-bottom: 4px;
    }
    h1 { font-size: 22px; font-weight: 700; margin-bottom: 12px; }
    .qr { margin: 16px auto; display: block; border: 1px solid #e5e7eb; border-radius: 8px; padding: 8px; }
    .section { margin: 10px 0; font-size: 13px; color: #374151; }
    .label { font-size: 11px; text-transform: uppercase; letter-spacing: .05em; color: #9ca3af; margin-bottom: 2px; }
    .badge {
      display: inline-block;
      background: #f3f4f6;
      border: 1px solid #d1d5db;
      border-radius: 999px;
      padding: 1px 8px;
      font-size: 11px;
      margin: 2px;
    }
    .active  { color: #16a34a; font-weight: 600; }
    .expired { color: #dc2626; font-weight: 600; }
    .token {
      font-family: monospace;
      font-size: 10px;
      color: #6b7280;
      word-break: break-all;
      background: #f9fafb;
      border: 1px solid #e5e7eb;
      border-radius: 6px;
      padding: 6px 8px;
      margin-top: 8px;
    }
    .divider { border: none; border-top: 1px solid #e5e7eb; margin: 14px 0; }
    @media print {
      body { padding: 0; }
      .card { border-color: #000; }
    }
  </style>
</head>
<body>
  <div class="card">
    <p class="app-title">🏑 Nightmanager</p>
    <h1>${user.name}</h1>
    <hr class="divider">
    <p class="label">Scan to login</p>
    <img class="qr" src="${dataUrl}" width="240" height="240" alt="QR Code">
    <hr class="divider">
    <div class="section">
      <p class="label">Roles</p>
      ${rolesHtml}
    </div>
    <div class="section">
      <p class="label">Valid until</p>
      <strong>${validUntil}</strong> &nbsp; ${statusLabel}
    </div>
    <div class="token">${user.token}</div>
  </div>
  <script>window.onload = () => { window.print(); }<\/script>
</body>
</html>`);
    win.document.close();
  }
}
