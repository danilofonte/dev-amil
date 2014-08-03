package models;

import javax.persistence.Column;
import javax.persistence.Entity;

import play.Play;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import utils.ValidationUtil;

@Entity
public class Jogador extends Model {

	public static final String JOGADOR_IA = Play.configuration.getProperty("jogador.ia");

	@Unique
	@Required
	@Column(name = "tx_nome")
	public String nome;

	@Column(name = "vl_awrds")
	public Long awards;

	public static Jogador iniciarJogador(String nome) {

		Jogador jogador = Jogador.find("nome", nome).first();

		if (jogador == null) {

			jogador = new Jogador();

			jogador.nome = nome;
			return jogador.save();
		} else
			return jogador;

	}

	public Jogador save() {

		ValidationUtil.validate(this);

		return super.save();

	}

	public Jogador update(String nome) {

		this.nome = nome;
		this.merge();

		return this.save();

	}
	
	public static Boolean isIa(String nome) {
		
		return nome.equals(JOGADOR_IA);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogador other = (Jogador) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	

}
