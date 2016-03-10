package ec.com.dlc.web.util;

import java.util.Map;

import javax.faces.context.FacesContext;

public class Utilitaria {

    public static String getLovParameter(String parametername) throws Exception {
        Map<String, String[]> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterValuesMap();
        String[] obj = params.get(parametername);
        if (obj == null) {
            return null;
        }
        return obj[0];
    }
}
