package biblioteca.modulovisitas.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vaadin.data.Validator;

public class IsEmail implements Validator{

	private static final long serialVersionUID = -6757616242123053935L;

	@Override
	public void validate(Object value) throws InvalidValueException {
		Pattern p = Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}");
		Matcher m = p.matcher(value.toString());
		if(!m.matches()){
			throw new InvalidValueException("No es un e-mail valido");
		}
	}
}
