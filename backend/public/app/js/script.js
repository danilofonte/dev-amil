(function(exports, global) {
    global["app"] = exports;
    (function($) {
        var modulo = angular.module("appModule", [ "ngRoute" ]);
        modulo.config([ "$routeProvider", function($routeProvider) {
            $routeProvider.when("/", {
                templateUrl: "home/inicial.html",
                controller: "InicialCtrl"
            }).otherwise({
                redirectTo: "/"
            });
        } ]).controller("AppCtrl", [ "$scope", "$rootScope", "$location", "$timeout", "config", function($scope, $rootScope, $location, $timeout, config) {} ]);
    })(jQuery);
    (function() {
        var modulo = angular.module("appModule");
        modulo.factory("config", function() {
            var defaultConfig = {
                BASE_URL: "/"
            };
            return defaultConfig;
        });
    })();
    (function() {
        var modulo = angular.module("appModule");
        modulo.controller("InicialCtrl", [ "$scope", "$rootScope", "partidaService", function($scope, $rootScope, partidaService) {
            $scope.partida = {};
            partidaService.list(function(data) {
                $scope.partidas = data;
            });
            $scope.atualizarGraficoGeral = function() {
                partidaService.mortesPorJogador($scope.partida, function(data) {
                    $scope.mortes = data;
                    var mortes = [];
                    var nomes = [];
                    var qtds = [];
                    _.each($scope.mortes, function(jogador) {
                        var estatistica = [];
                        nomes.push(jogador.nome);
                        qtds.push(jogador.qtd);
                        estatistica.push(jogador.nome);
                        estatistica.push(jogador.qtd);
                        mortes.push(estatistica);
                    });
                    var plot1 = $.jqplot("pie1", [ mortes ], {
                        gridPadding: {
                            top: 0,
                            bottom: 38,
                            left: 0,
                            right: 0
                        },
                        seriesDefaults: {
                            renderer: $.jqplot.PieRenderer,
                            trendline: {
                                show: false
                            },
                            rendererOptions: {
                                padding: 8,
                                showDataLabels: true
                            }
                        },
                        legend: {
                            show: true,
                            placement: "outside",
                            rendererOptions: {
                                numberRows: 1
                            },
                            location: "s",
                            marginTop: "15px"
                        }
                    });
                    plot1 = $.jqplot("chart1", [ qtds ], {
                        animate: !$.jqplot.use_excanvas,
                        seriesDefaults: {
                            renderer: $.jqplot.BarRenderer,
                            pointLabels: {
                                show: true
                            }
                        },
                        axes: {
                            xaxis: {
                                renderer: $.jqplot.CategoryAxisRenderer,
                                ticks: nomes
                            }
                        },
                        highlighter: {
                            show: false
                        }
                    });
                });
            };
            partidaService.armaFavoritaMelhorJogador(idPartida, function(data) {
                $scope.arma = data;
            });
        } ]);
    })();
    (function() {
        var modulo = angular.module("appModule");
        modulo.service("partidaService", [ "$http", "$rootScope", "config", function($http, $rootScope, config) {
            var service = {};
            service.list = function(successCallback, errorCallback) {
                $http({
                    method: "GET",
                    url: config.BASE_URL + "partidas",
                    cache: true
                }).success(successCallback).error(function(data) {});
            };
            service.mortesPorJogador = function(idPartida, successCallback, errorCallback) {
                $http({
                    method: "GET",
                    url: config.BASE_URL + "mortes/" + idPartida,
                    cache: true
                }).success(successCallback).error(function(data) {});
            };
            service.armaFavoritaMelhorJogador = function(idPartida, successCallback, errorCallback) {
                $http({
                    method: "GET",
                    url: config.BASE_URL + "arma/" + idPartida,
                    cache: true
                }).success(successCallback).error(function(data) {});
            };
            return service;
        } ]);
    })();
})({}, function() {
    return this;
}());