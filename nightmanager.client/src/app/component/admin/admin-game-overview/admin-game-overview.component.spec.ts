import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminGameOverviewComponent } from './admin-game-overview.component';

describe('AdminGameOverviewComponent', () => {
  let component: AdminGameOverviewComponent;
  let fixture: ComponentFixture<AdminGameOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminGameOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminGameOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
