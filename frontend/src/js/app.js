(function($) {

	var modulo = angular.module('appModule', ['ngRoute', 'ui.select2', 'textAngular']);

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
	}]);

	

})(jQuery);
