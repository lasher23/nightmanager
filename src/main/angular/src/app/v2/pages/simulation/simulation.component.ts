import {Component, inject, OnInit, signal} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {SimulationService} from '../../../service/simulation.service';
import {CategoryType, CATEGORY_TYPE_LABELS} from '../../../model/Category';
import {SimulationCategory, SimulationCategorySpec, SimulationTournamentSummary} from '../../../model/Simulation';

interface DraftCategoryRow {
  name: string;
  type: CategoryType;
  teamCount: number;
}

@Component({
  selector: 'nm-simulation',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="max-w-4xl mx-auto p-4">
      <h1 class="text-3xl font-bold mb-2">Simulation</h1>
      <p class="text-sm text-gray-500 mb-6">
        Testdaten (Turnier, Kategorien &amp; Teams) erzeugen, um die Spielplan-Generierung ohne echte Anmeldungen auszuprobieren.
      </p>

      @if (error()) {
        <div class="alert alert-error mb-4">{{ error() }}</div>
      }

      @if (!activeTournament()) {
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div class="card bg-base-100 shadow">
            <div class="card-body">
              <h2 class="card-title">Neues Simulations-Turnier</h2>
              <label class="label"><span class="label-text">Name</span></label>
              <input class="input input-bordered" [(ngModel)]="newTournamentName" placeholder="z.B. Simulation Nachtturnier" />
              <div class="card-actions justify-end mt-4">
                <button class="btn btn-primary" [disabled]="creating()" (click)="createTournament()">
                  @if (creating()) { <span class="loading loading-spinner loading-sm"></span> }
                  Erstellen &amp; starten
                </button>
              </div>
            </div>
          </div>

          <div class="card bg-base-100 shadow">
            <div class="card-body">
              <h2 class="card-title">Vorhandene Simulationen</h2>
              @if (tournaments().length === 0) {
                <p class="text-sm text-gray-400">Noch keine Simulationen vorhanden.</p>
              } @else {
                <ul class="divide-y divide-base-300">
                  @for (t of tournaments(); track t.id) {
                    <li class="py-2 flex items-center justify-between gap-2">
                      <div>
                        <p class="font-semibold">{{ t.name }}</p>
                        <p class="text-xs text-gray-400">{{ t.categoryCount }} Kategorien</p>
                      </div>
                      <div class="flex gap-2">
                        <button class="btn btn-sm btn-outline" (click)="openTournament(t)">Öffnen</button>
                        <button class="btn btn-sm btn-ghost text-error" (click)="deleteToDelete = t">✕</button>
                      </div>
                    </li>
                  }
                </ul>
              }
            </div>
          </div>
        </div>
      } @else {
        <div class="flex items-center gap-4 mb-4">
          <button class="btn btn-ghost btn-sm" (click)="backToOverview()">← Simulationen</button>
          <h2 class="text-xl font-bold">{{ activeTournament()!.name }}</h2>
        </div>

        <div class="card bg-base-100 shadow mb-6">
          <div class="card-body">
            <h3 class="card-title text-lg">Kategorien</h3>
            @if (existingCategories().length === 0) {
              <p class="text-sm text-gray-400">Noch keine Kategorien in dieser Simulation.</p>
            } @else {
              <ul class="divide-y divide-base-300 mb-2">
                @for (c of existingCategories(); track c.id) {
                  <li class="py-1.5 flex items-center justify-between text-sm">
                    <span>{{ c.name }}</span>
                    <span class="text-gray-400">{{ categoryTypeLabels[c.type] ?? c.type }} · {{ c.teamCount }} Teams</span>
                  </li>
                }
              </ul>
            }

            <h4 class="font-semibold mt-3 mb-1 text-sm">Neue Kategorien hinzufügen</h4>
            @for (row of draftRows(); track $index; let i = $index) {
              <div class="flex items-center gap-2 mb-2">
                <input class="input input-sm input-bordered flex-1" [(ngModel)]="row.name" placeholder="Kategoriename" />
                <select class="select select-sm select-bordered" [(ngModel)]="row.type">
                  @for (type of categoryTypes; track type) {
                    <option [ngValue]="type">{{ categoryTypeLabels[type] }}</option>
                  }
                </select>
                <input type="number" class="input input-sm input-bordered w-24" min="2" [(ngModel)]="row.teamCount" placeholder="Teams" />
                <button class="btn btn-sm btn-ghost text-error" (click)="removeRow(i)">✕</button>
              </div>
            }
            <div class="flex items-center justify-between mt-2">
              <button class="btn btn-sm btn-outline" (click)="addRow()">+ Kategorie</button>
              <button class="btn btn-sm btn-primary" [disabled]="submitting() || draftRows().length === 0" (click)="submitCategories()">
                @if (submitting()) { <span class="loading loading-spinner loading-xs"></span> }
                Kategorien erstellen
              </button>
            </div>
          </div>
        </div>

        <div class="flex justify-end">
          <button class="btn btn-success" [disabled]="existingCategories().length === 0" (click)="continueToGeneration()">
            Weiter zur Spielplan-Generierung →
          </button>
        </div>
      }
    </div>

    @if (deleteToDelete) {
      <dialog class="modal modal-open">
        <div class="modal-box">
          <h3 class="font-bold text-lg text-error">Simulation löschen?</h3>
          <p class="py-3 text-sm text-gray-600">
            "{{ deleteToDelete.name }}" inkl. aller Kategorien, Teams, Spiele und Vorschläge wird unwiderruflich gelöscht.
          </p>
          <div class="modal-action">
            <button class="btn btn-ghost" (click)="deleteToDelete = null">Abbrechen</button>
            <button class="btn btn-error" (click)="confirmDelete()">Löschen</button>
          </div>
        </div>
      </dialog>
    }
  `
})
export class SimulationComponent implements OnInit {
  private simulationService = inject(SimulationService);
  private router = inject(Router);

  tournaments = signal<SimulationTournamentSummary[]>([]);
  activeTournament = signal<SimulationTournamentSummary | null>(null);
  existingCategories = signal<SimulationCategory[]>([]);
  draftRows = signal<DraftCategoryRow[]>([]);

  newTournamentName = '';
  creating = signal(false);
  submitting = signal(false);
  error = signal<string | null>(null);
  deleteToDelete: SimulationTournamentSummary | null = null;

  categoryTypes = Object.values(CategoryType);
  categoryTypeLabels = CATEGORY_TYPE_LABELS as Record<string, string>;

  async ngOnInit(): Promise<void> {
    this.tournaments.set(await this.simulationService.listTournaments());
  }

  async createTournament(): Promise<void> {
    this.creating.set(true);
    this.error.set(null);
    try {
      const tournament = await this.simulationService.createTournament(this.newTournamentName);
      this.newTournamentName = '';
      await this.openTournament({id: tournament.id, name: tournament.name, categoryCount: 0});
    } catch (e: any) {
      this.error.set(e?.message ?? 'Fehler beim Erstellen');
    } finally {
      this.creating.set(false);
    }
  }

  async openTournament(summary: SimulationTournamentSummary): Promise<void> {
    this.activeTournament.set(summary);
    this.draftRows.set([{name: '', type: CategoryType.SINGLE_CATEGORY, teamCount: 6}]);
    this.existingCategories.set(await this.simulationService.getCategories(summary.id));
  }

  backToOverview(): void {
    this.activeTournament.set(null);
    this.ngOnInit();
  }

  addRow(): void {
    this.draftRows.set([...this.draftRows(), {name: '', type: CategoryType.SINGLE_CATEGORY, teamCount: 6}]);
  }

  removeRow(index: number): void {
    this.draftRows.set(this.draftRows().filter((_, i) => i !== index));
  }

  async submitCategories(): Promise<void> {
    const tournament = this.activeTournament();
    if (!tournament) return;
    const specs: SimulationCategorySpec[] = this.draftRows()
      .filter(r => r.name.trim().length > 0 && r.teamCount >= 2)
      .map(r => ({name: r.name.trim(), type: r.type, teamCount: r.teamCount}));
    if (specs.length === 0) {
      this.error.set('Bitte mindestens eine gültige Kategorie (Name + mind. 2 Teams) angeben');
      return;
    }
    this.submitting.set(true);
    this.error.set(null);
    try {
      await this.simulationService.addCategories(tournament.id, specs);
      this.existingCategories.set(await this.simulationService.getCategories(tournament.id));
      this.draftRows.set([{name: '', type: CategoryType.SINGLE_CATEGORY, teamCount: 6}]);
    } catch (e: any) {
      this.error.set(e?.message ?? 'Fehler beim Erstellen der Kategorien');
    } finally {
      this.submitting.set(false);
    }
  }

  continueToGeneration(): void {
    const tournament = this.activeTournament();
    if (!tournament) return;
    this.router.navigate(['/v2/games/generate'], {queryParams: {tournamentId: tournament.id}});
  }

  async confirmDelete(): Promise<void> {
    if (!this.deleteToDelete) return;
    const id = this.deleteToDelete.id;
    this.deleteToDelete = null;
    await this.simulationService.deleteTournament(id);
    this.tournaments.set(await this.simulationService.listTournaments());
  }
}
