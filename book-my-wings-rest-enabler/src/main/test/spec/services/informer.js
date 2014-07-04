'use strict';

describe('Service: Informer', function () {

  // load the service's module
  beforeEach(module('clientTestApp'));

  // instantiate service
  var Informer;
  beforeEach(inject(function (_Informer_) {
    Informer = _Informer_;
  }));

  it('should do something', function () {
    expect(!!Informer).toBe(true);
  });

});
