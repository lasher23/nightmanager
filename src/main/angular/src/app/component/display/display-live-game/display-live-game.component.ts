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
  private previousHomeScore: number = 0;
  private previousGuestScore: number = 0;
  homeScoreChanged: boolean = false;
  guestScoreChanged: boolean = false;

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
      
      const currentHomeScore = this.liveGame?.swappedLiveDisplay ? this.liveGame?.goalsTeamGuest : this.liveGame?.goalsTeamHome;
      const currentGuestScore = this.liveGame?.swappedLiveDisplay ? this.liveGame?.goalsTeamHome : this.liveGame?.goalsTeamGuest;
      
      this.liveGame = liveGames[0];
      
      const newHomeScore = this.liveGame?.swappedLiveDisplay ? this.liveGame?.goalsTeamGuest : this.liveGame?.goalsTeamHome;
      const newGuestScore = this.liveGame?.swappedLiveDisplay ? this.liveGame?.goalsTeamHome : this.liveGame?.goalsTeamGuest;
      
      // Trigger fireworks if score changed
      if (newHomeScore > this.previousHomeScore) {
        this.triggerFireworks();
        this.homeScoreChanged = true;
        setTimeout(() => this.homeScoreChanged = false, 600);
      }
      if (newGuestScore > this.previousGuestScore) {
        this.triggerFireworks();
        this.guestScoreChanged = true;
        setTimeout(() => this.guestScoreChanged = false, 600);
      }
      
      this.previousHomeScore = newHomeScore;
      this.previousGuestScore = newGuestScore;
      
      this.gameService.getNextGame(this.liveGame).then(game => this.nextGame = game);
    });
  }

  private triggerFireworks() {
    const container = document.querySelector('.live-game-card');
    if (!container) return;
    
    const rect = container.getBoundingClientRect();
    const centerX = rect.left + rect.width / 2;
    const centerY = rect.top + rect.height / 2;
    
    const particleCount = 50;
    
    for (let i = 0; i < particleCount; i++) {
      const particle = document.createElement('div');
      particle.style.position = 'fixed';
      particle.style.width = '8px';
      particle.style.height = '8px';
      particle.style.borderRadius = '50%';
      particle.style.pointerEvents = 'none';
      particle.style.left = centerX + 'px';
      particle.style.top = centerY + 'px';
      
      const colors = ['#ff1744', '#f50057', '#d50000', '#ff6e40', '#ff9100', '#ffd600', '#76ff03', '#00e676', '#1de9b6', '#00e5ff', '#00b0ff', '#536dfe', '#651fff', '#d500f9'];
      particle.style.backgroundColor = colors[Math.floor(Math.random() * colors.length)];
      
      document.body.appendChild(particle);
      
      const angle = (Math.PI * 2 * i) / particleCount;
      const velocity = 5 + Math.random() * 5;
      const vx = Math.cos(angle) * velocity;
      const vy = Math.sin(angle) * velocity;
      
      let x = centerX;
      let y = centerY;
      let life = 100;
      
      const animate = () => {
        x += vx;
        y += vy + 0.3; // gravity
        life -= 0.35;
        
        particle.style.left = x + 'px';
        particle.style.top = y + 'px';
        particle.style.opacity = (life / 100).toString();
        
        if (life > 0) {
          requestAnimationFrame(animate);
        } else {
          particle.remove();
        }
      };
      
      animate();
    }
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
