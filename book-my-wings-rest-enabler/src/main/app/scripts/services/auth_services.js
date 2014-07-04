'use strict';

/**
 * @ngdoc service
 * @name clientTestApp.Auth
 * @description # Auth Factory in the clientTestApp.
 */
angular.module('clientTestApp').factory(
		'AuthService',
		function($http, $location) {
			var currentUser;
			var dummy = {
				id : 'dummy',
				pilotRole : {
					pilotRole : 'dummy'
				}
			};

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
					return currentUser.id != 'dummy';
				},
				currentUser : function() {
					return currentUser ? currentUser : dummy;
				},
				isAdmin : function() {
					return currentUser.pilotRole.pilotRole === 'admin';
				},
				logout : function() {
					$http.defaults.headers.common.authorization = '';
					currentUser = dummy;
					$location.url('/');
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
