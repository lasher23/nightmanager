import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificationService } from './notification.service';
import { NotificationItem } from './notification.model';
import { Router } from '@angular/router';
import { tap } from 'rxjs';

@Component({
    imports: [CommonModule],
    selector: 'nm-notifications',
    template: `
  <div class="max-w-4xl mx-auto p-4">
    <h2 class="text-2xl mb-4">Notifications</h2>
    <ng-container *ngIf="items | async as items">

        <div *ngIf="items.length === 0" class="alert alert-info">No notifications</div>
        <div class="space-y-2">
          <div *ngFor="let n of items" class="card card-compact bg-base-100 shadow cursor-pointer" [class.opacity-60]="n.read" (click)="open(n)">
            <div class="card-body">
              <p class="text-sm text-muted">{{n.text}}</p>
              <div class="card-actions justify-end">
                <small class="text-xs text-gray-500">{{n.sentTime | date:'short'}}</small>
              </div>
            </div>
          </div>
        </div>
        </ng-container>
      </div>
  `
})
export class NotificationsComponent {
    notificationService = inject(NotificationService)
    items = this.notificationService.currentUserNotifications$.pipe(tap(notifications => this.notificationService.markAsRead(notifications)))
    constructor(private router: Router) { }

    open(n: NotificationItem) {
        if (n.url) {
            // navigate to the provided url
            this.router.navigateByUrl(n.url).catch(() => { });
        }
    }
}
