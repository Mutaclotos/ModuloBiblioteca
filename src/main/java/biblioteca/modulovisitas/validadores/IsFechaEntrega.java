package biblioteca.modulovisitas.validadores;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vaadin.data.Validator;

public class IsFechaEntrega implements Validator{
	private static final long serialVersionUID = 2610303187537557427L;

	@Override
	public void validate(Object value) throws InvalidValueException {
		Pattern p = Pattern.compile("([0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2})?");
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		String newValue = "";
		if(value != null)
		{
			newValue = formatter.format(value);
		}
		Matcher m = p.matcher(newValue);
		if(!m.matches()){
			throw new InvalidValueException("No es una fecha de entrega valida");
		}
	}
}
