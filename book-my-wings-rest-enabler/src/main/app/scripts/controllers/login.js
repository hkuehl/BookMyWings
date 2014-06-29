'use strict';

/**
 * @ngdoc function
 * @name clientTestApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the clientTestApp
 */
angular.module('clientTestApp')
  .controller('LoginCtrl', function ($scope, $http, $location, Base64) {

	  $scope.login = function(user) {
		  $http.defaults.headers.common.authorization = 'Basic ' + Base64.encode(user.name + ':' + user.pass);
    	  var redirectTo = $location.search()['redir'];
    	  
    	  if(redirectTo) {
    		  $location.url(redirectTo);
    		  $location.search('redir', '');
    	  }
	  }
  
  });
