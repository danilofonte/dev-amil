package models;

import enums.TipoAcaoEnum;
import exceptions.ValidationException;
import groovy.transform.TimedInterrupt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

import net.sf.oval.constraint.DateRange;
import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.MinLength;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import utils.ValidationUtil;

@Entity
public class Partida extends Model {

	@Unique
	@MaxLength(8)
	@MinLength(8)
	@Column(name = "idt_u_partida")
	public String identificadorPartida;

	@Required
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inicio")
	public Date dataInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_fim")
	public Date dataFim;

	@OneToMany(targetEntity = Jogador.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Jogador> jogadores;

	@OneToMany(targetEntity = HistoricoPartida.class, mappedBy = "partida", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<HistoricoPartida> historico;

	public Partida save() {

		ValidationUtil.validate(this);

		return super.save();
	}

	public Partida atualizarHistorico(List<HistoricoPartida> historico) {

		for (HistoricoPartida historicoPartida : historico) {
			this.atualizarHistorico(historicoPartida);
		}

		return this.save();
	}

	private Partida atualizarHistorico(HistoricoPartida historicoPartida) {

		if (this.historico == null)
			this.historico = new ArrayList<HistoricoPartida>();

		this.historico.add(historicoPartida);

		historicoPartida.inserirHistoricoPartida(this);

		return this.save();
	}

	public Partida atualizarListaJogadores(List<Jogador> jogadores) {

		if (this.dataFim == null) {

			if (this.jogadores == null)
				this.jogadores = new ArrayList<Jogador>();

			if (jogadores != null && !jogadores.isEmpty())
				this.jogadores.addAll(jogadores);

			return this.save();

		}

		return this;

	}

	public Partida finalizarPartida(HistoricoPartida historicoPartida) {

		if (historicoPartida.tipo.equals(TipoAcaoEnum.ENDED)) {
			this.dataFim = historicoPartida.dataAcao;
			
			this.save();

			this.atualizarHistorico(historicoPartida);

			return this;

		} else
			throw new ValidationException();

	}

	public Partida iniciarPartida(HistoricoPartida historicoPartida) {

		if (historicoPartida.tipo.equals(TipoAcaoEnum.STARTED)) {
			this.dataInicio = historicoPartida.dataAcao;
			
			this.save();

			this.atualizarHistorico(historicoPartida);

			return this;

		} else
			throw new ValidationException();

	}

}
