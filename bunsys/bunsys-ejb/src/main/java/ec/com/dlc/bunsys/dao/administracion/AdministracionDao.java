package ec.com.dlc.bunsys.dao.administracion;

import java.util.Collection;

import javax.persistence.Query;

import ec.com.dlc.bunsys.dao.general.GeneralDao;
import ec.com.dlc.bunsys.entity.administracion.Tadmtipocatalogo;
import ec.com.dlc.bunsys.util.FacturacionException;

public class AdministracionDao extends GeneralDao {

	@SuppressWarnings("unchecked")
	public Collection<Tadmtipocatalogo> obtenerCatalogos() throws FacturacionException{
		try {
			final StringBuilder sql = new StringBuilder("SELECT o FROM Tadmtipocatalogo o WHERE o.estado=:estado");
			Query query = this.entityManager.createQuery(sql.toString());
			query.setParameter("estado", "A");
			Collection<Tadmtipocatalogo> tipoCatalogoCollection = query.getResultList();
			return tipoCatalogoCollection;
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}
}
