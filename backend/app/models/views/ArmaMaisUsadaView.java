package models.views;

import java.util.List;

import models.HistoricoPartida;
import models.Jogador;

public class ArmaMaisUsadaView {
	
	public String nome;
	
	public Long qtd;
	
	public String nomeJogador;
	
	public ArmaMaisUsadaView(Long qtd, String nome,String nomeJogador) {
		super();
		this.nome = nome;
		this.qtd = qtd;
		this.nomeJogador = nomeJogador;
	}
	
	public static ArmaMaisUsadaView getEstatisticaArmaDoMelhorJogador(Long idPartida) {		
		
		List<MatadorView> matadores = Jogador.find("select new "+MatadorView.class.getName()+
				" (count(jogador.id),jogador.nome) from "+HistoricoPartida.class.getSimpleName()+
				" historico"+
				" join historico.jogadorExecutouAcao jogador"+
				" join historico.partida partida"+
				" where partida.id = :idPartida"+
				" and jogador.nome != :nome"+
				" group by jogador.nome")
				.setParameter("idPartida", idPartida)
				.setParameter("nome",Jogador.JOGADOR_IA).fetch();
		
		MatadorView matador = MatadorView.getMaiorMatador(matadores);
		
		Jogador jogador = Jogador.find("nome", matador.nome).first();
		
		return  Jogador.find("select new "+ArmaMaisUsadaView.class.getName()+
				" (count(jogador.id),arma.nome,jogador.nome) from "+HistoricoPartida.class.getSimpleName()+
				" historico"+
				" join historico.jogadorExecutouAcao jogador"+
				" join historico.partida partida"+
				" join historico.arma arma"+
				" where partida.id = :idPartida"+
				" and jogador.id = :idJogador"+
				" group by arma.nome")
				.setParameter("idPartida", idPartida)
				.setParameter("idJogador", jogador.id)
				.first();
		

		
	}

}
