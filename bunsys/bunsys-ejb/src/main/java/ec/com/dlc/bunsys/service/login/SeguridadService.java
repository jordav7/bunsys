package ec.com.dlc.bunsys.service.login;

import static javax.ejb.TransactionAttributeType.MANDATORY;

import java.util.Collection;

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
	
	/**
	 * Busca un usuario por nombre de usuario y password
	 * @param username
	 * @param password
	 * @return
	 * @throws FacturacionException
	 */
	public Tadmusuario loginUsuario(String username, String password) throws FacturacionException{
		Tadmusuario usuario = seguridadDao.buscaUsuarioLogin(username, password); 
		return usuario;
	}
	
	/**
	 * Busca una lista de personas en base a los filtros enviados
	 * @param codCompania
	 * @param nombreUsuario
	 * @param identificacion
	 * @param nombres
	 * @param apellidos
	 * @return
	 */
	public Collection<Tadmusuario> buscarUsuarios(Integer codCompania, String nombreUsuario, String identificacion, String nombres, String apellidos) throws FacturacionException{
		return null;
	}
}
