import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {takeUntilDestroyed} from "@angular/core/rxjs-interop";
import {StompService} from "../../../stomp.service";

@Component({
    selector: 'app-admin-home',
    templateUrl: './admin-home.component.html',
    styleUrls: ['./admin-home.component.scss'],
    standalone: false
})
export class AdminHomeComponent implements OnInit {

  fullMode = false;

  constructor(private activatedRoute: ActivatedRoute, private stompService: StompService,) {
    this.activatedRoute.queryParams.pipe(takeUntilDestroyed()).subscribe(params => {
      if (params.fullMode) {
        this.fullMode = true
      }
    })
  }

  ngOnInit() {
  }


  emitStartClicked() {
    this.stompService.publish({
      destination: "/topic/game-started",
      body: JSON.stringify({action: "startClicked"})
    })
  }
}
