'use strict';

/**
 * @ngdoc function
 * @name clientTestApp.controller:MybookingsCtrl
 * @description
 * # MybookingsCtrl
 * Controller of the clientTestApp
 */
angular.module('clientTestApp')
  .controller('MybookingsCtrl', function ($scope, $http, AuthService, Informer) {
  	$scope.events = [];
    $http
    .get(
    'http://localhost:8080/book-my-wings/rest/bookings/filter/pilot/' + AuthService.currentUser().id)
    .success(function(data) {
        angular.forEach(data, function (aBooking, key) {
        	$http.get('http://localhost:8080/book-my-wings/rest/bookings/booking/transitions/' + aBooking.bookingId).success(function(data) {
        		aBooking.transitions = data;
        	});

            $scope.events.push(aBooking);
        });
        
    }).error(function(data, status) {
        Informer.inform('Error reading your bookings: ' + data + status, 'error');
    });
  });
