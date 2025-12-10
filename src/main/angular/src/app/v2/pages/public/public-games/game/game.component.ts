import {Component, Input} from '@angular/core';
import {Observable} from "rxjs";
import {Game} from "../../../../../model/Game";
import {DatePipe, NgIf} from "@angular/common";

@Component({
    selector: 'app-game',
    templateUrl: './game.component.html',
    styleUrls: ['./game.component.css'],
    imports: [
        DatePipe,
        NgIf
    ]
})
export class GameComponent {

  @Input() game: Game;
}
