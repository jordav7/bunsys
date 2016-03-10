package ec.com.dlc.bunsys.commons.resource;

import java.util.ResourceBundle;

public class BunsysMessages {

	private static ResourceBundle bundle;
	
	private static final String RESOURCE = "ec.com.dlc.bunsys.commons.resource.bunsys";
	
	static {
		bundle = ResourceBundle.getBundle(RESOURCE);
	}
	
	public static String getString(String key) {
		return bundle.getString(key);
	}
	
}
