(function() {

	var modulo = angular.module('appModule');

	modulo.service('partidaService', ["$http", "$rootScope", "config", function($http, $rootScope, config) {

		var service = {};

		service.list = function(successCallback, errorCallback) {
			$http({
				method: "GET",
				url: config.BASE_URL + "partidas",
				cache: true
			}).success(successCallback).error(function(data) {
			});
		};

		service.mortesPorJogador = function(idPartida,successCallback, errorCallback) {
			$http({
				method: "GET",
				url: config.BASE_URL + "mortes/"+idPartida,
				cache: true
			}).success(successCallback).error(function(data) {
			});
		};

    service.armaFavoritaMelhorJogador = function(idPartida,successCallback, errorCallback) {
      $http({
        method: "GET",
        url: config.BASE_URL + "arma/"+idPartida,
        cache: true
      }).success(successCallback).error(function(data) {
      });
    };

		return service;

	}]);

})();