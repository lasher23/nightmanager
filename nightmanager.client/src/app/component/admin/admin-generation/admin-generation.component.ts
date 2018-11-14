import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../../../service/category.service';
import {Category, CategoryState} from '../../../model/Category';

@Component({
  selector: 'app-admin-generation',
  templateUrl: './admin-generation.component.html',
  styleUrls: ['./admin-generation.component.scss']
})
export class AdminGenerationComponent implements OnInit {
  categories: Array<Category>;
  displayedColumns = ['name', 'state'];
  state = CategoryState;

  constructor(private categoryService: CategoryService) {
  }

  ngOnInit() {
    this.categoryService.getAll().then(categories => this.categories = categories);
  }

}
