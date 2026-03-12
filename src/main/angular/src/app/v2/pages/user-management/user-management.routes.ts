import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () => import('./user-management.component').then(c => c.UserManagementComponent),
    children: [
      { path: '', redirectTo: 'users', pathMatch: 'full' },
      {
        path: 'users',
        loadComponent: () => import('./user-list.component').then(c => c.UserListComponent)
      },
      {
        path: 'users/:id',
        loadComponent: () => import('./user-edit.component').then(c => c.UserEditComponent)
      },
      {
        path: 'roles',
        loadComponent: () => import('./role-list.component').then(c => c.RoleListComponent)
      },
      {
        path: 'roles/:id',
        loadComponent: () => import('./role-edit.component').then(c => c.RoleEditComponent)
      },
    ]
  }
];
