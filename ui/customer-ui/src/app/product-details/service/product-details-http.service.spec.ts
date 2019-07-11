import { TestBed } from '@angular/core/testing';

import { ProductDetailsHttpService } from './product-details-http.service';

describe('ProductDetailsHttpService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProductDetailsHttpService = TestBed.get(ProductDetailsHttpService);
    expect(service).toBeTruthy();
  });
});
