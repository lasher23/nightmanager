import {Component, inject, signal} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {CdkDragDrop, DragDropModule, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import {RegistrationGroupService} from '../../../service/registration-group.service';
import {RegistrationRequestService} from '../../../service/registration-request.service';
import {RegistrationGroup} from '../../../model/RegistrationGroup';
import {RegistrationRequest} from '../../../model/RegistrationRequest';

interface CategoryBucket {
  name: string;
  requests: RegistrationRequest[];
}

@Component({
  selector: 'nm-tournament-generate',
  standalone: true,
  imports: [CommonModule, FormsModule, DragDropModule],
  styles: [`
    .bucket-col { min-height: 120px; }
    .cdk-drag-placeholder { opacity: 0.3; background: #e5e7eb; border-radius: 0.5rem; }
    .cdk-drag-animating { transition: transform 250ms cubic-bezier(0, 0, 0.2, 1); }
    .cdk-drop-list-dragging .cdk-drag:not(.cdk-drag-placeholder) { transition: transform 250ms cubic-bezier(0, 0, 0.2, 1); }
  `],
  template: `
    <div class="max-w-7xl mx-auto p-4">
      <div class="flex items-center gap-4 mb-6">
        <button class="btn btn-ghost btn-sm" (click)="back()">← Zurück</button>
        <div>
          <h1 class="text-3xl font-bold">Kategorien generieren</h1>
          @if (group()) {
            <p class="text-sm text-gray-500 mt-1">Gruppe: {{ group()!.name }}</p>
          }
        </div>
      </div>

      @if (loadError()) {
        <div class="alert alert-error">{{ loadError() }}</div>
      } @else if (loading()) {
        <div class="flex justify-center py-8"><span class="loading loading-spinner loading-lg"></span></div>
      } @else {

        <!-- Step 1: configure -->
        @if (buckets().length === 0) {
          <div class="card bg-base-100 shadow max-w-md mx-auto">
            <div class="card-body">
              <h2 class="card-title">Anzahl Kategorien</h2>
              <p class="text-sm text-gray-500">
                {{ approvedRequests().length }} genehmigte Anmeldungen verfügbar.
              </p>
              @if (approvedRequests().length === 0) {
                <div class="alert alert-warning mt-2">
                  Keine genehmigten Anmeldungen vorhanden. Zuerst Anmeldungen genehmigen.
                </div>
              } @else {
                <div class="form-control mt-3">
                  <label class="label"><span class="label-text">Kategorienanzahl</span></label>
                  <input type="number" class="input input-bordered w-full"
                         [(ngModel)]="categoryCount" min="1" [max]="approvedRequests().length" />
                </div>
                <div class="card-actions justify-end mt-4">
                  <button class="btn btn-primary" [disabled]="proposing() || categoryCount < 1" (click)="propose()">
                    @if (proposing()) { <span class="loading loading-spinner loading-sm"></span> }
                    Vorschlag erstellen
                  </button>
                </div>
              }
            </div>
          </div>
        } @else {
          <!-- Step 2: drag-and-drop board -->
          <div class="mb-4 flex items-center justify-between">
            <p class="text-sm text-gray-500">
              Teams per Drag &amp; Drop zwischen Kategorien verschieben. Kategorienamen sind editierbar.
            </p>
            <div class="flex gap-2">
              <button class="btn btn-ghost btn-sm" (click)="resetBuckets()">↺ Zurücksetzen</button>
              <button class="btn btn-success" [disabled]="generating()" (click)="generate()">
                @if (generating()) { <span class="loading loading-spinner loading-sm"></span> }
                Kategorien erstellen
              </button>
            </div>
          </div>

          @if (generateError()) {
            <div class="alert alert-error mb-4">{{ generateError() }}</div>
          }

          <div cdkDropListGroup class="flex gap-4 overflow-x-auto pb-4">
            @for (bucket of buckets(); track $index; let i = $index) {
              <div class="flex-shrink-0 w-64">
                <div class="card bg-base-200 shadow">
                  <div class="card-body p-3">
                    <input class="input input-sm input-bordered w-full font-bold mb-2"
                           [(ngModel)]="bucket.name"
                           placeholder="Kategoriename" />
                    <span class="text-xs text-gray-400 mb-2 block">{{ bucket.requests.length }} Teams</span>
                    <div class="bucket-col space-y-2"
                         cdkDropList
                         [id]="'bucket-' + i"
                         [cdkDropListData]="bucket.requests"
                         [cdkDropListConnectedTo]="connectedLists()"
                         (cdkDropListDropped)="onDrop($event)">
                      @for (req of bucket.requests; track req.id) {
                        <div cdkDrag
                             class="card bg-base-100 shadow-sm cursor-grab active:cursor-grabbing"
                             [cdkDragData]="req">
                          <div class="card-body p-2 py-1.5">
                            <p class="font-semibold text-sm">{{ req.teamName }}</p>
                            @if (req.memberBirthdays?.length) {
                              <p class="text-xs text-gray-400">
                                Ø {{ avgAge(req) }} J. · Älteste: {{ oldestAge(req) }} J.
                              </p>
                            }
                          </div>
                        </div>
                      }
                      @if (bucket.requests.length === 0) {
                        <div class="text-xs text-gray-400 text-center py-4 border border-dashed rounded-lg">
                          Keine Teams
                        </div>
                      }
                    </div>
                  </div>
                </div>
              </div>
            }
          </div>
        }
      }
    </div>

    <!-- Success modal -->
    @if (showSuccess()) {
      <dialog class="modal modal-open">
        <div class="modal-box">
          <h3 class="font-bold text-lg text-success">Kategorien erstellt!</h3>
          <p class="py-3 text-sm text-gray-600">
            {{ buckets().length }} Kategorien wurden erfolgreich angelegt.
          </p>
          <div class="modal-action">
            <button class="btn btn-primary" (click)="back()">Zurück zur Übersicht</button>
          </div>
        </div>
      </dialog>
    }
  `
})
export class TournamentGenerateComponent {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private groupService = inject(RegistrationGroupService);
  private requestService = inject(RegistrationRequestService);

  group = signal<RegistrationGroup | null>(null);
  allRequests = signal<RegistrationRequest[]>([]);
  approvedRequests = signal<RegistrationRequest[]>([]);
  buckets = signal<CategoryBucket[]>([]);

  loading = signal(true);
  loadError = signal<string | null>(null);
  proposing = signal(false);
  generating = signal(false);
  generateError = signal<string | null>(null);
  showSuccess = signal(false);
  categoryCount = 2;

  constructor() {
    this.route.params.subscribe(async params => {
      const groupId = Number(params['groupId']);
      if (groupId) await this.init(groupId);
    });
  }

  private async init(groupId: number): Promise<void> {
    this.loading.set(true);
    this.loadError.set(null);
    try {
      const [group, requests] = await Promise.all([
        this.groupService.getById(groupId),
        this.requestService.getByGroup(groupId),
      ]);
      this.group.set(group);
      this.allRequests.set(requests);
      this.approvedRequests.set(requests.filter(r => r.status === 'APPROVED'));
      this.categoryCount = Math.min(2, this.approvedRequests().length);
    } catch (e: any) {
      this.loadError.set(e?.message ?? 'Fehler beim Laden');
    } finally {
      this.loading.set(false);
    }
  }

  connectedLists(): string[] {
    return this.buckets().map((_, i) => `bucket-${i}`);
  }

  async propose(): Promise<void> {
    const group = this.group();
    if (!group) return;
    this.proposing.set(true);
    try {
      const proposal = await this.groupService.propose(group.id, this.categoryCount);
      const requestMap = new Map(this.approvedRequests().map(r => [r.id, r]));
      const newBuckets: CategoryBucket[] = proposal.map((ids, idx) => ({
        name: `${group.name} ${String.fromCharCode(65 + idx)}`,
        requests: ids.map(id => requestMap.get(id)!).filter(Boolean),
      }));
      this.buckets.set(newBuckets);
    } catch (e: any) {
      this.loadError.set(e?.message ?? 'Fehler beim Vorschlag');
    } finally {
      this.proposing.set(false);
    }
  }

  resetBuckets(): void {
    this.buckets.set([]);
  }

  onDrop(event: CdkDragDrop<RegistrationRequest[]>): void {
    if (event.previousContainer === event.container) {
      const list = [...event.container.data];
      moveItemInArray(list, event.previousIndex, event.currentIndex);
      const buckets = this.buckets().map(b =>
        b.requests === event.container.data ? {...b, requests: list} : b
      );
      this.buckets.set(buckets);
    } else {
      const prev = [...event.previousContainer.data];
      const curr = [...event.container.data];
      transferArrayItem(prev, curr, event.previousIndex, event.currentIndex);
      const buckets = this.buckets().map(b => {
        if (b.requests === event.previousContainer.data) return {...b, requests: prev};
        if (b.requests === event.container.data) return {...b, requests: curr};
        return b;
      });
      this.buckets.set(buckets);
    }
  }

  async generate(): Promise<void> {
    const group = this.group();
    if (!group) return;
    this.generating.set(true);
    this.generateError.set(null);
    try {
      const categories = this.buckets().map(b => ({
        name: b.name,
        requestIds: b.requests.map(r => r.id),
      }));
      await this.groupService.generate(group.id, categories);
      this.showSuccess.set(true);
    } catch (e: any) {
      this.generateError.set(e?.message ?? 'Fehler beim Generieren');
    } finally {
      this.generating.set(false);
    }
  }

  back(): void {
    this.router.navigate(['/v2/tournaments/registrations']);
  }

  avgAge(req: RegistrationRequest): number {
    if (!req.memberBirthdays?.length) return 0;
    const now = new Date();
    const ages = req.memberBirthdays.map(d => {
      const b = new Date(d);
      return now.getFullYear() - b.getFullYear();
    });
    return Math.round(ages.reduce((a, b) => a + b, 0) / ages.length);
  }

  oldestAge(req: RegistrationRequest): number {
    if (!req.memberBirthdays?.length) return 0;
    const now = new Date();
    return Math.max(...req.memberBirthdays.map(d => now.getFullYear() - new Date(d).getFullYear()));
  }
}
