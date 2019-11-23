import { TestBed } from '@angular/core/testing';

import { ExampleHttpService } from './example-http.service';

describe('ExampleHttpService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ExampleHttpService = TestBed.get(ExampleHttpService);
    expect(service).toBeTruthy();
  });
});
