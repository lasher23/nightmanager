import {Component, inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {map, mergeMap, switchMap} from "rxjs/operators";
import {CategoryService} from "../../../../service/category.service";
import {TeamService} from "../../../../service/team.service";
import {OneSignal} from "onesignal-ngx";
import {Team} from "../../../../model/Team";
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
  teams = this.categoryId.pipe(switchMap(id => this.teamService.getAllByCategory(id)), map(team => team.filter(t => !t.placeholder)))

  notificationService = inject(NotificationService)
  pushNotSupported = this.notificationService.isPushSupported()

  async toggleSubscribe(team: Team) {
    this.notificationService.toggleSubscribe(team);

  }

  get tagCount() {
    return this.notificationService.subscriptionCount
  }

  get isSubscribed() {
    return this.notificationService.isSubscribed
  }
}
