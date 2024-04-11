import {Component, OnInit} from '@angular/core';
import {TeamService} from '../../../service/team.service';
import {Team} from '../../../model/Team';
import {debug} from 'util';

@Component({
  selector: 'app-admin-teams',
  templateUrl: './admin-teams.component.html',
  styleUrls: ['./admin-teams.component.scss']
})
export class AdminTeamsComponent implements OnInit {
  teams: Team[];
  teamsFiltered: Team[];
  displayedColumns = ['name', 'paid'];
  search: string = '';

  constructor(private teamService: TeamService) {
  }

  ngOnInit() {
    this.teamService.getAll().then(teams => teams.filter(team => !team.placeholder)).then(teams => {
      this.teams = teams;
      this.teamsFiltered = this.teams;
    });
  }

  filter() {
    this.teamsFiltered = this.teams.filter(team => team.name.toLowerCase().includes(this.search.toLowerCase()));
  }

  save(team: Team) {
    this.teamService.save(team);
  }
}
