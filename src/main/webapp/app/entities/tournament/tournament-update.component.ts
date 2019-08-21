import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { ITournament, Tournament } from 'app/shared/model/tournament.model';
import { TournamentService } from './tournament.service';

@Component({
  selector: 'jhi-tournament-update',
  templateUrl: './tournament-update.component.html'
})
export class TournamentUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(255)]],
    active: [null, [Validators.required]]
  });

  constructor(protected tournamentService: TournamentService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ tournament }) => {
      this.updateForm(tournament);
    });
  }

  updateForm(tournament: ITournament) {
    this.editForm.patchValue({
      id: tournament.id,
      name: tournament.name,
      active: tournament.active
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const tournament = this.createFromForm();
    if (tournament.id !== undefined) {
      this.subscribeToSaveResponse(this.tournamentService.update(tournament));
    } else {
      this.subscribeToSaveResponse(this.tournamentService.create(tournament));
    }
  }

  private createFromForm(): ITournament {
    return {
      ...new Tournament(),
      id: this.editForm.get(['id']).value,
      name: this.editForm.get(['name']).value,
      active: this.editForm.get(['active']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITournament>>) {
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
