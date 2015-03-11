package ec.com.dlc.bunsys.dao.compras;

import java.util.Collection;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import ec.com.dlc.bunsys.dao.general.GeneralDao;
import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmparamsri;
import ec.com.dlc.bunsys.entity.cuentasxpagar.Tcxpproveedor;
import ec.com.dlc.bunsys.util.FacturacionException;

public class ComprasDao extends GeneralDao{
	/**
	 * Retorna una lista de cat&aacute;logos de parametros del SRI del tipo de documento del proveedor
	 * @param codCompania
	 * @param codTipoCatalogo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Collection<Tadmparamsri> obtenerTipoDocProv(Integer codTipoParametro, String cxp) throws FacturacionException{
		Collection<Tadmparamsri> catalogosColl = null;
		try{
			Query query = this.entityManager.createQuery("SELECT o FROM Tadmparamsri o WHERE o.pk.codigotipoparamsri=:codTipoParametro and o.cxp = :cxp");
			query.setParameter("codTipoParametro", codTipoParametro);
			query.setParameter("cxp", cxp);
			catalogosColl = query.getResultList();
			return catalogosColl;
		} catch(Throwable e){
			throw new FacturacionException(e);
		}
	}
	
	public Collection<Tcxpproveedor> obtenerProveedor(Integer codCompania, String codProv, String tipoId, Integer codTipoId, String id, String nombres, String apellidos, String grupoProv, Integer codGrupoProv,String estado, Integer estadoCodigo ) {
		Collection<Tcxpproveedor> proveedorColl;
		
		try{
			StringBuilder sql = new StringBuilder("SELECT o FROM Tcxpproveedor o LEFT JOIN FETCH o.tsyspersona p LEFT JOIN FETCH o.tadmgrupoproveedor LEFT JOIN FETCH o.tadmcontribuyente LEFT JOIN FETCH p.tadmestado WHERE o.pk.codigocompania = :codCompania ");
			if(StringUtils.isNotBlank(codProv)){
				sql.append("and upper(o.pk.codigoproveedor) like :codProv ");
			}
			
			if(StringUtils.isNotBlank(tipoId)){
				sql.append("and o.tsyspersona.tipoid = :tipoId and o.tsyspersona.tipoidcodigo = :codTipoId ");
			}
			
			if(StringUtils.isNotBlank(id)){
				sql.append("and upper(o.tsyspersona.identificacion) like :id ");
			}
			
			if(StringUtils.isNotBlank(nombres)){
				sql.append("and upper(o.tsyspersona.nombres) like :nombres ");
			}
			
			if(StringUtils.isNotBlank(apellidos)){
				sql.append("and upper(o.tsyspersona.apellidos) like :apellidos ");
			}
			
			if(StringUtils.isNotBlank(grupoProv)){
				sql.append("and o.grupoproveedor = :grupoProv and o.grupoproveedorcodigo = :codGrupoProv ");
			}
			
			if(StringUtils.isNotBlank(estado)){
				sql.append("and o.tsyspersona.estado = :estado and o.tsyspersona.estadocodigo = :estadoCodigo ");
			}
			
			Query query = this.entityManager.createQuery(sql.toString());
			query.setParameter("codCompania", codCompania);
			
			if(StringUtils.isNotBlank(codProv)){
				query.setParameter("codProv", "%"+codProv+"%");
			}
			
			if(StringUtils.isNotBlank(tipoId)){
				query.setParameter("tipoId",tipoId)
					 .setParameter("codTipoId", codTipoId);
			}
			
			if(StringUtils.isNotBlank(id)){
				query.setParameter("id","%"+id+"%");
			}
			
			if(StringUtils.isNotBlank(nombres)){
				query.setParameter("nombres","%"+nombres.toUpperCase()+"%");
			}
			
			if(StringUtils.isNotBlank(apellidos)){
				query.setParameter("apellidos","%"+apellidos.toUpperCase()+"%");
			}
			
			if(StringUtils.isNotBlank(grupoProv)){
				query.setParameter("grupoProv",grupoProv)
					 .setParameter("codGrupoProv", codGrupoProv);
			}
			
			if(StringUtils.isNotBlank(estado)){
				query.setParameter("estado",estado)
				 	 .setParameter("estadoCodigo", estadoCodigo);
			}
			
			proveedorColl = query.getResultList();
			return proveedorColl;
		}catch(Throwable e){
			throw new FacturacionException(e);
		}
	}
}
