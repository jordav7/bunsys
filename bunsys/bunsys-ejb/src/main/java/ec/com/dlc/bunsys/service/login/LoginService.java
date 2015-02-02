package ec.com.dlc.bunsys.service.login;

import static javax.ejb.TransactionAttributeType.MANDATORY;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import ec.com.dlc.bunsys.dao.facturacion.FacturaDao;
import ec.com.dlc.bunsys.entity.administracion.Tadmusuario;
import ec.com.dlc.bunsys.util.FacturacionException;

/**
 * Session Bean o Session Facade, contiene la l&oacute;gica funcional de un requerimiento especifico de la aplicaci&oacute;n<br/>
 * @author DAVID
 */
@Stateless
@TransactionAttribute(MANDATORY)
public class LoginService {

	@Inject
	private FacturaDao facturaDao;
	
	public Tadmusuario loginUsuario(String username, String password) throws FacturacionException{
		Tadmusuario usuario = facturaDao.buscaUsuarioLogin(username, password); 
		return usuario;
	}
}
