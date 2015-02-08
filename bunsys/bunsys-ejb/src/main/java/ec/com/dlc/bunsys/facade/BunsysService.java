package ec.com.dlc.bunsys.facade;

import java.util.Collection;

import javax.ejb.Local;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;
import ec.com.dlc.bunsys.entity.administracion.Tadmusuario;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.inventario.pk.TinvproductoPK;
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
	void eliminarArticulo(TinvproductoPK articuloPk, Integer estadoCodigo);
}
