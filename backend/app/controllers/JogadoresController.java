package controllers;

import java.util.List;

import models.HistoricoPartida;
import models.Jogador;
import models.views.MatadorView;
import serializers.JogadorSerializer;
import serializers.PartidaSerializer;

public class JogadoresController extends DefaultController {

	public static void list(Long idPartida) {

		List<Jogador> jogadores = Jogador
				.find("select jogador from " + HistoricoPartida.class.getSimpleName() + " historico" + " join historico.jogadorExecutouAcao jogador"
						+ " join historico.partida partida" + " where partida.id = :idPartida" + " and jogador.nome != :nome"
						+ " group by jogador.nome").setParameter("idPartida", idPartida).setParameter("nome", Jogador.JOGADOR_IA).fetch();

		renderJSON(jogadores, JogadorSerializer.jogadorSerializer);

	}

	public static void getJogador(Long idJogador) {

		renderJSON(Jogador.findById(idJogador), JogadorSerializer.dadosJogadorSerializer);

	}

	public static void getStreak(Long idJogador, Long idPartida) {

		Jogador jogador = Jogador.findById(idJogador);
		
		renderText(jogador.getStreak(idPartida));
		
	}

}
