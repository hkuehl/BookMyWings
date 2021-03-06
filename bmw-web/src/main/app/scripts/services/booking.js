'use strict';

/**
 * @ngdoc service
 * @name clientTestApp.Booking
 * @description
 * # Booking
 * Factory in the clientTestApp.
 */
angular.module('clientTestApp')
  .factory('Booking', function ($http, AuthService, Informer) {

    // Public API here
    return {
      addBooking: function (booking) {
          $http
          .post('http://localhost:8080/book-my-wings/rest/bookings/booking', booking)
          .success(function(data) {
              Informer.inform('Successfully added booking', 'info');
          }).error(function(data, status) {
              Informer.inform('Error while adding booking: ' + data + status, 'info');
          });
      },
      getBookingsForPilot: function () {
            
            return $http
            .get(
            'http://localhost:8080/book-my-wings/rest/bookings?start=0&pageSize=100')
            .success(function(data) {
                var events = [];
                $.each(data, function (index, aBooking) {
                    var singleEvent = {
                            id : aBooking.bookingId,
                            title : aBooking.aircraft.registration + ' ' + aBooking.pilot.firstName,
                            start : new Date(aBooking.start),
                            end : new Date(aBooking.end),
                            allDay : false
                    }
                    events.push(singleEvent);
                });
                return events;
            }).error(function(data, status) {
                
            });
            
      }
    };
  });
