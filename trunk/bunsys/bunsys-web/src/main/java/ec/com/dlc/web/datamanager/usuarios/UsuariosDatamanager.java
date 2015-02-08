/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.dlc.web.datamanager.usuarios;

import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author dcruz
 */
@ManagedBean(name = "usuariosDatamanager")
public class UsuariosDatamanager extends BaseDatamanager{

    @Override
    public String getIdDatamanager() {
        return "usuariosDatamanager";
    }
    
}
