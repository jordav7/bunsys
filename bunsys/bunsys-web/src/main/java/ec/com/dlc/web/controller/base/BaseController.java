package ec.com.dlc.web.controller.base;

import javax.annotation.PostConstruct;
import javax.faces.component.html.HtmlForm;
import javax.faces.context.FacesContext;

import ec.com.dlc.web.datamanager.base.BaseDatamanager;

/**
 * Controlador que tienen que heredarlo todos los controladores
 * @author DAVID
 *
 */
public abstract class BaseController {
	
	@PostConstruct
	public void init(){
		try{
			if(!getDatamanager().getInicializado()){
				this.inicializar();
				getDatamanager().setInicializado(Boolean.TRUE);
			}
		}catch (Throwable e){
//			FacesUtil.getInstancia().messageError(e.getMessage());
		}
	}
	
	/**
	 * Forma de la pantalla a ser manejada
	 */
	private HtmlForm htmlForm;

	/**
	 * Retorna el datamanager manejado en la aplicaci&oacute;n
	 * @return
	 */
	public abstract BaseDatamanager getDatamanager();
	
	/**
	 * Inicializa los valores del controlador
	 */
	public abstract void inicializar();

	public HtmlForm getHtmlForm() {
		return htmlForm;
	}
	
	public void eliminarDatamanager() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(getDatamanager().getIdDatamanager());
	}

	public void setHtmlForm(HtmlForm htmlForm) {
		this.htmlForm = htmlForm;
	}
	
}
