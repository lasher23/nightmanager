export enum CategoryState {
  GROUP_PHASE = 'GROUP_PHASE',
  SEMI_FINAL = 'SEMI_FINAL',
  FINAL = 'FINAL', FINISHED = 'FINISHED',
  CROKI_FIRST = 'CROKI_FIRST',
  DISABLED = 'DISABLED'
}

export enum CategoryType {
  SINGLE_CATEGORY = 'SINGLE_CATEGORY',
  DOUBLE_CATEGORIES = 'DOUBLE_CATEGORIES',
  YETIS_CUP = 'YETIS_CUP',
}

export const CATEGORY_TYPE_LABELS: Record<CategoryType, string> = {
  [CategoryType.SINGLE_CATEGORY]: 'Einzelkategorie',
  [CategoryType.DOUBLE_CATEGORIES]: 'Doppelkategorie',
  [CategoryType.YETIS_CUP]: 'Yetis Cup',
};

export interface Category {
  id: number;
  name: string;
  parentCategory: number;
  croki: boolean;
  state: CategoryState;
  remark: string;
  type: string;
  showOnDisplay: boolean;
  shotGameEnabled?: boolean;
}
