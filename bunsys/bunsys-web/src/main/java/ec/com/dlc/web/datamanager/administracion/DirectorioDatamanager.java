package ec.com.dlc.web.datamanager.administracion;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.login.LoginDatamanager;

@ManagedBean(name="directorioDatamanager")
@SessionScoped
public class DirectorioDatamanager extends BaseDatamanager {
	
	@ManagedProperty(value="#{loginDatamanager}")
	private LoginDatamanager loginDatamanager;

	private Collection<Tadmcatalogo> directoriosColl;
	
	@Override
	public String getIdDatamanager() {
		return "directorioDatamanager";
	}

	public Collection<Tadmcatalogo> getDirectoriosColl() {
		return directoriosColl;
	}

	public void setDirectoriosColl(Collection<Tadmcatalogo> directoriosColl) {
		this.directoriosColl = directoriosColl;
	}

	public LoginDatamanager getLoginDatamanager() {
		return loginDatamanager;
	}

	public void setLoginDatamanager(LoginDatamanager loginDatamanager) {
		this.loginDatamanager = loginDatamanager;
	}

}
