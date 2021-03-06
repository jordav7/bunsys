package ec.com.dlc.bunsys.service.parametrizacion;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import ec.com.dlc.bunsys.dao.administracion.AdministracionDao;
import ec.com.dlc.bunsys.dao.compras.ComprasDao;
import ec.com.dlc.bunsys.dao.facturacion.FacturaDao;
import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;
import ec.com.dlc.bunsys.entity.administracion.Tadmparamsri;
import ec.com.dlc.bunsys.entity.administracion.Tadmtipocatalogo;
import ec.com.dlc.bunsys.entity.administracion.pk.TadmcatalogoPK;
import ec.com.dlc.bunsys.entity.administracion.pk.TadmcompaniaPK;
import ec.com.dlc.bunsys.entity.cuentasxpagar.Tcxpproveedor;
import ec.com.dlc.bunsys.entity.cuentasxpagar.pk.TcxpproveedorPK;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.inventario.pk.TinvproductoPK;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.entity.seguridad.pk.TsyspersonaPK;
import ec.com.dlc.bunsys.util.FacturacionException;

/**
 * Servicio que contiene todas las funcionalidades de la parametrizaci&oacute;n del sistema
 * @author dcruz
 *
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ParametrizacionService {

	@Inject
	private FacturaDao facturaDao;
	
	@Inject
	private ComprasDao comprasDao;
	
	@Inject
	private AdministracionDao admDao;
	/**
	 * Busca todos los c&aacute;talogos que coinciden con compan&iacute; y cat&aacute;logo
	 * @param codigoCompania
	 * @param codigoCatalogo
	 * @return
	 * @throws FacturacionException
	 */
	public Collection<Tadmcatalogo> obtenerCatalogos(Integer codigoCompania, Integer codigoCatalogo) throws FacturacionException{
		return facturaDao.obtenerCatalogos(codigoCompania, codigoCatalogo);
	}
	
	public Collection<Tadmparamsri> obtenerCatalogosSri( Integer codigoCatalogo) throws FacturacionException{
		return facturaDao.obtenerCatalogosSri(codigoCatalogo);
	}
	
	/**
	 * Crea o actualiza un art&iacute;culo
	 * @param articulo
	 * @throws FacturacionException
	 */
	public void guardarArticulo(Tinvproducto articulo) throws FacturacionException{
		if(articulo.getPk().getCodigoproductos() != null){//actualizacion
			facturaDao.update(articulo);
		} else{
			facturaDao.create(articulo);
		}
	}
	
	/**
	 * Buscar art&iacute;culo
	 * @param articulo
	 * @throws FacturacionException
	 */
	public Collection<Tinvproducto> obtenerProductos(Integer codCompania, String codigoProducto, String codigoAuxiliar, String nombreProducto, String color, Integer colorCodigo, String estado, Integer estadoCodigo) {
		return facturaDao.obtenerProductos(codCompania, codigoProducto, codigoAuxiliar, nombreProducto, color, colorCodigo, estado, estadoCodigo);
	}
	
	/**
	 * Elimina art&iacute;culo
	 * @param articulo
	 * @throws FacturacionException
	 */
	public void eliminarArticulo(TinvproductoPK articuloPk, Integer estadoCodigo) throws FacturacionException{
		Tinvproducto articulo = facturaDao.findById(Tinvproducto.class, articuloPk);
		articulo.setEstado("I");
		articulo.setEstadocodigo(estadoCodigo);
		facturaDao.update(articulo);
	}
		
	/**
	 * Buscar art&iacute;culo
	 * @param articulo
	 * @throws FacturacionException
	 */
	public Collection<Tcxpproveedor> obtenerProveedores(Integer codCompania, String codProv, String tipoId, Integer codTipoId, String id, String nombres, String apellidos, String grupoProv, Integer codGrupoProv,String estado, Integer estadoCodigo) {
		return comprasDao.obtenerProveedor(codCompania, codProv, tipoId, codTipoId, id, nombres, apellidos, grupoProv, codGrupoProv, estado, estadoCodigo);
	}
	
	/**
	 * Busca todos los c&aacute;talogos de tipo de documento SRI proveedores
	 * @param codigoCompania
	 * @param codigoCatalogo
	 * @return
	 * @throws FacturacionException
	 */
	public Collection<Tadmparamsri> obtenerTipoDocSriCxp(Integer codTipoParametro, String cxp) throws FacturacionException{
		return  comprasDao.obtenerTipoDocProv(codTipoParametro, cxp);
	}
	
	public void guardarProveedor( Tcxpproveedor proveedor) throws FacturacionException {
		proveedor.getTsyspersona().getPk().setCodigocompania(proveedor.getPk().getCodigocompania());
		Tsyspersona persona = proveedor.getTsyspersona();
		if(persona.getPk().getCodigopersona() != null){
			comprasDao.update(proveedor.getTsyspersona());
			comprasDao.update(proveedor);
		}else{
			comprasDao.create(persona);
			proveedor.setCodigopersona(persona.getPk().getCodigopersona());
			comprasDao.create(proveedor);
		}
	}
	
	public void eliminarProveedor(TcxpproveedorPK proveedorPk, Integer estadoCodigo) throws FacturacionException{
		Tcxpproveedor proveedor= comprasDao.findById(Tcxpproveedor.class, proveedorPk);
		Tsyspersona persona = proveedor.getTsyspersona();
		persona.setEstado("I");
		persona.setEstadocodigo(estadoCodigo);
		comprasDao.update(persona);
		
	}
	
	public Tadmcompania buscarCompania(TadmcompaniaPK companiaPk) throws FacturacionException {
		Tadmcompania compania = admDao.findById(Tadmcompania.class, companiaPk);
		return compania;
	}
	
	public void actualizarCompania(Tadmcompania compania) {
		Tadmcompania compAct = admDao.findById(Tadmcompania.class, compania.getPk());
		compAct.setCodigoestablecimiento(compania.getCodigoestablecimiento());
		compAct.setCodigopuntoemision(compania.getCodigopuntoemision());
		compAct.setCorreo(compania.getCorreo());
		compAct.setDireccionestablecimiento(compania.getDireccionestablecimiento());
		compAct.setDireccionmatriz(compania.getDireccionmatriz());
		compAct.setIdcontador(compania.getIdcontador());
		compAct.setLogocompania(compania.getLogocompania());
		compAct.setNombrecomercial(compania.getNombrecomercial());
		compAct.setNumeroresolucion(compania.getNumeroresolucion());
		compAct.setObligacioncontabilidad(compania.getObligacioncontabilidad());
		compAct.setRazonsocial(compania.getRazonsocial());
		compAct.setRuc(compania.getRuc());
		compAct.setTelefono(compania.getTelefono());
		compAct.setTiemporespuesta(compania.getTiemporespuesta());
		compAct.setTipoidcontador(compania.getTipoidcontador());
		compAct.setTipocompania(compania.getTipocompania());
		compAct.setTipoambiente(compania.getTipoambiente());
		compAct.setTipoambientecodigo(compania.getTipoambientecodigo());
		
		admDao.update(compAct);
	}
	
	/**
	 * Busca un cliente po identificaci&ocute;n
	 * @param identificacion
	 * @return
	 * @throws FacturacionException
	 */
	public Tfaccliente busquedaClienteIdentificacion(String identificacion) throws FacturacionException {
		return facturaDao.buscarClientePorIdentificacion(identificacion);
	}
	
	
	
	//---------------------------------------
	/**
	 * Crea o actualiza un cliente
	 * @param cliente
	 * @throws FacturacionException
	 */
	public void guardarCliente(Tfaccliente cliente) throws FacturacionException{
		Tsyspersona persona = cliente.getTsyspersona();
		if(persona.getPk().getCodigopersona() != null){//actualizacion
			facturaDao.update(cliente.getTsyspersona());
			if(cliente.getPk().getCodigocompania()!=null){
				facturaDao.update(cliente);
			}else{
				cliente.setCodigopersona(persona.getPk().getCodigopersona());
				facturaDao.create(cliente);
			}
		} else{
			facturaDao.create(persona);
			cliente.setCodigopersona(persona.getPk().getCodigopersona());
			cliente.setTsyspersona(null);
			facturaDao.create(cliente);
		}
	}
	
	/**
	 * Buscar clientes
	 * @param articulo
	 * @throws FacturacionException
	 */
	public Collection<Tfaccliente> buscarClientes(Integer codCompania,
			String nombre, String apellido, String identificacion)throws FacturacionException {
		return facturaDao.buscarClientes(codCompania, nombre, apellido, identificacion);
	}
	
	/**
	 * Inactiva los clientes
	 * @param articulo
	 * @throws FacturacionException
	 */
	public void eliminarCliente(TsyspersonaPK personaPk, Integer estadoCodigo)throws FacturacionException{
		try{
			Tsyspersona persona= facturaDao.findById(Tsyspersona.class, personaPk);
			persona.setEstado("I");
			persona.setEstadocodigo(estadoCodigo);
			facturaDao.update(persona);
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}
	
	public Collection<Tadmcatalogo> obtenerCatalogos(Integer codigoCompania,Tadmcatalogo tadmcatalogo) throws FacturacionException{
		try{
			return facturaDao.obtieneCatalogosFiltros(codigoCompania, tadmcatalogo);
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}
	
	public void guardarCatalogo(Tadmcatalogo tadmcatalogo) throws FacturacionException{
		try {
			facturaDao.update(tadmcatalogo);
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}
	
	public void eliminarLogicoCatalogo(Tadmcatalogo tadmcatalogo) {
		try {
			tadmcatalogo.setEstado("I");
			facturaDao.update(tadmcatalogo);
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}
	
	public Collection<Tadmtipocatalogo> obtenerTipoCatalogos() throws FacturacionException {
		try {
			return admDao.obtenerCatalogos();
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}
	
	public void guardarDirectorios(Collection<Tadmcatalogo> tadmcatalogoColl) throws FacturacionException {
		try {
			for (Tadmcatalogo tadmcatalogo : tadmcatalogoColl) {
				Files.createDirectories(Paths.get(tadmcatalogo.getValor()));
				admDao.update(tadmcatalogo);
			}
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}
	
	public void guardarCatalogos(Collection<Tadmcatalogo> tadmcatalogoColl) throws FacturacionException {
		try {
			for (Tadmcatalogo tadmcatalogo : tadmcatalogoColl) {
				admDao.update(tadmcatalogo);
			}
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}
	
	public Tadmcatalogo obtenerCatalogo(Integer codCompania, Integer codTipo, String codCatalogo) throws FacturacionException {
		try {
			TadmcatalogoPK pk = new TadmcatalogoPK();
			pk.setCodigocompania(codCompania);
			pk.setCodigotipocatalogo(codTipo);
			pk.setCodigocatalogo(codCatalogo);
			return admDao.findById(Tadmcatalogo.class, pk);
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}

	/**
	 * Busca todos los c&aacute;talogos de tipo de documento SRI proveedores
	 * @param codigoCompania
	 * @param codigoCatalogo
	 * @return
	 * @throws FacturacionException
	 */
	public Collection<Tadmparamsri> parametroSri(Integer codTipoParametro) throws FacturacionException{
		return  comprasDao.parametroSri(codTipoParametro);
	}
}
