import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from "@angular/router";
import { map, mergeMap, startWith, switchMap } from "rxjs/operators";
import { CategoryService } from "../../../../service/category.service";
import { TeamService } from "../../../../service/team.service";
import { OneSignal } from "onesignal-ngx";
import { Team } from "../../../../model/Team";
import { NotificationService } from '../../notifications/notification.service';

@Component({
  selector: 'app-public-category-teams',
  imports: [CommonModule, RouterLink],
  templateUrl: './public-category-teams.component.html',
  styleUrls: ['./public-category-teams.component.css']
})
export class PublicCategoryTeamsComponent {
  private categoryService = inject(CategoryService);
  private teamService = inject(TeamService);
  categoryId = inject(ActivatedRoute).params.pipe(map(params => params.categoryId))
  category = this.categoryId.pipe(switchMap(id => this.categoryService.getById(id)))
  teams = this.categoryId.pipe(
    switchMap(id => this.teamService.getAllByCategory(id)),
    map(team => team.filter(t => !t.placeholder)
      .map(t => ({ ...t, isSubscribed$: this.notificationService.isSubscribed$(t) }))
    ))

  notificationService = inject(NotificationService)
  pushNotSupported = this.notificationService.isPushSupported()
  subscriptionCount$ = this.notificationService.subscriptionCount$.pipe(startWith(0))

  async toggleSubscribe(team: Team) {
    this.notificationService.toggleSubscribe(team);
  }

}
