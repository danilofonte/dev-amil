package builder;

import java.util.Date;

import models.Arma;
import models.HistoricoPartida;
import models.Jogador;
import models.Partida;
import enums.TipoAcaoEnum;

public class HistoricoPartidaBuilder extends
		DefaultModelBuilder<HistoricoPartida> {

	public HistoricoPartidaBuilder() {
		super(new HistoricoPartida());
	}

	@Override
	public HistoricoPartidaBuilder padrao() {

		model.dataAcao = new Date();
		model.tipo = TipoAcaoEnum.KILLED;

		return this;

	}

	public HistoricoPartidaBuilder comPartida(Partida partida) {

		model.partida = partida;

		return this;

	}

	public HistoricoPartidaBuilder comJogadorExecutorAcao(Jogador jogador) {

		model.jogadorExecutouAcao = jogador;

		return this;

	}

	public HistoricoPartidaBuilder comJogadorAlvoAcao(Jogador jogador) {

		model.jogadorRecebeuAcao = jogador;

		return this;

	}

	public HistoricoPartidaBuilder comArma(Arma arma) {

		model.arma = arma;

		return this;

	}

	public HistoricoPartidaBuilder comTipoAcao(TipoAcaoEnum tipo) {

		model.tipo = tipo;

		return this;

	}

	public HistoricoPartida save() {

		model.partida.save();
		model.jogadorExecutouAcao.save();
		model.jogadorRecebeuAcao.save();
		model.arma.save();

		return model.save();

	}

}
