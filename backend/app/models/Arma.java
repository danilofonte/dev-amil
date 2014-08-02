package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;

import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import utils.ValidationUtil;
import enums.TipoArmaEnum;

@Entity
public class Arma extends Model {
	
	private static final Long INFINITA = 0L;

	@Required
	@Unique
	@Column(name = "tx_nome")
	public String nome;

	@Required
	@Column(name = "vl_max_municao")
	public Long maxMunicao;

	@Enumerated
	@Required
	@Column(name = "tp_tipo_arma")
	public TipoArmaEnum tipo;

	public Arma save() {

		ValidationUtil.validate(this);

		return super.save();

	}

	public Arma update(String nome, Long municao) {

		this.nome = nome;
		this.maxMunicao = municao;
		this.merge();

		return this.save();

	}

}
