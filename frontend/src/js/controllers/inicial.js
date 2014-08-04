(function() {

	var modulo = angular.module('appModule');

	modulo.controller('InicialCtrl', function($scope, $rootScope, partidaService, jogadorService) {

    $scope.partida = {};

    $scope.jogador = {};

    

    $scope.vizualizarDadosJogador = function() {

      jogadorService.getJogador($scope.jogador,function(data){

        $scope.dadosJogador = data;

      });

      jogadorService.getStreak($scope.jogador,$scope.partida,function(data){

        $scope.streak = data;

      });


    };


    partidaService.list(function(data){

      $scope.partidas = data;

    });

    $scope.atualizarGraficoGeral = function() {

      jogadorService.list($scope.partida,function(data){

        $scope.jogadores = data;

      });


      partidaService.armaFavoritaMelhorJogador($scope.partida,function(data){

        $scope.arma = data;

      });

      partidaService.mortesPorJogador($scope.partida,function(data){

        $scope.mortes = data;

        var mortes = [];

        _.each($scope.mortes,function(jogador){
          var estatistica = [];

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



      });
    };

    
		

	});

})();