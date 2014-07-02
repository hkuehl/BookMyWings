'use strict';

/**
 * @ngdoc function
 * @name clientTestApp.controller:LoginCtrl
 * @description # LoginCtrl Controller of the clientTestApp
 */
angular.module('clientTestApp').controller(
		'LoginCtrl',
		function($scope, $http, $location, Base64, AuthService) {

			$scope.login = function(user) {
				$http.defaults.headers.common.authorization = 'Basic '
						+ Base64.encode(user.name + ':' + user.pass);
				AuthService.login(user.name, user.pass);

				var redirectTo = $location.search()['redir'];

				$location.url(redirectTo ? redirectTo : '/');
			}
		});
