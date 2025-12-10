import {Component, inject, OnInit, OnDestroy} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ShotGameService} from '../../../service/shotgame.service';
import {TeamService} from '../../../service/team.service';
import {ShotChangeNotifierService} from '../../../service/shot-change-notifier.service';
import {DestroyRef} from '@angular/core';
import {takeUntilDestroyed} from '@angular/core/rxjs-interop';

@Component({
    selector: 'app-shot-master',
    imports: [CommonModule],
    templateUrl: './shot-master.component.html',
    styleUrls: ['./shot-master.component.css']
})
export class ShotMasterComponent implements OnInit, OnDestroy {
  private shotService = inject(ShotGameService);
  private teamService = inject(TeamService);
  private shotChangeNotifier = inject(ShotChangeNotifierService);
  private destroyRef = inject(DestroyRef);

  // combined entries containing team + shots (shots default 0 if no ShotGame exists)
  entries: Array<{team: any, shots: number}> = [];
  loading = false;

  // buffer for pending increments: teamId -> count
  private pendingBuffer = new Map<number, number>();
  // timer for auto-send
  private autoSendTimer: any = null;
  // auto-send delay in ms
  private readonly autoSendDelay = 3000; // 3 seconds

  private loadEntries(): Promise<Array<{team: any, shots: number}>> {
    return Promise.all([
      this.teamService.getAll(),
      this.shotService.getRanking()
    ]).then(([teams, shots]) => {
      const shotMap = new Map<number, number>();
      (shots || []).forEach(s => {
        if (s.team && s.team.id != null) {
          shotMap.set(s.team.id, s.shots || 0);
        }
      });

      // filter out placeholder teams and teams whose category has shotGameEnabled === false
      const filteredTeams = (teams || []).filter(t => {
        // remove placeholders
        if (t.placeholder) {
          return false;
        }
        // if category explicitly disables shot game, exclude
        if (t.category && t.category.shotGameEnabled === false) {
          return false;
        }
        return true;
      });

      const merged = filteredTeams.map(t => ({team: t, shots: shotMap.get(t.id) || 0}));
      // sort by shots desc
      merged.sort((a, b) => b.shots - a.shots);
      return merged;
    });
  }

  reload() {
    this.loading = true;
    this.loadEntries().then(merged => {
      // replace entries only after new data is ready to avoid flicker
      this.entries = merged;
      this.loading = false;
    }).catch(e => {
      console.error('Failed to load entries', e);
      this.loading = false;
    });
  }

  // get pending count for a team
  getPendingCount(teamId: number): number {
    return this.pendingBuffer.get(teamId) || 0;
  }

  // get total pending count across all teams
  getTotalPendingCount(): number {
    let total = 0;
    this.pendingBuffer.forEach(count => {
      total += count;
    });
    return total;
  }

  getAbsPendingCount(teamId: number): number {
    return Math.abs(this.getPendingCount(teamId));
  }

  incrementLocal(teamId: number) {
    const current = this.pendingBuffer.get(teamId) || 0;
    this.pendingBuffer.set(teamId, current + 1);
    // restart auto-send timer
    this.restartAutoSendTimer();
  }

  // decrement locally (buffer negative count)
  decrementLocal(teamId: number) {
    const current = this.pendingBuffer.get(teamId) || 0;
    this.pendingBuffer.set(teamId, current - 1);
    this.restartAutoSendTimer();
  }

  // check if any pending updates
  hasPending(): boolean {
    return this.pendingBuffer.size > 0;
  }

  // send all buffered increments
  async sendPending() {
    if (!this.hasPending()) {
      return;
    }

    this.clearAutoSendTimer();
    const buffer = Array.from(this.pendingBuffer.entries());
    this.pendingBuffer.clear();

    try {
      // send all buffered increments in parallel
      await Promise.all(
        buffer.map(([teamId, count]) =>
          this.shotService.increment(teamId, count)
        )
      );
      // reload to show updated ranking
      this.reload();
    } catch (e) {
      console.error('Failed to send pending increments', e);
      // restore buffer on error
      buffer.forEach(([teamId, count]) => {
        this.pendingBuffer.set(teamId, (this.pendingBuffer.get(teamId) || 0) + count);
      });
    }
  }

  private restartAutoSendTimer() {
    this.clearAutoSendTimer();
    this.autoSendTimer = setTimeout(() => {
      this.sendPending();
    }, this.autoSendDelay);
  }

  private clearAutoSendTimer() {
    if (this.autoSendTimer) {
      clearTimeout(this.autoSendTimer);
      this.autoSendTimer = null;
    }
  }

  ngOnInit(): void {
    // initial load
    this.reload();
    // subscribe to shot changes and reload
    this.shotChangeNotifier.shotChanges$.pipe(takeUntilDestroyed(this.destroyRef)).subscribe(() => this.reload());
  }

  ngOnDestroy(): void {
    // send any pending on destroy
    if (this.hasPending()) {
      this.sendPending();
    }
    this.clearAutoSendTimer();
  }

}
