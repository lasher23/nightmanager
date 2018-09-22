import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayHomeComponent } from './display-home.component';

describe('DisplayHomeComponent', () => {
  let component: DisplayHomeComponent;
  let fixture: ComponentFixture<DisplayHomeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayHomeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
