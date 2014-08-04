(function(exports, global) {
    global["app"] = exports;
    (function($) {
        var modulo = angular.module("appModule", [ "ngRoute", "ui.select2", "textAngular" ]);
        modulo.config([ "$routeProvider", function($routeProvider) {
            $routeProvider.when("/", {
                templateUrl: "/home/inicial.html",
                controller: "InicialCtrl"
            }).otherwise({
                redirectTo: "/"
            });
        } ]);
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
        modulo.controller("InicialCtrl", [ "$scope", "$rootScope", function($scope, $rootScope) {} ]);
    })();
})({}, function() {
    return this;
}());