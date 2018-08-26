import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RefereeHallsComponent } from './referee-halls.component';

describe('RefereeHallsComponent', () => {
  let component: RefereeHallsComponent;
  let fixture: ComponentFixture<RefereeHallsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RefereeHallsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RefereeHallsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
