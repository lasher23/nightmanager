import { Component, OnDestroy, OnInit } from '@angular/core';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { ActivatedRoute } from '@angular/router';
import { GameService } from '../../../service/game.service';
import { Game } from '../../../model/Game';
import { CompatClient, Stomp } from '@stomp/stompjs';

@UntilDestroy()
@Component({
  selector: 'app-display-live-game',
  templateUrl: './display-live-game.component.html',
  styleUrls: ['./display-live-game.component.scss']
})
export class DisplayLiveGameComponent implements OnInit, OnDestroy {
  liveGame: Game;
  private client: CompatClient;
  private hallId: any;
  hasNoLiveGames: boolean;

  constructor(private route: ActivatedRoute, private gameService: GameService) { }

  ngOnInit(): void {
    this.route.queryParams.pipe(untilDestroyed(this)).subscribe(params => {
      this.hallId = params.hallId;
      this.loadLiveGame(this.hallId);
    });
    this.client = Stomp.client(`ws://${window.location.host}/nightmanager-websockets`);
    this.client.onConnect = () => this.setupWebsocketsListener();
    this.client.activate();
  }

  ngOnDestroy() {
    this.client.deactivate();
  }

  private loadLiveGame(hallId: number) {
    this.gameService.getLiveGameByHall(hallId).then(liveGames => {
      if (liveGames.length === 0) {
        this.hasNoLiveGames = true;
        return;
      }
      this.liveGame = liveGames[0];
    });
  }

  private setupWebsocketsListener() {
    this.client.subscribe('/topic/nightmanager-game-change', msg => this.loadLiveGame(this.hallId));
  }
}
