(function() {

	var modulo = angular.module('appModule');

	modulo.service('jogadorService', ["$http", "$rootScope", "config", function($http, $rootScope, config) {

		var service = {};

		service.list = function(idPartida,successCallback, errorCallback) {
			$http({
				method: "GET",
				url: config.BASE_URL + "jogadores/partida/"+idPartida,
				cache: true
			}).success(successCallback).error(function(data) {
			});
		};

		service.getJogador = function(idJogador,successCallback, errorCallback) {
			$http({
				method: "GET",
				url: config.BASE_URL + "jogadores/"+idJogador,
				cache: true
			}).success(successCallback).error(function(data) {
			});
		};

		service.getStreak = function(idJogador,idPartida,successCallback, errorCallback) {
			$http({
				method: "GET",
				url: config.BASE_URL + "jogadores/"+idJogador+"/streak/"+idPartida,
				cache: true
			}).success(successCallback).error(function(data) {
			});
		};

    

		return service;

	}]);

})();