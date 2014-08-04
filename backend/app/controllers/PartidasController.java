package controllers;

import models.Partida;
import models.views.ArmaMaisUsadaView;
import models.views.MatadorView;
import play.mvc.results.RenderJson;
import serializers.PartidaSerializer;

public class PartidasController extends DefaultController {
	
	public static void list() {
		
		renderJSON(Partida.findAll(),PartidaSerializer.partidaSerializer);
		
	}
	
	public static void estatisticaMortes(Long idPartida) {
		
		renderJSON(MatadorView.getEstatisticaMatador(idPartida),PartidaSerializer.viewSerializer);
		
	}
	
	public static void armaMaisUsada(Long idPartida) {
		
		renderJSON(ArmaMaisUsadaView.getEstatisticaArmaDoMelhorJogador(idPartida),PartidaSerializer.armaViewSerializer);
		
	}

}
