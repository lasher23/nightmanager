export enum CategoryState {
  GROUP_PHASE = 'GROUP_PHASE',
  SEMI_FINAL = 'SEMI_FINAL',
  FINAL = 'FINAL', FINISHED = 'FINISHED',
  CROKI_FIRST = 'CROKI_FIRST',
  DISABLED = 'DISABLED'
}

export interface Category {
  id: number;
  name: string;
  parentCategory: number;
  croki: boolean;
  state: CategoryState;
  remark: string;
  type: string;
  showOnDisplay: boolean;
}
