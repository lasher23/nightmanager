import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHall } from 'app/shared/model/hall.model';

type EntityResponseType = HttpResponse<IHall>;
type EntityArrayResponseType = HttpResponse<IHall[]>;

@Injectable({ providedIn: 'root' })
export class HallService {
  public resourceUrl = SERVER_API_URL + 'api/halls';

  constructor(protected http: HttpClient) {}

  create(hall: IHall): Observable<EntityResponseType> {
    return this.http.post<IHall>(this.resourceUrl, hall, { observe: 'response' });
  }

  update(hall: IHall): Observable<EntityResponseType> {
    return this.http.put<IHall>(this.resourceUrl, hall, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IHall>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IHall[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
