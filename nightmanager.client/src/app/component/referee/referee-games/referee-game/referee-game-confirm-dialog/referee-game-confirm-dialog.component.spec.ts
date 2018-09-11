import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RefereeGameConfirmDialogComponent } from './referee-game-confirm-dialog.component';

describe('RefereeGameConfirmDialogComponent', () => {
  let component: RefereeGameConfirmDialogComponent;
  let fixture: ComponentFixture<RefereeGameConfirmDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RefereeGameConfirmDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RefereeGameConfirmDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
