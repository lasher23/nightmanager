import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../../../service/category.service';
import {Category, CategoryState} from '../../../model/Category';
import {GenerationService} from '../../../service/generation.service';

@Component({
  selector: 'app-admin-generation',
  templateUrl: './admin-generation.component.html',
  styleUrls: ['./admin-generation.component.scss']
})
export class AdminGenerationComponent implements OnInit {
  categories: Array<Category>;
  displayedColumns = ['name', 'state'];
  state = CategoryState;

  constructor(private categoryService: CategoryService, private generationService: GenerationService) {
  }

  ngOnInit() {
    this.categoryService.getAll().then(categories => this.categories = categories);
  }

  generate(element: Category) {
    this.generationService.generate((element));
  }
}
