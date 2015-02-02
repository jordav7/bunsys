package ec.com.dlc.web.componentes;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.commons.resource.ContenidoMessages;
import ec.com.dlc.web.util.locator.BeanLocator;

/**
 * Clase que representa a las acciones del componente de creaci&oacute;n y edici&oacute;n de art&iacute;culos
 * @author dcruz
 *
 */
public class ArticuloComponent implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Art&iacute;culo a crearse
	 */
	private Tinvproducto articulo;
	/**
	 * Compan&iacute;a de la persona logueada
	 */
	private Integer codCompania;
	
	public ArticuloComponent(){
		
	}
	
	/**
	 * Instancia un art&iacute;culo nuevo
	 */
	public void crear() {
		this.articulo = new Tinvproducto();
	}
	
	/**
	 * Guarda los cambios de un determinado art&iacute;culo
	 */
	public void guardarCambios(){
		try{
			BunsysService bunsysService = (BunsysService) new BeanLocator.GlobalJNDIName().withAppName(ContenidoMessages.getString("application.name")).withModuleName(ContenidoMessages.getString("module.name")).withBeanName("BunsysServiceBean").withBusinessInterface(BunsysService.class).locate();
			bunsysService.guardarArticulo(articulo);
		} catch(Throwable e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", ""));
		}
	}
	
	/**
	 * Cancelamos los cambios
	 */
	public void cancelar() {
		this.articulo = new Tinvproducto();
	}

	public Tinvproducto getArticulo() {
		return articulo;
	}

	public void setArticulo(Tinvproducto articulo) {
		this.articulo = articulo;
	}

	public Integer getCodCompania() {
		return codCompania;
	}

	public void setCodCompania(Integer codCompania) {
		this.codCompania = codCompania;
	}

}
