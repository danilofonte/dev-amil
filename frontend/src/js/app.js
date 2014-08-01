(function($) {

	var modulo = angular.module('appModule', ['loadInterceptor', 'ngRoute', 'ui.select2', 'mascaras', 'textAngular']);

	modulo.config(['$routeProvider',

		function($routeProvider) {

			$routeProvider
				.when('/', {
					redirectTo: function() {

						if (app.Menu.instance)
							return app.Menu.instance.getUrlPaginaInicial();
						else
							return "";
					}
				})
				.when('', {
					templateUrl: '',
					controller: ''
				})				
				.otherwise({
					redirectTo: '/'
				});
	}]);

	

})(jQuery);
