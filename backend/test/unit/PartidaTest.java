package unit;

import models.Arma;
import models.HistoricoPartida;
import models.Jogador;
import models.Partida;

import org.junit.Before;
import org.junit.Test;

import utils.DatabaseCleaner;
import builder.ArmaBuilder;
import builder.HistoricoPartidaBuilder;
import builder.JogadorBuilder;
import builder.PartidaBuilder;
import exceptions.ValidationException;

public class PartidaTest {

	public static class SalvarAtualizarRemoverPartida extends DefaultUnitTest {

		private static Partida partida1, partida2;
		private static HistoricoPartida historico1, historico2;
		private static Jogador jogador1, jogador2;
		private static Arma arma1, arma2;

		@Before
		public void before() {

			DatabaseCleaner.cleanAll();
			commitTransaction();

			arma1 = new ArmaBuilder().padrao().build().save();
			arma2 = new ArmaBuilder().padrao().build().save();

			jogador1 = new JogadorBuilder().padrao().build().save();
			jogador2 = new JogadorBuilder().padrao().comNome("João").build()
					.save();

			historico1 = new HistoricoPartidaBuilder().padrao()
					.comJogadorAlvoAcao(jogador1)
					.comJogadorExecutorAcao(jogador2).comArma(arma1).build();
			historico2 = new HistoricoPartidaBuilder().padrao()
					.comJogadorAlvoAcao(jogador1)
					.comJogadorExecutorAcao(jogador2).comArma(arma1).build();

			partida1 = new PartidaBuilder().padrao().build();

		}

		@Test
		public void deveIniciarPartida() {

			assertNotNull(partida1.save());

		}
		
		@Test
		public void deveAtualizarHistoricoDaPartida() {
			
			partida1
			
		}

	}

}
