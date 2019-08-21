import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Hall } from 'app/shared/model/hall.model';
import { HallService } from './hall.service';
import { HallComponent } from './hall.component';
import { HallDetailComponent } from './hall-detail.component';
import { HallUpdateComponent } from './hall-update.component';
import { HallDeletePopupComponent } from './hall-delete-dialog.component';
import { IHall } from 'app/shared/model/hall.model';

@Injectable({ providedIn: 'root' })
export class HallResolve implements Resolve<IHall> {
  constructor(private service: HallService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IHall> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<Hall>) => response.ok),
        map((hall: HttpResponse<Hall>) => hall.body)
      );
    }
    return of(new Hall());
  }
}

export const hallRoute: Routes = [
  {
    path: '',
    component: HallComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'nightmanagerApp.hall.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: HallDetailComponent,
    resolve: {
      hall: HallResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'nightmanagerApp.hall.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: HallUpdateComponent,
    resolve: {
      hall: HallResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'nightmanagerApp.hall.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: HallUpdateComponent,
    resolve: {
      hall: HallResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'nightmanagerApp.hall.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const hallPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: HallDeletePopupComponent,
    resolve: {
      hall: HallResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'nightmanagerApp.hall.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
