import {Component} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Team} from '../../../../model/Team';
import {TeamService} from '../../../../service/team.service';

@Component({
  selector: 'app-team',
  templateUrl: './referee-team.component.html',
  styleUrls: ['./referee-team.component.scss']
})
export class RefereeTeamComponent {
  private team: Team;

  constructor(private route: ActivatedRoute, private teamService: TeamService) {
    this.route.params.subscribe(x => this.getTeam(x['id']));
  }

  getTeam(id: number) {
    this.teamService.getTeam(id).then(x => this.team = x);
  }
}
