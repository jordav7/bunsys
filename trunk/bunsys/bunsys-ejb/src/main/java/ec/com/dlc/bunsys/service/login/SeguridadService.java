package ec.com.dlc.bunsys.service.login;

import static javax.ejb.TransactionAttributeType.MANDATORY;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import ec.com.dlc.bunsys.dao.facturacion.SeguridadDao;
import ec.com.dlc.bunsys.entity.administracion.Tadmusuario;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
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
		return seguridadDao.buscarPorFiltros(codCompania, nombreUsuario, identificacion, nombres, apellidos);
	}
	
	/**
	 * Guarda los datos del usuarios
	 * @param tadmusuario
	 * @param tsyspersona
	 * @throws FacturacionException
	 */
	public void guardarUsuario(Integer codigocompania, Tadmusuario tadmusuario, Tsyspersona tsyspersona) throws FacturacionException{
		try {
			
			if(tsyspersona == null){
				throw new FacturacionException("La persona no debe ser nula");
			}
			
			if(tsyspersona.getPk() != null && tsyspersona.getPk().getCodigopersona() != null){
				seguridadDao.update(tsyspersona);
			} else{
				tsyspersona.getPk().setCodigocompania(codigocompania);
				tsyspersona.setEstado("A");
				tsyspersona.setEstadocodigo(16);
				tsyspersona.setTipoid("C");
				tsyspersona.setTipoidcodigo(17);
				seguridadDao.create(tsyspersona);
			}
			
			if(tadmusuario.getPk().getCodigousuario() != null){
				seguridadDao.update(tadmusuario);
			} else{
				tadmusuario.getPk().setCodigocompania(codigocompania);
				tadmusuario.setPassword("1111");
				tadmusuario.setCodigopersona(tsyspersona.getPk().getCodigopersona());
				tadmusuario.setFecharegistro(new Date());
				seguridadDao.create(tadmusuario);
			}
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}
}
