package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.data.validation.Required;
import play.db.jpa.Model;
import utils.ValidationUtil;
import enums.TipoAcaoEnum;

@Entity
public class HistoricoPartida extends Model {
	
	@Required
	@ManyToOne
	@JoinColumn(name="id_partida")
	public Partida partida;
	
	@Required
	@ManyToOne
	@JoinColumn(name="id_jogador_executor_acao")
	public Jogador jogadorExecutouAcao;
	
	@Required
	@ManyToOne
	@JoinColumn(name="id_jogador_alvo_acao")
	public Jogador jogadorRecebeuAcao;
	
	@ManyToOne
	@JoinColumn(name="id_arma")
	public Arma arma;
	
	@Column(name="dt_acao")
	public Date dataAcao;
	
	@Column(name="tp_acao")
	@Enumerated
	public TipoAcaoEnum tipo;	
	
	public HistoricoPartida save() {
		
		ValidationUtil.validate(this);
		
		return super.save();
	}

}
