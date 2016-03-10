/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.dlc.web.datamanager.usuarios;

import ec.com.dlc.bunsys.entity.administracion.Tadmusuario;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.login.LoginDatamanager;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author dcruz
 */
@ManagedBean(name = "usuariosDatamanager")
@SessionScoped
public class UsuariosDatamanager extends BaseDatamanager{
    
	/**
	 * Datamanager de sesi&oacute;n
	 */
	@ManagedProperty(value="#{loginDatamanager}")
	private LoginDatamanager loginDatamanager;
    /**
     * Usuario b&uacute;squeda
     */
    private Tadmusuario usuarioSearch;
    /**
     * Listado de usuarios 
     */
    private Collection<Tadmusuario> usuariosColl;
    /**
     * Usuario a guardar en la base de datos
     */
    private Tadmusuario usuario;
    
    @Override
    public String getIdDatamanager() {
        return "usuariosDatamanager";
    }

    public Tadmusuario getUsuarioSearch() {
        return usuarioSearch;
    }

    public void setUsuarioSearch(Tadmusuario usuarioSearch) {
        this.usuarioSearch = usuarioSearch;
    }

    public Tadmusuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Tadmusuario usuario) {
        this.usuario = usuario;
    }

    public Collection<Tadmusuario> getUsuariosColl() {
        return usuariosColl;
    }

    public void setUsuariosColl(Collection<Tadmusuario> usuariosColl) {
        this.usuariosColl = usuariosColl;
    }

	public LoginDatamanager getLoginDatamanager() {
		return loginDatamanager;
	}

	public void setLoginDatamanager(LoginDatamanager loginDatamanager) {
		this.loginDatamanager = loginDatamanager;
	}
    
}
