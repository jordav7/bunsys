package ec.com.dlc.bunsys.dao.util;

import java.util.ResourceBundle;

/**
 * Maneja los mensajes de propiedades del m&oacute;dulo de DAO
 * @author DAVID
 *
 */
public class DaoMessages {

	private static final String DAO_RESOURCE = "ec.com.dlc.bunsys.dao.util.dao_messages";
	
	private static ResourceBundle resource = null;
	
	static{
		resource = ResourceBundle.getBundle(DAO_RESOURCE);
	}
	
	public static String getProperty(String key) {
		return resource.getString(key);
	}
	
	public static Object getObject(String key) {
		return resource.getObject(key);
	}
	
}
