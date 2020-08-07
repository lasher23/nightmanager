import { Component, OnDestroy, OnInit } from '@angular/core';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { ActivatedRoute } from '@angular/router';
import { GameService } from '../../../service/game.service';
import { Game } from '../../../model/Game';
import { CompatClient, Stomp } from '@stomp/stompjs';
import { GameChangeNotifierService } from '../../../service/game-change-notifier.service';

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

  constructor(private route: ActivatedRoute, private gameService: GameService,
              private gameChangeNotifierService: GameChangeNotifierService) { }

  ngOnInit(): void {
    this.route.queryParams.pipe(untilDestroyed(this)).subscribe(params => {
      this.hallId = params.hallId;
      this.loadLiveGame(this.hallId);
    });
    this.gameChangeNotifierService.subscribe(() => this.loadLiveGame(this.hallId));
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
}
