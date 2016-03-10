package ec.com.dlc.web.datamanager.administracion;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmtipocatalogo;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.login.LoginDatamanager;

@ManagedBean(name="catalogoDatamanager")
@SessionScoped
public class CatalogoDatamanager extends BaseDatamanager {

	@ManagedProperty(value="#{loginDatamanager}")
	private LoginDatamanager loginDatamanager;
	
	/**
	 * Usado en b&uacute;squedas
	 */
	private Tadmcatalogo catalogoBusq;
	/**
	 * Usado en el mantenimiento
	 */
	private Tadmcatalogo catalogo;
	
	private Collection<Tadmtipocatalogo> tipoCatalogoColl;
	private Collection<Tadmcatalogo> catalogoColl;
	
	@Override
	public String getIdDatamanager() {
		// TODO Auto-generated method stub
		return "catalogoDatamanager";
	}

	public Tadmcatalogo getCatalogoBusq() {
		return catalogoBusq;
	}

	public void setCatalogoBusq(Tadmcatalogo catalogoBusq) {
		this.catalogoBusq = catalogoBusq;
	}

	public Tadmcatalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Tadmcatalogo catalogo) {
		this.catalogo = catalogo;
	}

	public Collection<Tadmcatalogo> getCatalogoColl() {
		return catalogoColl;
	}

	public void setCatalogoColl(Collection<Tadmcatalogo> catalogoColl) {
		this.catalogoColl = catalogoColl;
	}

	public Collection<Tadmtipocatalogo> getTipoCatalogoColl() {
		return tipoCatalogoColl;
	}

	public void setTipoCatalogoColl(Collection<Tadmtipocatalogo> tipoCatalogoColl) {
		this.tipoCatalogoColl = tipoCatalogoColl;
	}

	public LoginDatamanager getLoginDatamanager() {
		return loginDatamanager;
	}

	public void setLoginDatamanager(LoginDatamanager loginDatamanager) {
		this.loginDatamanager = loginDatamanager;
	}

}
