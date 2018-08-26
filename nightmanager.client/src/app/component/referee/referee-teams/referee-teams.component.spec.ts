import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RefereeTeamsComponent } from './referee-teams.component';

describe('RefereeTeamsComponent', () => {
  let component: RefereeTeamsComponent;
  let fixture: ComponentFixture<RefereeTeamsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RefereeTeamsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RefereeTeamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
