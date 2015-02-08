package ec.com.dlc.bunsys.dao.facturacion;

import java.util.Collection;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import ec.com.dlc.bunsys.dao.general.GeneralDao;
import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmusuario;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
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
			Query query = this.entityManager.createQuery("SELECT o FROM Tadmcatalogo o WHERE o.pk.codigocompania=:codcompania AND o.pk.codigotipocatalogo=:codtipocatalogo");
			query.setParameter("codcompania", codCompania);
			query.setParameter("codtipocatalogo", codTipoCatalogo);
			catalogosColl = query.getResultList();
			return catalogosColl;
		} catch(Throwable e){
			throw new FacturacionException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Tinvproducto> obtenerProductos(Integer codCompania, String codigoProducto, String codigoAuxiliar, String nombreProducto, String color, Integer colorCodigo, String estado, Integer estadoCodigo) {
		Collection<Tinvproducto> productosColl = null;
		
		try{
			final StringBuilder sql = new StringBuilder("SELECT o FROM Tinvproducto o LEFT JOIN FETCH o.tadmtipoproducto LEFT JOIN FETCH o.tadmestado  WHERE o.pk.codigocompania=:codcompania ");
			
			if(StringUtils.isNotBlank(codigoProducto)){
				sql.append("and o.pk.codigoproductos = :codigoProducto ");
			}
			
			if(StringUtils.isNotBlank(codigoAuxiliar)){
				sql.append("and o.codigoauxiliar = :codigoAuxiliar ");
			}
			
			if(StringUtils.isNotBlank(nombreProducto)){
				sql.append("and upper(o.nombre) like :nombreProducto ");
			}
			
			if(StringUtils.isNotBlank(color)){
				sql.append("and o.color = :color ");
				sql.append("and o.colorcodigo = :colorCodigo ");
			}
			
			if(StringUtils.isNotBlank(estado)){
				sql.append("and o.estado = :estado ");
				sql.append("and o.estadocodigo = :estadoCodigo ");
			}
			
			Query query = this.entityManager.createQuery(sql.toString());
			query.setParameter("codcompania", codCompania);
			
			if(StringUtils.isNotBlank(codigoProducto)){
				query.setParameter("codigoProducto", codigoProducto);
			}
			
			if(StringUtils.isNotBlank(codigoAuxiliar)){
				query.setParameter("codigoAuxiliar", codigoAuxiliar);
			}
			
			if(StringUtils.isNotBlank(nombreProducto)){
				query.setParameter("nombreProducto", "%"+ nombreProducto.toUpperCase()+"%");
			}
			
			if(StringUtils.isNotBlank(color)){
				query.setParameter("color", color);
				query.setParameter("colorCodigo", colorCodigo);
			}
			
			if(StringUtils.isNotBlank(estado)){
				query.setParameter("estado", estado);
				query.setParameter("estadoCodigo", estadoCodigo);
			}
			
			productosColl = query.getResultList();
			return productosColl;
		} catch(Throwable e){
			throw new FacturacionException(e);
		}
	}
	
}
