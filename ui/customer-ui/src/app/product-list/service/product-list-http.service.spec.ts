import { TestBed } from '@angular/core/testing';

import { ProductListHttpService } from './product-list-http.service';

describe('ProductListHttpService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProductListHttpService = TestBed.get(ProductListHttpService);
    expect(service).toBeTruthy();
  });
});
