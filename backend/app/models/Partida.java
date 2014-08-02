package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

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
	
	public Partida save() {
		
		ValidationUtil.validate(this);
		
		return super.save();		
	}
	
	

}
