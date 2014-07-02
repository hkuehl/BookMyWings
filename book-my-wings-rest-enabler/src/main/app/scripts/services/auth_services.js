'use strict';

/**
 * @ngdoc service
 * @name clientTestApp.Auth
 * @description # Auth Factory in the clientTestApp.
 */
angular.module('clientTestApp').factory(
		'AuthService',
		function($http) {
			var currentUser;

			return {
				login : function(username, password) {
					$http.get(
							'http://localhost:8080/book-my-wings/rest/pilots/'
									+ username)
					.success(function(data) {
						currentUser = data;
					}).error(function(data, status) {

					});
				},
				isLoggedIn : function() {
					return currentUser.id;
				},
				currentUser : function() {
					return currentUser;
				}
			};
		})
		.factory(
				'LoginRedirectInterceptor',
				function($q, $window, $location) {
					return function(promise) {
						var success = function(response) {
							return response;
						};

						var error = function(response) {
							if (response.status === 401
									|| response.status === 403) {
								var path = $location.path();
								$location.url('/login'
										+ (path ? '?redir=' + path : ''));
							}

							return $q.reject(response);
						};

						return promise.then(success, error);
					};
				});
