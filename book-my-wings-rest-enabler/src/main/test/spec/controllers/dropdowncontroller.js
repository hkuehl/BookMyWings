'use strict';

describe('Controller: DropdowncontrollerCtrl', function () {

  // load the controller's module
  beforeEach(module('clientTestApp'));

  var DropdowncontrollerCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    DropdowncontrollerCtrl = $controller('DropdowncontrollerCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
