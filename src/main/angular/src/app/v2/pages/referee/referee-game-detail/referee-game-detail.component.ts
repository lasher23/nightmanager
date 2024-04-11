import {Component} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {GameService} from "../../../../service/game.service";
import {flatMap, map, mergeMap} from "rxjs/operators";
import {Observable} from "rxjs";
import {Game} from "../../../../model/Game";

@Component({
  selector: 'app-referee-game-detail',
  templateUrl: './referee-game-detail.component.html',
  styleUrls: ['./referee-game-detail.component.css']
})
export class RefereeGameDetailComponent {

  game$: Observable<Game>;

  constructor(private activatedRoute: ActivatedRoute, private gameService: GameService) {
    this.game$ = activatedRoute.params.pipe(
      map(param => param.gameId),
      mergeMap(gameId => gameService.getGameById(gameId))
    );
  }
}
