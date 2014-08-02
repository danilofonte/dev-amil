package builder;

import enums.TipoArmaEnum;
import models.Arma;

public class ArmaBuilder extends DefaultModelBuilder<Arma> {

	public ArmaBuilder() {
		super(new Arma());
	}

	@Override
	public ArmaBuilder padrao() {
		
		model.nome = "AK47";
		model.maxMunicao = 50L;
		model.tipo = TipoArmaEnum.LONGADISTANCIA;
		
		return this;
		
	}
	
	public ArmaBuilder comTipo(TipoArmaEnum tipo) {
		
		model.tipo = tipo;
		
		return this;
	}
	
	

}
