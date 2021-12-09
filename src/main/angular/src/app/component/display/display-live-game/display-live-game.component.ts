import { Component, OnDestroy, OnInit } from '@angular/core';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { ActivatedRoute } from '@angular/router';
import { GameService } from '../../../service/game.service';
import { Game } from '../../../model/Game';
import { CompatClient, Stomp } from '@stomp/stompjs';
import { GameChangeNotifierService } from '../../../service/game-change-notifier.service';
import { interval } from 'rxjs';
import { tap } from 'rxjs/operators';

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
  private changeCount: number = 0;
  showAdd: boolean;
  addCount = 0;
  adds = [
    '/assets/adds/1.jpg'
  ];

  constructor(private route: ActivatedRoute, private gameService: GameService,
              private gameChangeNotifierService: GameChangeNotifierService) { }

  ngOnInit(): void {
    this.route.queryParams.pipe(untilDestroyed(this)).subscribe(params => {
      this.hallId = params.hallId;
      this.loadLiveGame(this.hallId);
    });
    this.gameChangeNotifierService.subscribe(() => this.loadLiveGame(this.hallId));
    interval(5000).subscribe(() => this.handleDisplayChange());
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

  private handleDisplayChange() {
    this.changeCount++;
    if (this.changeCount % 4) {
      this.showAdd = false;
    } else {
      this.showAdd = true;
      this.addCount++;
    }
  }
}
