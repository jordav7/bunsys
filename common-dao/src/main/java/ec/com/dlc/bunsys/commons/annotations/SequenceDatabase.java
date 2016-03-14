package ec.com.dlc.bunsys.commons.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import ec.com.dlc.bunsys.commons.enumeration.TypeGenerator;

/**
 * Anotaci&oacute;n que busca la secuencia adecuada de la base de datos
 * @author dcruz
 *
 */
@Target({ TYPE, METHOD, FIELD })
@Retention(RUNTIME)
public @interface SequenceDatabase {

	/**
	 * Nombre de la secuencia en la base de datos
	 * Se debe enviar un valor para que pueda consultar el el driver
	 */
	String sequenceName();
	
	/**
	 * Tipo de generador a usar para obtener la PK
	 * Se debe especificar este tipo para poder generar la PK
	 */
	TypeGenerator typeGenerator();
}
