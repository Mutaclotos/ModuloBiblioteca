package biblioteca.modulovisitas.validadores;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vaadin.data.Validator;

public class IsTelefono implements Validator{

	private static final long serialVersionUID = 8083897631071228868L;

	@Override
	public void validate(Object value) throws InvalidValueException {
		Pattern p = Pattern.compile("[0-9]{8}");
		Matcher m = p.matcher(value.toString());
		if(!m.matches()){
			throw new InvalidValueException("No es un telefono valido");
		}
	}

}
