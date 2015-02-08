package ec.com.dlc.bunsys.facade.impl;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;
import ec.com.dlc.bunsys.entity.administracion.Tadmusuario;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.inventario.pk.TinvproductoPK;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.bunsys.service.facturacion.FacturacionService;
import ec.com.dlc.bunsys.service.login.LoginService;
import ec.com.dlc.bunsys.service.parametrizacion.ParametrizacionService;
import ec.com.dlc.bunsys.util.FacturacionException;

@Stateless
@TransactionAttribute(REQUIRES_NEW)
public class BunsysServiceBean implements BunsysService {

	@EJB
	private FacturacionService facturacionService;
	
	@EJB
	private LoginService loginService;
	
	@EJB
	private ParametrizacionService parametrizacionService;
	
	@Override
	public Collection<Tadmcompania> obtenerCompanias() {
		return facturacionService.obtenerCompania();
	}

	@Override
	public Tadmusuario buscaLoginUsuario(String username, String password)
			throws FacturacionException {
		return loginService.loginUsuario(username, password);
	}

	@Override
	public Collection<Tadmcatalogo> buscarObtenerCatalogos(Integer codCompania,
			Integer codTipoCatalogo) throws FacturacionException {
		return parametrizacionService.obtenerCatalogos(codCompania, codTipoCatalogo);
	}

	@Override
	public void guardarArticulo(Tinvproducto articulo)
			throws FacturacionException {
		parametrizacionService.guardarArticulo(articulo);
	}

	@Override
	public Collection<Tinvproducto> buscarObtenerProductos(Integer codCompania,
			String codigoProducto, String codigoAuxiliar,
			String nombreProducto, String color, Integer colorCodigo,
			String estado, Integer estadoCodigo) throws FacturacionException {
		return parametrizacionService.obtenerProductos(codCompania, codigoProducto, codigoAuxiliar, nombreProducto, color, colorCodigo, estado, estadoCodigo);
	}

	@Override
	public void eliminarArticulo(TinvproductoPK articuloPk, Integer estadoCodigo) {
		parametrizacionService.eliminarArticulo(articuloPk, estadoCodigo);
	}

}
