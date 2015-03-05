package ec.com.dlc.web.componentes;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;

import ec.com.dlc.bunsys.commons.locator.BeanLocator;
import ec.com.dlc.bunsys.commons.resource.BunsysMessages;
import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmparamsri;
import ec.com.dlc.bunsys.entity.cuentasxpagar.Tcxpproveedor;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.commons.resource.ContenidoMessages;

public class ProveedorComponent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8301275219508679784L;

	/**
	 * Proveedor a crearse
	 */
	private Tcxpproveedor proveedor;
	
	private Integer compania;
	
	private Collection<Tadmcatalogo> tipoIdColl;
	
	private Collection<Tadmcatalogo> grupoProvColl;
	
	private Collection<Tadmcatalogo> contribuyenteColl;
	
	private Collection<Tadmparamsri> tipoDocColl;
	
	private Collection<Tadmcatalogo> estadoColl;
	
	private boolean edition;
	
	public ProveedorComponent() {
		this(1);
	}
	
	public ProveedorComponent(Integer compania) {
		BunsysService bunsysService = (BunsysService) new BeanLocator.GlobalJNDIName().withAppName(BunsysMessages.getString("application.name")).withModuleName(BunsysMessages.getString("module.name")).withBeanName("BunsysServiceBean").withBusinessInterface(BunsysService.class).locate();
		tipoIdColl = bunsysService.buscarObtenerCatalogos(compania, ContenidoMessages.getInteger("cod_catalogo_tipoid_persona"));
		grupoProvColl = bunsysService.buscarObtenerCatalogos(compania, ContenidoMessages.getInteger("cod_catalogo_grupo_prov"));
		contribuyenteColl = bunsysService.buscarObtenerCatalogos(compania, ContenidoMessages.getInteger("cod_catalogo_contribuyenge_prov"));
		tipoDocColl = bunsysService.buscarTipoDocSriCxp(ContenidoMessages.getInteger("cod_catalogo_tipodoc_prov"), "S");
		estadoColl = bunsysService.buscarObtenerCatalogos(compania, ContenidoMessages.getInteger("cod_catalogo_estado_persona"));
		
		this.edition=true;
		
	}

	/**
	 * Instancia un proveedor nuevo
	 */
	public void crear() {
		this.proveedor = new Tcxpproveedor();
		this.proveedor.setTsyspersona(new Tsyspersona());
		this.proveedor.getTsyspersona().setEstado("A");
	}
	
	public void guardarCambios() {
		try{
			if(StringUtils.isNotBlank(proveedor.getTsyspersona().getTipoid())){
				proveedor.getTsyspersona().setTipoidcodigo(ContenidoMessages.getInteger("cod_catalogo_tipoid_persona"));
			}
			if(StringUtils.isNotBlank(proveedor.getGrupoproveedor())){
				proveedor.setGrupoproveedorcodigo(ContenidoMessages.getInteger("cod_catalogo_grupo_prov"));
			}
			if(StringUtils.isNotBlank(proveedor.getContribuyentesri())){
				proveedor.setContribuyentesricodigo(ContenidoMessages.getInteger("cod_catalogo_contribuyenge_prov"));
			}
			if(StringUtils.isNotBlank(proveedor.getTipodocumento())){
				proveedor.setTipodocumentocodigo(ContenidoMessages.getInteger("cod_catalogo_tipodoc_prov"));
			}
			if(StringUtils.isNotBlank(proveedor.getTsyspersona().getEstado())){
				proveedor.getTsyspersona().setEstadocodigo(ContenidoMessages.getInteger("cod_catalogo_estado_persona"));
			}
			
			BunsysService bunsysService = (BunsysService) new BeanLocator.GlobalJNDIName().withAppName(BunsysMessages.getString("application.name")).withModuleName(BunsysMessages.getString("module.name")).withBeanName("BunsysServiceBean").withBusinessInterface(BunsysService.class).locate();
			bunsysService.guardarProveedor(proveedor);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,  ContenidoMessages.getString("msg_info_prov"), ContenidoMessages.getString("msg_info_prov")));
		}catch(Throwable e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  ContenidoMessages.getString("msg_error_prov"), ContenidoMessages.getString("msg_error_prov")));
		}
	}
	
	/**
	 * Cancelamos los cambios
	 */
	public void cancelar() {
		this.proveedor= new Tcxpproveedor();
		this.proveedor.setTsyspersona(new Tsyspersona());
	}
	
	public Tcxpproveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Tcxpproveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Integer getCompania() {
		return compania;
	}

	public void setCompania(Integer compania) {
		this.compania = compania;
	}

	public Collection<Tadmcatalogo> getTipoIdColl() {
		return tipoIdColl;
	}

	public void setTipoIdColl(Collection<Tadmcatalogo> tipoIdColl) {
		this.tipoIdColl = tipoIdColl;
	}

	public Collection<Tadmcatalogo> getGrupoProvColl() {
		return grupoProvColl;
	}

	public void setGrupoProvColl(Collection<Tadmcatalogo> grupoProvColl) {
		this.grupoProvColl = grupoProvColl;
	}

	public Collection<Tadmcatalogo> getContribuyenteColl() {
		return contribuyenteColl;
	}

	public void setContribuyenteColl(Collection<Tadmcatalogo> contribuyenteColl) {
		this.contribuyenteColl = contribuyenteColl;
	}

	public Collection<Tadmparamsri> getTipoDocColl() {
		return tipoDocColl;
	}

	public void setTipoDocColl(Collection<Tadmparamsri> tipoDocColl) {
		this.tipoDocColl = tipoDocColl;
	}

	public Collection<Tadmcatalogo> getEstadoColl() {
		return estadoColl;
	}

	public void setEstadoColl(Collection<Tadmcatalogo> estadoColl) {
		this.estadoColl = estadoColl;
	}

	public boolean isEdition() {
		return edition;
	}

	public void setEdition(boolean edition) {
		this.edition = edition;
	}
	
	
}
