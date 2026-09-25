import {Component, computed, inject, OnInit, signal} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {CdkDragDrop, DragDropModule, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import {GameGenerationService} from '../../../service/game-generation.service';
import {HallService} from '../../../service/hall.service';
import {Hall} from '../../../model/Hall';
import {GameGenerationProposal, ProposedGame} from '../../../model/GameGenerationProposal';

interface HallBlock {
  hall: Hall;
  games: ProposedGame[];
}

@Component({
  selector: 'nm-game-generation-editor',
  standalone: true,
  imports: [CommonModule, FormsModule, DragDropModule],
  styles: [`
    .game-row.cdk-drag-placeholder { opacity: 0.3; background: #e5e7eb; }
    .cdk-drag-animating { transition: transform 200ms cubic-bezier(0, 0, 0.2, 1); }
    .swap-pending { outline: 2px solid #f59e0b; outline-offset: -2px; }
  `],
  template: `
    <div class="max-w-6xl mx-auto p-4">
      <div class="flex items-center gap-4 mb-4">
        <button class="btn btn-ghost btn-sm" (click)="back()">← Zurück</button>
        @if (proposal(); as p) {
          <div class="flex-1">
            <input class="input input-sm input-bordered font-bold w-80" [(ngModel)]="p.name" (ngModelChange)="markDirty()" />
            <span class="badge badge-sm ml-2" [class.badge-success]="p.status === 'COMMITTED'" [class.badge-ghost]="p.status === 'DRAFT'">
              {{ p.status === 'COMMITTED' ? 'Umgesetzt' : 'Entwurf' }}
            </span>
          </div>
          <button class="btn btn-sm btn-outline" [disabled]="saving() || p.status === 'COMMITTED'" (click)="save()">
            @if (saving()) { <span class="loading loading-spinner loading-xs"></span> }
            Speichern
          </button>
          <button class="btn btn-sm" [class.btn-warning]="swapMode()" [class.btn-outline]="!swapMode()"
                  [disabled]="p.status === 'COMMITTED'" (click)="toggleSwapMode()">
            {{ swapMode() ? 'Tauschen beenden' : 'Spiele tauschen' }}
          </button>
          <button class="btn btn-sm btn-error btn-outline" [disabled]="p.status === 'COMMITTED'" (click)="confirmDelete = true">Verwerfen</button>
          <button class="btn btn-sm btn-success" [disabled]="p.status === 'COMMITTED' || committing()" (click)="confirmCommit = true">
            @if (committing()) { <span class="loading loading-spinner loading-xs"></span> }
            Übernehmen
          </button>
        }
      </div>

      @if (error()) {
        <div class="alert alert-error mb-4">{{ error() }}</div>
      }

      @if (loading()) {
        <div class="flex justify-center py-8"><span class="loading loading-spinner loading-lg"></span></div>
      } @else {
        <p class="text-sm text-gray-500 mb-4">
          @if (swapMode()) {
            Tauschmodus: zwei Spiele anklicken, um Zeit &amp; Halle zu tauschen.
          } @else {
            Spiel anklicken um Teams &amp; zugehörige Spiele hervorzuheben. Spiele per Drag &amp; Drop verschieben.
          }
        </p>
        <div cdkDropListGroup class="grid grid-cols-1 lg:grid-cols-2 gap-6">
          @for (block of hallBlocks(); track block.hall.id) {
            <div class="card bg-base-100 shadow">
              <div class="card-body p-4">
                <h2 class="card-title text-xl mb-2">{{ block.hall.name }}</h2>
                <div class="overflow-x-auto">
                  <table class="table table-sm w-full">
                    <thead>
                      <tr>
                        <th>Zeit</th>
                        <th>Kat.</th>
                        <th>Heim</th>
                        <th></th>
                        <th>Gast</th>
                        <th>Typ</th>
                      </tr>
                    </thead>
                    <tbody cdkDropList
                           [id]="'hall-' + block.hall.id"
                           [cdkDropListData]="block.games"
                           [cdkDropListConnectedTo]="hallListIds()"
                           (cdkDropListDropped)="onDrop($event, block.hall.id)">
                      @for (game of block.games; track game.tempId) {
                        <tr cdkDrag [cdkDragData]="game" class="game-row cursor-grab active:cursor-grabbing"
                            [class.outline]="isSameCategory(game)"
                            [class.outline-2]="isSameCategory(game)"
                            [class.-outline-offset-2]="isSameCategory(game)"
                            [class.outline-base-300]="isSameCategory(game)"
                            [class.swap-pending]="isSwapPending(game)"
                            (click)="onRowClick(game)">
                          <td class="font-mono text-xs">{{ game.startDate | date: 'HH:mm' }}</td>
                          <td class="text-xs">{{ categoryName(game.categoryRef) }}</td>
                          <td [class.font-bold]="game.type !== 'GROUP_STAGE'" [style.background-color]="cellColor(game.teamHome.ref)">{{ game.teamHome.name }}</td>
                          <td class="text-center text-xs">:</td>
                          <td [class.font-bold]="game.type !== 'GROUP_STAGE'" [style.background-color]="cellColor(game.teamGuest.ref)">{{ game.teamGuest.name }}</td>
                          <td>
                            <span class="badge badge-xs" [class.badge-ghost]="game.type === 'GROUP_STAGE'"
                                  [class.badge-warning]="game.type === 'SEMI_FINAL'"
                                  [class.badge-success]="game.type === 'FINAL'">
                              {{ gameTypeLabel(game.type) }}
                            </span>
                          </td>
                        </tr>
                      }
                      @if (block.games.length === 0) {
                        <tr><td colspan="6" class="text-center text-xs text-gray-400 py-4">Keine Spiele</td></tr>
                      }
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          }
        </div>
      }
    </div>

    @if (confirmCommit) {
      <dialog class="modal modal-open">
        <div class="modal-box">
          <h3 class="font-bold text-lg">Spielplan übernehmen?</h3>
          <p class="py-3 text-sm text-gray-600">
            Alle Spiele, Kategorien und Platzhalter-Teams dieses Vorschlags werden endgültig angelegt.
            Dies kann nicht rückgängig gemacht werden.
          </p>
          <div class="modal-action">
            <button class="btn btn-ghost" (click)="confirmCommit = false">Abbrechen</button>
            <button class="btn btn-success" (click)="commit()">Übernehmen</button>
          </div>
        </div>
      </dialog>
    }

    @if (confirmDelete) {
      <dialog class="modal modal-open">
        <div class="modal-box">
          <h3 class="font-bold text-lg text-error">Vorschlag verwerfen?</h3>
          <p class="py-3 text-sm text-gray-600">Dieser Entwurf wird unwiderruflich gelöscht.</p>
          <div class="modal-action">
            <button class="btn btn-ghost" (click)="confirmDelete = false">Abbrechen</button>
            <button class="btn btn-error" (click)="remove()">Löschen</button>
          </div>
        </div>
      </dialog>
    }
  `
})
export class GameGenerationEditorComponent implements OnInit {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private gameGenerationService = inject(GameGenerationService);
  private hallService = inject(HallService);

  proposal = signal<GameGenerationProposal | null>(null);
  halls = signal<Hall[]>([]);
  loading = signal(true);
  saving = signal(false);
  committing = signal(false);
  error = signal<string | null>(null);
  selectedGame = signal<ProposedGame | null>(null);
  swapMode = signal(false);
  swapFirst = signal<ProposedGame | null>(null);
  dirty = false;
  confirmCommit = false;
  confirmDelete = false;

  hallBlocks = computed<HallBlock[]>(() => {
    const proposal = this.proposal();
    if (!proposal) return [];
    return this.halls().map(hall => ({
      hall,
      games: proposal.games
        .filter(g => g.hallId === hall.id)
        .sort((a, b) => new Date(a.startDate).getTime() - new Date(b.startDate).getTime()),
    }));
  });

  hallListIds = computed<string[]>(() => this.halls().map(h => 'hall-' + h.id));

  async ngOnInit(): Promise<void> {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.loading.set(true);
    try {
      const [halls, proposal] = await Promise.all([
        this.hallService.getAllHalls(),
        this.gameGenerationService.getProposal(id),
      ]);
      this.halls.set(halls);
      this.proposal.set(proposal);
    } catch (e: any) {
      this.error.set(e?.message ?? 'Fehler beim Laden');
    } finally {
      this.loading.set(false);
    }
  }

  markDirty(): void {
    this.dirty = true;
  }

  categoryName(ref: string): string {
    return this.proposal()?.categories.find(c => c.ref === ref)?.name ?? ref;
  }

  gameTypeLabel(type: string): string {
    switch (type) {
      case 'SEMI_FINAL': return 'Halbfinal';
      case 'FINAL': return 'Final';
      default: return 'Gruppe';
    }
  }

  onRowClick(game: ProposedGame): void {
    if (this.swapMode()) {
      this.handleSwapClick(game);
    } else {
      this.selectGame(game);
    }
  }

  selectGame(game: ProposedGame): void {
    const current = this.selectedGame();
    this.selectedGame.set(current?.tempId === game.tempId ? null : game);
  }

  toggleSwapMode(): void {
    this.swapMode.set(!this.swapMode());
    this.swapFirst.set(null);
  }

  isSwapPending(game: ProposedGame): boolean {
    return this.swapFirst()?.tempId === game.tempId;
  }

  handleSwapClick(game: ProposedGame): void {
    const first = this.swapFirst();
    if (!first) {
      this.swapFirst.set(game);
      return;
    }
    if (first.tempId === game.tempId) {
      this.swapFirst.set(null);
      return;
    }
    this.swapGames(first, game);
    this.swapFirst.set(null);
  }

  private swapGames(gameA: ProposedGame, gameB: ProposedGame): void {
    const proposal = this.proposal();
    if (!proposal) return;
    const a = proposal.games.find(g => g.tempId === gameA.tempId);
    const b = proposal.games.find(g => g.tempId === gameB.tempId);
    if (!a || !b) return;
    const hallId = a.hallId;
    const startDate = a.startDate;
    a.hallId = b.hallId;
    a.startDate = b.startDate;
    b.hallId = hallId;
    b.startDate = startDate;
    this.proposal.set({...proposal});
    this.dirty = true;
  }

  isSameCategory(game: ProposedGame): boolean {
    const selected = this.selectedGame();
    return !!selected && selected.categoryRef === game.categoryRef;
  }

  /** Highlight only the team-name cell matching the selected game's home/guest team. */
  cellColor(teamRef: string): string | null {
    const selected = this.selectedGame();
    if (!selected) return null;
    if (teamRef === selected.teamHome.ref) return '#dbeafe';
    if (teamRef === selected.teamGuest.ref) return '#fee2e2';
    return null;
  }

  onDrop(event: CdkDragDrop<ProposedGame[]>, targetHallId: number): void {
    const proposal = this.proposal();
    if (!proposal) return;

    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(event.previousContainer.data, event.container.data, event.previousIndex, event.currentIndex);
    }
    event.container.data.forEach(game => game.hallId = targetHallId);

    const base = new Date(proposal.startTime).getTime();
    for (const block of this.hallBlocks()) {
      block.games.forEach((game, index) => {
        game.startDate = new Date(base + index * 10 * 60 * 1000).toISOString();
      });
    }

    proposal.games = this.hallBlocks().flatMap(b => b.games);
    this.proposal.set({...proposal});
    this.dirty = true;
  }

  async save(): Promise<void> {
    const proposal = this.proposal();
    if (!proposal) return;
    this.saving.set(true);
    this.error.set(null);
    try {
      const saved = await this.gameGenerationService.updateProposal(proposal.id, proposal);
      this.proposal.set(saved);
      this.dirty = false;
    } catch (e: any) {
      this.error.set(e?.message ?? 'Fehler beim Speichern');
    } finally {
      this.saving.set(false);
    }
  }

  async commit(): Promise<void> {
    const proposal = this.proposal();
    if (!proposal) return;
    this.confirmCommit = false;
    this.committing.set(true);
    this.error.set(null);
    try {
      if (this.dirty) {
        await this.gameGenerationService.updateProposal(proposal.id, proposal);
      }
      await this.gameGenerationService.commit(proposal.id);
      await this.router.navigate(['/v2/games/generate']);
    } catch (e: any) {
      this.error.set(e?.message ?? 'Fehler beim Übernehmen');
    } finally {
      this.committing.set(false);
    }
  }

  async remove(): Promise<void> {
    const proposal = this.proposal();
    if (!proposal) return;
    this.confirmDelete = false;
    await this.gameGenerationService.deleteProposal(proposal.id);
    await this.router.navigate(['/v2/games/generate']);
  }

  back(): void {
    this.router.navigate(['/v2/games/generate']);
  }
}
