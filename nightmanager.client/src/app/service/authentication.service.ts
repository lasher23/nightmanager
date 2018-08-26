import {Injectable} from '@angular/core';
import {st} from '@angular/core/src/render3';
import {HttpHeaders} from '@angular/common/http';
import {JwtHelperService} from '@auth0/angular-jwt';
import {getToken} from 'codelyzer/angular/styles/cssLexer';
import {debug} from 'util';

@Injectable({providedIn: 'root'})
export class AuthenticationService {
  authorizationHeaderKey = 'authHeader';

  rootUrl: String = 'api/';

  public get url() {
    return this.rootUrl;
  }

  saveAuthorizationHeader(authorizationHeader: string): void {
    localStorage.setItem(this.authorizationHeaderKey, authorizationHeader);
  }

  getAuthHeader(): HttpHeaders {
    const headers = new HttpHeaders();
    headers.set('Authorization', this.getJwt());
    return headers;
  }

  getJwt() {
    return localStorage.getItem(this.authorizationHeaderKey);
  }

  public isLoggedIn(): boolean {
    if (localStorage.getItem(this.authorizationHeaderKey)) {
      return true;
    }
    return false;
  }

  isTokenExpired(): boolean {
    const helper = new JwtHelperService();
    return helper.isTokenExpired(this.getJwt());
  }
}
