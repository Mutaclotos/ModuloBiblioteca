package biblioteca.modulovisitas.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vaadin.data.Validator;

public class IsApellido implements Validator{

	private static final long serialVersionUID = 1979492516802458577L;

	@Override
	public void validate(Object value) throws InvalidValueException {
		Pattern p = Pattern.compile("^[A-Z][a-z]+\\s[A-Z][a-z]*$");
		Matcher m = p.matcher(value.toString());
		if(!m.matches()){
			throw new InvalidValueException("No es un apellido valido");
		}
	}

}
