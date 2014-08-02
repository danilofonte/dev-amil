package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import utils.ValidationUtil;

@Entity
public class Partida extends Model {
	
	@Unique
	@Column(name="idt_u_partida")
	public Long identificadorPartida;
	
	@Required
	@Column(name="dt_inicio")
	private Date dataInicio;
	
	@Column(name="dt_fim")
	private Date dataFim;
	
	@OneToMany(targetEntity = Jogador.class, fetch=FetchType.LAZY)
	public List<Jogador> jogadores;
	
	@OneToMany(targetEntity = HistoricoPartida.class, fetch=FetchType.LAZY)
	public List<HistoricoPartida> historico;
	
	public Partida save() {
		
		ValidationUtil.validate(this);
		
		return super.save();		
	}
	
	

}
