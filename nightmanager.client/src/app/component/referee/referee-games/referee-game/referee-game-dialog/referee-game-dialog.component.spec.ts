import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RefereeGameDialogComponent } from './referee-game-dialog.component';

describe('RefereeGameDialogComponent', () => {
  let component: RefereeGameDialogComponent;
  let fixture: ComponentFixture<RefereeGameDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RefereeGameDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RefereeGameDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
