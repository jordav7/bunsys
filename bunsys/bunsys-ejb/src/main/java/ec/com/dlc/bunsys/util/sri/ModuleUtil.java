package ec.com.dlc.bunsys.util.sri;

/**
 * Obtengo el digito verificador como ultimo parametro de
 * 
 * @author dcruz
 *
 */
public class ModuleUtil {

	private static String invertirCadena(String cadena) {
		String cadenaInvertida = "";
		for (int x = cadena.length() - 1; x >= 0; x--) {
			cadenaInvertida = cadenaInvertida + cadena.charAt(x);
		}
		return cadenaInvertida;
	}

	public static int obtenerModulo11(String cadena) {
		cadena = invertirCadena(cadena);
		int pivote = 2;
		int longitudCadena = cadena.length();
		int cantidadTotal = 0;
		int b = 1;
		for (int i = 0; i < longitudCadena; i++) {
			if (pivote == 8) {
				pivote = 2;
			}
			int temporal = Integer.parseInt("" + cadena.substring(i, b));
			b++;
			temporal *= pivote;
			pivote++;
			cantidadTotal += temporal;
		}
		cantidadTotal = 11 - cantidadTotal % 11;
		switch (cantidadTotal) {
		case 11:
			cantidadTotal = 0;
			break;
		case 10:
			cantidadTotal = 1;
			break;
		default:
			break;
		}
		return cantidadTotal;
	}
	
	public static void main(String[] args) {
		System.out.println(obtenerModulo11("170520150405024075700012001002000000003123456781"));
	}
}
