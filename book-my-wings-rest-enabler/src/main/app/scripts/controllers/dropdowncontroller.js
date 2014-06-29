'use strict';

/**
 * @ngdoc function
 * @name clientTestApp.controller:DropdowncontrollerCtrl
 * @description # DropdowncontrollerCtrl Controller of the clientTestApp
 */
angular
		.module('clientTestApp')
		.controller(
				'DropdowncontrollerCtrl',
				function($scope, $http) {

					$scope.title = 'Please choose aircraft';

					$scope.status = {
						isopen : false
					};

					$scope.toggled = function(aircraft) {
						$scope.title = aircraft;
						$scope.status.isopen = !$scope.status.isopen;
					};

					$http
							.get(
									'http://localhost:8080/book-my-wings/rest/aircrafts?start=0&pageSize=100')
							.success(function(data) {
								$scope.aircrafts = data;
							}).error(function(data, status) {

							});

				});
