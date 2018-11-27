import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../../../service/category.service';
import {Category, CategoryState} from '../../../model/Category';
import {GenerationService} from '../../../service/generation.service';
import {MatDialog} from '@angular/material';
import {RefereeGameConfirmDialogComponent} from '../../referee/referee-games/referee-game/referee-game-confirm-dialog/referee-game-confirm-dialog.component';
import {AdminConfirmDialogComponent} from './admin-confirm-dialog/admin-confirm-dialog.component';

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
}
