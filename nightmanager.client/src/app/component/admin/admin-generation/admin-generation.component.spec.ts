import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminGenerationComponent } from './admin-generation.component';

describe('AdminGenerationComponent', () => {
  let component: AdminGenerationComponent;
  let fixture: ComponentFixture<AdminGenerationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminGenerationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminGenerationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
