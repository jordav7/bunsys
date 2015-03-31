package ec.com.dlc.bunsys.service.facturacion;

import static javax.ejb.TransactionAttributeType.MANDATORY;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import ec.com.dlc.bunsys.common.marshaller.MarshallerFactory;
import ec.com.dlc.bunsys.common.sign.factory.XmlSignFactory;
import ec.com.dlc.bunsys.common.util.Constants;
import ec.com.dlc.bunsys.common.util.ResponseServiceDto;
import ec.com.dlc.bunsys.dao.facturacion.FacturaDao;
import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;
import ec.com.dlc.bunsys.entity.administracion.Tadmconversionunidad;
import ec.com.dlc.bunsys.entity.administracion.pk.TadmcatalogoPK;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccuentasxcobrar;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfacformapago;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfaccuentasxcobrarPK;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.schema.v110.factura.Factura;
import ec.com.dlc.bunsys.schema.v110.factura.Factura.Detalles.Detalle.DetallesAdicionales;
import ec.com.dlc.bunsys.schema.v110.factura.Factura.Detalles.Detalle.DetallesAdicionales.DetAdicional;
import ec.com.dlc.bunsys.schema.v110.notacredito.Impuesto;
import ec.com.dlc.bunsys.schema.v110.notacredito.InfoTributaria;
import ec.com.dlc.bunsys.schema.v110.notacredito.NotaCredito;
import ec.com.dlc.bunsys.schema.v110.notacredito.NotaCredito.Detalles;
import ec.com.dlc.bunsys.schema.v110.notacredito.NotaCredito.Detalles.Detalle;
import ec.com.dlc.bunsys.schema.v110.notacredito.NotaCredito.Detalles.Detalle.Impuestos;
import ec.com.dlc.bunsys.schema.v110.notacredito.NotaCredito.InfoAdicional;
import ec.com.dlc.bunsys.schema.v110.notacredito.NotaCredito.InfoNotaCredito;
import ec.com.dlc.bunsys.schema.v110.notacredito.ObligadoContabilidad;
import ec.com.dlc.bunsys.schema.v110.notacredito.TotalConImpuestos;
import ec.com.dlc.bunsys.schema.v110.notacredito.TotalConImpuestos.TotalImpuesto;
import ec.com.dlc.bunsys.service.parametrizacion.SecuenciaService;
import ec.com.dlc.bunsys.util.ComprobantesUtil;
import ec.com.dlc.bunsys.util.FacturacionException;
import ec.com.dlc.bunsys.util.sri.ConstantesSRI;
import ec.com.dlc.bunsys.webservices.sri.autorizacion.Autorizacion;
import ec.com.dlc.bunsys.webservices.sri.autorizacion.AutorizacionComprobantesService;
import ec.com.dlc.bunsys.webservices.sri.autorizacion.Mensaje;
import ec.com.dlc.bunsys.webservices.sri.autorizacion.RespuestaComprobante;
import ec.com.dlc.bunsys.webservices.sri.recepcion.RecepcionComprobantesService;
import ec.com.dlc.bunsys.webservices.sri.recepcion.RespuestaSolicitud;

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
	
	public ResponseServiceDto grabarFactura(Tfaccabfactura tfaccabfactura,String accion,Collection<Tfacdetfactura>listaEliminar,Tadmcompania tadmcompania,Tfaccliente cliente)throws FacturacionException{
		ResponseServiceDto responseService = null;
		try{
			guardaFactuar(tfaccabfactura, accion, listaEliminar);
			//solo graba en estado sin firma
			if(tfaccabfactura.getEstadosri().equals("SF")){
				responseService = new ResponseServiceDto();
				responseService.setMensajes(new ArrayList<String>());
				responseService.getMensajes().add("SE GUARDO LA FACTURA");
				return responseService;
			}
			//guarda en estado de contingencia, genera el archivo y lo firma
//			if(tfaccabfactura.getEstadosri().equals("CO")){
				
			//guarad firma y envia  en estado firma y envia
			Factura factura= new Factura();
			completarDatosFactura(factura, tfaccabfactura, tadmcompania, cliente);
			//convierte
			String xml = MarshallerFactory.getInstancia().marshal(factura);
			//firma password y certificado
			xml = XmlSignFactory.getXmlDataSign().signXML(xml, new File("C:/Users/LuisH/Desktop/RESPALDO FACTURAEL/firmas"), tfaccabfactura.getAditionalProperty("passwordToken").toString());
			RecepcionComprobantesService recepcionComprobantesService = new RecepcionComprobantesService();
			
			if(tfaccabfactura.getEstadosri().equals("FE")){
				//web service valida el comprobante
				RespuestaSolicitud respuestaSolicitud = recepcionComprobantesService.getRecepcionComprobantesPort().validarComprobante(xml.getBytes());
				//estado recivido
				if(respuestaSolicitud.getEstado().equals(Constants.STATE_RECEIVED)){
					AutorizacionComprobantesService autorizacionService = new AutorizacionComprobantesService();
					RespuestaComprobante respuestaComprobante = autorizacionService.getAutorizacionComprobantesPort().autorizacionComprobante(factura.getInfoTributaria().getClaveAcceso());
					if(respuestaComprobante != null && !respuestaComprobante.getAutorizaciones().getAutorizacion().isEmpty()){
						for (Autorizacion autorizacion : respuestaComprobante.getAutorizaciones().getAutorizacion()) {
							StringBuilder comprobante = new StringBuilder("<![CDATA[").append(autorizacion.getComprobante()).append("]]>");
							autorizacion.setComprobante(comprobante.toString());
							String finalXml = MarshallerFactory.getInstancia().marshal(autorizacion);
							responseService = new ResponseServiceDto();
							responseService.setEstado(autorizacion.getEstado());
							responseService.setComprobante(finalXml);
							completaDatosRetorno(responseService, autorizacion);
							//generaComprobantes(responseService, sriNotaCredito, notaCredito, detallesNotaCreditoColl);
							break;
						}
					} else if(respuestaComprobante == null || respuestaComprobante.getAutorizaciones().getAutorizacion().isEmpty()) {
						responseService = new ResponseServiceDto();
						responseService.setEstado(Constants.STATE_NO_SUBMIT);
						throw new FacturacionException("ERROR al consultar al servicio de autorizacion");
					}
				} else {
					responseService = new ResponseServiceDto();
					responseService.setEstado(respuestaSolicitud.getEstado());
					throw new FacturacionException("El comprobante ha sido devuelto");
				}
			}
			
			return responseService;
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}
	
	private void guardaFactuar(Tfaccabfactura tfaccabfactura,String accion,Collection<Tfacdetfactura>listaEliminar){
		List<Tfacformapago> tfacformapagos=null;
		List<Tfaccuentasxcobrar> cuentasxcobrar=null;
		if(accion.equals("G")){
			Integer sec=secuenciaService.obtenerSecuenciaComp(tfaccabfactura.getPk().getCodigocompania(), ConstantesSRI.COD_FACTURA);
			tfaccabfactura.getPk().setNumerofactura(ComprobantesUtil.getInstancia().getsecuencia(sec.toString(), 9));
			//clave de acceso
			tfaccabfactura.setClaveacceso(secuenciaService.generaClaveAcceso(tfaccabfactura.getFechafactura(),tfaccabfactura.getPk().getCodigocompania(), tfaccabfactura.getPk().getNumerofactura()));
			facturaDao.create(tfaccabfactura);
		}else{
			tfacformapagos= tfacformapagos(tfaccabfactura.getPk().getCodigocompania(), tfaccabfactura.getPk().getNumerofactura());
			cuentasxcobrar= cuentasxcobrarxcompxnumfac(tfaccabfactura.getPk().getCodigocompania(), tfaccabfactura.getPk().getNumerofactura());
		}
		for(Tfacdetfactura tfacdetfactura: tfaccabfactura.getTfacdetfacturas()){
			if(tfacdetfactura.getPk().getCodigodetfactura()!=null){
				//tfacdetfactura.setTinvproducto(null);
				facturaDao.update(tfacdetfactura);
			}else{
				tfacdetfactura.setNumerofactura(tfaccabfactura.getPk().getNumerofactura());
				//tfacdetfactura.setTinvproducto(null);
				facturaDao.create(tfacdetfactura);
			}
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
		if(!accion.equals("G")){
			//clave de acceso
			tfaccabfactura.setClaveacceso(secuenciaService.generaClaveAcceso(tfaccabfactura.getFechafactura(),tfaccabfactura.getPk().getCodigocompania(), tfaccabfactura.getPk().getNumerofactura()));
			facturaDao.update(tfaccabfactura);
			//eliminar forma de pago efectivo anterior
			for(Tfacformapago formpagoelim:tfacformapagos){
				facturaDao.delete(formpagoelim);
			}
			//eliminar forma de pago efectivo credito
			for(Tfaccuentasxcobrar cuentasxcobelim:cuentasxcobrar){
				facturaDao.delete(cuentasxcobelim);
			}
		}
		
		//elimar detalle
		for(Tfacdetfactura eliminarItem:listaEliminar){
			facturaDao.delete(eliminarItem);
		}
	}
	
	private void completarDatosFactura(Factura factura,Tfaccabfactura tfaccabfactura,Tadmcompania tadmcompania,Tfaccliente cliente){
		factura.setId("comprobante");
		factura.setVersion("1.1.0");
		factura.setInfoTributaria(new ec.com.dlc.bunsys.schema.v110.factura.InfoTributaria());
		factura.getInfoTributaria().setAmbiente("1");
		factura.getInfoTributaria().setClaveAcceso(tfaccabfactura.getClaveacceso());//"1803201501050240757000110010010000000011234567817"
		factura.getInfoTributaria().setCodDoc("01");
		factura.getInfoTributaria().setDirMatriz(tadmcompania.getDireccionmatriz());//"NORTE"
		factura.getInfoTributaria().setEstab("001");
		factura.getInfoTributaria().setNombreComercial(tadmcompania.getNombrecomercial());//"PEPITO PEREZ"
		factura.getInfoTributaria().setPtoEmi("001");
		factura.getInfoTributaria().setRazonSocial(tadmcompania.getRazonsocial());//"PEPITO PEREZ"
		factura.getInfoTributaria().setRuc(tadmcompania.getRuc());//"0502407570001"
		factura.getInfoTributaria().setSecuencial("000000001");
		factura.getInfoTributaria().setTipoEmision("1");
		
		factura.setInfoFactura(new Factura.InfoFactura());
		factura.getInfoFactura().setComercioExterior("EXPORTADOR");
		factura.getInfoFactura().setContribuyenteEspecial("5368");
		factura.getInfoFactura().setDireccionComprador(cliente.getTsyspersona().getDireccion());//"NORTE"
		factura.getInfoFactura().setDirEstablecimiento("NORTE");
		factura.getInfoFactura().setFechaEmision(tfaccabfactura.getFechafactura().toString());//"18/03/2015"
		factura.getInfoFactura().setFleteInternacional(BigDecimal.ZERO);
		factura.getInfoFactura().setGastosAduaneros(BigDecimal.ZERO);
		factura.getInfoFactura().setGastosTransporteOtros(BigDecimal.ZERO);
//		factura.getInfoFactura().setGuiaRemision("00000001");
		factura.getInfoFactura().setIdentificacionComprador("12554");
		factura.getInfoFactura().setImporteTotal(new BigDecimal(1200.0));
		factura.getInfoFactura().setIncoTermFactura(tfaccabfactura.getFob());//**"FOB"
		factura.getInfoFactura().setIncoTermTotalSinImpuestos(tfaccabfactura.getFob());//"FOB"
		factura.getInfoFactura().setLugarIncoTerm("QUITO");
		factura.getInfoFactura().setMoneda("USD");
		factura.getInfoFactura().setObligadoContabilidad(ec.com.dlc.bunsys.schema.v110.factura.ObligadoContabilidad.NO);
		factura.getInfoFactura().setPaisAdquisicion("593");
		factura.getInfoFactura().setPaisDestino("593");
		factura.getInfoFactura().setPaisOrigen("593");
		factura.getInfoFactura().setPropina(BigDecimal.ZERO);
		factura.getInfoFactura().setPuertoDestino("GYE");
		factura.getInfoFactura().setPuertoEmbarque("GYE");
		factura.getInfoFactura().setRazonSocialComprador(cliente.getTsyspersona().getNombres()+cliente.getTsyspersona().getApellidos());//"COMPRADOR"
		factura.getInfoFactura().setSeguroInternacional(BigDecimal.ZERO);
		factura.getInfoFactura().setTipoIdentificacionComprador("08");
		factura.getInfoFactura().setTotalBaseImponibleReembolso(BigDecimal.ZERO);
		factura.getInfoFactura().setTotalComprobantesReembolso(BigDecimal.ZERO);
		factura.getInfoFactura().setTotalConImpuestos(new Factura.InfoFactura.TotalConImpuestos());
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().add(new Factura.InfoFactura.TotalConImpuestos.TotalImpuesto());
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setBaseImponible(BigDecimal.ZERO);
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setCodigo("1");
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setCodigoPorcentaje("12");
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setDescuentoAdicional(BigDecimal.ZERO);
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setTarifa(BigDecimal.ZERO);
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setValor(BigDecimal.ZERO);
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setValorDevolucionIva(BigDecimal.ZERO);
		factura.getInfoFactura().setTotalDescuento(BigDecimal.ZERO);
		factura.getInfoFactura().setTotalImpuestoReembolso(BigDecimal.ZERO);
		factura.getInfoFactura().setTotalSinImpuestos(new BigDecimal(1200.0));
		factura.getInfoFactura().setValorRetIva(BigDecimal.ZERO);
		factura.getInfoFactura().setValorRetRenta(BigDecimal.ZERO);
		
		factura.setDetalles(new Factura.Detalles());
		factura.getDetalles().getDetalle().add(new Factura.Detalles.Detalle());
		int i=0;
		for(Tfacdetfactura detallefactura: tfaccabfactura.getTfacdetfacturas()){
			factura.getDetalles().getDetalle().get(i).setCantidad(detallefactura.getCantidad());
			factura.getDetalles().getDetalle().get(i).setCodigoAuxiliar(detallefactura.getTinvproducto().getCodigoauxiliar());//"ART1"
			factura.getDetalles().getDetalle().get(i).setCodigoPrincipal(detallefactura.getTinvproducto().getPk().toString());//"PRINC1"
			factura.getDetalles().getDetalle().get(i).setDescripcion(detallefactura.getTinvproducto().getNombre());//"DESC"
			factura.getDetalles().getDetalle().get(i).setDescuento(detallefactura.getDescuento());
			factura.getDetalles().getDetalle().get(i).setDetallesAdicionales(new DetallesAdicionales());
			factura.getDetalles().getDetalle().get(i).getDetallesAdicionales().getDetAdicional().add(new DetAdicional());
			factura.getDetalles().getDetalle().get(i).getDetallesAdicionales().getDetAdicional().get(0).setNombre("Email");
			factura.getDetalles().getDetalle().get(i).getDetallesAdicionales().getDetAdicional().get(0).setValor(cliente.getTsyspersona().getCorreo());//"dcruz@bupartech.com"
			factura.getDetalles().getDetalle().get(i).setImpuestos(new Factura.Detalles.Detalle.Impuestos());
			factura.getDetalles().getDetalle().get(i).setPrecioTotalSinImpuesto(detallefactura.getTotal());
			factura.getDetalles().getDetalle().get(i).setPrecioUnitario(detallefactura.getPreciounitario());
			TadmcatalogoPK pk = new TadmcatalogoPK();
			pk.setCodigocatalogo(detallefactura.getUnidadventa());
			pk.setCodigocompania(detallefactura.getPk().getCodigocompania());
			pk.setCodigotipocatalogo(detallefactura.getUnidadventacodigo());
			Tadmcatalogo unidad =facturaDao.findById(Tadmcatalogo.class,pk);
			factura.getDetalles().getDetalle().get(i).setUnidadMedida(unidad.getDescripcion());//"UND"
			factura.getDetalles().getDetalle().get(i).setImpuestos(new Factura.Detalles.Detalle.Impuestos());
			factura.getDetalles().getDetalle().get(i).getImpuestos().getImpuesto().add(new ec.com.dlc.bunsys.schema.v110.factura.Impuesto());
			factura.getDetalles().getDetalle().get(i).getImpuestos().getImpuesto().get(0).setBaseImponible(new BigDecimal(1200.0));//**
			factura.getDetalles().getDetalle().get(i).getImpuestos().getImpuesto().get(0).setCodigo("2");
			factura.getDetalles().getDetalle().get(i).getImpuestos().getImpuesto().get(0).setCodigoPorcentaje("0");
			factura.getDetalles().getDetalle().get(i).getImpuestos().getImpuesto().get(0).setTarifa(BigDecimal.ZERO);
			factura.getDetalles().getDetalle().get(i).getImpuestos().getImpuesto().get(0).setValor(BigDecimal.ZERO);
			i++;
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
	
	public List<Tfaccabfactura> cabeceraFacturas(String numerofactura,String estadosri,Date fechainicio, Date fechafin)throws FacturacionException{
		return facturaDao.cabeceraFacturas(numerofactura,estadosri,fechainicio,fechafin);
	}
	
	public List<Tfacdetfactura> detalleFacturas(String numerofactura)throws FacturacionException{
		return facturaDao.detalleFacturas(numerofactura);
	}
	
	public List<Tfacformapago> tfacformapagos(Integer codigocompania,
			String numerofactura) throws FacturacionException{
		return facturaDao.tfacformapagos(codigocompania, numerofactura);
	}
	
	public List<Tfaccuentasxcobrar> cuentasxcobrarxcompxnumfac(Integer codigocompania,
			String numerofactura) throws FacturacionException{
		return facturaDao.cuentasxcobrarxcompxnumfac(codigocompania, numerofactura);
	}
	
	
	public Tfaccabfactura obtenerFactura(Integer codigoCompania, String numeroFactura) throws FacturacionException{
		return facturaDao.obtenerFacturaDetalles(codigoCompania, numeroFactura);
	}
		
	public ResponseServiceDto guardarEnviarNotaCredito(Tfaccabdevolucione notaCredito,
			Collection<Tfacdetdevolucione> detallesNotaCreditoColl, Tadmcompania empresa, String numeroComprobante) throws FacturacionException {
		try {
			ResponseServiceDto responseService = null;
			facturaDao.saveOrUpdate(notaCredito);
			for (Tfacdetdevolucione tfacdetdevolucione : detallesNotaCreditoColl) {
				facturaDao.saveOrUpdate(tfacdetdevolucione);
			}
			//una vez grabado genero los datos de la facturacion electronica
			NotaCredito sriNotaCredito = new NotaCredito();
			sriNotaCredito.setId("comprobante");
			sriNotaCredito.setInfoAdicional(new InfoAdicional());
			sriNotaCredito.setInfoNotaCredito(new InfoNotaCredito());
			sriNotaCredito.setInfoTributaria(new InfoTributaria());
			sriNotaCredito.setVersion("1.1.0");
			
			completaDatosNC(notaCredito, detallesNotaCreditoColl, empresa, numeroComprobante, sriNotaCredito);
			
			String xmlNC = MarshallerFactory.getInstancia().marshal(sriNotaCredito);
			xmlNC = XmlSignFactory.getXmlDataSign().signXML(xmlNC, new File(""), notaCredito.getAditionalProperty("passwordToken").toString());
			RecepcionComprobantesService recepcionComprobantesService = new RecepcionComprobantesService();
			RespuestaSolicitud respuestaSolicitud = recepcionComprobantesService.getRecepcionComprobantesPort().validarComprobante(xmlNC.getBytes());
			if(respuestaSolicitud.getEstado().equals(Constants.STATE_RECEIVED)){
				AutorizacionComprobantesService autorizacionService = new AutorizacionComprobantesService();
				RespuestaComprobante respuestaComprobante = autorizacionService.getAutorizacionComprobantesPort().autorizacionComprobante(sriNotaCredito.getInfoTributaria().getClaveAcceso());
				if(respuestaComprobante != null && !respuestaComprobante.getAutorizaciones().getAutorizacion().isEmpty()){
					for (Autorizacion autorizacion : respuestaComprobante.getAutorizaciones().getAutorizacion()) {
						StringBuilder comprobante = new StringBuilder("<![CDATA[").append(autorizacion.getComprobante()).append("]]>");
						autorizacion.setComprobante(comprobante.toString());
						String finalXml = MarshallerFactory.getInstancia().marshal(autorizacion);
						responseService = new ResponseServiceDto();
						responseService.setEstado(autorizacion.getEstado());
						responseService.setComprobante(finalXml);
						completaDatosRetorno(responseService, autorizacion);
						generaComprobantes(responseService, sriNotaCredito, notaCredito, detallesNotaCreditoColl);
						break;
					}
				} else if(respuestaComprobante == null || respuestaComprobante.getAutorizaciones().getAutorizacion().isEmpty()) {
					responseService = new ResponseServiceDto();
					responseService.setEstado(Constants.STATE_NO_SUBMIT);
					throw new FacturacionException("ERROR al consultar al servicio de autorizacion");
				}
			} else {
				responseService = new ResponseServiceDto();
				responseService.setEstado(respuestaSolicitud.getEstado());
				throw new FacturacionException("El comprobante ha sido devuelto");
			}
			return responseService;
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}
	
	private void completaDatosNC(Tfaccabdevolucione notaCredito, Collection<Tfacdetdevolucione> detallesNotaCreditoColl, Tadmcompania empresa, String numeroComprobante, NotaCredito sriNotaCredito) throws FacturacionException{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = format.format(notaCredito.getFechadevolucion());
		sriNotaCredito.getInfoTributaria().setAmbiente(empresa.getTipoambiente());
		sriNotaCredito.getInfoTributaria().setClaveAcceso(secuenciaService.generaClaveAcceso(notaCredito.getFechadevolucion(), empresa.getPk().getCodigocompania(), ConstantesSRI.COD_NOTA_CREDITO, numeroComprobante));
		sriNotaCredito.getInfoTributaria().setCodDoc(ConstantesSRI.COD_NOTA_CREDITO);
		sriNotaCredito.getInfoTributaria().setDirMatriz(empresa.getDireccionmatriz());
		sriNotaCredito.getInfoTributaria().setEstab(empresa.getCodigoestablecimiento());
		sriNotaCredito.getInfoTributaria().setPtoEmi(empresa.getCodigopuntoemision());
		sriNotaCredito.getInfoTributaria().setNombreComercial(empresa.getNombrecomercial());
		sriNotaCredito.getInfoTributaria().setRazonSocial(empresa.getRazonsocial());
		sriNotaCredito.getInfoTributaria().setRuc(empresa.getRuc());
		sriNotaCredito.getInfoTributaria().setSecuencial(numeroComprobante);
		sriNotaCredito.getInfoTributaria().setTipoEmision(ConstantesSRI.COD_EMISION_NORMAL);
		
		sriNotaCredito.getInfoNotaCredito().setCodDocModificado(ConstantesSRI.COD_FACTURA);
		sriNotaCredito.getInfoNotaCredito().setNumDocModificado(empresa.getCodigoestablecimiento()  + empresa.getCodigopuntoemision() + numeroComprobante);
		sriNotaCredito.getInfoNotaCredito().setContribuyenteEspecial(notaCredito.getCodigocliente());
		sriNotaCredito.getInfoNotaCredito().setDirEstablecimiento(empresa.getDireccionestablecimiento());
		sriNotaCredito.getInfoNotaCredito().setFechaEmision(fecha);
//		sriNotaCredito.getInfoNotaCredito().setFechaEmisionDocSustento(value);
		sriNotaCredito.getInfoNotaCredito().setIdentificacionComprador(notaCredito.getTfaccliente().getTsyspersona().getIdentificacion());
		sriNotaCredito.getInfoNotaCredito().setMoneda("DOLAR");
		sriNotaCredito.getInfoNotaCredito().setMotivo(notaCredito.getObservacion());
		sriNotaCredito.getInfoNotaCredito().setObligadoContabilidad(ObligadoContabilidad.SI);
		sriNotaCredito.getInfoNotaCredito().setRazonSocialComprador(notaCredito.getTfaccliente().getTsyspersona().getNombres());
		sriNotaCredito.getInfoNotaCredito().setTipoIdentificacionComprador(notaCredito.getTfaccliente().getTsyspersona().getTipoid());
		sriNotaCredito.getInfoNotaCredito().setTotalSinImpuestos(notaCredito.getSubtotalneto());
		sriNotaCredito.getInfoNotaCredito().setValorModificacion(BigDecimal.ZERO);
		sriNotaCredito.getInfoNotaCredito().setTotalConImpuestos(new TotalConImpuestos());
		TotalImpuesto totalImpuestoIVA = new TotalImpuesto();
		totalImpuestoIVA.setCodigo("2");
		totalImpuestoIVA.setCodigoPorcentaje("7");
		totalImpuestoIVA.setBaseImponible(sriNotaCredito.getInfoNotaCredito().getTotalSinImpuestos());
		totalImpuestoIVA.setValor(BigDecimal.ZERO);
		sriNotaCredito.getInfoNotaCredito().getTotalConImpuestos().getTotalImpuesto().add(totalImpuestoIVA);
		
		sriNotaCredito.setDetalles(new Detalles());
		for (Tfacdetdevolucione detdevolucione : detallesNotaCreditoColl) {
			Detalle detalle = new Detalle();
			detalle.setCantidad(detdevolucione.getCantidad());
			detalle.setCodigoAdicional(detdevolucione.getTinvproducto().getCodigoauxiliar());
			detalle.setCodigoInterno(detdevolucione.getTinvproducto().getPk().getCodigoproductos());
			detalle.setDescripcion(detdevolucione.getTinvproducto().getNombre());
			detalle.setDescuento(detdevolucione.getDescuento());
			detalle.setPrecioUnitario(detdevolucione.getPreciounitario());
			detalle.setPrecioTotalSinImpuesto(detalle.getPrecioUnitario().multiply(detalle.getCantidad()).subtract(detalle.getDescuento()));
			detalle.setImpuestos(new Impuestos());
			Impuesto impuestoIVA = new Impuesto();
			impuestoIVA.setBaseImponible(detalle.getPrecioTotalSinImpuesto());
			impuestoIVA.setValor(BigDecimal.ZERO);
//			impuestoIVA.setTarifa(new BigDecimal(12));
			impuestoIVA.setCodigo("2");
			impuestoIVA.setCodigoPorcentaje("7");
			sriNotaCredito.getDetalles().getDetalle().add(detalle);
		}
	}
	
	private void generaComprobantes(ResponseServiceDto responseService, NotaCredito notaCredito, Tfaccabdevolucione devolucion, Collection<Tfacdetdevolucione> detallesDevolucion) throws FacturacionException{
		try {
			//Genero xml
		} catch (Throwable e) {
			throw new FacturacionException("ERROR al generar los comprobantes electronicos", e);
		}
	}
	
	private void completaDatosRetorno(ResponseServiceDto responseServiceDto, Autorizacion autorizacion) throws FacturacionException{
		if(autorizacion.getMensajes() != null && !autorizacion.getMensajes().getMensaje().isEmpty()){
			responseServiceDto.setMensajes(new ArrayList<String>());
			for (Mensaje mensaje : autorizacion.getMensajes().getMensaje()) {
				responseServiceDto.getMensajes().add(mensaje.getIdentificador()+"-"+mensaje.getInformacionAdicional());
			}
		}
	}
}
