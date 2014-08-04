package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import utils.ValidationUtil;
import enums.TipoArmaEnum;

@Entity
@Table(name="arma")
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

	public static Arma iniciarArma(String nome, Long maxMunicao,TipoArmaEnum tipo) {

		Arma arma = Arma.find("nome", nome).first();

		if (arma == null) {
			
			arma = new Arma();
			
			arma.nome = nome;
			arma.tipo = tipo;
			arma.maxMunicao = maxMunicao != null ? maxMunicao : INFINITA;
			
			return arma.save();
		} else
			return arma;

	}
	
	public Arma update(String nome, Long municao) {

		this.nome = nome;
		this.maxMunicao = municao;
		this.merge();

		return this.save();

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arma other = (Arma) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
	
	

}
