export enum CategoryState {
  GROUP_PHASE, SEMI_FINAL, FINAL, FINISHED, CROKI_FIRST
}

export interface Category {
  id: number;
  name: string;
  parentCategory: number;
  croki: boolean;
  state: CategoryState;
}
