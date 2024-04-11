import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Team} from '../../../model/Team';
import {TeamService} from '../../../service/team.service';

@Component({
  selector: 'app-teams',
  templateUrl: './referee-teams.component.html',
  styleUrls: ['./referee-teams.component.scss']
})
export class RefereeTeamsComponent implements OnInit {
  teams: Array<Team> = [];
  displayedColumns = ['id', 'name'];

  constructor(private teamService: TeamService, private router: Router) {
  }

  ngOnInit() {
    this.teamService.getAll().then(x => this.teams = x);
  }

  selectTableRow(team: Team) {
    this.router.navigateByUrl('/teams/' + team.id);
  }
}
