package utils;

import play.data.validation.Validation;
import play.db.jpa.Model;
import exceptions.ValidationException;

public class ValidationUtil {

	public static void validate(Model model) {
		
		Validation validation = Validation.current();
		
		validation.valid(model);
		
		if (validation.hasErrors())
			throw new ValidationException(validation.errors());
	}
	
	public static void required(Object value, String msgKey) {
		
		if (value == null) {
			throw new ValidationException().mensagemUsuario(msgKey);
		}
	}
	
	public static void required(String value, String msgKey) {
		
		if (value == null || value.isEmpty()) {
			throw new ValidationException().mensagemUsuario(msgKey);
		}
	}
}
