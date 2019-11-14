import { TestBed } from '@angular/core/testing';

import { CplayerserviceService } from './cplayerservice.service';

describe('CplayerserviceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CplayerserviceService = TestBed.get(CplayerserviceService);
    expect(service).toBeTruthy();
  });
});
