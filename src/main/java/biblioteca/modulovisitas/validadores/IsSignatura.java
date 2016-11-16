package biblioteca.modulovisitas.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vaadin.data.Validator;

public class IsSignatura implements Validator{

	private static final long serialVersionUID = -1417033056435521050L;

	@Override
	public void validate(Object value) throws InvalidValueException {
		Pattern p = Pattern.compile("[A-Za-z0-9]+(\\-[A-Za-z0-9]+)?");
		Matcher m = p.matcher(value.toString());
		if(!m.matches()){
			throw new InvalidValueException("No es una signatura valida");
			
		}
	}
	
}
