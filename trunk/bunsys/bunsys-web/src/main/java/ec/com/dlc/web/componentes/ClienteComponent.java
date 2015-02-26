package ec.com.dlc.web.componentes;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.commons.resource.ContenidoMessages;
import ec.com.dlc.web.util.locator.BeanLocator;

/**
 * Clase que representa a las acciones del componente de creaci&oacute;n y edici&oacute;n de clientes
 *
 */
public class ClienteComponent implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * Cliente a crearse
	 */
	private Tfaccliente tfaccliente;
	/**
	 * Compan&iacute;a de la persona logueada
	 */
	private Integer codCompania;
	
	private Collection<Tadmcatalogo> formaPagoCatalogoColl;
	/**
	 * Estado de un art&iacute;culo
	 */
	private Collection<Tadmcatalogo> estadoCatalogoColl;
	/**
	 * Grupo de clientes
	 */
	private Collection<Tadmcatalogo> grupoCatalogoColl;
	/**
	 * tipo clientes
	 */
	private Collection<Tadmcatalogo> tipoCatalogoColl;
	
	public ClienteComponent(){
		this(1);
	}
	
	public ClienteComponent(Integer compania){
		BunsysService bunsysService = (BunsysService) new BeanLocator.GlobalJNDIName().withAppName(ContenidoMessages.getString("application.name")).withModuleName(ContenidoMessages.getString("module.name")).withBeanName("BunsysServiceBean").withBusinessInterface(BunsysService.class).locate();
		formaPagoCatalogoColl = bunsysService.buscarObtenerCatalogos(compania, ContenidoMessages.getInteger("cod_catalogo_forma_pago"));//27
		estadoCatalogoColl = bunsysService.buscarObtenerCatalogos(compania, ContenidoMessages.getInteger("cod_catalogo_estado_cliente"));//16
		grupoCatalogoColl = bunsysService.buscarObtenerCatalogos(compania, ContenidoMessages.getInteger("cod_catalogo_grupo_cliente"));//7
		tipoCatalogoColl = bunsysService.buscarObtenerCatalogos(compania, ContenidoMessages.getInteger("cod_catalogo_tipo_cliente"));//32
	}
	
	/**
	 * Instancia un art&iacute;culo nuevo
	 */
	public void crear() {
		this.tfaccliente = new Tfaccliente();
		tfaccliente.setTsyspersona(new Tsyspersona());
		this.tfaccliente.getTsyspersona().setEstado("A");
	}
	
	/**
	 * Guarda los cambios de un determinado art&iacute;culo
	 */
	public void guardarCambios(){
		try{
			if(StringUtils.isNotBlank(tfaccliente.getFormapago())){
				tfaccliente.setFormapagocodigo(ContenidoMessages.getInteger("cod_catalogo_forma_pago"));
			}
			if(StringUtils.isNotBlank(tfaccliente.getTsyspersona().getEstado())){
				tfaccliente.getTsyspersona().setEstadocodigo(ContenidoMessages.getInteger("cod_catalogo_estado_cliente"));
				for(Tadmcatalogo item:estadoCatalogoColl){
					if(item.getPk().getCodigocatalogo().equals(tfaccliente.getTsyspersona().getEstado())){
						tfaccliente.getTsyspersona().setTadmestado(item);
						//tfaccliente.getTsyspersona().getTadmestado().setDescripcion(item.getDescripcion());
					}
				}
			}
			if(StringUtils.isNotBlank(tfaccliente.getGrupocliente())){
				tfaccliente.setGrupoclientecodigo(ContenidoMessages.getInteger("cod_catalogo_grupo_cliente"));
			}
			
			if(StringUtils.isNotBlank(tfaccliente.getTsyspersona().getTipoid())){
				tfaccliente.getTsyspersona().setTipoidcodigo(ContenidoMessages.getInteger("cod_catalogo_tipo_cliente"));
			}
			BunsysService bunsysService = (BunsysService) new BeanLocator.GlobalJNDIName().withAppName(ContenidoMessages.getString("application.name")).withModuleName(ContenidoMessages.getString("module.name")).withBeanName("BunsysServiceBean").withBusinessInterface(BunsysService.class).locate();
			bunsysService.guardarCliente(tfaccliente);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_info_cliente"), ContenidoMessages.getString("msg_info_cliente")));
		}
		catch(Throwable e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ContenidoMessages.getString("msg_error_cliente"), ContenidoMessages.getString("msg_error_cliente")));
		}
	}
	
	/**
	 * Cancelamos los cambios
	 */
	public void cancelar() {
		this.tfaccliente = new Tfaccliente();
		tfaccliente.setTsyspersona(new Tsyspersona());
		this.tfaccliente.getTsyspersona().setEstado("A");
	}

	public Tfaccliente getTfaccliente() {
		return tfaccliente;
	}

	public void setTfaccliente(Tfaccliente tfaccliente) {
		this.tfaccliente = tfaccliente;
	}

	public Integer getCodCompania() {
		return codCompania;
	}

	public void setCodCompania(Integer codCompania) {
		this.codCompania = codCompania;
	}

	public Collection<Tadmcatalogo> getFormaPagoCatalogoColl() {
		return formaPagoCatalogoColl;
	}

	public void setFormaPagoCatalogoColl(
			Collection<Tadmcatalogo> formaPagoCatalogoColl) {
		this.formaPagoCatalogoColl = formaPagoCatalogoColl;
	}

	public Collection<Tadmcatalogo> getEstadoCatalogoColl() {
		return estadoCatalogoColl;
	}

	public void setEstadoCatalogoColl(Collection<Tadmcatalogo> estadoCatalogoColl) {
		this.estadoCatalogoColl = estadoCatalogoColl;
	}

	public Collection<Tadmcatalogo> getGrupoCatalogoColl() {
		return grupoCatalogoColl;
	}

	public void setGrupoCatalogoColl(Collection<Tadmcatalogo> grupoCatalogoColl) {
		this.grupoCatalogoColl = grupoCatalogoColl;
	}

	public Collection<Tadmcatalogo> getTipoCatalogoColl() {
		return tipoCatalogoColl;
	}

	public void setTipoCatalogoColl(Collection<Tadmcatalogo> tipoCatalogoColl) {
		this.tipoCatalogoColl = tipoCatalogoColl;
	}

	
}
