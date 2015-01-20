package ec.com.dlc.bunsys.entity.common;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class EntityCommonImpl implements EntityCommon {

	private Map<String, Object> aditionalProperties = new HashMap<String, Object>();
	
	@Override
	public Object getAditionalProperty(String key) {
		return this.aditionalProperties.get(key);
	}

	@Override
	public void addAditionalProperty(String key, Object value) {
		this.aditionalProperties.put(key, value);
	}


}
