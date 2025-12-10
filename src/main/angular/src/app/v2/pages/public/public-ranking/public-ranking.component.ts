import {Component, inject} from '@angular/core';
import { CommonModule } from '@angular/common';
import {CategoryService} from "../../../../service/category.service";
import {TeamService} from "../../../../service/team.service";
import {ActivatedRoute} from "@angular/router";
import {map, switchMap} from "rxjs/operators";

@Component({
    selector: 'app-public-ranking',
    imports: [CommonModule],
    templateUrl: './public-ranking.component.html',
    styleUrls: ['./public-ranking.component.css']
})
export class PublicRankingComponent {
  private categoryService = inject(CategoryService);
  private teamService = inject(TeamService);
  categoryId = inject(ActivatedRoute).params.pipe(map(params => params.categoryId))
  category = this.categoryId.pipe(switchMap(id => this.categoryService.getById(id)))
  teams = this.categoryId.pipe(switchMap(id => this.teamService.getAllByCategorySorted(id)), map(team => team.filter(t => !t.placeholder)))

}
