package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import utils.ValidationUtil;

@Entity
public class Jogador extends Model {
	
	@Unique
	@Required
	@Column(name="tx_nome")
	public String nome;

	public Jogador save() {
		
		ValidationUtil.validate(this);
		
		return super.save();
		
	}
	
	public Jogador update(String nome) {
		
		this.nome = nome;
		this.merge();
		
		return this.save();
		
		
		
	}
	
}
