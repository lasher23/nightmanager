import {Component, OnInit} from '@angular/core';
import {Category} from "../../../model/Category";
import {AdminConfirmDialogComponent} from "../admin-generation/admin-confirm-dialog/admin-confirm-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {CategoryService} from "../../../service/category.service";
import {HttpClient} from "@angular/common/http";
import {lastValueFrom} from "rxjs";
import {HttpProxyService} from "../../../service/http-proxy.service";

@Component({
  selector: 'app-admin-send-notifications',
  templateUrl: './admin-send-notifications.component.html',
  styleUrls: ['./admin-send-notifications.component.css']
})
export class AdminSendNotificationsComponent implements OnInit {
  displayedColumns = ['name', 'state', "send"];
  categories: Array<Category>;

  constructor(public dialog: MatDialog, private categoryService: CategoryService, private httpClient: HttpProxyService) {
  }

  ngOnInit() {
    this.categoryService.getAll().then(categories => this.categories = categories);
  }

  sendForCategory(element: Category) {
    this.dialog.open(AdminConfirmDialogComponent).afterClosed().subscribe(result => {
      if (result) {
        this.httpClient.post("notifications/category", element).catch(console.error)
      }
    });
  }

}
