/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NightmanagerTestModule } from '../../../test.module';
import { HallDetailComponent } from 'app/entities/hall/hall-detail.component';
import { Hall } from 'app/shared/model/hall.model';

describe('Component Tests', () => {
  describe('Hall Management Detail Component', () => {
    let comp: HallDetailComponent;
    let fixture: ComponentFixture<HallDetailComponent>;
    const route = ({ data: of({ hall: new Hall(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NightmanagerTestModule],
        declarations: [HallDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(HallDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(HallDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.hall).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
