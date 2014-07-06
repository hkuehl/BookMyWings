'use strict';

describe('Controller: MybookingsCtrl', function () {

  // load the controller's module
  beforeEach(module('clientTestApp'));

  var MybookingsCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    MybookingsCtrl = $controller('MybookingsCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
