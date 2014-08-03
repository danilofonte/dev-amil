package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.data.validation.Required;
import play.db.jpa.Model;
import utils.ValidationUtil;
import enums.TipoAcaoEnum;

@Entity
public class HistoricoPartida extends Model {
	
	@Required
	@ManyToOne
	public Partida partida;
	
	@Required
	@ManyToOne
	public Jogador jogadorExecutouAcao;
	
	@Required
	@ManyToOne
	public Jogador jogadorRecebeuAcao;
	
	@ManyToOne
	public Arma arma;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dt_acao")
	public Date dataAcao;
	
	@Column(name="tp_acao")
	@Enumerated
	public TipoAcaoEnum tipo;	
	
	public HistoricoPartida save() {
		
		ValidationUtil.validate(this);
		
		return super.save();
	}
	
	public HistoricoPartida inserirHistoricoPartida(Partida partida) {
		
		if (this.partida == null)
			this.partida = partida;
		
		return this.save();
	}

}
