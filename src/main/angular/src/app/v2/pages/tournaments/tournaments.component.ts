import {Component, inject, signal} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {TournamentService} from '../../../service/tournament.service';
import {TournamentStore} from '../../../service/tournament-store.service';
import {Tournament, TournamentState, TOURNAMENT_STATE_LABELS} from '../../../model/Tournament';

@Component({
  selector: 'nm-tournaments',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="max-w-4xl mx-auto p-4">
      <div class="flex items-center justify-between mb-6">
        <h1 class="text-3xl font-bold">Turniere</h1>
        <button class="btn btn-primary btn-sm" (click)="openCreate()">+ Neues Turnier</button>
      </div>

      @if (loading()) {
        <div class="flex justify-center py-8"><span class="loading loading-spinner loading-lg"></span></div>
      } @else if (error()) {
        <div class="alert alert-error">{{ error() }}</div>
      } @else if (tournaments().length === 0) {
        <div class="text-center py-12 text-gray-500">Noch keine Turniere vorhanden.</div>
      } @else {
        <div class="space-y-3">
          @for (t of tournaments(); track t.id) {
            <div class="card bg-base-100 shadow" [class.ring-2]="(activeTournament$ | async)?.id === t.id" [class.ring-primary]="(activeTournament$ | async)?.id === t.id">
              <div class="card-body py-3">
                <div class="flex items-start justify-between gap-2">
                  <div class="flex-1 min-w-0">
                    <div class="flex items-center gap-2 flex-wrap">
                      <h3 class="font-bold text-lg">{{ t.name }}</h3>
                      <span class="badge badge-sm" [ngClass]="stateBadgeClass(t.state)">{{ stateLabel(t.state) }}</span>
                      @if ((activeTournament$ | async)?.id === t.id) {
                        <span class="badge badge-primary badge-sm">Aktiv</span>
                      }
                    </div>
                    @if (t.dateFrom || t.dateTo) {
                      <div class="text-sm text-gray-500 mt-1">
                        {{ t.dateFrom ?? '?' }} – {{ t.dateTo ?? '?' }}
                      </div>
                    }
                  </div>
                  <div class="flex gap-2 flex-shrink-0">
                    <button class="btn btn-sm btn-outline" (click)="tournamentStore.setActive(t)" title="Als aktives Turnier setzen">
                      Auswählen
                    </button>
                    <button class="btn btn-sm btn-outline" (click)="openEdit(t)">Bearbeiten</button>
                    <button class="btn btn-sm btn-error btn-outline" (click)="deleteTarget.set(t)">Löschen</button>
                  </div>
                </div>
              </div>
            </div>
          }
        </div>
      }

      <!-- Create / Edit modal -->
      @if (editTarget()) {
        <dialog class="modal modal-open">
          <div class="modal-box">
            <h3 class="font-bold text-lg">{{ editTarget()!.id ? 'Turnier bearbeiten' : 'Neues Turnier' }}</h3>
            <div class="form-control mt-4 gap-3">
              <label class="label"><span class="label-text">Name</span></label>
              <input class="input input-bordered w-full" [(ngModel)]="editTarget()!.name" placeholder="z.B. Night 2026" />

              <label class="label"><span class="label-text">Von</span></label>
              <input type="date" class="input input-bordered w-full" [(ngModel)]="editTarget()!.dateFrom" />

              <label class="label"><span class="label-text">Bis</span></label>
              <input type="date" class="input input-bordered w-full" [(ngModel)]="editTarget()!.dateTo" />

              <label class="label"><span class="label-text">Status</span></label>
              <select class="select select-bordered w-full" [(ngModel)]="editTarget()!.state">
                @for (s of stateOptions; track s.value) {
                  <option [value]="s.value">{{ s.label }}</option>
                }
              </select>
            </div>
            <div class="modal-action">
              <button class="btn btn-ghost" (click)="editTarget.set(null)">Abbrechen</button>
              <button class="btn btn-primary" [disabled]="saving()" (click)="save()">
                @if (saving()) { <span class="loading loading-spinner loading-sm"></span> }
                Speichern
              </button>
            </div>
          </div>
          <div class="modal-backdrop" (click)="editTarget.set(null)"></div>
        </dialog>
      }

      <!-- Delete confirmation modal -->
      @if (deleteTarget()) {
        <dialog class="modal modal-open">
          <div class="modal-box">
            <h3 class="font-bold text-lg">Turnier "{{ deleteTarget()!.name }}" löschen?</h3>
            <p class="py-2 text-sm text-gray-500">Kategorien dieses Turniers werden nicht gelöscht, aber die Turnier-Zuordnung wird aufgehoben.</p>
            <div class="modal-action">
              <button class="btn btn-ghost" (click)="deleteTarget.set(null)">Abbrechen</button>
              <button class="btn btn-error" [disabled]="deleting()" (click)="doDelete()">
                @if (deleting()) { <span class="loading loading-spinner loading-sm"></span> }
                Löschen
              </button>
            </div>
          </div>
          <div class="modal-backdrop" (click)="deleteTarget.set(null)"></div>
        </dialog>
      }
    </div>
  `
})
export class TournamentsComponent {
  private service = inject(TournamentService);
  tournamentStore = inject(TournamentStore);
  activeTournament$ = this.tournamentStore.active$;

  tournaments = signal<Tournament[]>([]);
  loading = signal(true);
  error = signal<string | null>(null);
  editTarget = signal<Partial<Tournament> | null>(null);
  deleteTarget = signal<Tournament | null>(null);
  saving = signal(false);
  deleting = signal(false);

  stateOptions = Object.values(TournamentState).map(v => ({value: v, label: TOURNAMENT_STATE_LABELS[v]}));

  constructor() {
    this.loadAll();
  }

  private async loadAll(): Promise<void> {
    this.loading.set(true);
    this.error.set(null);
    try {
      this.tournaments.set(await this.service.getAll());
    } catch (e: any) {
      this.error.set(e?.message ?? 'Fehler beim Laden');
    } finally {
      this.loading.set(false);
    }
  }

  openCreate(): void {
    this.editTarget.set({name: '', dateFrom: null, dateTo: null, state: TournamentState.DRAFT});
  }

  openEdit(t: Tournament): void {
    this.editTarget.set({...t});
  }

  async save(): Promise<void> {
    const target = this.editTarget();
    if (!target?.name?.trim()) return;
    this.saving.set(true);
    try {
      if (target.id) {
        await this.service.update(target as Tournament);
      } else {
        await this.service.create(target);
      }
      this.editTarget.set(null);
      await this.loadAll();
      await this.tournamentStore.reload();
    } catch (e: any) {
      this.error.set(e?.message ?? 'Fehler beim Speichern');
    } finally {
      this.saving.set(false);
    }
  }

  async doDelete(): Promise<void> {
    const target = this.deleteTarget();
    if (!target) return;
    this.deleting.set(true);
    try {
      await this.service.delete(target.id);
      this.deleteTarget.set(null);
      await this.loadAll();
      await this.tournamentStore.reload();
    } catch (e: any) {
      this.error.set(e?.message ?? 'Fehler beim Löschen');
    } finally {
      this.deleting.set(false);
    }
  }

  stateLabel(state: TournamentState): string {
    return TOURNAMENT_STATE_LABELS[state] ?? state;
  }

  stateBadgeClass(state: TournamentState): string {
    switch (state) {
      case TournamentState.DRAFT:             return 'badge-ghost';
      case TournamentState.REGISTRATION_OPEN: return 'badge-info';
      case TournamentState.IN_PROGRESS:       return 'badge-warning';
      case TournamentState.DONE:              return 'badge-success';
      default:                                return 'badge-outline';
    }
  }
}
