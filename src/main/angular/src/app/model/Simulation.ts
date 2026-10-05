export interface SimulationTournamentSummary {
  id: number;
  name: string;
  categoryCount: number;
}

export interface SimulationCategorySpec {
  name: string;
  type: string;
  teamCount: number;
}

export interface SimulationCategory {
  id: number;
  name: string;
  type: string;
  teamCount: number;
}
