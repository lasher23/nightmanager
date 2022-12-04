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
    '/assets/adds/1386.jpg',
    '/assets/adds/20131008_Bande x-ride.jpg',
    '/assets/adds/Barbara HM.jpg',
    '/assets/adds/Bell.jpg',
    '/assets/adds/Biosphäre Berg-Käserei Entlebuch.jpg',
    '/assets/adds/Coiffure_cmyk.jpg',
    '/assets/adds/Creative Foto.jpg',
    '/assets/adds/Duss.jpg',
    '/assets/adds/Elektro Widmer-1.jpg',
    '/assets/adds/Fahrschule Müller.jpg',
    '/assets/adds/Felicitas Andermatt1.jpg',
    '/assets/adds/Frey Bäck.jpg',
    '/assets/adds/Garage Helfenstein.jpg',
    '/assets/adds/Huwyler.jpg',
    '/assets/adds/Konzept 5.jpg',
    '/assets/adds/Limacher Treuhand.png',
    '/assets/adds/Logo_Slogan_4farbCMYK mit Adresse.jpg',
    '/assets/adds/LUKB.JPG',
    '/assets/adds/Maler Lipp.jpg',
    '/assets/adds/Mobiliar.jpg',
    '/assets/adds/Nr.15_300dpi.jpg',
    '/assets/adds/Raiffeisen.jpg',
    '/assets/adds/RoterLoewen.jpg',
    '/assets/adds/Rüttimann-Logo S_W.jpg',
    '/assets/adds/Schlosserei Daniel Estermann.png',
    '/assets/adds/Schreinerei Schuler AG.jpg',
    '/assets/adds/Schürch Getränke.jpg',
    '/assets/adds/sl_lu_wappen1.jpg',
    '/assets/adds/Spartakus.png',
    '/assets/adds/Topharm Maunz.jpg',
    '/assets/adds/Tschopp Holzbau.jpg',
    '/assets/adds/Zimmerei Wolf.jpg',
    '/assets/adds/Zwick Storen.jpg',
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
