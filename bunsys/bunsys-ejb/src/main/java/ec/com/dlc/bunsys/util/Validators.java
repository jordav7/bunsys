package ec.com.dlc.bunsys.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {
	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final String PATTERN_CELULAR = "^\\+?\\d{1,3}?[- .]?\\(?(?:\\d{2,3})\\)?[- .]?\\d\\d\\d[- .]?\\d\\d\\d\\d$";
	
	public static boolean isTelefonoValido( String celular) {
		Pattern pattern = Pattern.compile(PATTERN_CELULAR);
		Matcher matcher = pattern.matcher(celular);
		
		return matcher.matches();
	}
	/**
     * Validacion dado el email con regular expression.
     * 
     * @param email  email para la validacion
     * @return true email valido
     */
    public static boolean isEmailValido(String email) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        
        return matcher.matches();
    }
    
	public static boolean isCedulaValida(String cedula) {
		boolean cedulaCorrecta = false;

		try {
			if (cedula.length() == 10) {
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {
	
				int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
				int verificador = Integer.parseInt(cedula.substring(9,10));
				int suma = 0;
				int digito = 0;
				
				for (int i = 0; i < (cedula.length() - 1); i++) {
				 digito = Integer.parseInt(cedula.substring(i, i + 1))* coefValCedula[i];
				 suma += ((digito % 10) + (digito / 10));
				}
		
				if ((suma % 10 == 0) && (suma % 10 == verificador)) {
				 cedulaCorrecta = true;
				}
				else if ((10 - (suma % 10)) == verificador) {
				 cedulaCorrecta = true;
				} else {
				 cedulaCorrecta = false;
				}
				} else {
				cedulaCorrecta = false;
				}
			} else {
			cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			cedulaCorrecta = false;
		}	

		return cedulaCorrecta;
	}
	
	 public static void main(String args[]){
		System.out.println("Valida cedula :: "+isTelefonoValido("002-258-7583"));
	}
}
