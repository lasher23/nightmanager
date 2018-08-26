import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AuthenticationService} from './authentication.service';

@Injectable()
export class HttpProxyService {

  constructor(private http: HttpClient, private rootService: AuthenticationService) {
  }

  createAuthorizationHeader(): HttpHeaders {
    return new HttpHeaders({'Authorization': 'Bearer ' + this.rootService.getJwt()});
  }


  get<T>(url: string, queryParameters = {}): Promise<T> {
    const headers = this.createAuthorizationHeader();
    return this.http.get<T>(this.rootService.url + url, {
      headers: headers,
      params: queryParameters
    }).toPromise();
  }

  post<T>(url, data): Promise<T> {
    const headers = this.createAuthorizationHeader();
    debugger
    return this.http.post<T>(this.rootService.url + url, data, {
      headers: headers
    }).toPromise();
  }
}