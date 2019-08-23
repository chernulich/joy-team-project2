import { TestBed } from '@angular/core/testing';

import { SliderService } from './slider.service';

describe('SliderServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SliderServiceService = TestBed.get(SliderServiceService);
    expect(service).toBeTruthy();
  });
});
