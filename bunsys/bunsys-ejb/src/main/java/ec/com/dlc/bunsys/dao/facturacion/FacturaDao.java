package ec.com.dlc.bunsys.dao.facturacion;

import java.util.Collection;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import ec.com.dlc.bunsys.dao.general.GeneralDao;
import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.util.FacturacionException;

/**
 * DAO de la aplicaci&oacute;n realizar las consultas u operacion propias de un DAO
 * @author DAVID
 *
 */
public class FacturaDao extends GeneralDao {

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
			final StringBuilder sql = new StringBuilder("SELECT o FROM Tinvproducto o LEFT JOIN FETCH o.tadmtipoproducto LEFT JOIN FETCH o.tadmestado  WHERE o.pk.codigocompania=:codCompania ");
			
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
			query.setParameter("codCompania", codCompania);
			
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
	
	@SuppressWarnings("unchecked")
	public Collection<Tfaccabdevolucione> buscarNotaCreditoCompleto(Integer codcompania, Tfaccabdevolucione tfaccabdevolucione, Tsyspersona tsyspersona) throws FacturacionException {
		Collection<Tfaccabdevolucione> notasCreditoColl = null;
		try {
			final StringBuilder sqlNotaCredito = new StringBuilder("SELECT o FROM Tfaccabdevolucione o LEFT JOIN FETCH o.tfaccliente c LEFT JOIN FETCH c.tsyspersona p WHERE o.pk.codigocompania = :codcompania");
			if(StringUtils.isNotBlank(tfaccabdevolucione.getPk().getNumerodevoluciones())){
				sqlNotaCredito.append(" AND o.pk.numerodevoluciones=:numeronotacredito");
			}
			if(StringUtils.isNotBlank(tfaccabdevolucione.getNumerofactura())){
				sqlNotaCredito.append(" AND o.numerofactura=:numerofactura");
			}
			if(tfaccabdevolucione.getFechadevolucion() != null){
				sqlNotaCredito.append(" AND o.fechadevolucion=:fechadevolucion");
			}
			if(StringUtils.isNotBlank(tsyspersona.getIdentificacion())){
				sqlNotaCredito.append(" AND p.identificacion=:identificacion");
			}
			if(StringUtils.isNotBlank(tsyspersona.getNombres())){
				sqlNotaCredito.append(" AND UPPER(p.nombres) LIKE :nombres");
			}
			if(StringUtils.isNotBlank(tsyspersona.getApellidos())){
				sqlNotaCredito.append(" AND UPPER(p.apellidos) LIKE :apellidos");
			}
			Query query = this.entityManager.createQuery(sqlNotaCredito.toString());
			query.setParameter("codcompania", codcompania);
			if(StringUtils.isNotBlank(tfaccabdevolucione.getPk().getNumerodevoluciones())){
				query.setParameter("numeronotacredito", tfaccabdevolucione.getPk().getNumerodevoluciones());
			}
			if(StringUtils.isNotBlank(tfaccabdevolucione.getNumerofactura())){
				query.setParameter("numerofactura", tfaccabdevolucione.getNumerofactura());
			}
			if(tfaccabdevolucione.getFechadevolucion() != null){
				query.setParameter("fechadevolucion", tfaccabdevolucione.getFechadevolucion());
			}
			if(StringUtils.isNotBlank(tsyspersona.getIdentificacion())){
				query.setParameter("identificacion", tsyspersona.getIdentificacion());
			}
			if(StringUtils.isNotBlank(tsyspersona.getNombres())){
				query.setParameter("nombres", tsyspersona.getNombres());
			}
			if(StringUtils.isNotBlank(tsyspersona.getApellidos())){
				sqlNotaCredito.append(" AND o.apellidos=:apellidos");
				query.setParameter("apellidos", tsyspersona.getApellidos());
			}
			notasCreditoColl = query.getResultList();
			return notasCreditoColl;
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}
	
	/**
	 * Encuentra un cliente por indetificaci&oacute;n
	 * @param identificacion
	 * @return
	 * @throws FacturacionException
	 */
	public Tfaccliente buscarClientePorIdentificacion(String identificacion) throws FacturacionException {
		Tfaccliente cliente = null;
		try {
			final StringBuilder queryCliente = new StringBuilder("SELECT o FROM Tfaccliente o LEFT JOIN FETCH o.tsyspersona p WHERE p.identificacion=:identificacion");
			Query query = this.entityManager.createQuery(queryCliente.toString());
			query.setParameter("identificacion", identificacion);
			cliente = (Tfaccliente) query.getSingleResult();
			return cliente;
		} catch(NoResultException e){
			throw new FacturacionException(e);
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}
	
}
