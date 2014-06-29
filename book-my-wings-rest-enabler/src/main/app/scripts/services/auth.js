'use strict';

/**
 * @ngdoc service
 * @name clientTestApp.Auth
 * @description
 * # Auth
 * Factory in the clientTestApp.
 */
angular.module('clientTestApp')
  .factory('Auth', function Auth ($q, $window, $location) {
	  return function (promise) {
	      var success = function (response) {
	          return response;
	      };

	      var error = function (response) {
	          if (response.status === 401 || response.status === 403) {
	        	  var path = $location.path();
	              $location.url('/login'+'?redir='+path);
	          }

	          return $q.reject(response);
	      };

	      return promise.then(success, error);
	  };
  });
