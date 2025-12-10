import {Component, OnInit} from '@angular/core';
import {Category, CategoryState} from '../../../model/Category';
import {CategoryService} from '../../../service/category.service';
import {Router} from '@angular/router';

@Component({
    selector: 'app-display-category-overview',
    templateUrl: './display-category-overview.component.html',
    styleUrls: ['./display-category-overview.component.scss'],
    standalone: false
})
export class DisplayCategoryOverviewComponent implements OnInit {
  categories: Array<Category>;
  displayedColumns = ['name'];

  constructor(private categoryService: CategoryService, private router: Router) {
  }

  ngOnInit() {
    this.categoryService.getAll().then(categories => this.categories = categories.filter(x => x.state !== CategoryState.DISABLED));
  }

  openCategory(category: Category) {
    this.router.navigateByUrl('display/details/categories/' + category.id);

  }
}
