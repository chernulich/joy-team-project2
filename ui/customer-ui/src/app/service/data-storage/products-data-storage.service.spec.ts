import { TestBed } from '@angular/core/testing';

import { ProductsDataStorageService } from './products-data-storage.service';

// @ts-ignore
describe('ProductsDataStorageService', () => {
  // @ts-ignore
  beforeEach(() => TestBed.configureTestingModule({}));

  // @ts-ignore
  it('should be created', () => {
    const service: ProductsDataStorageService = TestBed.get(ProductsDataStorageService);
    // @ts-ignore
    expect(service).toBeTruthy();
  });
});
