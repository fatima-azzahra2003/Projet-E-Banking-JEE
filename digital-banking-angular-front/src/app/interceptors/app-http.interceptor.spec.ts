import { TestBed } from '@angular/core/testing';

import {appHttpInterceptor } from './app-http.interceptor';

describe('AppHttpInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      appHttpInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: appHttpInterceptor = TestBed.inject(appHttpInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
