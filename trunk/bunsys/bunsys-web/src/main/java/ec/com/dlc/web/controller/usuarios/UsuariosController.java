/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.dlc.web.controller.usuarios;

import ec.com.dlc.bunsys.entity.administracion.Tadmusuario;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.usuarios.UsuariosDatamanager;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

/**
 *
 * @author dcruz
 */
@ManagedBean(name = "usuariosController")
@ViewScoped
public class UsuariosController extends BaseController{

    @ManagedProperty(value = "#{usuariosDatamanager}")
    private UsuariosDatamanager usuariosDatamanager;
    
    @Inject
    private BunsysService bunsysService;
    
    @Override
    public BaseDatamanager getDatamanager() {
        return usuariosDatamanager;
    }

    @Override
    public void inicializar() {
    	usuariosDatamanager.setUsuarioSearch(new Tadmusuario());
        usuariosDatamanager.getUsuarioSearch().setTsyspersona(new Tsyspersona());
    }
    
    /**
     * Busca los usuarios con los filtros de b&uacute;squeda enviados
     */
    public void buscar(){
        usuariosDatamanager.setUsuariosColl(
        	bunsysService.buscarUsuarios(usuariosDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), 
        		usuariosDatamanager.getUsuarioSearch().getUsuario(), 
        		usuariosDatamanager.getUsuarioSearch().getTsyspersona().getIdentificacion(), 
        		usuariosDatamanager.getUsuarioSearch().getTsyspersona().getNombres(), 
        		usuariosDatamanager.getUsuarioSearch().getTsyspersona().getApellidos()));
    }
    
    /**
     * Abre el popup de usuarios
     */
    public void crear(){
        usuariosDatamanager.setUsuario(new Tadmusuario());
        usuariosDatamanager.getUsuario().setTsyspersona(new Tsyspersona());
    }
    
    /**
     * Elimina un registro
     */
    public void eliminar() {
		try {
			bunsysService.eliminarUsuario(usuariosDatamanager.getUsuario());
			buscar();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado correctamente", "Registro eliminado correctamente"));
		} catch (Throwable e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}
	}
    
    /**
     * Guarda los usuarios con personas
     */
    public void guardar() {
    	try{
    		bunsysService.guardarUsuario(usuariosDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), usuariosDatamanager.getUsuario(), usuariosDatamanager.getUsuario().getTsyspersona());
    		buscar();
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro guardado correctamente", "Registro guardado correctamente"));
    	}catch(Throwable e){
    		FacesContext.getCurrentInstance().validationFailed();
    		RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
    	}
	}
    
    public void cerrar() {
		try {
			RequestContext.getCurrentInstance().execute("PF('dialogUsuario').hide();");
		} catch (Throwable e) {
			FacesContext.getCurrentInstance().validationFailed();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}
	}
    
    public UsuariosDatamanager getUsuariosDatamanager() {
        return usuariosDatamanager;
    }

    public void setUsuariosDatamanager(UsuariosDatamanager usuariosDatamanager) {
        this.usuariosDatamanager = usuariosDatamanager;
    }
    
}
