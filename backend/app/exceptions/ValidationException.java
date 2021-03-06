package exceptions;

import java.util.List;

import play.data.validation.Error;

public class ValidationException extends RuntimeException {

	public List<Error> errors;
	private String chaveMensagemUsuario;
	private Object [] args;
	
	public ValidationException() {
		super();
	}
	
	public ValidationException(List<Error> errors) {
		
		this.errors = errors;
		
		this.mensagemUsuario("VALIDACAO_ERRO", getErrorsMessage());
	}
	
	public ValidationException(String motivoErro) {
		this.mensagemUsuario(motivoErro);
	}
	
	private String getErrorsMessage() {
		
		if (this.errors == null || this.errors.isEmpty())
			return super.getMessage();

		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < errors.size() - 1 ; i++)
			sb.append(errors.get(i).message()).append("\n");
		
		return sb.toString();
	}
	
	public ValidationException mensagemUsuario(String chaveMensagem, Object ... args) {
		this.args = args;
		this.chaveMensagemUsuario = chaveMensagem;
		return this;
	}
}
