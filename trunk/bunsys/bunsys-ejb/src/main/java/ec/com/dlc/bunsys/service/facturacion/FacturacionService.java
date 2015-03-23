package ec.com.dlc.bunsys.service.facturacion;

import static javax.ejb.TransactionAttributeType.MANDATORY;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import ec.com.dlc.bunsys.dao.facturacion.FacturaDao;
import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;
import ec.com.dlc.bunsys.entity.administracion.Tadmconversionunidad;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccuentasxcobrar;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfacformapago;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfaccuentasxcobrarPK;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.service.parametrizacion.SecuenciaService;
import ec.com.dlc.bunsys.util.ComprobantesUtil;
import ec.com.dlc.bunsys.util.FacturacionException;
import ec.com.dlc.bunsys.util.sri.ConstantesSRI;

/**
 * Bean que contiene toda la l&oacute;gica del m&oacute;dulo de facturaci&oacute;n
 * @author DAVID
 */
@Stateless
@TransactionAttribute(MANDATORY)
public class FacturacionService {

	@Inject
	private FacturaDao facturaDao;
	
	@Inject
	private SecuenciaService secuenciaService;
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
	public void guardarProforma(Tfaccabproforma tfaccabproform,String accion,Collection<Tfacdetproforma>listaEliminar) throws FacturacionException {
		try{
			Collection<Tfacdetproforma>tfacdetproformas=tfaccabproform.getTfacdetproformas();
			if(accion.equals("G")){
				facturaDao.create(tfaccabproform);
			}
			for(Tfacdetproforma tfacdetproforma2:tfacdetproformas){
				Tfacdetproforma tfacdetproforma = tfacdetproforma2;
				if(tfacdetproforma.getPk().getCodigodetalleprof()!= null){
					//elimina
					facturaDao.update(tfacdetproforma);
				}else{
					tfacdetproforma.setNumeroproforma(tfaccabproform.getPk().getNumeroproforma());
					facturaDao.create(tfacdetproforma);
				}
			}
			if(!accion.equals("G")){
				tfaccabproform.setTfaccliente(null);
				facturaDao.update(tfaccabproform);
			}
			//elimar detalle
			for(Tfacdetproforma eliminarItem:listaEliminar){
				facturaDao.delete(eliminarItem);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new FacturacionException(e);
		}
	}
	
	public void grabarFactura(Tfaccabfactura tfaccabfactura)throws FacturacionException{
		try{
			Integer sec=secuenciaService.obtenerSecuenciaComp(tfaccabfactura.getPk().getCodigocompania(), ConstantesSRI.COD_FACTURA);
			tfaccabfactura.getPk().setNumerofactura(ComprobantesUtil.getInstancia().getsecuencia(sec.toString(), 9));
			tfaccabfactura.setClaveacceso(
					secuenciaService.generaClaveAcceso(tfaccabfactura.getFechafactura(),
							                           tfaccabfactura.getPk().getCodigocompania(), tfaccabfactura.getPk().getNumerofactura()));
			facturaDao.create(tfaccabfactura);
			for(Tfacdetfactura tfacdetfactura: tfaccabfactura.getTfacdetfacturas()){
				tfacdetfactura.setNumerofactura(tfaccabfactura.getPk().getNumerofactura());
				tfacdetfactura.setTinvproducto(null);
				facturaDao.create(tfacdetfactura);
			}
			//efectivo
			for(Tfacformapago tfacformapago:tfaccabfactura.getTfacformapagos()){
				tfacformapago.setNumerofactura(tfaccabfactura.getPk().getNumerofactura());
				facturaDao.create(tfacformapago);
			}
			//credito
			for(Tfaccuentasxcobrar tfaccuentasxcobrar:tfaccabfactura.getTfaccuentasxcobrars()){
				tfaccuentasxcobrar.setNumerofactura(tfaccabfactura.getPk().getNumerofactura());
				facturaDao.create(tfaccuentasxcobrar);
				tfaccuentasxcobrar.setReferencia(tfaccuentasxcobrar.getPk().getCodigocuenxcobr());
				facturaDao.update(tfaccuentasxcobrar);
			}
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}
	
	/**
	 * lista las cabeceras de las proformas
	 * @param numeroproforma
	 * @return
	 */
	public List<Tfaccabproforma> cabeceraProformas(String numeroproforma){
		return facturaDao.cabeceraProformas(numeroproforma);
	}
	
	public List<Tfacdetproforma> detalleProformas(String numeroproforma){
		return facturaDao.detalleProformas(numeroproforma);
	}
	public Collection<Tfaccuentasxcobrar> obtenerFacturasCredito(Integer codCompania, String numFac, String codId, String nombres, String apellidos, Date fecEmi, Date fecVen, Date fecPag, String numDoc) {
		return facturaDao.obtenerFacturasCredito(codCompania, numFac, codId, nombres, apellidos, fecEmi, fecVen, fecPag, numDoc);
	}
	
	public Collection<Tfaccuentasxcobrar> obtenerCuentasPorCobrar(Integer codCompania, String codigoCliente) throws FacturacionException {
		return facturaDao.obtenerCuentasPorCobrar(codCompania, codigoCliente);
	}
	
	public void grabaAbono(Tfaccuentasxcobrar abono) {
		facturaDao.create(abono);
	}
	
	public Tfaccuentasxcobrar buscarCxc( TfaccuentasxcobrarPK pkCxc) {
		return facturaDao.findById(Tfaccuentasxcobrar.class, pkCxc);
	}
	
	public void actualizarCxc(Tfaccuentasxcobrar cxc) {
		facturaDao.update(cxc);
	}
	
	public List<Tfaccabfactura> cabeceraFacturas(String numerofactura,String estadosri)throws FacturacionException{
		return facturaDao.cabeceraFacturas(numerofactura,estadosri);
	}
	
	public List<Tfacdetfactura> detalleFacturas(String numerofactura)throws FacturacionException{
		return facturaDao.detalleFacturas(numerofactura);
	}
	
	public List<Tfacformapago> tfacformapagos(Integer codigocompania,
			String numerofactura) throws FacturacionException{
		return facturaDao.tfacformapagos(codigocompania, numerofactura);
	}
	
	public Tfaccabfactura obtenerFactura(Integer codigoCompania, String numeroFactura) throws FacturacionException{
		return facturaDao.obtenerFacturaDetalles(codigoCompania, numeroFactura);
	}
		
	public void guardarEnviarNotaCredito(Tfaccabdevolucione notaCredito,
			Collection<Tfacdetdevolucione> detallesNotaCreditoColl) throws FacturacionException {
		
	}
}
