import {Injectable} from '@angular/core';
import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent} from '@angular/common/http';
import {Observable, from, switchMap} from 'rxjs';
import {AuthService} from './auth.service';

/**
 * HTTP interceptor that automatically attaches the OIDC access token
 * as a Bearer token to all outgoing /api/ requests.
 */
@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Only add token for API requests
    if (!req.url.includes('/api/') && !req.url.includes('api/')) {
      return next.handle(req);
    }

    return from(this.authService.getAccessToken()).pipe(
      switchMap(token => {
        if (token) {
          const authReq = req.clone({
            setHeaders: {Authorization: `Bearer ${token}`}
          });
          return next.handle(authReq);
        }
        return next.handle(req);
      })
    );
  }
}
