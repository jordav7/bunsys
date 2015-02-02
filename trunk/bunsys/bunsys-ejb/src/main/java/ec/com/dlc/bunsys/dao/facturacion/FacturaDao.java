package ec.com.dlc.bunsys.dao.facturacion;

import java.util.Collection;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import ec.com.dlc.bunsys.dao.general.GeneralDao;
import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmusuario;
import ec.com.dlc.bunsys.util.FacturacionException;

/**
 * DAO de la aplicaci&oacute;n realizar las consultas u operacion propias de un DAO
 * @author DAVID
 *
 */
public class FacturaDao extends GeneralDao {

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
	
	/**
	 * Retorna una lista de cat&aacute;logos en base al c&oacute;digo de compan&iacute;a y c&oacute;digo tipo cat&aacute;logo
	 * @param codCompania
	 * @param codTipoCatalogo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Collection<Tadmcatalogo> obtenerCatalogos(Integer codCompania, Integer codTipoCatalogo) throws FacturacionException{
		Collection<Tadmcatalogo> catalogosColl = null;
		try{
			Query query = this.entityManager.createQuery("SELECT o FROM Tadmcatalogo o WHERE o.codigocompania=:codcompania AND o.codigotipocatalogo=:codtipocatalogo");
			query.setParameter("codcompania", codCompania);
			query.setParameter("codtipocatalogo", codTipoCatalogo);
			catalogosColl = query.getResultList();
			return catalogosColl;
		} catch(Throwable e){
			throw new FacturacionException(e);
		}
	}
}
