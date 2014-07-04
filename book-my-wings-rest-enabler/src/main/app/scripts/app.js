'use strict';

/**
 * @ngdoc overview
 * @name clientTestApp
 * @description
 * # clientTestApp
 *
 * Main module of the application.
 */
angular
  .module('clientTestApp', [
    'ngRoute', 'ui.calendar', 'ui.bootstrap'
  ])
  .config(function ($routeProvider, $httpProvider) {
      //register http-interceptor
//      $httpProvider.responseInterceptors.push('LoginRedirectInterceptor');
      
	  $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .when('/bookingCalendar', {
        templateUrl: 'views/bookingcalendar.html',
        controller: 'BookingcalendarCtrl'
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl'
      })
      .when('/myBookings', {
        templateUrl: 'views/mybookings.html',
        controller: 'MybookingsCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
      //Enable cross domain calls
      $httpProvider.defaults.useXDomain = true;
  });
