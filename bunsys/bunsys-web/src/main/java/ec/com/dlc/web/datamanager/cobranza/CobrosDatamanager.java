package ec.com.dlc.web.datamanager.cobranza;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.facturacion.Tfaccuentasxcobrar;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.login.LoginDatamanager;

@ManagedBean(name="cobrosDatamanager")
@SessionScoped
public class CobrosDatamanager extends BaseDatamanager {
	@ManagedProperty(value="#{loginDatamanager}")
	private LoginDatamanager loginDatamanager;
	
	private Tfaccuentasxcobrar cxcSearch;
	
	private Collection<Tfaccuentasxcobrar> cxcColl;
	
	
	@Override
	public String getIdDatamanager() {
		return "cobrosDataManager";
	}

	public Tfaccuentasxcobrar getCxcSearch() {
		return cxcSearch;
	}

	public void setCxcSearch(Tfaccuentasxcobrar cxcSearch) {
		this.cxcSearch = cxcSearch;
	}

	public Collection<Tfaccuentasxcobrar> getCxcColl() {
		return cxcColl;
	}

	public void setCxcColl(Collection<Tfaccuentasxcobrar> cxcColl) {
		this.cxcColl = cxcColl;
	}

	public LoginDatamanager getLoginDatamanager() {
		return loginDatamanager;
	}

	public void setLoginDatamanager(LoginDatamanager loginDatamanager) {
		this.loginDatamanager = loginDatamanager;
	}
	
}
