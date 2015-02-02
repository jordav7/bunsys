package ec.com.dlc.bunsys.service.parametrizacion;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import ec.com.dlc.bunsys.dao.facturacion.FacturaDao;
import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
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
}
