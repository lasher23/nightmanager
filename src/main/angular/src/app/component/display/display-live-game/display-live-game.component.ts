import {Component, OnInit} from '@angular/core';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import {ActivatedRoute} from '@angular/router';
import {GameService} from '../../../service/game.service';
import {Game} from '../../../model/Game';
import {GameChangeNotifierService} from '../../../service/game-change-notifier.service';
import {interval} from 'rxjs';

@UntilDestroy()
@Component({
    selector: 'app-display-live-game',
    templateUrl: './display-live-game.component.html',
    styleUrls: ['./display-live-game.component.scss'],
    standalone: false
})
export class DisplayLiveGameComponent implements OnInit {
  liveGame: Game;
  private hallId: any;
  hasNoLiveGames: boolean;
  private changeCount: number = 0;
  showAdd: boolean;
  addCount = 0;

  private nextGame: Game;

  constructor(private route: ActivatedRoute, private gameService: GameService,
              private gameChangeNotifierService: GameChangeNotifierService) {
  }

  ngOnInit(): void {
    this.route.queryParams.pipe(untilDestroyed(this)).subscribe(params => {
      this.hallId = params.hallId;
      this.loadLiveGame(this.hallId);
    });

    this.gameChangeNotifierService.gameChanges$.pipe(untilDestroyed(this)).subscribe(() => this.loadLiveGame(this.hallId));
    interval(5000).subscribe(() => {
      this.handleDisplayChange();
    });
  }

  private loadLiveGame(hallId: number) {
    this.gameService.getLiveGameByHall(hallId).then(liveGames => {
      if (liveGames.length === 0) {
        this.hasNoLiveGames = true;
        return;
      }
      this.hasNoLiveGames = false;
      this.liveGame = liveGames[0];
      this.gameService.getNextGame(this.liveGame).then(game => this.nextGame = game);
    });
  }

  private handleDisplayChange() {
    this.changeCount++;
    if (this.changeCount % 6) {
      this.showAdd = false;
    } else {
      this.showAdd = true;
      this.addCount++;
    }
  }
}
