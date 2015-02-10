package ec.com.dlc.bunsys.facade;

import java.util.Collection;

import javax.ejb.Local;
import javax.persistence.criteria.CriteriaBuilder.In;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;
import ec.com.dlc.bunsys.entity.administracion.Tadmparamsri;
import ec.com.dlc.bunsys.entity.administracion.Tadmusuario;
import ec.com.dlc.bunsys.entity.cuentasxpagar.Tcxpproveedor;
import ec.com.dlc.bunsys.entity.cuentasxpagar.pk.TcxpproveedorPK;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.inventario.pk.TinvproductoPK;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.util.FacturacionException;

/**
 * Acceso principal a la aplicaci&oacute;n desde la vista, todos los servicios deben ser invocados desde este punto<br/>
 * Se basa en el patr&oacute;n <code>Application Service</code>
 * @author DAVID
 */
@Local
public interface BunsysService {

	public Collection<Tadmcompania> obtenerCompanias() ;
	
	/**
	 * Hace el login del usuario
	 * @param username
	 * @param password
	 * @return
	 * @throws FacturacionException
	 */
	Tadmusuario buscaLoginUsuario(String username, String password) throws FacturacionException;
	
	/**
	 * Busca cat&aacute;logos de la base
	 * @param codCompania
	 * @param codTipoCatalogo
	 * @return
	 * @throws FacturacionException
	 */
	Collection<Tadmcatalogo> buscarObtenerCatalogos(Integer codCompania, Integer codTipoCatalogo) throws FacturacionException;
	
	/**
	 * Crea o actualiza un nuevo art&iacute;culo
	 * @param articulo
	 * @throws FacturacionException
	 */
	void guardarArticulo(Tinvproducto articulo) throws FacturacionException;
	
	/**
	 * Buscar art&iacute;culos con parametros
	 * @param articulo
	 * @throws FacturacionException
	 */
	Collection<Tinvproducto> buscarObtenerProductos(Integer codCompania, String codigoProducto, String codigoAuxiliar, String nombreProducto, String color, Integer colorCodigo, String estado, Integer estadoCodigo) throws FacturacionException;
	
	/**
	 * Elimina art&iacute;culos
	 * @param articulo
	 * @throws FacturacionException
	 */
	void eliminarArticulo(TinvproductoPK articuloPk, Integer estadoCodigo) throws FacturacionException;
	
	/**
	 * Busca Proveedores
	 */
	Collection<Tcxpproveedor> buscarObtenerProveedores(Integer codCompania, String codProv, String tipoId, Integer codTipoId, String id, String nombres, String apellidos, String grupoProv, Integer codGrupoProv,String estado, Integer estadoCodigo);
	
	/**
	 * Busca cat&aacute;logos de par&aacute;metros SRI cxp
	 * @param codCompania
	 * @param codTipoCatalogo
	 * @return
	 * @throws FacturacionException
	 */
	Collection<Tadmparamsri> buscarTipoDocSriCxp(Integer codTipoParametro, String cxp) throws FacturacionException;
	
	void guardarProveedor(Tcxpproveedor proveedor) throws FacturacionException;
	
	void eliminarProveedor(TcxpproveedorPK proveedorPk, Integer estadoCodigo) throws FacturacionException;
	
	
	/**
	 * Busca usuarios en base a los par&aacute;metros de consulta enviados
	 * @param codCompania
	 * @param nombreUsuario
	 * @param identificacion
	 * @param nombres
	 * @param apellidos
	 * @return
	 */
	Collection<Tadmusuario> buscarUsuarios(Integer codCompania, String nombreUsuario, String identificacion, String nombres, String apellidos);
	
	/**
	 * Guarda el usuario enviado y graba o actualiza informaci&oacute;n de la persona
	 * @param codigocompania
	 * @param tadmusuario
	 * @param tsyspersona
	 * @throws FacturacionException
	 */
	void guardarUsuario(Integer codigocompania, Tadmusuario tadmusuario, Tsyspersona tsyspersona) throws FacturacionException;
}
