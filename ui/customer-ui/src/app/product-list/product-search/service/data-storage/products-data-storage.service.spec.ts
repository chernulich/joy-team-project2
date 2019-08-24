import { TestBed } from '@angular/core/testing';

import { ProductsDataStorageService } from './products-data-storage.service';

describe('ProductsDataStorageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProductsDataStorageService = TestBed.get(ProductsDataStorageService);
    expect(service).toBeTruthy();
  });
});
