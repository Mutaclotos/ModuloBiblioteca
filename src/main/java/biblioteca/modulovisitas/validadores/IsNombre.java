package biblioteca.modulovisitas.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vaadin.data.Validator;

public class IsNombre implements Validator{
	
	private static final long serialVersionUID = -8358840829699744496L;

	@Override
	public void validate(Object value) throws InvalidValueException {
		Pattern p = Pattern.compile("^[A-Z]([a-z])+(\\s[A-Z]([a-z])+)?$");
		Matcher m = p.matcher(value.toString());
		if(!m.matches()){
			throw new InvalidValueException("No es un nombre valido");
		}
	}
}
