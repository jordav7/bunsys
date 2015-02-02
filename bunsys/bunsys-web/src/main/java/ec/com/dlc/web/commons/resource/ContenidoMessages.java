package ec.com.dlc.web.commons.resource;

import java.util.ResourceBundle;

public class ContenidoMessages {

	private static final String BASE_RESOURCES = "ec.com.dlc.web.commons.resource.contenido";
	
	private static ResourceBundle bundle;
	
	static{
		bundle = ResourceBundle.getBundle(BASE_RESOURCES);
	}
	
	public static String getString(String key) {
		try{
			return bundle.getString(key);
		} catch(Throwable e){
			return "";
		}
	}
	
	public static Integer getInteger(String key) {
		try {
			String value = bundle.getString(key);
			if(value != null){
				return Integer.parseInt(value);
			} else{
				return -1;
			}
		} catch (Throwable e) {
			return -1;
		}
	}
}
