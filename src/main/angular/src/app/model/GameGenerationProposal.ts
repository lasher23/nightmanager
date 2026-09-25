export enum ProposalStatus {
  DRAFT = 'DRAFT',
  COMMITTED = 'COMMITTED',
}

export interface TeamRef {
  ref: string;
  name: string;
  existingTeamId: number | null;
  categoryRef: string | null;
}

export interface ProposedCategory {
  ref: string;
  name: string;
  type: string;
  existingCategoryId: number | null;
  parentCategoryId: number | null;
}

export interface ProposedGame {
  tempId: string;
  categoryRef: string;
  hallId: number;
  startDate: string;
  type: 'GROUP_STAGE' | 'SEMI_FINAL' | 'FINAL';
  teamHome: TeamRef;
  teamGuest: TeamRef;
  placeholder: boolean;
}

export interface GameGenerationProposal {
  id: number;
  tournamentId: number;
  name: string;
  startTime: string;
  status: ProposalStatus;
  createdAt: string;
  updatedAt: string;
  categories: ProposedCategory[];
  games: ProposedGame[];
}

export interface ProposalSummary {
  id: number;
  name: string;
  startTime: string;
  status: ProposalStatus;
  createdAt: string;
  updatedAt: string;
  gameCount: number;
}

export interface ProposeRequest {
  name: string | null;
  categoryIds: number[];
  startTime: string;
}
