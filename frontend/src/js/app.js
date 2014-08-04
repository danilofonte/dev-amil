(function($) {

	var modulo = angular.module('appModule', ['ngRoute']);

	modulo.config(['$routeProvider',

		function($routeProvider) {

			$routeProvider				
				.when('/', {
					templateUrl: '/home/inicial.html',
					controller: 'InicialCtrl'
				})				
				.otherwise({
					redirectTo: '/'
				});
	}])

  .controller('AppCtrl', ["$scope", "$rootScope", "$location", "$timeout", "config"
    function($scope, $rootScope, $location, $timeout, config) {


  }]);

	

})(jQuery);
