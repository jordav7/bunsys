package ec.com.dlc.web.util.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Clase que maneja los mensajes JSF una p&aacute;gina
 * @author dcruz
 *
 */
public class MessagesUtil {

	/**
	 * Crea un mensjae informativo
	 * @param message
	 */
	public static void showInfoMessage(String message) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
	}
	
	public static void showWarnMessage(String message) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
	}
	
	public static void showErrorMessage(String message) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.validationFailed();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
	}
	
	public static void showFatalMessage(String message) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.validationFailed();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, message, message));
	}
}
