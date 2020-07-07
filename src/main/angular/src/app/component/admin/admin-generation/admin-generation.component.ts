import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../../../service/category.service';
import {Category, CategoryState} from '../../../model/Category';
import {GenerationService} from '../../../service/generation.service';
import {AdminConfirmDialogComponent} from './admin-confirm-dialog/admin-confirm-dialog.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-admin-generation',
  templateUrl: './admin-generation.component.html',
  styleUrls: ['./admin-generation.component.scss']
})
export class AdminGenerationComponent implements OnInit {
  categories: Array<Category>;
  displayedColumns = ['name', 'state'];
  state = CategoryState;

  constructor(private categoryService: CategoryService, private generationService: GenerationService, public dialog: MatDialog) {
  }

  ngOnInit() {
    this.categoryService.getAll().then(categories => this.categories = categories);
  }

  generate(element: Category) {
    this.dialog.open(AdminConfirmDialogComponent).afterClosed().subscribe(result => {
      if (result) {
        this.generationService.generate((element));
      }
    });
  }

  isGenerateSemis(category: Category): boolean {
    if (category.type === 'DOUBLE_CATEGORIES') {
      return category.parentCategory == null && category.state === CategoryState.DISABLED;
    }
    return category.state === CategoryState.GROUP_PHASE;
  }
}
