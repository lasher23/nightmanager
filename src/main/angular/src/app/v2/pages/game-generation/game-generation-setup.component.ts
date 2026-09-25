import {Component, inject, OnInit, signal} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {TournamentService} from '../../../service/tournament.service';
import {GameGenerationService} from '../../../service/game-generation.service';
import {Tournament} from '../../../model/Tournament';
import {Category, CATEGORY_TYPE_LABELS} from '../../../model/Category';
import {ProposalSummary} from '../../../model/GameGenerationProposal';

@Component({
  selector: 'nm-game-generation-setup',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="max-w-4xl mx-auto p-4">
      <h1 class="text-3xl font-bold mb-6">Spielplan generieren</h1>

      <div class="form-control mb-6 max-w-sm">
        <label class="label"><span class="label-text">Turnier</span></label>
        <select class="select select-bordered" [(ngModel)]="selectedTournamentId" (ngModelChange)="onTournamentChange()">
          <option [ngValue]="null">Bitte wählen...</option>
          @for (t of tournaments(); track t.id) {
            <option [ngValue]="t.id">{{ t.name }}</option>
          }
        </select>
      </div>

      @if (selectedTournamentId) {
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div class="card bg-base-100 shadow">
            <div class="card-body">
              <h2 class="card-title">Neuer Vorschlag</h2>
              @if (categories().length === 0) {
                <div class="alert alert-warning">Keine offenen Kategorien (ohne Spiele) für dieses Turnier gefunden.</div>
              } @else {
                <label class="label"><span class="label-text">Kategorien</span></label>
                <div class="space-y-1 max-h-64 overflow-y-auto border border-base-300 rounded-lg p-2">
                  @for (c of categories(); track c.id) {
                    <label class="flex items-center gap-2 cursor-pointer">
                      <input type="checkbox" class="checkbox checkbox-sm"
                             [checked]="selectedCategoryIds().includes(c.id)"
                             (change)="toggleCategory(c.id)" />
                      <span>{{ c.name }}</span>
                      <span class="badge badge-ghost badge-sm">{{ categoryTypeLabels[c.type] ?? c.type }}</span>
                    </label>
                  }
                </div>

                <label class="label mt-3"><span class="label-text">Startzeit</span></label>
                <input type="datetime-local" class="input input-bordered" [(ngModel)]="startTime" />

                <label class="label mt-3"><span class="label-text">Name (optional)</span></label>
                <input type="text" class="input input-bordered" [(ngModel)]="proposalName" placeholder="z.B. Spielplan Samstag" />

                @if (error()) {
                  <div class="alert alert-error mt-3">{{ error() }}</div>
                }

                <div class="card-actions justify-end mt-4">
                  <button class="btn btn-primary" [disabled]="proposing() || selectedCategoryIds().length === 0 || !startTime"
                          (click)="propose()">
                    @if (proposing()) { <span class="loading loading-spinner loading-sm"></span> }
                    Vorschlag erstellen
                  </button>
                </div>
              }
            </div>
          </div>

          <div class="card bg-base-100 shadow">
            <div class="card-body">
              <h2 class="card-title">Gespeicherte Vorschläge</h2>
              @if (proposals().length === 0) {
                <p class="text-sm text-gray-400">Noch keine Vorschläge vorhanden.</p>
              } @else {
                <ul class="divide-y divide-base-300">
                  @for (p of proposals(); track p.id) {
                    <li class="py-2 flex items-center justify-between gap-2">
                      <div>
                        <p class="font-semibold">{{ p.name }}</p>
                        <p class="text-xs text-gray-400">
                          {{ p.gameCount }} Spiele ·
                          <span class="badge badge-sm" [class.badge-success]="p.status === 'COMMITTED'" [class.badge-ghost]="p.status === 'DRAFT'">
                            {{ p.status === 'COMMITTED' ? 'Umgesetzt' : 'Entwurf' }}
                          </span>
                        </p>
                      </div>
                      <div class="flex gap-2">
                        <button class="btn btn-sm btn-outline" (click)="open(p.id)">Öffnen</button>
                        <button class="btn btn-sm btn-ghost text-error" (click)="remove(p.id)">✕</button>
                      </div>
                    </li>
                  }
                </ul>
              }
            </div>
          </div>
        </div>
      }
    </div>
  `
})
export class GameGenerationSetupComponent implements OnInit {
  private tournamentService = inject(TournamentService);
  private gameGenerationService = inject(GameGenerationService);
  private router = inject(Router);

  tournaments = signal<Tournament[]>([]);
  categories = signal<Category[]>([]);
  proposals = signal<ProposalSummary[]>([]);
  selectedCategoryIds = signal<number[]>([]);
  categoryTypeLabels = CATEGORY_TYPE_LABELS as Record<string, string>;

  selectedTournamentId: number | null = null;
  startTime = '';
  proposalName = '';
  proposing = signal(false);
  error = signal<string | null>(null);

  async ngOnInit(): Promise<void> {
    this.tournaments.set(await this.tournamentService.getAll());
    const now = new Date();
    now.setMinutes(0, 0, 0);
    now.setHours(now.getHours() + 1);
    this.startTime = this.toLocalDateTimeInput(now);
  }

  async onTournamentChange(): Promise<void> {
    this.selectedCategoryIds.set([]);
    if (!this.selectedTournamentId) {
      this.categories.set([]);
      this.proposals.set([]);
      return;
    }
    const [categories, proposals] = await Promise.all([
      this.gameGenerationService.getEligibleCategories(this.selectedTournamentId),
      this.gameGenerationService.listProposals(this.selectedTournamentId),
    ]);
    this.categories.set(categories);
    this.proposals.set(proposals);
  }

  toggleCategory(id: number): void {
    const current = this.selectedCategoryIds();
    this.selectedCategoryIds.set(
      current.includes(id) ? current.filter(x => x !== id) : [...current, id]
    );
  }

  async propose(): Promise<void> {
    if (!this.selectedTournamentId) return;
    this.proposing.set(true);
    this.error.set(null);
    try {
      const proposal = await this.gameGenerationService.propose(this.selectedTournamentId, {
        name: this.proposalName || null,
        categoryIds: this.selectedCategoryIds(),
        startTime: this.startTime,
      });
      await this.router.navigate(['/v2/games/generate', proposal.id]);
    } catch (e: any) {
      this.error.set(e?.message ?? 'Fehler beim Erstellen des Vorschlags');
    } finally {
      this.proposing.set(false);
    }
  }

  async open(id: number): Promise<void> {
    await this.router.navigate(['/v2/games/generate', id]);
  }

  async remove(id: number): Promise<void> {
    if (!this.selectedTournamentId) return;
    await this.gameGenerationService.deleteProposal(id);
    this.proposals.set(await this.gameGenerationService.listProposals(this.selectedTournamentId));
  }

  private toLocalDateTimeInput(date: Date): string {
    const pad = (n: number) => String(n).padStart(2, '0');
    return `${date.getFullYear()}-${pad(date.getMonth() + 1)}-${pad(date.getDate())}T${pad(date.getHours())}:${pad(date.getMinutes())}`;
  }
}
