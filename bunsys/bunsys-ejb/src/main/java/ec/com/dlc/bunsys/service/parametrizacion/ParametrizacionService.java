package ec.com.dlc.bunsys.service.parametrizacion;

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
import ec.com.dlc.bunsys.entity.administracion.pk.TadmcompaniaPK;
import ec.com.dlc.bunsys.entity.cuentasxpagar.Tcxpproveedor;
import ec.com.dlc.bunsys.entity.cuentasxpagar.pk.TcxpproveedorPK;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.inventario.pk.TinvproductoPK;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
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
		
		admDao.update(compAct);
	}
}
