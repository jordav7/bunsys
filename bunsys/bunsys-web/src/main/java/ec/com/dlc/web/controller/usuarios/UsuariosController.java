/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.dlc.web.controller.usuarios;

import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.usuarios.UsuariosDatamanager;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author dcruz
 */
@ManagedBean(name = "usuariosController")
@ViewScoped
public class UsuariosController extends BaseController{

    @ManagedProperty(value = "#{usuariosDatamanager}")
    private UsuariosDatamanager usuariosDatamanager;
    
    @Override
    public BaseDatamanager getDatamanager() {
        return usuariosDatamanager;
    }

    @Override
    public void inicializar() {
        
    }
    
    public UsuariosDatamanager getUsuariosDatamanager() {
        return usuariosDatamanager;
    }

    public void setUsuariosDatamanager(UsuariosDatamanager usuariosDatamanager) {
        this.usuariosDatamanager = usuariosDatamanager;
    }
    
}
