package ec.com.dlc.web.datamanager.compras;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.cuentasxpagar.Tcxpproveedor;
import ec.com.dlc.web.componentes.ProveedorComponent;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.login.LoginDatamanager;

@ManagedBean(name="proveedorDatamanager")
@SessionScoped
public class ProveedorDatamanager  extends BaseDatamanager{

	
	@ManagedProperty(value="#{loginDatamanager}")
	private LoginDatamanager loginDatamanager;
	
	private Tcxpproveedor proveedorSearch;
	
	private Collection<Tcxpproveedor> proveedorColl;
	
	private Collection<Tadmcatalogo> tipoIdColl;
	
	private Collection<Tadmcatalogo> grupoProvColl;
	
	private Collection<Tadmcatalogo> estadoProvColl;
	
	private ProveedorComponent proveedorComponente;
	
	
	@Override
	public String getIdDatamanager() {
		return "proveedorDatamanager";
	}

	public LoginDatamanager getLoginDatamanager() {
		return loginDatamanager;
	}

	public void setLoginDatamanager(LoginDatamanager loginDatamanager) {
		this.loginDatamanager = loginDatamanager;
	}

	public Tcxpproveedor getProveedorSearch() {
		return proveedorSearch;
	}

	public void setProveedorSearch(Tcxpproveedor proveedorSearch) {
		this.proveedorSearch = proveedorSearch;
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

	public Collection<Tadmcatalogo> getEstadoProvColl() {
		return estadoProvColl;
	}

	public void setEstadoProvColl(Collection<Tadmcatalogo> estadoProvColl) {
		this.estadoProvColl = estadoProvColl;
	}

	public Collection<Tcxpproveedor> getProveedorColl() {
		return proveedorColl;
	}

	public void setProveedorColl(Collection<Tcxpproveedor> proveedorColl) {
		this.proveedorColl = proveedorColl;
	}

	public ProveedorComponent getProveedorComponente() {
		return proveedorComponente;
	}

	public void setProveedorComponente(ProveedorComponent proveedorComponente) {
		this.proveedorComponente = proveedorComponente;
	}
	
	

}
