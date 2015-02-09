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
	
	@SuppressWarnings("unchecked")
	public Collection<Tadmusuario> buscarPorFiltros(Integer codCompania, String nombreUsuario, String identificacion, String nombres, String apellidos) throws FacturacionException {
		final StringBuilder usuarioSQL = new StringBuilder("SELECT o FROM Tadmusuario o  LEFT JOIN FETCH o.tsyspersona p LEFT JOIN FETCH p.tadmestado WHERE o.pk.codigocompania=:codigocompania");
		try {
			if(StringUtils.isNotBlank(nombreUsuario)){
				usuarioSQL.append(" AND o.usuario=:usuario");
			}
			if(StringUtils.isNotBlank(identificacion)){
				usuarioSQL.append(" AND p.identificacion=:identificacion");
			}
			if(StringUtils.isNotBlank(nombres)){
				usuarioSQL.append(" AND UPPER(p.nombres) LIKE :nombres");
			}
			if(StringUtils.isNotBlank(apellidos)){
				usuarioSQL.append(" AND UPPER(p.apellidos) LIKE :apellidos");
			}
			Query query = entityManager.createQuery(usuarioSQL.toString());
			query.setParameter("codigocompania", codCompania);
			if(StringUtils.isNotBlank(nombreUsuario)){
				query.setParameter("usuario", nombreUsuario);
			}
			if(StringUtils.isNotBlank(identificacion)){
				query.setParameter("identificacion", identificacion);
			}
			if(StringUtils.isNotBlank(nombres)){
				query.setParameter("nombres", (new StringBuilder("%").append(nombres.toUpperCase()).append("%")).toString());
			}
			if(StringUtils.isNotBlank(apellidos)){
				query.setParameter("apellidos", (new StringBuilder("%").append(apellidos.toUpperCase()).append("%").toString()));
			}
			return query.getResultList();
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}
}
