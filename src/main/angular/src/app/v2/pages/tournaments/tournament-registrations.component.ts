import {Component, inject, signal} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {RouterLink} from '@angular/router';
import {TournamentStore} from '../../../service/tournament-store.service';
import {RegistrationGroupService} from '../../../service/registration-group.service';
import {RegistrationRequestService} from '../../../service/registration-request.service';
import {RegistrationGroup} from '../../../model/RegistrationGroup';
import {RegistrationRequest, STATUS_LABELS} from '../../../model/RegistrationRequest';

@Component({
  selector: 'nm-tournament-registrations',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterLink],
  template: `
    <div class="max-w-5xl mx-auto p-4">
      <div class="flex items-center justify-between mb-6">
        <div>
          <h1 class="text-3xl font-bold">Anmeldungen</h1>
          <p class="text-sm text-gray-500 mt-1">Turnier: {{ (activeTournament$ | async)?.name }}</p>
        </div>
        <button class="btn btn-primary btn-sm" (click)="openCreateGroup()">+ Anmeldegruppe</button>
      </div>

      @if (loading()) {
        <div class="flex justify-center py-8"><span class="loading loading-spinner loading-lg"></span></div>
      } @else if (error()) {
        <div class="alert alert-error">{{ error() }}</div>
      } @else if (groups().length === 0) {
        <div class="text-center py-12 text-gray-500">Noch keine Anmeldegruppen vorhanden. Füge zuerst eine Gruppe hinzu.</div>
      } @else {
        @for (group of groups(); track group.id) {
          <div class="card bg-base-100 shadow mb-6">
            <div class="card-body">
              <div class="flex items-center justify-between mb-3">
                <div class="flex items-center gap-3">
                  <h2 class="text-xl font-bold">{{ group.name }}</h2>
                  @if (group.requiresAge) {
                    <span class="badge badge-info badge-sm">Altersangabe erforderlich</span>
                  }
                </div>
                <div class="flex gap-2">
                  <a [routerLink]="['/v2/tournaments/generate', group.id]" class="btn btn-sm btn-outline btn-success">
                    Kategorien generieren
                  </a>
                  <button class="btn btn-sm btn-ghost" (click)="openEditGroup(group)">Bearbeiten</button>
                  <button class="btn btn-sm btn-error btn-outline" (click)="deleteGroupTarget.set(group)">Löschen</button>
                </div>
              </div>

              @if (requestsByGroup()[group.id]?.length) {
                <div class="overflow-x-auto">
                  <table class="table table-sm w-full">
                    <thead>
                      <tr>
                        <th>Teamname</th>
                        <th>Kontakt</th>
                        @if (group.requiresAge) { <th>Geburtstage</th> }
                        <th>Eingereicht</th>
                        <th>Status</th>
                        <th class="text-right">Aktionen</th>
                      </tr>
                    </thead>
                    <tbody>
                      @for (req of requestsByGroup()[group.id]; track req.id) {
                        <tr>
                          <td class="font-medium">{{ req.teamName }}</td>
                          <td class="text-sm text-gray-500">{{ req.contactEmail }}</td>
                          @if (group.requiresAge) {
                            <td class="text-xs text-gray-500">
                              {{ req.memberBirthdays?.join(', ') || '–' }}
                            </td>
                          }
                          <td class="text-xs text-gray-400">{{ req.createdAt | date:'dd.MM.yy HH:mm' }}</td>
                          <td>
                            <span class="badge badge-sm" [ngClass]="statusBadge(req.status)">
                              {{ statusLabel(req.status) }}
                            </span>
                          </td>
                          <td class="text-right">
                            <div class="flex gap-1 justify-end">
                              @if (req.status !== 'APPROVED') {
                                <button class="btn btn-xs btn-success btn-outline" (click)="approve(req)">✓</button>
                              }
                              @if (req.status !== 'REJECTED') {
                                <button class="btn btn-xs btn-error btn-outline" (click)="reject(req)">✗</button>
                              }
                            </div>
                          </td>
                        </tr>
                      }
                    </tbody>
                  </table>
                </div>
              } @else {
                <p class="text-sm text-gray-400 text-center py-4">Noch keine Anmeldungen in dieser Gruppe.</p>
              }
            </div>
          </div>
        }
      }
    </div>

    <!-- Create / Edit group modal -->
    @if (editGroupTarget()) {
      <dialog class="modal modal-open">
        <div class="modal-box">
          <h3 class="font-bold text-lg">{{ editGroupTarget()!.id ? 'Gruppe bearbeiten' : 'Neue Anmeldegruppe' }}</h3>
          <div class="flex flex-col gap-4 mt-4">
            <div>
              <label class="label"><span class="label-text">Name</span></label>
              <input class="input input-bordered w-full" [(ngModel)]="editGroupTarget()!.name" placeholder="z.B. Herren, Damen, Mixed..." />
            </div>
            <label class="cursor-pointer label">
              <span class="label-text">Altersangabe erforderlich</span>
              <input type="checkbox" class="toggle toggle-info" [(ngModel)]="editGroupTarget()!.requiresAge" />
            </label>
          </div>
          <div class="modal-action">
            <button class="btn btn-ghost" (click)="editGroupTarget.set(null)">Abbrechen</button>
            <button class="btn btn-primary" [disabled]="savingGroup()" (click)="saveGroup()">
              @if (savingGroup()) { <span class="loading loading-spinner loading-sm"></span> }
              Speichern
            </button>
          </div>
        </div>
        <div class="modal-backdrop" (click)="editGroupTarget.set(null)"></div>
      </dialog>
    }

    <!-- Delete group confirmation -->
    @if (deleteGroupTarget()) {
      <dialog class="modal modal-open">
        <div class="modal-box">
          <h3 class="font-bold text-lg">Gruppe "{{ deleteGroupTarget()!.name }}" löschen?</h3>
          <p class="text-sm text-gray-500 py-2">Alle Anmeldungen dieser Gruppe werden ebenfalls gelöscht.</p>
          <div class="modal-action">
            <button class="btn btn-ghost" (click)="deleteGroupTarget.set(null)">Abbrechen</button>
            <button class="btn btn-error" [disabled]="deletingGroup()" (click)="doDeleteGroup()">
              @if (deletingGroup()) { <span class="loading loading-spinner loading-sm"></span> }
              Löschen
            </button>
          </div>
        </div>
        <div class="modal-backdrop" (click)="deleteGroupTarget.set(null)"></div>
      </dialog>
    }
  `
})
export class TournamentRegistrationsComponent {
  private tournamentStore = inject(TournamentStore);
  private groupService = inject(RegistrationGroupService);
  private requestService = inject(RegistrationRequestService);

