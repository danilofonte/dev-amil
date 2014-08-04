package models.views;

import java.util.List;

import models.HistoricoPartida;
import models.Jogador;

public class MatadorView {
	
	public String nome;
	
	public Long qtd;

	public MatadorView(Long qtd, String nome) {
		super();
		this.nome = nome;
		this.qtd = qtd;
	}
	
	public static List<MatadorView> getEstatisticaMatador(Long idPartida) {
		
		return Jogador.find("select new "+MatadorView.class.getName()+
				" (count(jogador.id),jogador.nome) from "+HistoricoPartida.class.getSimpleName()+
				" historico"+
				" join historico.jogadorExecutouAcao jogador"+
				" join historico.partida partida"+
				" where partida.id = :idPartida"+
				" and jogador.nome <> :nome"+
				" group by jogador.nome")
				.setParameter("idPartida", idPartida)
				.setParameter("nome",Jogador.JOGADOR_IA).fetch();
		
	}
	
	public static MatadorView getMaiorMatador(List<MatadorView> matadores) {
		
		MatadorView matador = null;
		
		for (MatadorView matadorView : matadores) {
			
			if (matador == null)
				matador = matadorView;
			
			else if (matador.qtd < matadorView.qtd)
				matador = matadorView;
		}
		
		return matador;
		
	}

}
