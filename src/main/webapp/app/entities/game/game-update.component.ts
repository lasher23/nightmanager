import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IGame, Game } from 'app/shared/model/game.model';
import { GameService } from './game.service';
import { ITeam } from 'app/shared/model/team.model';
import { TeamService } from 'app/entities/team';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category';
import { IHall } from 'app/shared/model/hall.model';
import { HallService } from 'app/entities/hall';

@Component({
  selector: 'jhi-game-update',
  templateUrl: './game-update.component.html'
})
export class GameUpdateComponent implements OnInit {
  isSaving: boolean;

  teams: ITeam[];

  categories: ICategory[];

  halls: IHall[];

  editForm = this.fb.group({
    id: [],
    goalsHome: [null, [Validators.required]],
    goalsGuest: [null, [Validators.required]],
    state: [null, [Validators.required]],
    placeholder: [null, [Validators.required]],
    start: [null, [Validators.required]],
    duration: [],
    type: [null, [Validators.required]],
    homeId: [],
    guestId: [],
    categoryId: [],
    hallId: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected gameService: GameService,
    protected teamService: TeamService,
    protected categoryService: CategoryService,
    protected hallService: HallService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ game }) => {
      this.updateForm(game);
    });
    this.teamService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ITeam[]>) => mayBeOk.ok),
        map((response: HttpResponse<ITeam[]>) => response.body)
      )
      .subscribe((res: ITeam[]) => (this.teams = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.categoryService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ICategory[]>) => mayBeOk.ok),
        map((response: HttpResponse<ICategory[]>) => response.body)
      )
      .subscribe((res: ICategory[]) => (this.categories = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.hallService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IHall[]>) => mayBeOk.ok),
        map((response: HttpResponse<IHall[]>) => response.body)
      )
      .subscribe((res: IHall[]) => (this.halls = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(game: IGame) {
    this.editForm.patchValue({
      id: game.id,
      goalsHome: game.goalsHome,
      goalsGuest: game.goalsGuest,
      state: game.state,
      placeholder: game.placeholder,
      start: game.start != null ? game.start.format(DATE_TIME_FORMAT) : null,
      duration: game.duration,
      type: game.type,
      homeId: game.homeId,
      guestId: game.guestId,
      categoryId: game.categoryId,
      hallId: game.hallId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const game = this.createFromForm();
    if (game.id !== undefined) {
      this.subscribeToSaveResponse(this.gameService.update(game));
    } else {
      this.subscribeToSaveResponse(this.gameService.create(game));
    }
  }

  private createFromForm(): IGame {
    return {
      ...new Game(),
      id: this.editForm.get(['id']).value,
      goalsHome: this.editForm.get(['goalsHome']).value,
      goalsGuest: this.editForm.get(['goalsGuest']).value,
      state: this.editForm.get(['state']).value,
      placeholder: this.editForm.get(['placeholder']).value,
      start: this.editForm.get(['start']).value != null ? moment(this.editForm.get(['start']).value, DATE_TIME_FORMAT) : undefined,
      duration: this.editForm.get(['duration']).value,
      type: this.editForm.get(['type']).value,
      homeId: this.editForm.get(['homeId']).value,
      guestId: this.editForm.get(['guestId']).value,
      categoryId: this.editForm.get(['categoryId']).value,
      hallId: this.editForm.get(['hallId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGame>>) {
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

  trackTeamById(index: number, item: ITeam) {
    return item.id;
  }

  trackCategoryById(index: number, item: ICategory) {
    return item.id;
  }

  trackHallById(index: number, item: IHall) {
    return item.id;
  }
}
