package ec.com.dlc.web.datamanager.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.administracion.Tadmusuario;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;

@ManagedBean(name="loginDatamanager")
@SessionScoped
public class LoginDatamanager extends BaseDatamanager {

	/**
	 * usuario en sesi&oacute;n
	 */
	private Tadmusuario login;
	
	/**
	 * Nombre de usuario
	 */
	private String username;
	/**
	 * Password de usuario
	 */
	private String password;
	
	@Override
	public String getIdDatamanager() {
		return "loginDatamanager";
	}

	public Tadmusuario getLogin() {
		return login;
	}

	public void setLogin(Tadmusuario login) {
		this.login = login;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
