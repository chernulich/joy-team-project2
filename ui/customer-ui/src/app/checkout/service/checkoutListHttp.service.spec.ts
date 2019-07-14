import {TestBed} from '@angular/core/testing';
import {CheckoutListHttpService} from './checkoutListHttp.service'


describe('CheckoutListHttpService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CheckoutListHttpService = TestBed.get(CheckoutListHttpService);
    expect(service).toBeTruthy();
  });
});
