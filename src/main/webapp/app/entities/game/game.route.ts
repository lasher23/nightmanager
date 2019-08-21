import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Game } from 'app/shared/model/game.model';
import { GameService } from './game.service';
import { GameComponent } from './game.component';
import { GameDetailComponent } from './game-detail.component';
import { GameUpdateComponent } from './game-update.component';
import { GameDeletePopupComponent } from './game-delete-dialog.component';
import { IGame } from 'app/shared/model/game.model';

@Injectable({ providedIn: 'root' })
export class GameResolve implements Resolve<IGame> {
  constructor(private service: GameService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IGame> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Game>) => response.ok),
        map((game: HttpResponse<Game>) => game.body)
      );
    }
    return of(new Game());
  }
}

export const gameRoute: Routes = [
  {
    path: '',
    component: GameComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'nightmanagerApp.game.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: GameDetailComponent,
    resolve: {
      game: GameResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'nightmanagerApp.game.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: GameUpdateComponent,
    resolve: {
      game: GameResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'nightmanagerApp.game.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: GameUpdateComponent,
    resolve: {
      game: GameResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'nightmanagerApp.game.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const gamePopupRoute: Routes = [
  {
    path: ':id/delete',
    component: GameDeletePopupComponent,
    resolve: {
      game: GameResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'nightmanagerApp.game.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
