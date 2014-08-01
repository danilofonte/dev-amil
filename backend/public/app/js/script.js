(function(exports, global) {
    global["app"] = exports;
    (function($) {
        var modulo = angular.module("appModule", [ "ngRoute", "ui.select2", "mascaras", "textAngular" ]);
        modulo.config([ "$routeProvider", function($routeProvider) {
            $routeProvider.when("", {
                templateUrl: "",
                controller: ""
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
})({}, function() {
    return this;
}());