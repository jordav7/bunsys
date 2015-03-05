package ec.com.dlc.web.componentes;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import ec.com.dlc.bunsys.commons.locator.BeanLocator;
import ec.com.dlc.bunsys.commons.resource.BunsysMessages;
import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.commons.resource.ContenidoMessages;

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
	
	private Collection<Tadmcatalogo> colorCatalogoColl;
	/**
	 * Estado de un art&iacute;culo
	 */
	private Collection<Tadmcatalogo> estadoCatalogoColl;
	
	private Collection<Tadmcatalogo> unidadVentaColl;
	
	private Collection<Tadmcatalogo> aptaCatalogoColl;
	
	private Collection<Tadmcatalogo> tipoProductoColl;
	
	private Collection<Tadmcatalogo> ivaCatalogoColl;
	
	private Collection<Tadmcatalogo> iceCatalogoColl;
	
	private Collection<Tadmcatalogo> irbpnrCatalogoColl;
	
	private boolean edition;
	
	public ArticuloComponent(){
		this(1);
	}
	
	public ArticuloComponent(Integer compania){
		BunsysService bunsysService = (BunsysService) new BeanLocator.GlobalJNDIName().withAppName(BunsysMessages.getString("application.name")).withModuleName(BunsysMessages.getString("module.name")).withBeanName("BunsysServiceBean").withBusinessInterface(BunsysService.class).locate();
		colorCatalogoColl = bunsysService.buscarObtenerCatalogos(compania, ContenidoMessages.getInteger("cod_catalogo_color_articulo"));
		estadoCatalogoColl = bunsysService.buscarObtenerCatalogos(compania, ContenidoMessages.getInteger("cod_catalogo_estado_articulo"));
		unidadVentaColl = bunsysService.buscarObtenerCatalogos(compania, ContenidoMessages.getInteger("cod_catalogo_unidad_venta_articulo"));
		aptaCatalogoColl = bunsysService.buscarObtenerCatalogos(compania, ContenidoMessages.getInteger("cod_catalogo_apta_articulo"));
		tipoProductoColl = bunsysService.buscarObtenerCatalogos(compania, ContenidoMessages.getInteger("cod_catalogo_tipo_articulo"));
		ivaCatalogoColl =  bunsysService.buscarObtenerCatalogos(compania, ContenidoMessages.getInteger("cod_catalogo_iva_articulo"));
		irbpnrCatalogoColl = bunsysService.buscarObtenerCatalogos(compania, ContenidoMessages.getInteger("cod_catalogo_irbpnr_articulo"));
		iceCatalogoColl = bunsysService.buscarObtenerCatalogos(compania, ContenidoMessages.getInteger("cod_catalogo_ice_articulo"));
		
		this.edition = true;
	}
	
	/**
	 * Instancia un art&iacute;culo nuevo
	 */
	public void crear() {
		this.articulo = new Tinvproducto();
		this.articulo.setEstado("A");
		this.articulo.setPeso(0d);
		this.articulo.setPreciounitario(0d);
		this.edition=Boolean.FALSE;
	}
	
	/**
	 * Guarda los cambios de un determinado art&iacute;culo
	 */
	public void guardarCambios(){
		try{
			if(StringUtils.isNotBlank(articulo.getColor())){
				articulo.setColorcodigo(ContenidoMessages.getInteger("cod_catalogo_color_articulo"));
			}
			
			if(StringUtils.isNotBlank(articulo.getEstado())){
				articulo.setEstadocodigo(ContenidoMessages.getInteger("cod_catalogo_estado_articulo"));
			}
			
			if(StringUtils.isNotBlank(articulo.getUnidadventa())){
				articulo.setUnidadventacodigo(ContenidoMessages.getInteger("cod_catalogo_unidad_venta_articulo"));
			}
			
			if(StringUtils.isNotBlank(articulo.getAtpa())){
				articulo.setAtpacodigo(ContenidoMessages.getInteger("cod_catalogo_apta_articulo"));
			}
			
			if(StringUtils.isNotBlank(articulo.getTipoproducto())){
				articulo.setTipoproductocodigo(ContenidoMessages.getInteger("cod_catalogo_tipo_articulo"));
			}
			
			if(StringUtils.isNotBlank(articulo.getIva())){
				articulo.setIvacodigo(ContenidoMessages.getInteger("cod_catalogo_iva_articulo"));
			}
			
			if(StringUtils.isNotBlank(articulo.getIrbpnr())){
				articulo.setIrbpnrcodigo(ContenidoMessages.getInteger("cod_catalogo_irbpnr_articulo"));
			}
			
			if(StringUtils.isNotBlank(articulo.getIce())){
				articulo.setIcecodigo(ContenidoMessages.getInteger("cod_catalogo_ice_articulo"));
			}
			
			BunsysService bunsysService = (BunsysService) new BeanLocator.GlobalJNDIName().withAppName(BunsysMessages.getString("application.name")).withModuleName(BunsysMessages.getString("module.name")).withBeanName("BunsysServiceBean").withBusinessInterface(BunsysService.class).locate();
			bunsysService.guardarArticulo(articulo);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_info_articulo"), ContenidoMessages.getString("msg_info_articulo")));
		} catch(Throwable e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ContenidoMessages.getString("msg_error_articulo"), ContenidoMessages.getString("msg_error_articulo")));
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

	public Collection<Tadmcatalogo> getColorCatalogoColl() {
		return colorCatalogoColl;
	}

	public void setColorCatalogoColl(Collection<Tadmcatalogo> colorCatalogoColl) {
		this.colorCatalogoColl = colorCatalogoColl;
	}

	public Collection<Tadmcatalogo> getEstadoCatalogoColl() {
		return estadoCatalogoColl;
	}

	public void setEstadoCatalogoColl(Collection<Tadmcatalogo> estadoCatalogoColl) {
		this.estadoCatalogoColl = estadoCatalogoColl;
	}

	public Collection<Tadmcatalogo> getUnidadVentaColl() {
		return unidadVentaColl;
	}

	public void setUnidadVentaColl(Collection<Tadmcatalogo> unidadVentaColl) {
		this.unidadVentaColl = unidadVentaColl;
	}

	public Collection<Tadmcatalogo> getAptaCatalogoColl() {
		return aptaCatalogoColl;
	}

	public void setAptaCatalogoColl(Collection<Tadmcatalogo> aptaCatalogoColl) {
		this.aptaCatalogoColl = aptaCatalogoColl;
	}

	public Collection<Tadmcatalogo> getTipoProductoColl() {
		return tipoProductoColl;
	}

	public void setTipoProductoColl(Collection<Tadmcatalogo> tipoProductoColl) {
		this.tipoProductoColl = tipoProductoColl;
	}

	public Collection<Tadmcatalogo> getIvaCatalogoColl() {
		return ivaCatalogoColl;
	}

	public void setIvaCatalogoColl(Collection<Tadmcatalogo> ivaCatalogoColl) {
		this.ivaCatalogoColl = ivaCatalogoColl;
	}

	public Collection<Tadmcatalogo> getIceCatalogoColl() {
		return iceCatalogoColl;
	}

	public void setIceCatalogoColl(Collection<Tadmcatalogo> iceCatalogoColl) {
		this.iceCatalogoColl = iceCatalogoColl;
	}

	public Collection<Tadmcatalogo> getIrbpnrCatalogoColl() {
		return irbpnrCatalogoColl;
	}

	public void setIrbpnrCatalogoColl(Collection<Tadmcatalogo> irbpnrCatalogoColl) {
		this.irbpnrCatalogoColl = irbpnrCatalogoColl;
	}

	public boolean isEdition() {
		return edition;
	}

	public void setEdition(boolean edition) {
		this.edition = edition;
	}
	
}
