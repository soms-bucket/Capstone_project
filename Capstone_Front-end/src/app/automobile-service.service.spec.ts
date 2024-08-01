import { TestBed } from '@angular/core/testing';

import { AutomobileServiceService } from './automobile-service.service';

describe('AutomobileServiceService', () => {
  let service: AutomobileServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutomobileServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
