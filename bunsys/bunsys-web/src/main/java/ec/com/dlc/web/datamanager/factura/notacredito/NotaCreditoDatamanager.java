package ec.com.dlc.web.datamanager.factura.notacredito;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetdevolucione;
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
	
	private Tadmcompania compania;
	
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

	
}
