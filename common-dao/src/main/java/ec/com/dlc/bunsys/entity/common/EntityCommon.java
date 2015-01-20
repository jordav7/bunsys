package ec.com.dlc.bunsys.entity.common;

import java.io.Serializable;

/**
 * Contiene m&eacute;todos generales de una entidad
 * @author DAVID
 *
 */
public interface EntityCommon extends Serializable{

	/**
	 * Obtiene una propiedad agregada din&aacute;micamente a la entidad
	 * @param key clave de la propiedad
	 * @return valor de la propiedad
	 */
	public Object getAditionalProperty(String key);
	
	/**
	 * Almacena una propiedad din&aacute;mica en la entidad
	 * @param key clave de la propiedad
	 * @param value valor de la propiedad
	 */
	public void addAditionalProperty(String key, Object value);
	
}
