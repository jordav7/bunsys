package ec.com.dlc.bunsys.service.facturacion;

import static javax.ejb.TransactionAttributeType.MANDATORY;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringEscapeUtils;

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
import ec.com.dlc.bunsys.util.JRArrayDataSource;
import ec.com.dlc.bunsys.util.log.FacturacionLogger;
import ec.com.dlc.bunsys.util.sri.ConstantesSRI;
import ec.com.dlc.bunsys.webservices.sri.autorizacion.Autorizacion;
import ec.com.dlc.bunsys.webservices.sri.autorizacion.AutorizacionComprobantesService;
import ec.com.dlc.bunsys.webservices.sri.autorizacion.Mensaje;
import ec.com.dlc.bunsys.webservices.sri.autorizacion.RespuestaComprobante;
import ec.com.dlc.bunsys.webservices.sri.recepcion.Comprobante;
import ec.com.dlc.bunsys.webservices.sri.recepcion.RecepcionComprobantesService;
import ec.com.dlc.bunsys.webservices.sri.recepcion.RespuestaSolicitud;

/**
 * Bean que contiene toda la l&oacute;gica del m&oacute;dulo de
 * facturaci&oacute;n
 * 
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
	 * Busca las notas de cr&eacute;dito en base a los filtros enviados desde la
	 * pantalla
	 * 
	 * @param codCompania
	 * @param tfaccabdevolucione
	 * @param tsyspersona
	 * @return
	 * @throws FacturacionException
	 */
	public Collection<Tfaccabdevolucione> buscarNotaCredito(
			Integer codCompania, Tfaccabdevolucione tfaccabdevolucione,
			Tsyspersona tsyspersona) throws FacturacionException {
		return facturaDao.buscarNotaCreditoCompleto(codCompania,
				tfaccabdevolucione, tsyspersona);
	}

	// -----------------------------------

	/**
	 * Busaca el tipo de conversion del producto selecionado
	 * 
	 * @param unidadVentaCodigo
	 * @param unidadVenta
	 * @return
	 */
	public Tadmconversionunidad conversionArticulo(Integer unidadVentaCodigo,
			String unidadVenta) {
		return facturaDao.conversionArticulo(unidadVentaCodigo, unidadVenta);
	}

	/**
	 * Actualiza o graba una proforma
	 * 
	 * @param tfaccabproform
	 * @param accion
	 * @throws FacturacionException
	 */
	public void guardarProforma(Tfaccabproforma tfaccabproform, String accion,
			Collection<Tfacdetproforma> listaEliminar)
			throws FacturacionException {
		try {
			Collection<Tfacdetproforma> tfacdetproformas = tfaccabproform
					.getTfacdetproformas();
			if (accion.equals("G")) {
				facturaDao.create(tfaccabproform);
			}
			for (Tfacdetproforma tfacdetproforma2 : tfacdetproformas) {
				Tfacdetproforma tfacdetproforma = tfacdetproforma2;
				if (tfacdetproforma.getPk().getCodigodetalleprof() != null) {
					// elimina
					facturaDao.update(tfacdetproforma);
				} else {
					tfacdetproforma.setNumeroproforma(tfaccabproform.getPk()
							.getNumeroproforma());
					facturaDao.create(tfacdetproforma);
				}
			}
			if (!accion.equals("G")) {
				tfaccabproform.setTfaccliente(null);
				facturaDao.update(tfaccabproform);
			}
			// elimar detalle
			for (Tfacdetproforma eliminarItem : listaEliminar) {
				facturaDao.delete(eliminarItem);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new FacturacionException(e);
		}
	}

	public ResponseServiceDto grabarFactura(Tfaccabfactura tfaccabfactura,
			String accion, Collection<Tfacdetfactura> listaEliminar,
			Tadmcompania tadmcompania, Tfaccliente cliente)
			throws FacturacionException {
		ResponseServiceDto responseService = new ResponseServiceDto();
		try {
			guardaFactuar(tfaccabfactura, accion, listaEliminar);
			// solo graba en estado sin firma
			if (tfaccabfactura.getEstadosri().equals("SF")) {
				// responseService = new ResponseServiceDto();
				responseService.setMensajes(new ArrayList<String>());
				responseService.getMensajes().add("SE GUARDO LA FACTURA");
				return responseService;
			}

			// guarad firma y envia en estado firma y envia
			Factura factura = new Factura();
			completarDatosFactura(factura, tfaccabfactura, tadmcompania,
					cliente);
			// convierte
			String xml = MarshallerFactory.getInstancia().marshal(factura);
			// firma password y certificado
			String rutafirma = ComprobantesUtil.getInstancia().obtenerRutaCertificado(tfaccabfactura.getPk().getCodigocompania());
			xml = XmlSignFactory.getXmlDataSign().signXML(xml,new File(rutafirma),tfaccabfactura.getAditionalProperty("passwordToken").toString());
			// guarda en estado de contingencia, genera el archivo y lo firma
			if (tfaccabfactura.getEstadosri().equals("CO")) {
				responseService.setEstado(tfaccabfactura.getEstadosri());
				responseService.setComprobante(xml);
				generaComprobantesPDF(responseService, factura, tfaccabfactura,
						cliente, tadmcompania);
			}
			if (tfaccabfactura.getEstadosri().equals("FE")) {
				RecepcionComprobantesService recepcionComprobantesService = new RecepcionComprobantesService();
				// web service valida el comprobante
				RespuestaSolicitud respuestaSolicitud = recepcionComprobantesService
						.getRecepcionComprobantesPort().validarComprobante(
								xml.getBytes());
				// estado recivido
				System.out.println(respuestaSolicitud.getEstado());
				if (respuestaSolicitud.getEstado().equals("RECIBIDA")) {// Constants.STATE_RECEIVED
					AutorizacionComprobantesService autorizacionService = new AutorizacionComprobantesService();
					//respuesta de autorizacion
					RespuestaComprobante respuestaComprobante = autorizacionService.getAutorizacionComprobantesPort()
															   .autorizacionComprobante(factura.getInfoTributaria().getClaveAcceso());
					if (respuestaComprobante != null && !respuestaComprobante.getAutorizaciones().getAutorizacion().isEmpty()) {
						for (Autorizacion autorizacion : respuestaComprobante.getAutorizaciones().getAutorizacion()) {
							StringBuilder comprobante = new StringBuilder("<![CDATA[").append(autorizacion.getComprobante()).append("]]>");
							autorizacion.setComprobante(comprobante.toString());
							String finalXml = MarshallerFactory.getInstancia().marshal(autorizacion);
							responseService.setEstado(autorizacion.getEstado());
							responseService.setComprobante(finalXml);
							completaDatosRetorno(responseService, autorizacion);
							generaComprobantesPDF(responseService, factura,	tfaccabfactura, cliente, tadmcompania);
							break;
						}
					} else if (respuestaComprobante == null	|| respuestaComprobante.getAutorizaciones().getAutorizacion().isEmpty()) {
						responseService = new ResponseServiceDto();
						responseService.setEstado("NO ENVIADO SRI");
						responseService.setMensajes(new ArrayList<String>());
						String mensajeerror="";
						for (Comprobante mensaje : respuestaSolicitud.getComprobantes().getComprobante()) {
							for (ec.com.dlc.bunsys.webservices.sri.recepcion.Mensaje men : mensaje.getMensajes().getMensaje()) {
								System.out.println("...."+men.getMensaje());
								mensajeerror=men.getMensaje();
								responseService.getMensajes().add(men.getMensaje());
							}
						}
						throw new FacturacionException("ERROR al consultar al servicio de autorizacion : "+mensajeerror);
					}
				} else {
					responseService = new ResponseServiceDto();
					responseService.setEstado(respuestaSolicitud.getEstado());
					responseService.setMensajes(new ArrayList<String>());
					String mensajeerror="";
					for (Comprobante mensaje : respuestaSolicitud.getComprobantes().getComprobante()) {
						for (ec.com.dlc.bunsys.webservices.sri.recepcion.Mensaje men : mensaje.getMensajes().getMensaje()) {
							System.out.println(men.getMensaje());
							responseService.getMensajes().add(men.getMensaje());
							mensajeerror=men.getMensaje();
						}
					}
					throw new FacturacionException("El comprobante ha sido devuelto : "+mensajeerror);
				}
			}
			return responseService;
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}

	private void guardaFactuar(Tfaccabfactura tfaccabfactura, String accion,
			Collection<Tfacdetfactura> listaEliminar) {
		List<Tfacformapago> tfacformapagos = null;
		List<Tfaccuentasxcobrar> cuentasxcobrar = null;
		if (accion.equals("G")) {
			Integer sec = secuenciaService.obtenerSecuenciaComp(tfaccabfactura.getPk().getCodigocompania(), ConstantesSRI.COD_FACTURA);
			tfaccabfactura.getPk().setNumerofactura(ComprobantesUtil.getInstancia().getsecuencia(sec.toString(), 9));
			// clave de acceso
			tfaccabfactura.setClaveacceso(secuenciaService.generaClaveAcceso(tfaccabfactura.getFechafactura(), tfaccabfactura.getPk()
							.getCodigocompania(), tfaccabfactura.getPk().getNumerofactura()));
			facturaDao.create(tfaccabfactura);
		} else {
			tfacformapagos = tfacformapagos(tfaccabfactura.getPk().getCodigocompania(), tfaccabfactura.getPk().getNumerofactura());
			cuentasxcobrar = cuentasxcobrarxcompxnumfac(tfaccabfactura.getPk().getCodigocompania(), tfaccabfactura.getPk().getNumerofactura());
		}
		for (Tfacdetfactura tfacdetfactura : tfaccabfactura.getTfacdetfacturas()) {
			if (tfacdetfactura.getPk().getCodigodetfactura() != null) {
				// tfacdetfactura.setTinvproducto(null);
				facturaDao.update(tfacdetfactura);
			} else {
				tfacdetfactura.setNumerofactura(tfaccabfactura.getPk().getNumerofactura());
				// tfacdetfactura.setTinvproducto(null);
				facturaDao.create(tfacdetfactura);
			}
		}
		// efectivo
		for (Tfacformapago tfacformapago : tfaccabfactura.getTfacformapagos()) {
			tfacformapago.setNumerofactura(tfaccabfactura.getPk().getNumerofactura());
			facturaDao.create(tfacformapago);
		}
		// credito
		for (Tfaccuentasxcobrar tfaccuentasxcobrar : tfaccabfactura.getTfaccuentasxcobrars()) {
			tfaccuentasxcobrar.setNumerofactura(tfaccabfactura.getPk().getNumerofactura());
			facturaDao.create(tfaccuentasxcobrar);
			tfaccuentasxcobrar.setReferencia(tfaccuentasxcobrar.getPk().getCodigocuenxcobr());
			facturaDao.update(tfaccuentasxcobrar);
		}
		if (!accion.equals("G")) {
			// clave de acceso
			tfaccabfactura.setClaveacceso(secuenciaService.generaClaveAcceso(tfaccabfactura.getFechafactura(), tfaccabfactura.getPk().getCodigocompania(), tfaccabfactura.getPk().getNumerofactura()));
			facturaDao.update(tfaccabfactura);
			// eliminar forma de pago efectivo anterior
			for (Tfacformapago formpagoelim : tfacformapagos) {
				facturaDao.delete(formpagoelim);
			}
			// eliminar forma de pago efectivo credito
			for (Tfaccuentasxcobrar cuentasxcobelim : cuentasxcobrar) {
				facturaDao.delete(cuentasxcobelim);
			}
		}
		// elimar detalle
		for (Tfacdetfactura eliminarItem : listaEliminar) {
			facturaDao.delete(eliminarItem);
		}
	}

	private void completarDatosFactura(Factura factura,
			Tfaccabfactura tfaccabfactura, Tadmcompania tadmcompania,
			Tfaccliente cliente) {
		factura.setId("comprobante");
		factura.setVersion("1.1.0");
		factura.setInfoTributaria(new ec.com.dlc.bunsys.schema.v110.factura.InfoTributaria());
		factura.getInfoTributaria().setAmbiente(tadmcompania.getTipoambiente());
		factura.getInfoTributaria().setClaveAcceso(tfaccabfactura.getClaveacceso());// "1803201501050240757000110010010000000011234567817"
		factura.getInfoTributaria().setCodDoc(ConstantesSRI.COD_FACTURA);// segun la tabla 4 01 es para factura
		factura.getInfoTributaria().setDirMatriz(StringEscapeUtils.escapeJava(tadmcompania.getDireccionmatriz()));// "NORTE"
		factura.getInfoTributaria().setEstab(tadmcompania.getCodigoestablecimiento());// "001"
		factura.getInfoTributaria().setNombreComercial(StringEscapeUtils.escapeJava(tadmcompania.getNombrecomercial()));// "PEPITO PEREZ"
		factura.getInfoTributaria().setPtoEmi(tadmcompania.getCodigopuntoemision());// "001"
		factura.getInfoTributaria().setRazonSocial(	StringEscapeUtils.escapeJava(tadmcompania.getRazonsocial()));// "PEPITO PEREZ"
		factura.getInfoTributaria().setRuc(tadmcompania.getRuc());// "0502407570001"
		factura.getInfoTributaria().setSecuencial(tfaccabfactura.getPk().getNumerofactura());// "000000001"
		factura.getInfoTributaria().setTipoEmision(	ConstantesSRI.COD_EMISION_NORMAL);// tabla 2 (Emision Normal 1 -Emision por Indisponibilidad del Sistema 2)

		factura.setInfoFactura(new Factura.InfoFactura());
		factura.getInfoFactura().setComercioExterior("EXPORTADOR");
		factura.getInfoFactura().setContribuyenteEspecial(cliente.getPk().getCodigocliente());// /"5368"
		factura.getInfoFactura().setDireccionComprador(StringEscapeUtils.escapeJava(cliente.getTsyspersona().getDireccion()));// "NORTE"
		factura.getInfoFactura().setDirEstablecimiento(tadmcompania.getDireccionestablecimiento());// "NORTE"
		SimpleDateFormat fromat = new SimpleDateFormat("dd/MM/yyyy");
		factura.getInfoFactura().setFechaEmision(fromat.format(tfaccabfactura.getFechafactura()));// "18/03/2015"

		factura.getInfoFactura().setFleteInternacional(BigDecimal.ZERO);
		factura.getInfoFactura().setGastosAduaneros(BigDecimal.ZERO);
		factura.getInfoFactura().setGastosTransporteOtros(BigDecimal.ZERO);
		// factura.getInfoFactura().setGuiaRemision("00000001");
		factura.getInfoFactura().setIdentificacionComprador(
				cliente.getTsyspersona().getIdentificacion());// "12554"
		factura.getInfoFactura().setImporteTotal(tfaccabfactura.getTotal());// (new BigDecimal(1200.0)
		factura.getInfoFactura().setIncoTermFactura(tfaccabfactura.getFob());// **"FOB"
		factura.getInfoFactura().setIncoTermTotalSinImpuestos(tfaccabfactura.getFob());// "FOB"

		factura.getInfoFactura().setLugarIncoTerm("QUITO");
		factura.getInfoFactura().setMoneda("USD");
		factura.getInfoFactura().setObligadoContabilidad(ec.com.dlc.bunsys.schema.v110.factura.ObligadoContabilidad.NO);

		factura.getInfoFactura().setPaisAdquisicion(tfaccabfactura.getArea());// "593"

		factura.getInfoFactura().setPaisDestino("593");
		factura.getInfoFactura().setPaisOrigen(tfaccabfactura.getArea());// 593
		factura.getInfoFactura().setPropina(BigDecimal.ZERO);
		factura.getInfoFactura().setPuertoDestino("GYE");
		factura.getInfoFactura().setPuertoEmbarque("GYE");

		factura.getInfoFactura().setRazonSocialComprador(StringEscapeUtils.escapeJava(cliente.getTsyspersona().getApellidos()));// "COMPRADOR"
		factura.getInfoFactura().setSeguroInternacional(BigDecimal.ZERO);

		TadmcatalogoPK pk1 = new TadmcatalogoPK();
		pk1.setCodigocatalogo(cliente.getTsyspersona().getTipoid());
		pk1.setCodigocompania(tfaccabfactura.getPk().getCodigocompania());
		pk1.setCodigotipocatalogo(cliente.getTsyspersona().getTipoidcodigo());
		Tadmcatalogo tipoIndentificacion = facturaDao.findById(Tadmcatalogo.class, pk1);

		factura.getInfoFactura().setTipoIdentificacionComprador(tipoIndentificacion.getValor());// "08"(IDENTIFICACION DELEXTERIOR* 08 - RUC 04 - CEDULA 05 -PASAPORTE 06 -VENTA A CONSUMIDOR FINAL*07)

		factura.getInfoFactura().setTotalBaseImponibleReembolso(BigDecimal.ZERO);
		factura.getInfoFactura().setTotalComprobantesReembolso(BigDecimal.ZERO);
		factura.getInfoFactura().setTotalConImpuestos(new Factura.InfoFactura.TotalConImpuestos());
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().add(new Factura.InfoFactura.TotalConImpuestos.TotalImpuesto());
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setBaseImponible(round(tfaccabfactura.getSubtotalneto()));// BigDecimal.ZERO
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setCodigo("1");
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setCodigoPorcentaje("12");// (12 Denominación del comprobante de venta Numérico 2 Opcional)
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setDescuentoAdicional(BigDecimal.ZERO);
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setTarifa(BigDecimal.ZERO);
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setValor(BigDecimal.ZERO);
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setValorDevolucionIva(BigDecimal.ZERO);
		if (tfaccabfactura.getTotaldescuento() != null	&& tfaccabfactura.getTotaldescuento().compareTo(new BigDecimal(0)) > 0) {
			factura.getInfoFactura().setTotalDescuento(round(tfaccabfactura.getTotaldescuento()));// BigDecimal.ZERO
		} else {
			factura.getInfoFactura().setTotalDescuento(BigDecimal.ZERO);//
		}

		factura.getInfoFactura().setTotalImpuestoReembolso(BigDecimal.ZERO);

		factura.getInfoFactura().setTotalSinImpuestos(tfaccabfactura.getSubtotalneto());
		if (tfaccabfactura.getIva() != null	&& tfaccabfactura.getIva().compareTo(new BigDecimal(0)) > 0) {
			factura.getInfoFactura().setValorRetIva(round(tfaccabfactura.getIva()));
		} else {
			factura.getInfoFactura().setValorRetIva(BigDecimal.ZERO);
		}

		factura.getInfoFactura().setValorRetRenta(BigDecimal.ZERO);

		factura.setDetalles(new Factura.Detalles());
		for (Tfacdetfactura detallefactura : tfaccabfactura.getTfacdetfacturas()) {
			Factura.Detalles.Detalle detalle = new Factura.Detalles.Detalle();
			// factura.getDetalles().getDetalle().add(new
			// Factura.Detalles.Detalle());
			detalle.setCantidad(round(detallefactura.getCantidad()));
			detalle.setCodigoAuxiliar(detallefactura.getTinvproducto().getCodigoauxiliar());// "ART1"
			detalle.setCodigoPrincipal("PRINC1");// //detallefactura.getTinvproducto().getPk().toString()
			detalle.setDescripcion(StringEscapeUtils.escapeJava(detallefactura.getTinvproducto().getNombre()));// "DESC"
			if (detallefactura.getDescuento() != null && detallefactura.getDescuento().compareTo(new BigDecimal(0)) > 0) {
				detalle.setDescuento(round(detallefactura.getDescuento()));
			} else {
				detalle.setDescuento(new BigDecimal(0));
			}

			detalle.setDetallesAdicionales(new DetallesAdicionales());
			detalle.getDetallesAdicionales().getDetAdicional().add(new DetAdicional());
			detalle.getDetallesAdicionales().getDetAdicional().get(0).setNombre("Email");
			if(cliente!=null && cliente.getTsyspersona()!=null && cliente.getTsyspersona().getCorreo()!=null){
				detalle.getDetallesAdicionales().getDetAdicional().get(0).setValor(StringEscapeUtils.escapeJava(cliente.getTsyspersona().getCorreo()));// "dcruz@bupartech.com"
			}else{
				detalle.getDetallesAdicionales().getDetAdicional().get(0).setValor("dcruz@bupartech.com");// "dcruz@bupartech.com"
			}
			
			detalle.setImpuestos(new Factura.Detalles.Detalle.Impuestos());
			detalle.setPrecioTotalSinImpuesto(round(detallefactura.getTotal()));
			detalle.setPrecioUnitario(round(detallefactura.getPreciounitario()));
			TadmcatalogoPK pk = new TadmcatalogoPK();
			pk.setCodigocatalogo(detallefactura.getUnidadventa());
			pk.setCodigocompania(detallefactura.getPk().getCodigocompania());
			pk.setCodigotipocatalogo(detallefactura.getUnidadventacodigo());
			Tadmcatalogo unidad = facturaDao.findById(Tadmcatalogo.class, pk);
			detalle.setUnidadMedida(StringEscapeUtils.escapeJava(unidad	.getDescripcion()));// "UND"
			detalle.setImpuestos(new Factura.Detalles.Detalle.Impuestos());
			detalle.getImpuestos().getImpuesto().add(new ec.com.dlc.bunsys.schema.v110.factura.Impuesto());

			detalle.getImpuestos().getImpuesto().get(0).setBaseImponible(detallefactura.getTotal());// ** new BigDecimal(1200.0)

			detalle.getImpuestos().getImpuesto().get(0).setCodigo("2");
			detalle.getImpuestos().getImpuesto().get(0).setCodigoPorcentaje("0");

			detalle.getImpuestos().getImpuesto().get(0).setTarifa(BigDecimal.ZERO);
			detalle.getImpuestos().getImpuesto().get(0).setValor(BigDecimal.ZERO);

			factura.getDetalles().getDetalle().add(detalle);
		}
	}

	/**
	 * lista las cabeceras de las proformas
	 * 
	 * @param numeroproforma
	 * @return
	 */
	public List<Tfaccabproforma> cabeceraProformas(String numeroproforma) {
		return facturaDao.cabeceraProformas(numeroproforma);
	}

	public List<Tfacdetproforma> detalleProformas(String numeroproforma) {
		return facturaDao.detalleProformas(numeroproforma);
	}

	public Collection<Tfaccuentasxcobrar> obtenerFacturasCredito(
			Integer codCompania, String numFac, String codId, String nombres,
			String apellidos, Date fecEmi, Date fecVen, Date fecPag,
			String numDoc) {
		return facturaDao.obtenerFacturasCredito(codCompania, numFac, codId,
				nombres, apellidos, fecEmi, fecVen, fecPag, numDoc);
	}

	public Collection<Tfaccuentasxcobrar> obtenerCuentasPorCobrar(
			Integer codCompania, String codigoCliente)
			throws FacturacionException {
		return facturaDao.obtenerCuentasPorCobrar(codCompania, codigoCliente);
	}

	public void grabaAbono(Tfaccuentasxcobrar abono) {
		facturaDao.create(abono);
	}

	public Tfaccuentasxcobrar buscarCxc(TfaccuentasxcobrarPK pkCxc) {
		return facturaDao.findById(Tfaccuentasxcobrar.class, pkCxc);
	}

	public void actualizarCxc(Tfaccuentasxcobrar cxc) {
		facturaDao.update(cxc);
	}

	public List<Tfaccabfactura> cabeceraFacturas(String numerofactura,
			String estadosri, Date fechainicio, Date fechafin)
			throws FacturacionException {
		return facturaDao.cabeceraFacturas(numerofactura, estadosri,
				fechainicio, fechafin);
	}

	public List<Tfacdetfactura> detalleFacturas(String numerofactura)
			throws FacturacionException {
		return facturaDao.detalleFacturas(numerofactura);
	}

	public List<Tfacformapago> tfacformapagos(Integer codigocompania,
			String numerofactura) throws FacturacionException {
		return facturaDao.tfacformapagos(codigocompania, numerofactura);
	}

	public List<Tfaccuentasxcobrar> cuentasxcobrarxcompxnumfac(
			Integer codigocompania, String numerofactura)
			throws FacturacionException {
		return facturaDao.cuentasxcobrarxcompxnumfac(codigocompania,
				numerofactura);
	}

	public Tfaccabfactura obtenerFactura(Integer codigoCompania,
			String numeroFactura) throws FacturacionException {
		return facturaDao.obtenerFacturaDetalles(codigoCompania, numeroFactura);
	}

	public ResponseServiceDto guardarEnviarNotaCredito(
			Tfaccabdevolucione notaCredito,
			Collection<Tfacdetdevolucione> detallesNotaCreditoColl,
			Tadmcompania empresa, String numeroComprobante)
			throws FacturacionException {
		try {
			ResponseServiceDto responseService = null;
			notaCredito.getPk().setCodigocompania(
					empresa.getPk().getCodigocompania());
			facturaDao.saveOrUpdate(notaCredito);
			for (Tfacdetdevolucione tfacdetdevolucione : detallesNotaCreditoColl) {
				tfacdetdevolucione.getPk().setCodigocompania(
						empresa.getPk().getCodigocompania());
				tfacdetdevolucione.getPk().setNumerodevoluciones(
						notaCredito.getPk().getNumerodevoluciones());
				facturaDao.saveOrUpdate(tfacdetdevolucione);
			}
			// una vez grabado genero los datos de la facturacion electronica
			NotaCredito sriNotaCredito = new NotaCredito();
			sriNotaCredito.setId("comprobante");
			sriNotaCredito.setInfoAdicional(new InfoAdicional());
			sriNotaCredito.setInfoNotaCredito(new InfoNotaCredito());
			sriNotaCredito.setInfoTributaria(new InfoTributaria());
			sriNotaCredito.setVersion("1.1.0");

			completaDatosNC(notaCredito, detallesNotaCreditoColl, empresa,
					numeroComprobante, sriNotaCredito);

			String xmlNC = MarshallerFactory.getInstancia().marshal(
					sriNotaCredito);
			xmlNC = XmlSignFactory.getXmlDataSign().signXML(
					xmlNC,
					new File(ComprobantesUtil.getInstancia()
							.obtenerRutaCertificado(
									empresa.getPk().getCodigocompania())),
					notaCredito.getAditionalProperty("passwordToken")
							.toString());
			RecepcionComprobantesService recepcionComprobantesService = new RecepcionComprobantesService();
			RespuestaSolicitud respuestaSolicitud = recepcionComprobantesService
					.getRecepcionComprobantesPort().validarComprobante(
							xmlNC.getBytes());
			if (respuestaSolicitud.getEstado().equals(Constants.STATE_RECEIVED)) {
				AutorizacionComprobantesService autorizacionService = new AutorizacionComprobantesService();
				RespuestaComprobante respuestaComprobante = autorizacionService
						.getAutorizacionComprobantesPort()
						.autorizacionComprobante(
								sriNotaCredito.getInfoTributaria()
										.getClaveAcceso());
				if (respuestaComprobante != null
						&& !respuestaComprobante.getAutorizaciones()
								.getAutorizacion().isEmpty()) {
					for (Autorizacion autorizacion : respuestaComprobante
							.getAutorizaciones().getAutorizacion()) {
						StringBuilder comprobante = new StringBuilder(
								"<![CDATA[").append(xmlNC).append("]]>");
						autorizacion.setComprobante(comprobante.toString());
						String finalXml = MarshallerFactory.getInstancia()
								.marshal(autorizacion);
						responseService = new ResponseServiceDto();
						responseService.setEstado(autorizacion.getEstado());
						responseService.setComprobante(finalXml);
						completaDatosRetorno(responseService, autorizacion);
						generaComprobantes(responseService, sriNotaCredito,
								notaCredito, detallesNotaCreditoColl, empresa);
						break;
					}
				} else if (respuestaComprobante == null
						|| respuestaComprobante.getAutorizaciones()
								.getAutorizacion().isEmpty()) {
					responseService = new ResponseServiceDto();
					responseService.setEstado(Constants.STATE_NO_SUBMIT);
					throw new FacturacionException(
							"ERROR al consultar al servicio de autorizacion");
				}
			} else {
				responseService = new ResponseServiceDto();
				responseService.setEstado(respuestaSolicitud.getEstado());
				throw new FacturacionException(
						"El comprobante ha sido devuelto");
			}
			return responseService;
		} catch (Throwable e) {
			throw new FacturacionException(e);
		}
	}

	private void completaDatosNC(Tfaccabdevolucione notaCredito,
			Collection<Tfacdetdevolucione> detallesNotaCreditoColl,
			Tadmcompania empresa, String numeroComprobante,
			NotaCredito sriNotaCredito) throws FacturacionException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = format.format(notaCredito.getFechadevolucion());
		sriNotaCredito.getInfoTributaria().setAmbiente(
				empresa.getTipoambiente());
		sriNotaCredito.getInfoTributaria().setClaveAcceso(
				secuenciaService.generaClaveAcceso(notaCredito
						.getFechadevolucion(), empresa.getPk()
						.getCodigocompania(), ConstantesSRI.COD_NOTA_CREDITO,
						notaCredito.getPk().getNumerodevoluciones()));
		sriNotaCredito.getInfoTributaria().setCodDoc(
				ConstantesSRI.COD_NOTA_CREDITO);
		sriNotaCredito.getInfoTributaria().setDirMatriz(
				empresa.getDireccionmatriz());
		sriNotaCredito.getInfoTributaria().setEstab(
				empresa.getCodigoestablecimiento());
		sriNotaCredito.getInfoTributaria().setPtoEmi(
				empresa.getCodigopuntoemision());
		sriNotaCredito.getInfoTributaria().setNombreComercial(
				empresa.getNombrecomercial());
		sriNotaCredito.getInfoTributaria().setRazonSocial(
				empresa.getRazonsocial());
		sriNotaCredito.getInfoTributaria().setRuc(empresa.getRuc());
		sriNotaCredito.getInfoTributaria().setSecuencial(
				notaCredito.getPk().getNumerodevoluciones());
		sriNotaCredito.getInfoTributaria().setTipoEmision(
				ConstantesSRI.COD_EMISION_NORMAL);

		sriNotaCredito.getInfoNotaCredito().setCodDocModificado(
				ConstantesSRI.COD_FACTURA);
		sriNotaCredito.getInfoNotaCredito().setNumDocModificado(
				empresa.getCodigoestablecimiento()
						+ empresa.getCodigopuntoemision() + numeroComprobante);
		sriNotaCredito.getInfoNotaCredito().setContribuyenteEspecial(
				notaCredito.getCodigocliente());
		sriNotaCredito.getInfoNotaCredito().setDirEstablecimiento(
				empresa.getDireccionestablecimiento());
		sriNotaCredito.getInfoNotaCredito().setFechaEmision(fecha);
		// sriNotaCredito.getInfoNotaCredito().setFechaEmisionDocSustento(value);
		sriNotaCredito.getInfoNotaCredito().setIdentificacionComprador(
				notaCredito.getTfaccliente().getTsyspersona()
						.getIdentificacion());
		sriNotaCredito.getInfoNotaCredito().setMoneda("DOLAR");
		sriNotaCredito.getInfoNotaCredito().setMotivo(
				notaCredito.getObservacion());
		sriNotaCredito.getInfoNotaCredito().setObligadoContabilidad(
				ObligadoContabilidad.SI);
		sriNotaCredito.getInfoNotaCredito().setRazonSocialComprador(
				notaCredito.getTfaccliente().getTsyspersona().getNombres());
		sriNotaCredito.getInfoNotaCredito().setTipoIdentificacionComprador(
				notaCredito.getTfaccliente().getTsyspersona().getTipoid());
		sriNotaCredito.getInfoNotaCredito().setTotalSinImpuestos(
				round(notaCredito.getSubtotalneto()));
		sriNotaCredito.getInfoNotaCredito().setValorModificacion(
				BigDecimal.ZERO);
		sriNotaCredito.getInfoNotaCredito().setTotalConImpuestos(
				new TotalConImpuestos());
		TotalImpuesto totalImpuestoIVA = new TotalImpuesto();
		totalImpuestoIVA.setCodigo("2");
		totalImpuestoIVA.setCodigoPorcentaje("7");
		totalImpuestoIVA.setBaseImponible(round(sriNotaCredito
				.getInfoNotaCredito().getTotalSinImpuestos()));
		totalImpuestoIVA.setValor(BigDecimal.ZERO);
		sriNotaCredito.getInfoNotaCredito().getTotalConImpuestos()
				.getTotalImpuesto().add(totalImpuestoIVA);

		sriNotaCredito.setDetalles(new Detalles());
		for (Tfacdetdevolucione detdevolucione : detallesNotaCreditoColl) {
			Detalle detalle = new Detalle();
			detalle.setCantidad(detdevolucione.getCantidad());
			detalle.setCodigoAdicional(detdevolucione.getTinvproducto()
					.getCodigoauxiliar());
			detalle.setCodigoInterno(detdevolucione.getTinvproducto().getPk()
					.getCodigoproductos());
			detalle.setDescripcion(detdevolucione.getTinvproducto().getNombre());
			detalle.setDescuento(round(detdevolucione.getDescuento()));
			detalle.setPrecioUnitario(round(detdevolucione.getPreciounitario()));
			detalle.setPrecioTotalSinImpuesto(round(detalle.getPrecioUnitario()
					.multiply(detalle.getCantidad())
					.subtract(detalle.getDescuento())));
			detalle.setImpuestos(new Impuestos());
			Impuesto impuestoIVA = new Impuesto();
			impuestoIVA.setBaseImponible(round(detalle
					.getPrecioTotalSinImpuesto()));
			impuestoIVA.setValor(BigDecimal.ZERO);
			// impuestoIVA.setTarifa(new BigDecimal(12));
			impuestoIVA.setCodigo("2");
			impuestoIVA.setCodigoPorcentaje("7");
			sriNotaCredito.getDetalles().getDetalle().add(detalle);
		}
	}

	private void generaComprobantes(ResponseServiceDto responseService,
			NotaCredito notaCredito, Tfaccabdevolucione devolucion,
			Collection<Tfacdetdevolucione> detallesDevolucion,
			Tadmcompania empresa) throws FacturacionException {
		try {
			// Genero xml y pdf
			String rutaComprobante = "";
			rutaComprobante = new StringBuilder(File.separator).append(
					responseService.getEstado()).toString();
			Path dirComprobante = Paths.get(ComprobantesUtil.getInstancia()
					.obtenerDirectorioNotaCredito(
							empresa.getPk().getCodigocompania())
					+ rutaComprobante);
			generaNotaCreditoXML(responseService, notaCredito, devolucion,
					empresa, dirComprobante);
			generaNotaCreditoPDF(responseService, notaCredito, devolucion,
					detallesDevolucion, dirComprobante, empresa);
		} catch (Throwable e) {
			throw new FacturacionException(
					"ERROR al generar los comprobantes electronicos", e);
		}
	}

	private void generaNotaCreditoXML(ResponseServiceDto responseService,
			NotaCredito notaCredito, Tfaccabdevolucione devolucion,
			Tadmcompania empresa, Path dirComprobante)
			throws FacturacionException {
		try {
			Files.createDirectories(dirComprobante);
			Files.write(
					Paths.get(dirComprobante.toAbsolutePath() + File.separator
							+ notaCredito.getInfoTributaria().getClaveAcceso()
							+ ".xml"), responseService.getComprobante()
							.getBytes(), StandardOpenOption.WRITE);
		} catch (Throwable e) {
			FacturacionLogger.log.error(e.getMessage(), e);
		}
	}

	private void generaNotaCreditoPDF(ResponseServiceDto responseService,
			NotaCredito notaCredito, Tfaccabdevolucione devolucion,
			Collection<Tfacdetdevolucione> detallesDevolucio,
			Path dirComprobante, Tadmcompania empresa) {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			SimpleDateFormat formatDates = new SimpleDateFormat("dd/MM/yyyy");
			URL in = this.getClass().getResource(
					"/ec/com/dlc/bunsys/commons/reports/NotaCreditoB.jasper");
			jasperReport = (JasperReport) JRLoader.loadObject(in);

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("dirProvee", empresa.getDireccionmatriz());
			param.put("rucProvee", empresa.getRuc());
			param.put("numDocumento", notaCredito.getInfoTributaria()
					.getEstab()
					+ "-"
					+ notaCredito.getInfoTributaria().getPtoEmi()
					+ "-"
					+ notaCredito.getInfoTributaria().getSecuencial());
			param.put("numAutoriza", notaCredito.getInfoTributaria()
					.getClaveAcceso());
			param.put("fechaAutoriza", notaCredito.getInfoNotaCredito()
					.getFechaEmision());
			param.put("ambiente", empresa.getTipoambiente());
			param.put("emision", formatDates.format(new Date()));
			param.put("claveAcceso", notaCredito.getInfoTributaria()
					.getClaveAcceso());
			param.put("razonProvee", notaCredito.getInfoTributaria()
					.getRazonSocial());
			param.put("razonCliente", notaCredito.getInfoNotaCredito()
					.getRazonSocialComprador());
			param.put("rucCliente", notaCredito.getInfoNotaCredito()
					.getIdentificacionComprador());
			param.put("fechaEmision", formatDates.format(new Date()));
			param.put("fechaEmiComp", formatDates.format(new Date()));
			param.put("motivo", notaCredito.getInfoNotaCredito().getMotivo());
			param.put("numComprobante", notaCredito.getInfoTributaria()
					.getEstab()
					+ "-"
					+ notaCredito.getInfoTributaria().getPtoEmi()
					+ "-"
					+ devolucion.getNumerofactura());
			param.put("imgLogo", "");
			param.put("totalGravCero", notaCredito.getInfoNotaCredito()
					.getTotalSinImpuestos());
			param.put("totalGravDoce", BigDecimal.ZERO);
			param.put("importeIva", BigDecimal.ZERO);
			param.put("total", notaCredito.getInfoNotaCredito()
					.getTotalSinImpuestos());
			param.put("numContribuyente", "");
			param.put("master", devolucion.getMasterawb());
			param.put("house", devolucion.getHouseawb());
			param.put("airline", devolucion.getAirline());
			param.put("dae", devolucion.getReferendo());
			param.put("marcacion", devolucion.getConsignee());
			param.put("consignatario", devolucion.getFixedprice());

			// se procesa el archivo jasper
			jasperPrint = JasperFillManager.fillReport(jasperReport, param,
					createDatasourceNC(detallesDevolucio));
			// se crea el archivo PDF
			JasperExportManager.exportReportToPdfFile(jasperPrint,
					dirComprobante.toAbsolutePath() + File.separator
							+ responseService.getEstado() + File.separator
							+ notaCredito.getInfoTributaria().getClaveAcceso()
							+ ".pdf");
		} catch (Throwable e) {
			FacturacionLogger.log.error(e.getMessage(), e);
		}
	}

	private JRDataSource createDatasourceNC(
			Collection<Tfacdetdevolucione> detalleDevolucionesColl) {
		Collection<Object[]> c = new ArrayList<Object[]>();
		for (Tfacdetdevolucione detalleDevolucion : detalleDevolucionesColl) {
			c.add(new Object[] { detalleDevolucion.getCodigoproductos(),
					detalleDevolucion.getTinvproducto().getNombre(),
					round(detalleDevolucion.getTotalstems()),
					round(detalleDevolucion.getPreciounitario()),
					round(detalleDevolucion.getTotal()) });
		}

		JRDataSource datasource = new JRArrayDataSource(c);
		return datasource;
	}

	private void completaDatosRetorno(ResponseServiceDto responseServiceDto,
			Autorizacion autorizacion) throws FacturacionException {
		if (autorizacion.getMensajes() != null
				&& !autorizacion.getMensajes().getMensaje().isEmpty()) {
			responseServiceDto.setMensajes(new ArrayList<String>());
			for (Mensaje mensaje : autorizacion.getMensajes().getMensaje()) {
				responseServiceDto.getMensajes()
						.add(mensaje.getIdentificador() + "-"
								+ mensaje.getMensaje());
			}
		}
	}

	public static BigDecimal round(BigDecimal d) {
		int mode = BigDecimal.ROUND_UP;
		return d.setScale(2, mode);
	}

	private void generaComprobantesPDF(ResponseServiceDto responseService,
			Factura factura, Tfaccabfactura tfaccabfactura,
			Tfaccliente cliente, Tadmcompania compania)
			throws FacturacionException {
		try {
			// Genero xml y pdf
			String rutaComprobante = "";
			rutaComprobante = new StringBuilder(File.separator).append(
					responseService.getEstado()).toString();
			Path dirComprobante = Paths.get(ComprobantesUtil.getInstancia()
					.obtenerDirectorioFacturas(
							tfaccabfactura.getPk().getCodigocompania())
					+ rutaComprobante);
			generaPDFXML(responseService, factura, compania, dirComprobante);
			pdf(responseService, factura, tfaccabfactura, cliente, compania,
					dirComprobante);// tfaccabfactura, cliente, compania,
									// rutaPdf
		} catch (Throwable e) {
			throw new FacturacionException(
					"ERROR al generar los comprobantes electronicos", e);
		}
	}

	private void generaPDFXML(ResponseServiceDto responseService,
			Factura factura, Tadmcompania empresa, Path dirComprobante)
			throws FacturacionException {
		try {
			Files.createDirectories(dirComprobante);
			Files.write(Paths.get(dirComprobante.toAbsolutePath()
					+ File.separator
					+ factura.getInfoTributaria().getClaveAcceso() + ".xml"),
					responseService.getComprobante().getBytes(),
					StandardOpenOption.CREATE);
		} catch (Throwable e) {
			FacturacionLogger.log.error(e.getMessage(), e);
		}
	}

	@SuppressWarnings("static-access")
	private void pdf(ResponseServiceDto responseService,Factura factura,Tfaccabfactura tfaccabfactura, Tfaccliente cliente,Tadmcompania compania,Path dirComprobante){
        JasperReport jasperReport;
        JasperPrint jasperPrint;                
        try
        {
          //se carga el reporte
          URL  in=this.getClass().getResource( "/ec/com/dlc/bunsys/commons/reports/factura.jasper" );
          System.out.println("url :::: "+in.getPath());
          jasperReport=(JasperReport)JRLoader.loadObject(in);
          
          Map<String, Object> param = new HashMap<String, Object>();
          param.put("dirProvee", compania.getDireccionestablecimiento());//"test direccion prov"
          param.put("rucProvee", compania.getRuc());//"1721087524"
          param.put("numDocumento",tfaccabfactura.getPk().getNumerofactura());// "0010010002525"
          param.put("numAutoriza", tfaccabfactura.getClaveacceso());
          param.put("fechaAutoriza", "");
          param.put("ambiente",compania.getTipoambiente());
          SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");
          param.put("emision", format.format(tfaccabfactura.getFechafactura()));
          param.put("claveAcceso", tfaccabfactura.getClaveacceso());
          param.put("razonProvee", StringEscapeUtils.escapeJava(compania.getRazonsocial()));
          param.put("razonCliente", StringEscapeUtils.escapeJava(cliente.getTsyspersona().getApellidos()));
          param.put("rucCliente", cliente.getTsyspersona().getIdentificacion());
          param.put("fechaEmision", format.format(tfaccabfactura.getFechafactura()));
          param.put("fechaEmiComp", format.format(tfaccabfactura.getFechafactura()));
          param.put("motivo", "");
          param.put("numComprobante", "");
          param.put("imgLogo", "");
          if(tfaccabfactura.getSubtotalnoiva()!=null){
        	  param.put("totalGravCero", tfaccabfactura.getSubtotalnoiva());
          }else{
        	  param.put("totalGravCero", new BigDecimal(0));
          }
          if(tfaccabfactura.getIva()!=null){
        	  param.put("totalGravDoce",tfaccabfactura.getIva());// 
          }else{
        	  param.put("totalGravDoce",new BigDecimal(0));// new BigDecimal(0)
          }
          param.put("importeIva", new BigDecimal(0));
          param.put("total", tfaccabfactura.getTotal());
          //param.put("totalGravCero", new BigDecimal(0));
          param.put("numContribuyente", compania.getNombrecomercial());
          param.put("master", tfaccabfactura.getMasterawb());
          param.put("house", tfaccabfactura.getHouseawb());
          TadmcatalogoPK pk = new TadmcatalogoPK();
		  pk.setCodigocatalogo(tfaccabfactura.getAirline());
		  pk.setCodigocompania(tfaccabfactura.getPk().getCodigocompania());
		  pk.setCodigotipocatalogo(tfaccabfactura.getAirlinecodigo());
		  Tadmcatalogo aerolinea =facturaDao.findById(Tadmcatalogo.class,pk);
          param.put("airline", aerolinea.getDescripcion());
          param.put("dae", tfaccabfactura.getReferendo());
          param.put("marcacion", tfaccabfactura.getConsignee());//marcacion
          param.put("consignatario", tfaccabfactura.getFixedprice());//consignatario
          //se procesa el archivo jasper
          jasperPrint = JasperFillManager.fillReport(jasperReport, param, this.createDatasourceDet(tfaccabfactura.getTfacdetfacturas()) );
          //se crea el archivo PDF
          JasperExportManager.exportReportToPdfFile(jasperPrint, dirComprobante.toAbsolutePath() + File.separator + factura.getInfoTributaria().getClaveAcceso() +".pdf");
        }
        catch (JRException ex)
        {
          System.err.println( "Error iReport: " + ex.getMessage() );
        }
	}

	private static JRDataSource createDatasourceDet(
			Collection<Tfacdetfactura> detalleList) {
		Collection<Object[]> c = new ArrayList<Object[]>();
		for (Tfacdetfactura item : detalleList) {
			c.add(new Object[] {
					Integer.parseInt(item.getCantidad().toString()),
					item.getTinvproducto().getNombre(),
					round(new BigDecimal(item.getTinvproducto()
							.getPreciounitario())),
					round(item.getTotal()),
					Integer.parseInt(item.getTinvproducto().getPk()
							.getCodigoproductos()) });
		}
		JRDataSource dt = new JRArrayDataSource(c);
		return dt;
	}
}
