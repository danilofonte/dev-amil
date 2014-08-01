(function($) {

	var modulo = angular.module('appModule', ['ngRoute', 'ui.select2', 'mascaras', 'textAngular']);

	modulo.config(['$routeProvider',

		function($routeProvider) {

			$routeProvider				
				.when('', {
					templateUrl: '',
					controller: ''
				})				
				.otherwise({
					redirectTo: '/'
				});
	}]);

	

})(jQuery);
