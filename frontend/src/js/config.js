(function() {

	var modulo = angular.module('appModule');

	modulo.factory('config', function() {

		//local
		var defaultConfig = {
			BASE_URL: '/',
		};

		return defaultConfig;
	});

})();