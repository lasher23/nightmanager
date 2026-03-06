import {Injectable} from '@angular/core';
import {UserManager, User} from 'oidc-client-ts';
import {authConfig} from './auth.config';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({providedIn: 'root'})
export class AuthService {

  private userManager: UserManager;
  private userSubject = new BehaviorSubject<User | null>(null);
  public user$: Observable<User | null> = this.userSubject.asObservable();
  private loggingOut = false;

  public get url(): string {
    return 'api/';
  }

  constructor() {
    this.userManager = new UserManager(authConfig);
    this.userManager.events.addUserLoaded(user => {
      this.userSubject.next(user);
    });

    this.userManager.events.addUserUnloaded(() => {
      this.userSubject.next(null);
    });

    this.userManager.events.addAccessTokenExpired(() => {
      if (!this.loggingOut) {
        this.login();
      }
    });

    // Load user from storage on startup
    this.userManager.getUser().then(user => {
      if (user && !user.expired) {
        this.userSubject.next(user);
      }
    });
  }

  public async login(): Promise<void> {
    await this.userManager.signinRedirect();
  }

  public async handleCallback(): Promise<User> {
    const user = await this.userManager.signinRedirectCallback();
    this.userSubject.next(user);
    return user;
  }

  public async logout(): Promise<void> {
    this.loggingOut = true;
    await this.userManager.signoutRedirect();
  }

  public async getAccessToken(): Promise<string | null> {
    const user = await this.userManager.getUser();
    if (user && !user.expired) {
      return user.access_token;
    }
    return null;
  }

  public isLoggedIn(): boolean {
    const user = this.userSubject.value;
    return user != null && !user.expired;
  }

  public getRoles(): string[] {
    const user = this.userSubject.value;
    if (!user?.profile) {
      return [];
    }
    const roles = (user.profile as any)['roles'];
    return Array.isArray(roles) ? roles : [];
  }

  public getUsername(): string {
    const user = this.userSubject.value;
    return (user?.profile as any)?.['preferred_username'] ?? user?.profile?.email ?? '';
  }

  public getEmail(): string {
    return this.userSubject.value?.profile?.email ?? '';
  }
}
