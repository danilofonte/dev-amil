package models;

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
	@MaxLength(6)
	@MinLength(6)
	@Column(name = "idt_u_partida")
	public String identificadorPartida;

	@Required
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inicio")
	public Date dataInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_fim")
	public Date dataFim;

	@OneToMany(targetEntity = Jogador.class, fetch = FetchType.LAZY)
	public List<Jogador> jogadores;

	@OneToMany(targetEntity = HistoricoPartida.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<HistoricoPartida> historico;

	public Partida save() {

		ValidationUtil.validate(this);

		return super.save();
	}

	public Partida atualizarHistorico(List<HistoricoPartida> historico) {

		if (historico != null && !historico.isEmpty())
			this.historico = historico;
		
		this.merge();

		return super.save();
	}

	public Partida atualizarJogadores(List<Jogador> jogadores) {

		if (jogadores != null && !jogadores.isEmpty())
			this.jogadores = jogadores;
		
		this.merge();

		return super.save();

	}
	
	public Partida finalizarPartida(Date dataFim) {
		
		this.dataFim = dataFim;
		this.merge();
		
		return super.save();
		
		
	}

}
