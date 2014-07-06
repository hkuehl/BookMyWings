'use strict';

/**
 * @ngdoc function
 * @name clientTestApp.controller:HeaderCtrl
 * @description # HeaderCtrl Controller of the clientTestApp
 */
angular.module('clientTestApp').controller('HeaderCtrl',
		function($scope, $location, AuthService) {
			$scope.$watch(AuthService.isLoggedIn, function(isLoggedIn) {
				$scope.isLoggedIn = isLoggedIn;
				$scope.user = AuthService.currentUser();
				$scope.isAdmin = AuthService.isAdmin();
			});
			
			$scope.active = function (path) {
				return path === $location.path();
			}

			$scope.logout = function () {
				AuthService.logout();
			}
			
		});
