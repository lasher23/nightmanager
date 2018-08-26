import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RefereeGameComponent } from './referee-game.component';

describe('RefereeGameComponent', () => {
  let component: RefereeGameComponent;
  let fixture: ComponentFixture<RefereeGameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RefereeGameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RefereeGameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
