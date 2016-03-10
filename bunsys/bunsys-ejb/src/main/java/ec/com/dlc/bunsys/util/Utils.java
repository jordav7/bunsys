package ec.com.dlc.bunsys.util;

import java.math.BigDecimal;

public class Utils {

	public static BigDecimal round(BigDecimal numero, Integer numeroDecimales) {
		int mode = BigDecimal.ROUND_UP ;
		return numero.setScale(numeroDecimales, mode);
	}
}
