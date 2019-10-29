import { TestBed } from '@angular/core/testing';

import { ProductResultService } from './product-result.service';

// @ts-ignore
describe('ProductResultService', () => {
  // @ts-ignore
  beforeEach(() => TestBed.configureTestingModule({}));

  // @ts-ignore
  it('should be created', () => {
    const service: ProductResultService = TestBed.get(ProductResultService);
    // @ts-ignore
    expect(service).toBeTruthy();
  });
});
