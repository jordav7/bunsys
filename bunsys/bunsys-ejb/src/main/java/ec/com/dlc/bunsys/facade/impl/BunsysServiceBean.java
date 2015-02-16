package ec.com.dlc.bunsys.facade.impl;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;
import ec.com.dlc.bunsys.entity.administracion.Tadmparamsri;
import ec.com.dlc.bunsys.entity.administracion.Tadmusuario;
import ec.com.dlc.bunsys.entity.administracion.pk.TadmcompaniaPK;
import ec.com.dlc.bunsys.entity.cuentasxpagar.Tcxpproveedor;
import ec.com.dlc.bunsys.entity.cuentasxpagar.pk.TcxpproveedorPK;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.inventario.pk.TinvproductoPK;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.bunsys.service.facturacion.FacturacionService;
import ec.com.dlc.bunsys.service.login.SeguridadService;
import ec.com.dlc.bunsys.service.parametrizacion.ParametrizacionService;
import ec.com.dlc.bunsys.util.FacturacionException;

@Stateless
@TransactionAttribute(REQUIRES_NEW)
public class BunsysServiceBean implements BunsysService {

	@EJB
	private FacturacionService facturacionService;
	
	@EJB
	private SeguridadService seguridadService;
	
	@EJB
	private ParametrizacionService parametrizacionService;
	
	@Override
	public Collection<Tadmcompania> obtenerCompanias() {
		return facturacionService.obtenerCompania();
	}

	@Override
	public Tadmusuario buscaLoginUsuario(String username, String password)
			throws FacturacionException {
		return seguridadService.loginUsuario(username, password);
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

	@Override
	public Collection<Tcxpproveedor> buscarObtenerProveedores(
			Integer codCompania, String codProv, String tipoId,
			Integer codTipoId, String id, String nombres, String apellidos,
			String grupoProv, Integer codGrupoProv, String estado,
			Integer estadoCodigo) {
		return parametrizacionService.obtenerProveedores(codCompania, codProv, tipoId, codTipoId, id, nombres, apellidos, grupoProv, codGrupoProv, estado, estadoCodigo);
	}

	@Override
	public Collection<Tadmparamsri> buscarTipoDocSriCxp(Integer codTipoParametro, String cxp) throws FacturacionException {
		return parametrizacionService.obtenerTipoDocSriCxp(codTipoParametro, cxp);
	}

	@Override
	public void guardarProveedor(Tcxpproveedor proveedor)
			throws FacturacionException {
		// TODO Auto-generated method stub
		parametrizacionService.guardarProveedor(proveedor);
	}

	@Override
	public void eliminarProveedor(TcxpproveedorPK proveedorPk,
			Integer estadoCodigo) throws FacturacionException {
		parametrizacionService.eliminarProveedor(proveedorPk, estadoCodigo);
	}
	
	@Override
	public Collection<Tadmusuario> buscarUsuarios(Integer codCompania, String nombreUsuario, String identificacion, String nombres, String apellidos) {
		return seguridadService.buscarUsuarios(codCompania, nombreUsuario, identificacion, nombres, apellidos);
	}

	@Override
	public void guardarUsuario(Integer codigocompania,Tadmusuario tadmusuario, Tsyspersona tsyspersona)
			throws FacturacionException {
		seguridadService.guardarUsuario(codigocompania, tadmusuario, tsyspersona);
	}

	@Override
	public void eliminarUsuario(Tadmusuario tadmusuario)
			throws FacturacionException {
		seguridadService.eliminarUsuario(tadmusuario);
	}

	@Override
	public Tadmcompania buscarCompania(TadmcompaniaPK companiaPk) throws FacturacionException {
		return parametrizacionService.buscarCompania(companiaPk);
	}

	@Override
	public void actualizarCompania(Tadmcompania compania)
			throws FacturacionException {
		parametrizacionService.actualizarCompania(compania);
	}

	@Override
	public Collection<Tfaccabdevolucione> buscarNotasCredito(Integer codCompania,
			Tfaccabdevolucione tfaccabdevolucione, Tsyspersona tsyspersona)
			throws FacturacionException {
		return facturacionService.buscarNotaCredito(codCompania, tfaccabdevolucione, tsyspersona);
	}

	@Override
	public Tfaccliente buscarPorIdentificacion(String identificacion)
			throws FacturacionException {
		return parametrizacionService.busquedaClienteIdentificacion(identificacion);
	}
}
