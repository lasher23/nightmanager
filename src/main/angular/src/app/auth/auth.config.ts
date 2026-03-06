import {UserManagerSettings, WebStorageStateStore} from 'oidc-client-ts';

/**
 * In development, the Angular dev server is at localhost:4200 but the
 * Authorization Server lives on the Spring Boot backend at localhost:8080.
 * In production, everything is same-origin.
 */
const isDevMode = window.location.hostname === 'localhost' && window.location.port === '4200';
const authServerUrl = isDevMode ? 'http://localhost:8080' : window.location.origin;

export const authConfig: UserManagerSettings = {
  authority: authServerUrl,
  client_id: 'nightmanager-client',
  redirect_uri: `${window.location.origin}/oidc/callback`,
  post_logout_redirect_uri: window.location.origin,
  response_type: 'code',
  scope: 'openid profile email offline_access',
  automaticSilentRenew: true,
  userStore: new WebStorageStateStore({store: window.localStorage}),
};
