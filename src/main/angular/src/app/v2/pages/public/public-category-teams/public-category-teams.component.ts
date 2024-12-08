import {Component, inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {map, mergeMap, switchMap} from "rxjs/operators";
import {CategoryService} from "../../../../service/category.service";
import {TeamService} from "../../../../service/team.service";
import {OneSignal} from "onesignal-ngx";
import {Team} from "../../../../model/Team";

@Component({
  selector: 'app-public-category-teams',
  standalone: true,
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

  private oneSignal = inject(OneSignal)
  pushNotSupported = this.oneSignal.Notifications.isPushSupported()

  async toggleSubscribe(team: Team) {
    const tagId = PublicCategoryTeamsComponent.tagId(team);
    if (!this.isSubscribed(team)) {
      this.oneSignal.User.addTag(tagId, "true")
    } else {
      this.oneSignal.User.removeTag(tagId)
    }

  }

  private static tagId(team: Team) {
    return "team-" + team.id;
  }

  get tagCount() {
    const tags = (window.OneSignal.User as any).getTags();
    return Object.keys(tags).filter(tag => !!tags[tag]).length
  }

  isSubscribed(team: Team) {
    if (!((window as any)?.OneSignal?.User as any)?.getTags) {
      return;
    }
    return (window.OneSignal.User as any).getTags()[PublicCategoryTeamsComponent.tagId(team)] === "true"
  }
}
