import {Component, inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {CategoryService} from "../../../../service/category.service";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-public-categories',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './public-categories.component.html',
  styleUrls: ['./public-categories.component.css']
})
export class PublicCategoriesComponent {
  categories = inject(CategoryService).getAllActive().then(cat => cat.sort((a, b) => a.id - b.id));
}
