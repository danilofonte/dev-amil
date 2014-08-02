package utils;

import models.Arma;
import models.HistoricoPartida;
import models.Jogador;
import models.Partida;
import play.test.Fixtures;

public class DatabaseCleaner {

	public static void cleanAll() {
		
	
		
		Fixtures.delete(
				HistoricoPartida.class,
				Arma.class,
				Jogador.class,
				Partida.class
				);
		
		
	}

}
