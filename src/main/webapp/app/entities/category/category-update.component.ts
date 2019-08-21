import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ICategory, Category } from 'app/shared/model/category.model';
import { CategoryService } from './category.service';
import { ITournament } from 'app/shared/model/tournament.model';
import { TournamentService } from 'app/entities/tournament';

@Component({
  selector: 'jhi-category-update',
  templateUrl: './category-update.component.html'
})
export class CategoryUpdateComponent implements OnInit {
  isSaving: boolean;

  tournaments: ITournament[];

  categories: ICategory[];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(255)]],
    type: [null, [Validators.required]],
    state: [null, [Validators.required]],
    remark: [],
    tournamentId: [],
    parentCategoryId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected categoryService: CategoryService,
    protected tournamentService: TournamentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ category }) => {
      this.updateForm(category);
    });
    this.tournamentService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ITournament[]>) => mayBeOk.ok),
        map((response: HttpResponse<ITournament[]>) => response.body)
      )
      .subscribe((res: ITournament[]) => (this.tournaments = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.categoryService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICategory[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICategory[]>) => response.body)
      )
      .subscribe((res: ICategory[]) => (this.categories = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(category: ICategory) {
    this.editForm.patchValue({
      id: category.id,
      name: category.name,
      type: category.type,
      state: category.state,
      remark: category.remark,
      tournamentId: category.tournamentId,
      parentCategoryId: category.parentCategoryId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const category = this.createFromForm();
    if (category.id !== undefined) {
      this.subscribeToSaveResponse(this.categoryService.update(category));
    } else {
      this.subscribeToSaveResponse(this.categoryService.create(category));
    }
  }

  private createFromForm(): ICategory {
    return {
      ...new Category(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      type: this.editForm.get(['type']).value,
      state: this.editForm.get(['state']).value,
      remark: this.editForm.get(['remark']).value,
      tournamentId: this.editForm.get(['tournamentId']).value,
      parentCategoryId: this.editForm.get(['parentCategoryId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategory>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackTournamentById(index: number, item: ITournament) {
    return item.id;
  }

  trackCategoryById(index: number, item: ICategory) {
    return item.id;
  }
}
