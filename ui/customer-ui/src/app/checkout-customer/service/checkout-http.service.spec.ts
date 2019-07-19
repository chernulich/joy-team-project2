import {TestBed} from '@angular/core/testing';
import {CheckoutHttpService} from './checkout-http.service'


describe('CheckoutHttpService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CheckoutHttpService = TestBed.get(CheckoutHttpService);
    expect(service).toBeTruthy();
  });
});
