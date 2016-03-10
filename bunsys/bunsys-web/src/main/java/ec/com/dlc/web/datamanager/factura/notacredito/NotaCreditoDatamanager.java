package ec.com.dlc.web.datamanager.factura.notacredito;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetdevolucione;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.login.LoginDatamanager;

@ManagedBean(name="notaCreditoDatamanager")
@SessionScoped
public class NotaCreditoDatamanager extends BaseDatamanager {

	@ManagedProperty(value="#{loginDatamanager}")
	private LoginDatamanager loginDatamanager;
	/**
	 * Cabecera devoluci&oacute;n
	 */
	private Tfaccabdevolucione cabdevoluciones;
	/**
	 * Detalle de la nota de cr&eacute;dito
	 */
	private Collection<Tfacdetdevolucione> detdevolucionesColl;
	/**
	 * Estado de la nota de cr&eacute;dito
	 */
	private Collection<Tadmcatalogo> catalogoEstadoColl;
	private Collection<Tadmcatalogo> distritoVueloColl;
	private Collection<Tadmcatalogo> cargueraColl;
	private Collection<Tadmcatalogo> fobColl;
	private Collection<Tadmcatalogo> aerolineaColl;
	private Collection<Tadmcatalogo> picesTypeColl;
	
	private Tadmcompania compania;
	
	private Tinvproducto tinvproducto;
	
	private boolean edicion;
	
	@Override
	public String getIdDatamanager() {
		return "notaCreditoDatamanager";
	}

	public Tfaccabdevolucione getCabdevoluciones() {
		return cabdevoluciones;
	}

	public void setCabdevoluciones(Tfaccabdevolucione cabdevoluciones) {
		this.cabdevoluciones = cabdevoluciones;
	}

	public Collection<Tfacdetdevolucione> getDetdevolucionesColl() {
		return detdevolucionesColl;
	}

	public void setDetdevolucionesColl(
			Collection<Tfacdetdevolucione> detdevolucionesColl) {
		this.detdevolucionesColl = detdevolucionesColl;
	}

	public Collection<Tadmcatalogo> getCatalogoEstadoColl() {
		return catalogoEstadoColl;
	}

	public void setCatalogoEstadoColl(Collection<Tadmcatalogo> catalogoEstadoColl) {
		this.catalogoEstadoColl = catalogoEstadoColl;
	}

	public Tadmcompania getCompania() {
		return compania;
	}

	public void setCompania(Tadmcompania compania) {
		this.compania = compania;
	}
	
	public LoginDatamanager getLoginDatamanager() {
		return loginDatamanager;
	}

	public void setLoginDatamanager(LoginDatamanager loginDatamanager) {
		this.loginDatamanager = loginDatamanager;
	}

	public boolean isEdicion() {
		return edicion;
	}

	public void setEdicion(boolean edicion) {
		this.edicion = edicion;
	}

	public Tinvproducto getTinvproducto() {
		return tinvproducto;
	}

	public void setTinvproducto(Tinvproducto tinvproducto) {
		this.tinvproducto = tinvproducto;
	}

	public Collection<Tadmcatalogo> getDistritoVueloColl() {
		return distritoVueloColl;
	}

	public void setDistritoVueloColl(Collection<Tadmcatalogo> distritoVueloColl) {
		this.distritoVueloColl = distritoVueloColl;
	}

	public Collection<Tadmcatalogo> getCargueraColl() {
		return cargueraColl;
	}

	public void setCargueraColl(Collection<Tadmcatalogo> cargueraColl) {
		this.cargueraColl = cargueraColl;
	}

	public Collection<Tadmcatalogo> getFobColl() {
		return fobColl;
	}

	public void setFobColl(Collection<Tadmcatalogo> fobColl) {
		this.fobColl = fobColl;
	}

	public Collection<Tadmcatalogo> getAerolineaColl() {
		return aerolineaColl;
	}

	public void setAerolineaColl(Collection<Tadmcatalogo> aerolineaColl) {
		this.aerolineaColl = aerolineaColl;
	}

	public Collection<Tadmcatalogo> getPicesTypeColl() {
		return picesTypeColl;
	}

	public void setPicesTypeColl(Collection<Tadmcatalogo> picesTypeColl) {
		this.picesTypeColl = picesTypeColl;
	}

}
