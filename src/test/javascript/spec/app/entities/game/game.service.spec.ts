/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { GameService } from 'app/entities/game/game.service';
import { IGame, Game, GameState, GameType } from 'app/shared/model/game.model';

describe('Service Tests', () => {
  describe('Game Service', () => {
    let injector: TestBed;
    let service: GameService;
    let httpMock: HttpTestingController;
    let elemDefault: IGame;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(GameService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Game(0, 0, 0, GameState.OPEN, false, currentDate, 0, GameType.GROUP_STAGE);
    });

    describe('Service methods', () => {
      it('should find an element', async () => {
        const returnedFromService = Object.assign(
          {
            start: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a Game', async () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            start: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            start: currentDate
          },
          returnedFromService
        );
        service
          .create(new Game(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a Game', async () => {
        const returnedFromService = Object.assign(
          {
            goalsHome: 1,
            goalsGuest: 1,
            state: 'BBBBBB',
            placeholder: true,
            start: currentDate.format(DATE_TIME_FORMAT),
            duration: 1,
            type: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            start: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of Game', async () => {
        const returnedFromService = Object.assign(
          {
            goalsHome: 1,
            goalsGuest: 1,
            state: 'BBBBBB',
            placeholder: true,
            start: currentDate.format(DATE_TIME_FORMAT),
            duration: 1,
            type: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            start: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Game', async () => {
        const rxPromise = service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
