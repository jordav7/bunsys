package ec.com.dlc.web.datamanager.administracion;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.login.LoginDatamanager;

@ManagedBean
@SessionScoped
public class SecuenciaDatamanager extends BaseDatamanager {

	@ManagedProperty(value="#{loginDatamanager}")
	private LoginDatamanager loginDatamanager;
	
	private Collection<Tadmcatalogo> secuenciasColl;
	
	@Override
	public String getIdDatamanager() {
		return "secuenciaDatamanager";
	}

	public Collection<Tadmcatalogo> getSecuenciasColl() {
		return secuenciasColl;
	}

	public void setSecuenciasColl(Collection<Tadmcatalogo> secuenciasColl) {
		this.secuenciasColl = secuenciasColl;
	}

	public LoginDatamanager getLoginDatamanager() {
		return loginDatamanager;
	}

	public void setLoginDatamanager(LoginDatamanager loginDatamanager) {
		this.loginDatamanager = loginDatamanager;
	}

}
