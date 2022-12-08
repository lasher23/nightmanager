import {Component, OnDestroy, OnInit} from '@angular/core';
import {UntilDestroy, untilDestroyed} from '@ngneat/until-destroy';
import {ActivatedRoute} from '@angular/router';
import {GameService} from '../../../service/game.service';
import {Game} from '../../../model/Game';
import {CompatClient, Stomp} from '@stomp/stompjs';
import {GameChangeNotifierService} from '../../../service/game-change-notifier.service';
import {interval} from 'rxjs';
import {tap} from 'rxjs/operators';

@UntilDestroy()
@Component({
  selector: 'app-display-live-game',
  templateUrl: './display-live-game.component.html',
  styleUrls: ['./display-live-game.component.scss']
})
export class DisplayLiveGameComponent implements OnInit {
  liveGame: Game;
  private hallId: any;
  hasNoLiveGames: boolean;
  private changeCount: number = 0;
  showAdd: boolean;
  addCount = 0;
  adds = [
    '/assets/adds/2022_CKW.svg',
    '/assets/adds/2022_GTEstermann.svg',
    '/assets/adds/2022_Vitaluce.svg',
    '/assets/adds/Bandenwerbung_1386.svg',
    '/assets/adds/Bell.jpeg',
    '/assets/adds/Blesshess.PNG',
    '/assets/adds/Coiffure_cmyk.png',
    '/assets/adds/Concordia_2022.png',
    '/assets/adds/Creative_Foto_AG.jpeg',
    '/assets/adds/Die Mobiliar.PNG',
    '/assets/adds/Duss_Kuchen.PNG',
    '/assets/adds/Elektro Widmer.PNG',
    '/assets/adds/Fahrschule Müller.PNG',
    '/assets/adds/Garage Fünfstern AG.png',
    '/assets/adds/Garage Helfenstein.jpeg',
    '/assets/adds/Hotel zum Roten Löwen.jpeg',
    '/assets/adds/InseratWolf_Zimmerei.svg',
    '/assets/adds/Konzept5_Logo.svg',
    '/assets/adds/Limacher Treuhand.png',
    '/assets/adds/LKB_PROB.JPG',
    '/assets/adds/Logo GravoCorner.png',
    '/assets/adds/Logo HuwylerUnihockey.jpg',
    '/assets/adds/Logo mit Name und Ort.jpg',
    '/assets/adds/Logo_Spartakus_lang_def.png',
    '/assets/adds/Marco-Zemp-Baloise.svg',
    '/assets/adds/Ottos AG.jpg',
    '/assets/adds/Praxis für Homöopathie.PNG',
    '/assets/adds/Raiffeisen Logo.jpg',
    '/assets/adds/Rüttimann-Logo S_W.jpg',
    '/assets/adds/SCH+£RCH_amstutz_regio_fg.jpg',
    '/assets/adds/Schlosserei Daniel Estermann.png',
    '/assets/adds/Schreinerei Gassmann_2022.png',
    '/assets/adds/Schreinerei-Schuler-AG.svg',
    '/assets/adds/Sportagon.png',
    '/assets/adds/Sportunion Hildisrieden.png',
    '/assets/adds/Therapie & Trainingscenter Hildisrieden.PNG',
    '/assets/adds/Tschopp Holzbau.png',
    '/assets/adds/Werbeblache_Barbara Hair_neu.svg',
    '/assets/adds/x-ride.PNG',
    '/assets/adds/Zwick Storen.jpeg',
    '/assets/adds/Zwyssig Bäckerei.jpeg',
  ];

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
      this.liveGame = liveGames[0];
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
