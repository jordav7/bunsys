package ec.com.dlc.web.controller.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import ec.com.dlc.bunsys.entity.administracion.Tadmusuario;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.login.LoginDatamanager;

@ManagedBean(name="loginController")
@ViewScoped
public class LoginController extends BaseController {
	
	@ManagedProperty(value="#{loginDatamanager}")
	private LoginDatamanager loginDatamanager;
	
	@Inject
	private BunsysService bunsysService;
	
	@Override
	public BaseDatamanager getDatamanager() {
		return loginDatamanager;
	}

	@Override
	public void inicializar() {

	}
	
	/**
	 * Hace el login de usuario
	 */
	public String ingreso() {
		Tadmusuario usuarioLogueado = bunsysService.buscaLoginUsuario(loginDatamanager.getUsername(), loginDatamanager.getPassword());
		if(usuarioLogueado != null){
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			session.setAttribute("usuarioLogueado", usuarioLogueado);
			loginDatamanager.setLogin(usuarioLogueado);
			return "/home.xhtml?faces-redirect=true";
		} else{
			RequestContext.getCurrentInstance().execute("$('.alert-no-user').css('visibility','visible')");
			RequestContext.getCurrentInstance().execute("$('.alert-no-user').removeClass('hide')");
			return null;
		}
	}

	public LoginDatamanager getLoginDatamanager() {
		return loginDatamanager;
	}

	public void setLoginDatamanager(LoginDatamanager loginDatamanager) {
		this.loginDatamanager = loginDatamanager;
	}

}
