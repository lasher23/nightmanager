import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {DisplayConfig, DisplayConfigService} from "../../../../service/display-config.service";
import {FormsModule} from "@angular/forms";

@Component({
    selector: 'app-display-v2',
    imports: [CommonModule, RouterLink, FormsModule],
    templateUrl: './display-home-v2.component.html',
    styleUrls: ['./display-home-v2.component.css']
})
export class DisplayHomeV2Component {
  config: DisplayConfig

  constructor(private router: Router, private displayConfigService: DisplayConfigService) {
    this.config = this.displayConfigService.getConfig()
  }


  async startDisplayMode() {
    this.displayConfigService.save(this.config)
    await this.router.navigate(["/display"], {queryParams: {displayMode: true}});
  }

}
