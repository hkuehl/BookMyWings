'use strict';

/**
 * @ngdoc function
 * @name clientTestApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the clientTestApp
 */
angular.module('clientTestApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
