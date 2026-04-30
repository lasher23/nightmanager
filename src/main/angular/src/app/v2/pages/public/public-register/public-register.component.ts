import {Component, inject, signal, computed} from '@angular/core';
import {toSignal} from '@angular/core/rxjs-interop';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {TournamentStore} from '../../../../service/tournament-store.service';
import {RegistrationGroupService} from '../../../../service/registration-group.service';
import {RegistrationRequestService} from '../../../../service/registration-request.service';
import {RegistrationGroup} from '../../../../model/RegistrationGroup';
import {TournamentState} from '../../../../model/Tournament';

@Component({
  selector: 'app-public-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="max-w-lg mx-auto p-4">
      <h1 class="text-3xl font-bold mb-2">Anmelden</h1>

      @if (loading()) {
        <div class="flex justify-center py-8"><span class="loading loading-spinner loading-lg"></span></div>
      } @else if (tournament()?.state !== 'REGISTRATION_OPEN') {
        <div class="alert alert-warning mt-4">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01M12 2a10 10 0 100 20A10 10 0 0012 2z"/>
          </svg>
          <span>Anmeldungen für <strong>{{ tournament()?.name }}</strong> sind derzeit nicht offen.</span>
        </div>
      } @else if (submitted()) {
        <div class="alert alert-success mt-4">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"/>
          </svg>
          <div>
            <div class="font-bold">Anmeldung eingegangen!</div>
            <div class="text-sm">Dein Team wurde zur Prüfung eingereicht. Du wirst per E-Mail benachrichtigt.</div>
          </div>
        </div>
        <button class="btn btn-ghost mt-4" (click)="reset()">Weitere Anmeldung einreichen</button>
      } @else {
        <p class="text-sm text-gray-500 mb-6">
          Turnier: <strong>{{ tournament()?.name }}</strong>
          @if (tournament()?.dateFrom) {
            &nbsp;·&nbsp;{{ tournament()!.dateFrom | slice:0:10 }}
            @if (tournament()!.dateTo) { – {{ tournament()!.dateTo | slice:0:10 }} }
          }
        </p>

        @if (error()) {
          <div class="alert alert-error mb-4">{{ error() }}</div>
        }

        <div class="flex flex-col gap-5">
          <!-- Team name -->
          <div>
            <label class="label"><span class="label-text font-medium">Teamname *</span></label>
            <input class="input input-bordered w-full" [(ngModel)]="teamName" placeholder="z.B. Die Unbesiegbaren" />
          </div>

          <!-- Registration group -->
          @if (groups().length > 1) {
            <div>
              <label class="label"><span class="label-text font-medium">Anmeldegruppe *</span></label>
              <select class="select select-bordered w-full" [ngModel]="selectedGroupId()" (ngModelChange)="selectedGroupId.set(+$event)">
                <option [value]="null" disabled>Bitte auswählen...</option>
                @for (g of groups(); track g.id) {
                  <option [value]="g.id">{{ g.name }}</option>
                }
              </select>
            </div>
          }

          <!-- Birthdays (only if requiresAge on selected group) -->
          @if (selectedGroup()?.requiresAge) {
            <div>
              <label class="label">
                <span class="label-text font-medium">Geburtstage der Mitglieder</span>
                <span class="label-text-alt">Bis zu 5 Mitglieder</span>
              </label>
              <div class="flex flex-col gap-2 mt-1">
                @for (i of birthdaySlots; track i) {
                  <input type="date" class="input input-bordered w-full"
                         [(ngModel)]="birthdays[i]"
                         [placeholder]="'Mitglied ' + (i + 1)" />
                }
              </div>
            </div>
          }

          <button class="btn btn-primary" [disabled]="saving() || !isValid()" (click)="submit()">
            @if (saving()) { <span class="loading loading-spinner loading-sm"></span> }
            Anmeldung einreichen
          </button>
        </div>
      }
    </div>
  `
})
export class PublicRegisterComponent {
  private tournamentStore = inject(TournamentStore);
  private registrationGroupService = inject(RegistrationGroupService);
  private registrationRequestService = inject(RegistrationRequestService);

  tournament = toSignal(this.tournamentStore.active$);

  groups = signal<RegistrationGroup[]>([]);
  loading = signal(true);
  error = signal<string | null>(null);
  saving = signal(false);
  submitted = signal(false);

  teamName = '';
  selectedGroupId = signal<number | null>(null);
  birthdays: string[] = ['', '', '', '', ''];
  birthdaySlots = [0, 1, 2, 3, 4];

  selectedGroup = computed(() => this.groups().find(g => +g.id === +(this.selectedGroupId() ?? -1)) ?? null);

  constructor() {
    this.tournamentStore.active$.subscribe(async t => {
      if (!t) { this.loading.set(false); return; }
      try {
        const gs = await this.registrationGroupService.getByTournament(t.id);
        this.groups.set(gs);
        if (gs.length === 1) this.selectedGroupId.set(gs[0].id);
      } catch {
        // ignore — groups might simply not be set up yet
      } finally {
        this.loading.set(false);
      }
    });
  }

  isValid(): boolean {
    return !!this.teamName.trim() && !!this.selectedGroupId();
  }

  async submit(): Promise<void> {
    if (!this.isValid()) return;
    this.saving.set(true);
    this.error.set(null);
    try {
      const filledBirthdays = this.birthdays.filter(b => !!b);
      await this.registrationRequestService.create(this.selectedGroupId()!, {
        teamName: this.teamName.trim(),
        memberBirthdays: filledBirthdays.length ? filledBirthdays : [],
      });
      this.submitted.set(true);
    } catch (e: any) {
      this.error.set(e?.error?.message ?? 'Fehler beim Einreichen der Anmeldung.');
    } finally {
      this.saving.set(false);
    }
  }

  reset(): void {
    this.submitted.set(false);
    this.teamName = '';
    this.birthdays = ['', '', '', '', ''];
    if (this.groups().length !== 1) this.selectedGroupId.set(null);
  }
}
