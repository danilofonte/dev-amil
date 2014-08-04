package builder;

import java.util.Date;
import java.util.List;

import models.HistoricoPartida;
import models.Jogador;
import models.Partida;

public class PartidaBuilder extends DefaultModelBuilder<Partida> {

	public PartidaBuilder() {
		super(new Partida());
	}

	@Override
	public PartidaBuilder padrao() {

		model.identificadorPartida = "11111111";
		model.dataInicio = new Date();

		return this;

	}

	public PartidaBuilder comFim(Date dataFim) {

		model.dataFim = dataFim;

		return this;
	}

	public PartidaBuilder comHistoricoPartida(List<HistoricoPartida> historico) {

		model.historico = historico;

		return this;

	}

}
