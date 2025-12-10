import {Component, inject, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ShotGameService} from '../../../../service/shotgame.service';
import {ShotChangeNotifierService} from '../../../../service/shot-change-notifier.service';
import {DestroyRef} from '@angular/core';
import {takeUntilDestroyed} from '@angular/core/rxjs-interop';
import {fromEvent} from 'rxjs';
import {debounceTime} from 'rxjs/operators';

@Component({
    selector: 'app-public-shotgame',
    imports: [CommonModule],
    templateUrl: './public-shotgame.component.html',
    styleUrls: ['./public-shotgame.component.css']
})
export class PublicShotgameComponent implements OnInit {
  private shotService = inject(ShotGameService);
  private shotChangeNotifier = inject(ShotChangeNotifierService);
  private destroyRef = inject(DestroyRef);

  shots: any[] = [];
  shotsPromise = this.shotService.getRanking();
  
  // track previous shots for change detection
  private previousShots: Map<number, number> = new Map();
  private previousRanks: Map<number, number> = new Map();
  // teams that recently changed (for animation)
  animatingTeams: Set<number> = new Set();
  rankChangeTeams: Map<number, 'up' | 'down'> = new Map();

  reload() {
    this.shotsPromise = this.shotService.getRanking().then(data => {
      // detect changes and trigger animations
      (data || []).forEach((shot, index) => {
        const teamId = shot.team?.id;
        const newCount = shot.shots || 0;
        const oldCount = this.previousShots.get(teamId);
        const oldRank = this.previousRanks.get(teamId);
        const newRank = index;
        
        if (oldCount !== undefined && oldCount !== newCount) {
          // shots changed, trigger animation
          this.triggerAnimation(teamId);
          if (newCount > oldCount) {
            this.triggerFireworks(teamId);
          }
        }

        // detect rank change
        if (oldRank !== undefined && oldRank !== newRank) {
          if (newRank < oldRank) {
            // moved up in ranking
            this.rankChangeTeams.set(teamId, 'up');
          } else {
            // moved down in ranking
            this.rankChangeTeams.set(teamId, 'down');
          }
          
          // clear animation after 2s
          setTimeout(() => {
            this.rankChangeTeams.delete(teamId);
          }, 2000);
        }

        this.previousShots.set(teamId, newCount);
        this.previousRanks.set(teamId, newRank);
      });
      
      this.shots = data || [];
      // after DOM updates, adjust team name sizes to fit
      setTimeout(() => this.adjustAllTeamNames(), 0);
      return data;
    });
  }

  private adjustAllTeamNames() {
    // run in next paint to ensure layout is stable
    requestAnimationFrame(() => {
      (this.shots || []).forEach(s => {
        const id = s.team?.id;
        if (id != null) {
          const el = document.getElementById(`team-name-${id}`) as HTMLElement | null;
          if (el) this.adjustFontSizeToFit(el);
        }
      });
    });
  }

  private adjustFontSizeToFit(el: HTMLElement) {
    // store numeric original font-size once
    let orig = el.dataset['originalFontSizeNumeric'];
    const cs = window.getComputedStyle(el);
    if (!orig) {
      orig = (parseFloat(cs.fontSize || '16')).toString();
      el.dataset['originalFontSizeNumeric'] = orig;
    }

    // restore original size first
    el.style.fontSize = parseFloat(el.dataset['originalFontSizeNumeric'] || '16') + 'px';

    const minSize = 12; // px minimum
    const curSize = parseFloat(window.getComputedStyle(el).fontSize || '16');

    // if text overflows horizontally, compute proportional size in one step
    if (el.scrollWidth > el.clientWidth) {
      const ratio = el.clientWidth / el.scrollWidth;
      let newSize = Math.floor(curSize * ratio);
      if (newSize < minSize) newSize = minSize;
      el.style.fontSize = newSize + 'px';
      // if still overflows, finally set ellipsis (CSS will handle)
    }
  }

  private triggerAnimation(teamId: number) {
    this.animatingTeams.add(teamId);
    setTimeout(() => {
      this.animatingTeams.delete(teamId);
    }, 600);
  }

  private triggerFireworks(teamId: number) {
    // create multiple confetti particles
    const container = document.getElementById(`team-${teamId}`);
    if (!container) return;
    
    const rect = container.getBoundingClientRect();
    for (let i = 0; i < 30; i++) {
      const confetti = document.createElement('div');
      confetti.className = 'confetti';
      confetti.style.left = rect.left + rect.width / 2 + 'px';
      confetti.style.top = rect.top + 'px';
      confetti.style.position = 'fixed';
      confetti.style.width = '8px';
      confetti.style.height = '8px';
      confetti.style.pointerEvents = 'none';
      confetti.style.borderRadius = '50%';
      
      const colors = ['#fbbf24', '#34d399', '#3b82f6', '#f87171', '#a78bfa'];
      confetti.style.backgroundColor = colors[Math.floor(Math.random() * colors.length)];
      
      document.body.appendChild(confetti);
      
      const angle = (Math.PI * 2 * i) / 30;
      const velocity = 3 + Math.random() * 4;
      const vx = Math.cos(angle) * velocity;
      const vy = Math.sin(angle) * velocity - 2;
      
      let x = parseFloat(confetti.style.left);
      let y = parseFloat(confetti.style.top);
      let life = 100;
      
      const animate = () => {
        x += vx;
        y += vy;
        life -= 0.35;
        
        confetti.style.left = x + 'px';
        confetti.style.top = y + 'px';
        confetti.style.opacity = (life / 100).toString();
        
        if (life > 0) {
          requestAnimationFrame(animate);
        } else {
          confetti.remove();
        }
      };
      
      animate();
    }
  }

  ngOnInit(): void {
    // initial load
    this.reload();
    // subscribe to shot changes and reload
    this.shotChangeNotifier.shotChanges$.pipe(takeUntilDestroyed(this.destroyRef)).subscribe(() => this.reload());
    // adjust on window resize
    fromEvent(window, 'resize').pipe(debounceTime(150), takeUntilDestroyed(this.destroyRef)).subscribe(() => this.adjustAllTeamNames());
  }
  
  ngOnDestroy(): void {
    // no-op, takeUntilDestroyed handles subscriptions
  }
}
