package unit;

import java.util.ArrayList;
import java.util.List;

import models.Arma;
import models.HistoricoPartida;
import models.Jogador;
import models.Partida;

import org.junit.Before;
import org.junit.Test;

import enums.TipoAcaoEnum;
import exceptions.ValidationException;
import utils.DatabaseCleaner;
import builder.ArmaBuilder;
import builder.HistoricoPartidaBuilder;
import builder.JogadorBuilder;
import builder.PartidaBuilder;

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

			arma1 = new ArmaBuilder().padrao().save();
			arma2 = new ArmaBuilder().padrao().save();

			jogador1 = new JogadorBuilder().padrao().save();
			jogador2 = new JogadorBuilder().padrao().comNome("João").save();

			historico1 = new HistoricoPartidaBuilder().padrao()
					.comJogadorAlvoAcao(jogador1)
					.comJogadorExecutorAcao(jogador2).comArma(arma1).build();
			historico2 = new HistoricoPartidaBuilder().padrao()
					.comJogadorAlvoAcao(jogador1)
					.comJogadorExecutorAcao(jogador2).comArma(arma1).build();

			partida1 = new PartidaBuilder().padrao().build();
			
			partida2= new PartidaBuilder().padrao().build();

		}

		@Test
		public void deveIniciarPartida() {

			historico1.tipo = TipoAcaoEnum.STARTED;

			assertNotNull(partida1.iniciarPartida(historico1));

			assertTrue(partida1.historico.size() == 1);

		}

		@Test
		public void deveAtualizarHistoricoDaPartida() {

			partida1.save();

			List<HistoricoPartida> lista = new ArrayList<HistoricoPartida>();
			lista.add(historico1);
			lista.add(historico2);

			partida1.atualizarHistorico(lista);

			assertTrue(HistoricoPartida.find("partida.id", partida1.id).fetch()
					.size() == 2);

			assertTrue(partida1.historico.size() == 2);

		}

		@Test
		public void deveAtualizarListaDeJogadoresDaPartida() {

			partida1.save();

			List<Jogador> lista = new ArrayList<Jogador>();
			lista.add(jogador1);
			lista.add(jogador2);

			partida1.atualizarListaJogadores(lista);

			assertTrue(partida1.jogadores.size() == 2);

		}

		@Test
		public void deveFinalizarPartida() {

			historico1.tipo = TipoAcaoEnum.ENDED;
			historico1.identificadorPartida = partida1.identificadorPartida;

			assertNotNull(partida1.finalizarPartida(historico1));
			
			assertTrue(partida1.dataFim != null);

			assertTrue(partida1.historico.size() == 1);

		}

		@Test(expected = ValidationException.class)
		public void naoDeveFinalizarPartidaComTipoAcaoErrado() {

			partida1.finalizarPartida(historico1);

		}
		
		@Test(expected = ValidationException.class)
		public void naoDeveFinalizarPartidaErrada() {
			
			historico1.tipo = TipoAcaoEnum.ENDED;

			partida1.finalizarPartida(historico1);

		}
		
		@Test(expected = ValidationException.class)
		public void naoDeveIniciarPartidaJaIniciada() {
			
			historico1.tipo = TipoAcaoEnum.STARTED;
			historico2.tipo = TipoAcaoEnum.STARTED;
			
			assertNotNull(partida1.iniciarPartida(historico1));					
			partida2.iniciarPartida(historico2);
			assertTrue(partida2.dataInicio == null);
			
		}

	}

}
