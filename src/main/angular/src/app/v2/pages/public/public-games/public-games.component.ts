import {Component, inject} from '@angular/core';
import {CommonModule} from '@angular/common';
import {GameService} from "../../../../service/game.service";
import {ActivatedRoute} from "@angular/router";
import {switchMap} from "rxjs/operators";
import {GameComponent} from "./game/game.component";


@Component({
  selector: 'app-public-games',
  standalone: true,
  imports: [CommonModule, GameComponent],
  templateUrl: './public-games.component.html',
  styleUrls: ['./public-games.component.css']
})
export class PublicGamesComponent {
  gameService = inject(GameService);
  games = inject(ActivatedRoute).queryParams.pipe(
    switchMap(params => this.gameService.getAllGamesByFilter(params.categoryId, params.teamId)),
  )

}
