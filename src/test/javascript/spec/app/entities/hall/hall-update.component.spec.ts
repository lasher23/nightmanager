/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NightmanagerTestModule } from '../../../test.module';
import { HallUpdateComponent } from 'app/entities/hall/hall-update.component';
import { HallService } from 'app/entities/hall/hall.service';
import { Hall } from 'app/shared/model/hall.model';

describe('Component Tests', () => {
  describe('Hall Management Update Component', () => {
    let comp: HallUpdateComponent;
    let fixture: ComponentFixture<HallUpdateComponent>;
    let service: HallService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NightmanagerTestModule],
        declarations: [HallUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(HallUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(HallUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HallService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Hall(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Hall();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
