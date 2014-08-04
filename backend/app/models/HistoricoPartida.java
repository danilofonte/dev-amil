package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import play.data.validation.Required;
import play.db.jpa.Model;
import utils.ValidationUtil;
import enums.TipoAcaoEnum;
import exceptions.ValidationException;

@Entity
@Table(name="historicopartida")
public class HistoricoPartida extends Model {

	@Required
	@ManyToOne
	public Partida partida;

	@ManyToOne
	public Jogador jogadorExecutouAcao;

	@ManyToOne
	public Jogador jogadorRecebeuAcao;

	@ManyToOne
	public Arma arma;

	@Required
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_acao")
	public Date dataAcao;

	@Column(name = "tp_acao")
	@Enumerated
	public TipoAcaoEnum tipo;

	@Transient
	public String identificadorPartida;
	
	

	public HistoricoPartida() {
		super();
	}

	public HistoricoPartida(Date dataAcao) {
		super();
		this.dataAcao = dataAcao;
	}
	
		

	public HistoricoPartida(Date dataAcao,	TipoAcaoEnum tipo) {
		super();
		this.dataAcao = dataAcao;
		this.tipo = tipo;
	}

	public HistoricoPartida(Jogador jogadorExecutouAcao,
			Jogador jogadorRecebeuAcao, Arma arma, Date dataAcao,
			TipoAcaoEnum tipo) {
		super();
		this.jogadorExecutouAcao = jogadorExecutouAcao;
		this.jogadorRecebeuAcao = jogadorRecebeuAcao;
		this.arma = arma;
		this.dataAcao = dataAcao;
		this.tipo = tipo;
	}

	public HistoricoPartida save() {

		ValidationUtil.validate(this);

		this.validate();

		return super.save();
	}

	public HistoricoPartida inserirHistoricoPartida(Partida partida) {

		if (this.partida == null)
			this.partida = partida;

		return this.save();
	}

	private void validate() {

		if (this.tipo.equals(TipoAcaoEnum.KILLED)
				&& (this.jogadorExecutouAcao == null
						|| jogadorRecebeuAcao == null))
			throw new ValidationException();

	}

}
