import {Injectable} from '@angular/core';
import {AuthenticationService} from './authentication.service';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {User} from '../model/User';
import {Router} from '@angular/router';
import {Observable, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient, private router: Router, private authService: AuthenticationService) {
  }

  public login(user: User): Promise<User> {
    return this.http.post<User>(this.authService.url + 'login', user, {observe: 'response'}).toPromise().then(response => {
      if (response.headers.has('Authorization')) {
        this.authService.saveAuthorizationHeader(response.headers.get('Authorization'));
        return response.body;
      }
      return response.body;
    });
  }
}
