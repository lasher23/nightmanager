import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { OneSignal } from 'onesignal-ngx';
import { BehaviorSubject, map, of, switchMap } from 'rxjs';
import { Team } from 'src/app/model/Team';

interface NotificationLogDto {
    id: number;
    text: string;
    tagId?: string;
    sentTime?: string; // ISO
    url?: string;
}

const STORAGE_KEY = 'nm_notifications_v1';

@Injectable({ providedIn: 'root' })
export class NotificationService {
    private triggerNotifications = new BehaviorSubject(null);
    private oneSignal = inject(OneSignal);
    currentUserNotifications$ = this.triggerNotifications.pipe(switchMap(() => {
        return this.fetchForTags(this.tagIds)
    }))
    unreadNotificationCount$ = this.currentUserNotifications$.pipe(map(notifications => {
        const readNotifications = this.getReadNotifications();
        return notifications.map(notification => notification.id)
            .filter(id => !readNotifications.includes(id)).length
    }));


    constructor(private http: HttpClient) {
        
        (this.oneSignal.User as any).addEventListener('change', () => this.triggerNotifications.next(null))
    }

    isPushSupported() {
        return this.oneSignal.Notifications.isPushSupported();
    }

    fetchForTags(tags: string[]) {
        if (!tags || tags.length === 0) return of([]);
        return this.http.post<NotificationLogDto[]>('/api/notifications/tags', tags)
    }

    markAsRead(notifications: { id: number }[]) {
        localStorage.setItem(STORAGE_KEY, JSON.stringify([...new Set(this.getReadNotifications().concat(notifications.map(n => n.id)))]));
    }

    private getReadNotifications() {
        return JSON.parse(localStorage.getItem(STORAGE_KEY) || "[]")
    }
    async toggleSubscribe(team: Team) {
        const tagId = NotificationService.tagId(team);
        if (!this.isSubscribed(team)) {
            this.oneSignal.User.addTag(tagId, "true")
        } else {
            this.oneSignal.User.removeTag(tagId)
        }

    }
    isSubscribed(team: Team) {
        if (!((window as any)?.OneSignal?.User as any)?.getTags) {
            return;
        }
        return (window.OneSignal.User as any).getTags()[NotificationService.tagId(team)] === "true"
    }

    get subscriptionCount() {
        return this.tagIds.length
    }

    get tagIds() {
        const tags = (window.OneSignal?.User as any)?.getTags() || [];
        return Object.keys(tags).filter(tag => !!tags[tag])
    }
    private static tagId(team: Team) {
        return "team-" + team.id;
    }
}