  activeTournament$ = this.tournamentStore.active$;

  groups = signal<RegistrationGroup[]>([]);
  requestsByGroup = signal<Record<number, RegistrationRequest[]>>({});
  loading = signal(true);
  error = signal<string | null>(null);

  editGroupTarget = signal<Partial<RegistrationGroup> | null>(null);
  deleteGroupTarget = signal<RegistrationGroup | null>(null);
  savingGroup = signal(false);
  deletingGroup = signal(false);

  constructor() {
    this.tournamentStore.active$.subscribe(t => {
      if (t) this.loadAll(t.id);
    });
  }

  private async loadAll(tournamentId: number): Promise<void> {
    this.loading.set(true);
    this.error.set(null);
    try {
      const [groups, requests] = await Promise.all([
        this.groupService.getByTournament(tournamentId),
        this.requestService.getByTournament(tournamentId),
      ]);
      this.groups.set(groups);
      const byGroup: Record<number, RegistrationRequest[]> = {};
      for (const r of requests) {
        const gid = r.registrationGroup.id;
        if (!byGroup[gid]) byGroup[gid] = [];
        byGroup[gid].push(r);
      }
      this.requestsByGroup.set(byGroup);
    } catch (e: any) {
      this.error.set(e?.message ?? 'Fehler beim Laden');
    } finally {
      this.loading.set(false);
    }
  }

  openCreateGroup(): void {
    this.editGroupTarget.set({name: '', requiresAge: false} as any);
  }

  openEditGroup(g: RegistrationGroup): void {
    this.editGroupTarget.set({...g});
  }

  async saveGroup(): Promise<void> {
    const t = this.tournamentStore.getActiveTournamentId();
    const target = this.editGroupTarget();
    if (!target?.name?.trim() || !t) return;
    this.savingGroup.set(true);
    try {
      if ((target as any).id) {
        await this.groupService.update((target as any).id, target);
      } else {
        await this.groupService.create(t, target);
      }
      this.editGroupTarget.set(null);
      await this.loadAll(t);
    } catch (e: any) {
      this.error.set(e?.message ?? 'Fehler beim Speichern');
    } finally {
      this.savingGroup.set(false);
    }
  }

  async doDeleteGroup(): Promise<void> {
    const target = this.deleteGroupTarget();
    const t = this.tournamentStore.getActiveTournamentId();
    if (!target || !t) return;
    this.deletingGroup.set(true);
    try {
      await this.groupService.delete(target.id);
      this.deleteGroupTarget.set(null);
      await this.loadAll(t);
    } catch (e: any) {
      this.error.set(e?.message ?? 'Fehler beim Löschen');
    } finally {
      this.deletingGroup.set(false);
    }
  }

  async approve(req: RegistrationRequest): Promise<void> {
    try {
      const updated = await this.requestService.approve(req.id);
      this.updateRequest(updated);
    } catch (e: any) {
      this.error.set(e?.message ?? 'Fehler');
    }
  }

  async reject(req: RegistrationRequest): Promise<void> {
    try {
      const updated = await this.requestService.reject(req.id);
      this.updateRequest(updated);
    } catch (e: any) {
      this.error.set(e?.message ?? 'Fehler');
    }
  }

  private updateRequest(updated: RegistrationRequest): void {
    const map = {...this.requestsByGroup()};
    const gid = updated.registrationGroup.id;
    if (map[gid]) {
      map[gid] = map[gid].map(r => r.id === updated.id ? updated : r);
    }
    this.requestsByGroup.set(map);
  }

  statusLabel(status: string): string {
    return STATUS_LABELS[status as keyof typeof STATUS_LABELS] ?? status;
  }

  statusBadge(status: string): string {
    return status === 'APPROVED' ? 'badge-success' : status === 'REJECTED' ? 'badge-error' : 'badge-warning';
  }
}
