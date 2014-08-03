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

public class HistoricoPartidaTest {

	public static class SalvarAtualizarRemoverHistoricoPartida extends
			DefaultUnitTest {

		private static Partida partida1;
		private static HistoricoPartida historico1;
		private static Jogador jogador1, jogador2;
		private static Arma arma1;

		@Before
		public void before() {

			DatabaseCleaner.cleanAll();
			commitTransaction();		
			
			arma1 = new ArmaBuilder().padrao().save();

			jogador1 = new JogadorBuilder().padrao().save();
			jogador2 = new JogadorBuilder().padrao().comNome("João").save();

			partida1 = new PartidaBuilder().padrao().save();
			
			historico1 = new HistoricoPartidaBuilder().padrao().comJogadorAlvoAcao(jogador1).comJogadorExecutorAcao(jogador2).comArma(arma1).build();

		}
		
		@Test
		public void deveSalvarHistoricoPartida() {
			
			assertNotNull(historico1.inserirHistoricoPartida(partida1));
			
		}
		
		@Test(expected = ValidationException.class)
		public void naoDeveSalvarSemPartida() {
			
			historico1.inserirHistoricoPartida(null);
			
		}
		
		@Test(expected = ValidationException.class)
		public void naoDeveSalvarSemDataAcao() {
			
			historico1.dataAcao = null;
			
			historico1.inserirHistoricoPartida(partida1);
			
		}
		
		@Test(expected =  ValidationException.class)
		public void naoDeveSalvarHistoricoInconsistente() {
			
			historico1.jogadorExecutouAcao = null;
			
			historico1.inserirHistoricoPartida(partida1);
			
		}
		
	}

}
