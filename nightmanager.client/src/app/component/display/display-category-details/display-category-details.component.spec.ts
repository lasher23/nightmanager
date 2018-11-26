import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayCategoryDetailsComponent } from './display-category-details.component';

describe('DisplayCategoryDetailsComponent', () => {
  let component: DisplayCategoryDetailsComponent;
  let fixture: ComponentFixture<DisplayCategoryDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayCategoryDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayCategoryDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
