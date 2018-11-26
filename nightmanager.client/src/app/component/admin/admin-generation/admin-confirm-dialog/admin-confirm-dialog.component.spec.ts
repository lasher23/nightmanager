import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminConfirmDialogComponent } from './admin-confirm-dialog.component';

describe('AdminConfirmDialogComponent', () => {
  let component: AdminConfirmDialogComponent;
  let fixture: ComponentFixture<AdminConfirmDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminConfirmDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminConfirmDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
