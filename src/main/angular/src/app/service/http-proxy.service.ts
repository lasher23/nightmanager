import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AuthService} from '../auth/auth.service';

@Injectable()
export class HttpProxyService {

  constructor(private http: HttpClient, private authService: AuthService) {
  }

  get<T>(url: string, queryParameters = {}): Promise<T> {
    return this.http.get<T>(this.authService.url + url, {
      params: queryParameters
    }).toPromise();
  }

  post<T>(url: string, data: any): Promise<T> {
    return this.http.post<T>(this.authService.url + url, data).toPromise();
  }

  put<T>(url: string, data: any): Promise<T> {
    return this.http.put<T>(this.authService.url + url, data).toPromise();
  }

  patch<T>(url: string, data: any): Promise<T> {
    return this.http.patch<T>(this.authService.url + url, data).toPromise();
  }

  delete<T>(url: string): Promise<T> {
    return this.http.delete<T>(this.authService.url + url).toPromise();
  }
}
