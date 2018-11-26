import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayCategoryOverviewComponent } from './display-category-overview.component';

describe('DisplayCategoryOverviewComponent', () => {
  let component: DisplayCategoryOverviewComponent;
  let fixture: ComponentFixture<DisplayCategoryOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayCategoryOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayCategoryOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
