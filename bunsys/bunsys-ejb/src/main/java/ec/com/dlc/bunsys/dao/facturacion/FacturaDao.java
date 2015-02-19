package ec.com.dlc.bunsys.dao.facturacion;

import java.util.Collection;
import java.util.Date;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import ec.com.dlc.bunsys.dao.general.GeneralDao;
import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmconversionunidad;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccuentasxcobrar;
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
	
	public Collection<Tfaccuentasxcobrar> obtenerFacturasCredito(Integer codCompania, String numFac, String codId, String nombres, String apellidos, Date fecEmi, Date fecVen, Date fecPag, String numDoc) {
		Collection<Tfaccuentasxcobrar> cxcColl = null;
		try{
			final StringBuilder sql = new StringBuilder("SELECT o from Tfaccuentasxcobrar o LEFT JOIN FETCH o.tfaccliente p LEFT JOIN FETCH p.tsyspersona where o.numdoc is not null and o.pk.codigocompania=:codCompania ");
			
			if(StringUtils.isNotBlank(numFac)){
				sql.append("and o.numerofactura = :numFac ");
			}
			
			if (StringUtils.isNotBlank(codId)) {
				sql.append("and o.tfaccliente.tsyspersona.identificacion = :codId ");
			}
			
			if (StringUtils.isNotBlank(nombres)) {
				sql.append("and upper(o.tfaccliente.tsyspersona.nombres) like :nombres ");
			}
			
			if (StringUtils.isNotBlank(apellidos)) {
				sql.append("and upper(o.tfaccliente.tsyspersona.apellidos) like :apellidos ");
			}
			
			if (fecEmi != null) {
				sql.append("and o.fechaemision = :fecEmi ");
			}
			
			if (fecVen != null) {
				sql.append("and o.fechavence = :fecVen ");
			}
			
			if (fecPag != null) {
				sql.append("and o.fechapago = :fecPag ");
			}
			
			if(StringUtils.isNotBlank(numDoc)){
				sql.append("and o.numdoc = :numDoc ");
			}
			
			Query query = this.entityManager.createQuery(sql.toString());
			query.setParameter("codCompania", codCompania);
			
			if(StringUtils.isNotBlank(numFac)){
				query.setParameter("numFac", numFac);
			}
			
			if (StringUtils.isNotBlank(codId)) {
				query.setParameter("codId", codId);
			}
			
			if (StringUtils.isNotBlank(nombres)) {
				query.setParameter("nombres", nombres.toUpperCase());
			}
			
			if (StringUtils.isNotBlank(apellidos)) {
				query.setParameter("apellidos", apellidos.toUpperCase());
			}
			
			if (fecEmi != null) {
				query.setParameter("fecEmi", fecEmi);
			}
			
			if (fecVen != null) {
				query.setParameter("fecVen", fecVen);
			}
			
			if (fecPag != null) {
				query.setParameter("fecPag", fecPag);
			}
			
			if(StringUtils.isNotBlank(numDoc)){
				query.setParameter("numDoc", numDoc);
			}
			
			cxcColl = query.getResultList();
			return cxcColl;
		}catch(Throwable e){
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
			return null;
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Tfaccuentasxcobrar> obtenerCuentasPorCobrar(Integer codCompania, String codigoCliente) throws FacturacionException {
		Collection<Tfaccuentasxcobrar> cxcColl = null;
		try{
			final String sql="SELECT o FROM Tfaccuentasxcobrar o LEFT JOIN FETCH o.tadmtipodoc where o.numdoc is null and o.pk.codigocompania=:codCompania and o.tfaccliente.pk.codigocliente = :codigoCliente  ";
			Query query = this.entityManager.createQuery(sql);
			query.setParameter("codCompania", codCompania);
			query.setParameter("codigoCliente", codigoCliente);
			cxcColl = query.getResultList();
			return cxcColl;
		}catch (Throwable e) {
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
	
	//--------
	@SuppressWarnings("unchecked")
	public Collection<Tfaccliente> buscarClientes(Integer codCompania,	String nombre, String apellido, String identificacion) {
		Collection<Tfaccliente> clientesColl = null;
		try{
			final StringBuilder sql = new StringBuilder("SELECT o FROM Tfaccliente o "
					                                 + " LEFT JOIN FETCH o.tadmformapago"
					                                 + " LEFT JOIN FETCH o.tadmgrupocliente  "
					                                 + " LEFT JOIN FETCH o.tsyspersona  p"
					                                 + " LEFT JOIN FETCH p.tadmestado "
					                                 + " LEFT JOIN FETCH p.tadmtipopersona "
					                                 + "WHERE o.pk.codigocompania=:codcompania ");
			
			if(StringUtils.isNotBlank(nombre)){
				sql.append("and upper(p.nombres) like= :nombres ");
			}
			
			if(StringUtils.isNotBlank(apellido)){
				sql.append("and upper(p.apellidos) like = :apellidos ");
			}
			
			if(StringUtils.isNotBlank(identificacion)){
				sql.append("and p.identificacion :identificacion ");
			}
			
			Query query = this.entityManager.createQuery(sql.toString());
			query.setParameter("codcompania", codCompania);
			
			if(StringUtils.isNotBlank(nombre)){
				query.setParameter("nombres", "%"+ nombre.toUpperCase()+"%");
			}
			
			if(StringUtils.isNotBlank(apellido)){
				query.setParameter("codigoAuxiliar","%"+apellido.toUpperCase()+"%");
			}
			
			if(StringUtils.isNotBlank(identificacion)){
				query.setParameter("identificacion", identificacion);
			}
			
			clientesColl = query.getResultList();
			return clientesColl;
		} catch(Throwable e){
			throw new FacturacionException(e);
		}
	}
	
	
	public Tadmconversionunidad conversionArticulo(Integer unidadVentaCodigo, String unidadVenta){
		try{
			System.out.println(unidadVentaCodigo+" ----- "+unidadVenta);
			Query query = entityManager.createQuery("SELECT o FROM Tadmconversionunidad o "+
					" where o.unidadventa=:unidadventa and o.unidadventacodigo=:unidadventacodigo");
			query.setParameter("unidadventacodigo", unidadVentaCodigo);
			query.setParameter("unidadventa", unidadVenta);
			return (Tadmconversionunidad)query.getResultList().get(0);
		} catch (Exception e){
			throw new FacturacionException(e);
		}
	}
}
