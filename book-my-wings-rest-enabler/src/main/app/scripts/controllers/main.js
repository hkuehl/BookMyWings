'use strict';

/**
 * @ngdoc function
 * @name clientTestApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the clientTestApp
 */
angular.module('clientTestApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
