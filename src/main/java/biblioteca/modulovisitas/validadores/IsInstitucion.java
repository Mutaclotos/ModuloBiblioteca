package biblioteca.modulovisitas.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vaadin.data.Validator;

public class IsInstitucion implements Validator{
	private static final long serialVersionUID = -3028055282832175180L;

	@Override
	public void validate(Object value) throws InvalidValueException {
		Pattern p = Pattern.compile("^[A-Z0-9]([A-Za-z0-9]|\\s)+");
		Matcher m = p.matcher(value.toString());
		if(!m.matches()){
			throw new InvalidValueException("No es una institución valida");
		}
	}
}
