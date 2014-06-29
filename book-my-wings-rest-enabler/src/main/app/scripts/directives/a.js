'use strict';

/**
 * @ngdoc directive
 * @name clientTestApp.directive:a
 * @description
 * # a
 */
angular.module('clientTestApp')
.directive('a', function() {
    return {
        restrict: 'E',
        link: function(scope, elem, attrs) {
            if(attrs.ngClick || attrs.href === '' || attrs.href === '#'){
                elem.on('click', function(e){
                    e.preventDefault();
                });
            }
        }
   };
  });
