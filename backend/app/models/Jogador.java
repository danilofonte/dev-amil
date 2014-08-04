package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ManyToAny;

import enums.TipoAcaoEnum;
import play.Play;
import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.Model;
import utils.DataUtil;
import utils.ValidationUtil;

@Entity
@Table(name="jogador")
public class Jogador extends Model {

	public static final String JOGADOR_IA = Play.configuration.getProperty("jogador.ia");

	@Unique
	@Required
	@Column(name = "tx_nome")
	public String nome;

	@Column(name = "vl_awrds")
	public Long awards;
	
	@Transient
	public List<HistoricoPartida> historicoJogador = new ArrayList<HistoricoPartida>();
	
	

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

	public void atualizarAwards() {
		
		this.atualizrPorSobrevivencia();

		this.atualizarPorKills();

		this.save();

	}
	
	public void atualizrPorSobrevivencia() {
		
		Long mortes = 0L;
		
		for (HistoricoPartida historico : this.historicoJogador) {
			
			if (historico.jogadorRecebeuAcao.equals(this) && historico.tipo.equals(TipoAcaoEnum.KILLED))
				mortes += 1;
			
		}
		
		if (mortes == 0)
			this.awards = this.awards != null ? this.awards + 1 : 1;
		
	}

	private void atualizarPorKills() {

		if (this.historicoJogador.size() >= 5) {

			Long minutosEntreKills = 0L;
			Date ultimaData = null;

			int contador = 1;

			for (HistoricoPartida historico : this.historicoJogador) {

				if (historico.jogadorExecutouAcao.equals(this)) {

					if (ultimaData == null) {
						minutosEntreKills += DataUtil.diferencaEmMinutos(historico.dataAcao, historico.dataAcao);
					} else
						minutosEntreKills += DataUtil.diferencaEmMinutos(ultimaData, historico.dataAcao);

					ultimaData = historico.dataAcao;

					contador++;
				}
				if (contador % 5 == 0) {

					if (minutosEntreKills <= 5)

						this.awards = this.awards != null ? this.awards + 1 : 1;

					ultimaData = null;
					minutosEntreKills = 0L;
				}

			}

		}

	}
	
	public Long getStreak(Long idPartida) {
		
		Long ultimoStreak = 0L;
		Long atualStreak = 0L;
		
		Partida partida = Partida.findById(idPartida);
		
		for (HistoricoPartida historico : partida.historico) {
			
			if (historico.jogadorExecutouAcao.equals(this)) 
				atualStreak += 1;				
			
			else if (historico.jogadorRecebeuAcao.equals(this))
				atualStreak = 0L;
			
			ultimoStreak = ultimoStreak <= atualStreak ? atualStreak : ultimoStreak;
			
		}
		
		return ultimoStreak;
		
	}

}
