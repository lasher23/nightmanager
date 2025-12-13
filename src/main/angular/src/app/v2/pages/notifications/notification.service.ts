import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { OneSignal } from 'onesignal-ngx';
import { BehaviorSubject, combineLatest, defer, distinctUntilChanged, from, map, merge, of, startWith, Subject, switchMap, tap, timer } from 'rxjs';
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
    triggerOneSignalReady(): any {
      this.triggerNotifications.next(null);
    }
    private triggerNotifications = new BehaviorSubject(null);
    private subscriptionChanged = new Subject();

    private oneSignal = inject(OneSignal);
    currentUserNotifications$ = merge(this.triggerNotifications, timer(0, 30000)).pipe(switchMap(() => {
        return from(this.getTagIds()).pipe(switchMap(ids => this.fetchForTags(ids)))
    }))
    readNotifications$ = new BehaviorSubject(this.getReadNotifications());
    unreadNotificationCount$ = combineLatest([this.currentUserNotifications$, this.readNotifications$]).pipe(map(([notifications, readNotifications]) => {
        return notifications.map(notification => notification.id)
            .filter(id => !readNotifications.includes(id)).length
    }));
    subscriptionCount$ = merge(this.subscriptionChanged, this.triggerNotifications)
        .pipe(switchMap(async () => (await this.getTagIds()).length));


    constructor(private http: HttpClient) {
        this.oneSignal.User.addEventListener('change', () => {
            this.subscriptionChanged.next(null);
            this.triggerNotifications.next(null);
        });
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
        this.readNotifications$.next(this.getReadNotifications());
    }

    private getReadNotifications() {
        return JSON.parse(localStorage.getItem(STORAGE_KEY) || "[]")
    }
    async toggleSubscribe(team: Team) {
        const tagId = NotificationService.tagId(team);
        if (! await this.isSubscribedSync(team)) {
            this.oneSignal.User.addTag(tagId, "true")
        } else {
            this.oneSignal.User.removeTag(tagId)
        }
        this.subscriptionChanged.next(null);
        this.triggerNotifications.next(null);
    }

    isSubscribed$(team: Team) {
        return this.subscriptionChanged.pipe(
            startWith(null),
            switchMap(() => from(this.isSubscribedSync(team))),
            distinctUntilChanged(),
        );
    }

    private async isSubscribedSync(team: Team): Promise<boolean> {
        return (await this.oneSignal.User.getTags())[NotificationService.tagId(team)] === "true"
    }


    async getTagIds() {
        const tags = (await this.oneSignal.User?.getTags()) || [];
        return Object.keys(tags).filter(tag => !!tags[tag])
    }
    private static tagId(team: Team) {
        return "team-" + team.id;
    }
}

