package ec.com.dlc.bunsys.facade;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;
import ec.com.dlc.bunsys.entity.administracion.Tadmconversionunidad;
import ec.com.dlc.bunsys.entity.administracion.Tadmparamsri;
import ec.com.dlc.bunsys.entity.administracion.Tadmtipocatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmusuario;
import ec.com.dlc.bunsys.entity.administracion.pk.TadmcompaniaPK;
import ec.com.dlc.bunsys.entity.cuentasxpagar.Tcxpproveedor;
import ec.com.dlc.bunsys.entity.cuentasxpagar.pk.TcxpproveedorPK;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccuentasxcobrar;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfacformapago;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.inventario.pk.TinvproductoPK;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.entity.seguridad.pk.TsyspersonaPK;
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
	
	
	public Collection<Tadmparamsri> buscarObtenerCatSri(Integer codigoCatalogo);
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
	
	
	/**
	 * Inactiva un usuario
	 * @param tadmusuario
	 * @throws FacturacionException
	 */
	void eliminarUsuario(Tadmusuario tadmusuario) throws FacturacionException;
	
	
	/**
	 * Busca companias en base al PK
	 * @param companiaPk
	 * @return
	 */
	Tadmcompania buscarCompania(TadmcompaniaPK companiaPk) throws FacturacionException;
	
	void actualizarCompania(Tadmcompania compania) throws FacturacionException;
	
	/**
	 * Realiza la b&uacute;squeda de las notas de cr&eacute;dito
	 * @param codCompania
	 * @param tfaccabdevolucione
	 * @param tsyspersona
	 * @return
	 * @throws FacturacionException
	 */
	Collection<Tfaccabdevolucione> buscarNotasCredito(Integer codCompania, Tfaccabdevolucione tfaccabdevolucione, Tsyspersona tsyspersona) throws FacturacionException;
	
	/**
	 * Busca un cliente por identificaci&oacute;n
	 * @param identificacion
	 * @return
	 * @throws FacturacionException
	 */
	Tfaccliente buscarPorIdentificacion(String identificacion) throws FacturacionException;
	
	
	
	
	
	//-----------------------
	
	/**
	 * Graba la factura
	 * @param tfaccabfactura
	 */
	public void grabarFactura(Tfaccabfactura tfaccabfactura,String accion,Collection<Tfacdetfactura>listaEliminar);
	
	/**
	 * Crea o actualiza un nuevo cliente
	 * @param cliente
	 * @throws FacturacionException
	 */
	void guardarCliente(Tfaccliente tfaccliente) throws FacturacionException;
	
	/**
	 * Busca los clientes
	 * @param codCompania
	 * @param nombre
	 * @param apellido
	 * @param identificacion
	 * @return Collection<Tfaccliente>
	 * @throws FacturacionException
	 */
	Collection<Tfaccliente> buscarClientes(Integer codCompania, String nombre, String apellido, String identificacion) throws FacturacionException;
	
	/**
	 * Elimina el cliente
	 * @param tfacclientePK
	 * @param estadoCodigo
	 */
	void eliminarCliente(TsyspersonaPK personaPk, Integer estadoCodigo);
	
	/**
	 * Tabla de conversion segun la unidad de venta del articulo
	 * @param unidadVentaCodigo
	 * @param unidadVenta
	 * @return
	 */
	public Tadmconversionunidad conversionArticulo(Integer unidadVentaCodigo, String unidadVenta);
	
	/**
	 * Guarda la proforma
	 * @param tfaccabproforma
	 * @throws FacturacionException
	 */
	void guardarProforma(Tfaccabproforma tfaccabproforma, String accion,Collection<Tfacdetproforma>listaEliminar) throws FacturacionException;
	
	List<Tfaccabproforma> cabeceraProformas(String numeroproforma)throws FacturacionException;
	
	List<Tfacdetproforma> detalleProformas(String numeroproforma)throws FacturacionException;
	

	Collection<Tfaccuentasxcobrar> obtenerFacturasCredito(Integer codCompania, String numFac, String codId, String nombres, String apellidos, Date fecEmi, Date fecVen, Date fecPag, String numDoc) throws FacturacionException ;
	
	
	Collection<Tfaccuentasxcobrar> obtenerCuentasPorCobrar(Integer codCompania, String codigoCliente) throws FacturacionException ;
	
	Collection<Tadmcatalogo> obtenerCatalogos(Integer codigocompania, Tadmcatalogo tadmcatalogo) throws FacturacionException;
	
	void guardarCatalogo(Tadmcatalogo tadmcatalogo) throws FacturacionException;
	
	void guardarCatalogos(Collection<Tadmcatalogo> tadmcatalogoColl) throws FacturacionException;
	
	void guardarDirectorios(Collection<Tadmcatalogo> tadmcatalogoColl) throws FacturacionException;
	
	void eliminarCatalogo(Tadmcatalogo tadmcatalogo) throws FacturacionException;
	
	Collection<Tadmtipocatalogo> obtenerTiposCatalogo() throws FacturacionException;
	
	Tadmcatalogo obtenerCatalogo(Integer codcompania, Integer codigotipo, String valor) throws FacturacionException;
	
	void registrarPago(Collection<Tfaccuentasxcobrar> cxcColl, Integer codCompania, Integer estadoCodigo, Integer tipoDocCodigo, String numdoc, String concepto, Date fechaPago);
	
	Integer obtenerSecuencias(Integer codCompania, String codComprobante) throws FacturacionException;
	
	List<Tfaccabfactura> cabeceraFacturas(String numerofactura,String estadosri,Date fechainicio, Date fechafin)throws FacturacionException;
	
	List<Tfacdetfactura> detalleFacturas(String numerofactura)throws FacturacionException;
	
	/**
	 * Busca cat&aacute;logos de par&aacute;metros SRI
	 * @param codTipoCatalogo
	 * @return Collection<Tadmparamsri>
	 * @throws FacturacionException
	 */
	Collection<Tadmparamsri> parametroSri(Integer codTipoParametro) throws FacturacionException ;
	/**
	 * listado de las formas de pago de la factura
	 * @param codigocompania
	 * @param numerofactura
	 * @return List<Tfacformapago>
	 */
	List<Tfacformapago>tfacformapagos(Integer codigocompania,String numerofactura);
	
	Tfaccabfactura obtenerFactura(Integer codigoCompania, String numeroFactura) throws FacturacionException;
	
	void guardarNotaCredito(Tfaccabdevolucione notaCredito, Collection<Tfacdetdevolucione> detallesNotaCreditoColl) throws FacturacionException;
}
