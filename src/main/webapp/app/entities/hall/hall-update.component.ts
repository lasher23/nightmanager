import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IHall, Hall } from 'app/shared/model/hall.model';
import { HallService } from './hall.service';

@Component({
  selector: 'jhi-hall-update',
  templateUrl: './hall-update.component.html'
})
export class HallUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(255)]]
  });

  constructor(protected hallService: HallService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ hall }) => {
      this.updateForm(hall);
    });
  }

  updateForm(hall: IHall) {
    this.editForm.patchValue({
      id: hall.id,
      name: hall.name
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const hall = this.createFromForm();
    if (hall.id !== undefined) {
      this.subscribeToSaveResponse(this.hallService.update(hall));
    } else {
      this.subscribeToSaveResponse(this.hallService.create(hall));
    }
  }

  private createFromForm(): IHall {
    return {
      ...new Hall(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHall>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
