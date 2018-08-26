import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RefereeTeamComponent } from './referee-team.component';

describe('RefereeTeamComponent', () => {
  let component: RefereeTeamComponent;
  let fixture: ComponentFixture<RefereeTeamComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RefereeTeamComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RefereeTeamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
