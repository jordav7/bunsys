package ec.com.dlc.bunsys.service.facturacion;

import static javax.ejb.TransactionAttributeType.MANDATORY;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import ec.com.dlc.bunsys.dao.facturacion.FacturaDao;
import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;
import ec.com.dlc.bunsys.entity.administracion.Tadmconversionunidad;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetproforma;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccuentasxcobrar;
import ec.com.dlc.bunsys.util.FacturacionException;

/**
 * Bean que contiene toda la l&oacute;gica del m&oacute;dulo de facturaci&oacute;n
 * @author DAVID
 */
@Stateless
@TransactionAttribute(MANDATORY)
public class FacturacionService {

	@Inject
	private FacturaDao facturaDao;
	
	public Collection<Tadmcompania> obtenerCompania() {
		return facturaDao.findObjects(new Tadmcompania());
	}

	
	/**
	 * Busca las notas de cr&eacute;dito en base a los filtros enviados desde la pantalla
	 * @param codCompania
	 * @param tfaccabdevolucione
	 * @param tsyspersona
	 * @return
	 * @throws FacturacionException
	 */
	public Collection<Tfaccabdevolucione> buscarNotaCredito(Integer codCompania, Tfaccabdevolucione tfaccabdevolucione, Tsyspersona tsyspersona) throws FacturacionException{
		return facturaDao.buscarNotaCreditoCompleto(codCompania, tfaccabdevolucione, tsyspersona);
	}
	
	
	
	//-----------------------------------
	
	/**
	 * Busaca el tipo de conversion del producto selecionado
	 * @param unidadVentaCodigo
	 * @param unidadVenta
	 * @return
	 */
	public Tadmconversionunidad conversionArticulo(Integer unidadVentaCodigo, String unidadVenta){
		return facturaDao.conversionArticulo(unidadVentaCodigo,unidadVenta);
	}
	
	/**
	 * Actualiza o graba una proforma
	 * @param tfaccabproform
	 * @param accion
	 * @throws FacturacionException
	 */
	public void guardarProforma(Tfaccabproforma tfaccabproform,String accion) throws FacturacionException {
		if(accion.equals("G")){
			facturaDao.create(tfaccabproform);
		}else{
			facturaDao.update(tfaccabproform);
		}
		int i=1;
		for(Tfacdetproforma tfacdetproforma2:tfaccabproform.getTfacdetproformas()){
			Tfacdetproforma tfacdetproforma = tfacdetproforma2;
			if(tfacdetproforma.getPk().getCodigodetalleprof()!= null){
				facturaDao.update(tfacdetproforma);
			}else{
				tfacdetproforma.getPk().setCodigodetalleprof(i);
				tfacdetproforma.setNumeroproforma(tfaccabproform.getPk().getNumeroproforma());
				facturaDao.create(tfacdetproforma);
			}
		}
	}
	
	public void grabarFactura(Tfaccabfactura tfaccabfactura){
		tfaccabfactura.setTadmcatalogo(null);
		facturaDao.create(tfaccabfactura);
		for(Tfacdetfactura tfacdetfactura: tfaccabfactura.getTfacdetfacturas()){
			tfacdetfactura.setNumerofactura(tfaccabfactura.getPk().getNumerofactura());
			tfacdetfactura.setTinvproducto(null);
			facturaDao.create(tfacdetfactura);
		}
	}
	
	public Collection<Tfaccuentasxcobrar> obtenerFacturasCredito(Integer codCompania, String numFac, String codId, String nombres, String apellidos, Date fecEmi, Date fecVen, Date fecPag, String numDoc) {
		return facturaDao.obtenerFacturasCredito(codCompania, numFac, codId, nombres, apellidos, fecEmi, fecVen, fecPag, numDoc);
	}
	
	public Collection<Tfaccuentasxcobrar> obtenerCuentasPorCobrar(Integer codCompania, String codigoCliente) throws FacturacionException {
		return facturaDao.obtenerCuentasPorCobrar(codCompania, codigoCliente);
	}
}
