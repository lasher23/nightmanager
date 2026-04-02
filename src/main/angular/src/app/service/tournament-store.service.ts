import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Tournament} from '../model/Tournament';
import {TournamentService} from './tournament.service';

const SESSION_KEY = 'activeTournamentId';

@Injectable({
  providedIn: 'root'
})
export class TournamentStore {
  private _tournaments$ = new BehaviorSubject<Tournament[]>([]);
  private _active$ = new BehaviorSubject<Tournament | null>(null);

  tournaments$: Observable<Tournament[]> = this._tournaments$.asObservable();
  active$: Observable<Tournament | null> = this._active$.asObservable();

  constructor(private tournamentService: TournamentService) {
    this.load();
  }

  private async load(): Promise<void> {
    try {
      const tournaments = await this.tournamentService.getAll();
      this._tournaments$.next(tournaments);
      const storedId = sessionStorage.getItem(SESSION_KEY);
      if (storedId) {
        const match = tournaments.find(t => String(t.id) === storedId);
        if (match) {
          this._active$.next(match);
          return;
        }
      }
      // Default to the first non-DONE tournament, or the first one
      const preferred = tournaments.find(t => t.state !== 'DONE') ?? tournaments[0] ?? null;
      this._active$.next(preferred);
      if (preferred) {
        sessionStorage.setItem(SESSION_KEY, String(preferred.id));
      }
    } catch (e) {
      console.error('Could not load tournaments', e);
    }
  }

  setActive(tournament: Tournament): void {
    this._active$.next(tournament);
    sessionStorage.setItem(SESSION_KEY, String(tournament.id));
  }

  getActiveTournamentId(): number | null {
    const active = this._active$.getValue();
    return active ? active.id : null;
  }

  reload(): Promise<void> {
    return this.load();
  }
}
