package ec.com.dlc.bunsys.service.login;

import static javax.ejb.TransactionAttributeType.MANDATORY;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import ec.com.dlc.bunsys.dao.facturacion.SeguridadDao;
import ec.com.dlc.bunsys.entity.administracion.Tadmusuario;
import ec.com.dlc.bunsys.util.FacturacionException;

@Stateless
@TransactionAttribute(MANDATORY)
public class SeguridadService {

	@Inject
	private SeguridadDao seguridadDao;
	
	public Tadmusuario loginUsuario(String username, String password) throws FacturacionException{
		Tadmusuario usuario = seguridadDao.buscaUsuarioLogin(username, password); 
		return usuario;
	}
}
