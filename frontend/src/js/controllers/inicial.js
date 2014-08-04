(function() {

	var modulo = angular.module('appModule');

	modulo.controller('InicialCtrl', function($scope, $rootScope, partidaService) {

    $scope.partida = {};


    partidaService.list(function(data){

      $scope.partidas = data;

    });

    $scope.atualizarGraficoGeral = function() {

      partidaService.armaFavoritaMelhorJogador($scope.partida,function(data){

        $scope.arma = data;

      });

      partidaService.mortesPorJogador($scope.partida,function(data){

        $scope.mortes = data;

        var mortes = [];
        var nomes = [];
        var qtds = [];

        _.each($scope.mortes,function(jogador){
          var estatistica = [];

          nomes.push(jogador.nome);
          qtds.push(jogador.qtd);

          estatistica.push(jogador.nome);
          estatistica.push(jogador.qtd);
          mortes.push(estatistica);
        });

        var plot1 = $.jqplot('pie1', [mortes], {
           gridPadding: {top:0, bottom:38, left:0, right:0},
           seriesDefaults:{
               renderer:$.jqplot.PieRenderer, 
               trendline:{ show:false }, 
               rendererOptions: { padding: 8, showDataLabels: true }
           },
           legend:{
               show:true, 
               placement: 'outside', 
               rendererOptions: {
                   numberRows: 1
               }, 
               location:'s',
               marginTop: '15px'
           }       
        });

        plot1 = $.jqplot('chart1', [qtds], {
            // Only animate if we're not using excanvas (not in IE 7 or IE 8)..
            animate: !$.jqplot.use_excanvas,
            seriesDefaults:{
                renderer:$.jqplot.BarRenderer,
                pointLabels: { show: true }
            },
            axes: {
                xaxis: {
                    renderer: $.jqplot.CategoryAxisRenderer,
                    ticks: nomes
                }
            },
            highlighter: { show: false }
        });

      });
    };

    
		

	});

})();