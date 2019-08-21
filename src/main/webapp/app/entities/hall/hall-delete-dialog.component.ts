import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHall } from 'app/shared/model/hall.model';
import { HallService } from './hall.service';

@Component({
  selector: 'jhi-hall-delete-dialog',
  templateUrl: './hall-delete-dialog.component.html'
})
export class HallDeleteDialogComponent {
  hall: IHall;

  constructor(protected hallService: HallService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.hallService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'hallListModification',
        content: 'Deleted an hall'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-hall-delete-popup',
  template: ''
})
export class HallDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ hall }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(HallDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.hall = hall;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/hall', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/hall', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
