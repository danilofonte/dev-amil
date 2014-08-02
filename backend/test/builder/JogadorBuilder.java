package builder;

import models.Jogador;

public class JogadorBuilder extends DefaultModelBuilder<Jogador> {

	public JogadorBuilder() {
		super(new Jogador());

	}

	@Override
	public JogadorBuilder padrao() {
		model.nome = "Danilo";

		return this;
	}
	
	public JogadorBuilder comNome(String nome) {
		
		model.nome = nome;
		
		return this;
		
	}
	

}
