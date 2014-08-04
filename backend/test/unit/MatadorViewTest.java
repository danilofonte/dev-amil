package unit;

import java.util.ArrayList;
import java.util.List;

import models.Arma;
import models.HistoricoPartida;
import models.Jogador;
import models.Partida;
import models.views.MatadorView;

import org.junit.Before;
import org.junit.Test;

import utils.DatabaseCleaner;
import builder.ArmaBuilder;
import builder.HistoricoPartidaBuilder;
import builder.JogadorBuilder;
import builder.PartidaBuilder;

public class MatadorViewTest extends DefaultUnitTest {

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

		historico1 = new HistoricoPartidaBuilder().padrao().comJogadorAlvoAcao(jogador1).comJogadorExecutorAcao(jogador2).comArma(arma1).build();
		historico2 = new HistoricoPartidaBuilder().padrao().comJogadorAlvoAcao(jogador1).comJogadorExecutorAcao(jogador2).comArma(arma1).build();

		partida1 = new PartidaBuilder().padrao().build();

	}
	
	@Test
	public void deveMontarEstatisticas() {
		
		partida1.save();

		List<HistoricoPartida> lista = new ArrayList<HistoricoPartida>();
		lista.add(historico1);
		lista.add(historico2);

		partida1.atualizarHistorico(lista);
		
		assertTrue(MatadorView.getEstatisticaMatador(partida1.id).size() > 0);
	}

}
