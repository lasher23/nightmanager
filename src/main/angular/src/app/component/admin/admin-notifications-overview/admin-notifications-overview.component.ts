import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {lastValueFrom, Observable} from "rxjs";
import {HttpProxyService} from "../../../service/http-proxy.service";

@Component({
  selector: 'app-admin-notifications-overview',
  templateUrl: './admin-notifications-overview.component.html',
  styleUrls: ['./admin-notifications-overview.component.css']
})
export class AdminNotificationsOverviewComponent implements OnInit {
  notifications: Promise<any[]>;
  displayedColumns = [
    "text",
    "toNumber",
    "sentTime",
    "success",
    "reference",
    "retry",
  ]

  constructor(private httpClient: HttpProxyService) {
  }

  ngOnInit(): void {
    this.loadData();
  }


  loadData() {
    this.notifications = this.httpClient.get<any[]>("notifications");
  }

  retry(notificationLog: any) {
    this.httpClient.post("notifications", notificationLog).catch(console.error);
  }
}

