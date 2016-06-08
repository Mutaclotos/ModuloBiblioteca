package biblioteca.modulovisitas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {
	public static boolean valida(String regex, String texto){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(texto);
		return m.matches();
	}
	
	public static boolean isMail(String mail){
		return valida("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}",mail);
	}
	
	public static boolean isNombre(String name){
		return valida("^[A-Z]([a-z])+(\\s[A-Z]([a-z])+)?$",name);
	}
	
	public static boolean isApellido(String apellidos){
		return valida("^[A-Z][a-z]+\\s[A-Z][a-z]+$",apellidos);
	}
	
	public static boolean isCedula(String cedula){
		return valida("([0-9]{9}|[0-9]-[0-9]{4}-[0-9]{4})", cedula);
	}
	
	public static boolean isCarne(String carne){
		return valida("[A-Za-z0-9]",carne);
	}
	
	public static boolean isInstitucion(String institucion){
		return valida("^[A-Z0-9]([A-Za-z0-9]|\\s)+",institucion);
	}
	
	public static boolean isHora(String hora){
		return valida("[0-9]{1,2}:[0-9]{1,2}", hora);
	}
	
}
