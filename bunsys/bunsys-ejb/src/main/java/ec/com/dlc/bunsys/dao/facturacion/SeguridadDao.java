package ec.com.dlc.bunsys.dao.facturacion;

import java.util.Collection;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import ec.com.dlc.bunsys.dao.general.GeneralDao;
import ec.com.dlc.bunsys.entity.administracion.Tadmusuario;
import ec.com.dlc.bunsys.util.FacturacionException;

public class SeguridadDao extends GeneralDao {

	/**
	 * Busca el usuario por nombre y password
	 * @param username
	 * @param password
	 * @return
	 * @throws FacturacionException
	 */
	public Tadmusuario buscaUsuarioLogin(String username, String password) throws FacturacionException{
		Tadmusuario usuario = null;
		try {
			Query query = this.entityManager.createQuery("SELECT o FROM Tadmusuario o WHERE o.usuario=:usuario AND o.password=:password");
			query.setParameter("usuario", username);
			query.setParameter("password", password); 
			usuario = (Tadmusuario) query.getSingleResult();
		} catch (NoResultException e) {
			usuario = null;
		} catch (NonUniqueResultException e) {
			usuario = null;
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
		return usuario;
	}
	
	public Collection<Tadmusuario> buscarPorFiltros(Integer codCompania, String nombreUsuario, String identificacion, String nombres, String apellidos) throws FacturacionException {
		final StringBuilder usuarioSQL = new StringBuilder("SELECT o FROM Tadmusuario o  LEFT JOIN FETCH o.tsyspersona WHERE o.pk.codigocompania=:codigocompania");
		try {
			if(StringUtils.isNotBlank(nombreUsuario)){
				usuarioSQL.append(" AND o.usuario=:usuario");
			}
			if(StringUtils.isNotBlank(identificacion)){
				usuarioSQL.append(" AND o.identificacion=:identificacion");
				usuarioSQL.append(" AND o.identificacion=:identificacion");
			}
			return null;
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}
}
