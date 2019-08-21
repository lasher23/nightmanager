import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGame } from 'app/shared/model/game.model';

type EntityResponseType = HttpResponse<IGame>;
type EntityArrayResponseType = HttpResponse<IGame[]>;

@Injectable({ providedIn: 'root' })
export class GameService {
  public resourceUrl = SERVER_API_URL + 'api/games';

  constructor(protected http: HttpClient) {}

  create(game: IGame): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(game);
    return this.http
      .post<IGame>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(game: IGame): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(game);
    return this.http
      .put<IGame>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IGame>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGame[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(game: IGame): IGame {
    const copy: IGame = Object.assign({}, game, {
      start: game.start != null && game.start.isValid() ? game.start.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.start = res.body.start != null ? moment(res.body.start) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((game: IGame) => {
        game.start = game.start != null ? moment(game.start) : null;
      });
    }
    return res;
  }
}
