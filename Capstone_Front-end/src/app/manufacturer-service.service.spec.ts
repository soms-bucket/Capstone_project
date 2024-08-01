import { TestBed } from '@angular/core/testing';

import { ManufacturerServiceService } from './manufacturer-service.service';

describe('ManufacturerServiceService', () => {
  let service: ManufacturerServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ManufacturerServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
