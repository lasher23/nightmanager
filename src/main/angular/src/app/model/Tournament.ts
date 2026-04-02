export enum TournamentState {
  DRAFT = 'DRAFT',
  REGISTRATION_OPEN = 'REGISTRATION_OPEN',
  IN_PROGRESS = 'IN_PROGRESS',
  DONE = 'DONE'
}

export const TOURNAMENT_STATE_LABELS: Record<TournamentState, string> = {
  [TournamentState.DRAFT]: 'Entwurf',
  [TournamentState.REGISTRATION_OPEN]: 'Anmeldung offen',
  [TournamentState.IN_PROGRESS]: 'Laufend',
  [TournamentState.DONE]: 'Abgeschlossen',
};

export interface Tournament {
  id: number;
  name: string;
  dateFrom: string | null;
  dateTo: string | null;
  state: TournamentState;
}
