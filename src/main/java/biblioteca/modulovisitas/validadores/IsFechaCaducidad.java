package biblioteca.modulovisitas.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vaadin.data.Validator;

public class IsFechaCaducidad implements Validator{
	private static final long serialVersionUID = 2610303187537557427L;

	@Override
	public void validate(Object value) throws InvalidValueException {
		Pattern p = Pattern.compile("([0-9]{1,2}:[0-9]{1,2})");
		Matcher m = p.matcher(value.toString());
		if(!m.matches()){
			throw new InvalidValueException("No es una fecha de caducidad valida");
		}
	}
}
