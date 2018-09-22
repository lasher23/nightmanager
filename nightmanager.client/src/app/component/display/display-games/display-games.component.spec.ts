import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayGamesComponent } from './display-games.component';

describe('DisplayGamesComponent', () => {
  let component: DisplayGamesComponent;
  let fixture: ComponentFixture<DisplayGamesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayGamesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayGamesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
