import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RefereeGamesComponent } from './referee-games.component';

describe('RefereeGamesComponent', () => {
  let component: RefereeGamesComponent;
  let fixture: ComponentFixture<RefereeGamesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RefereeGamesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RefereeGamesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
