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

import ec.com.dlc.bunsys.common.dom.TransformXML;
import ec.com.dlc.bunsys.common.marshaller.MarshallerFactory;
import ec.com.dlc.bunsys.common.sign.factory.XmlSignFactory;
import ec.com.dlc.bunsys.common.util.ResponseServiceDto;
import ec.com.dlc.bunsys.dao.facturacion.FacturaDao;
import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;
<<<<<<< HEAD
=======
import ec.com.dlc.bunsys.entity.administracion.Tadmconversionunidad;
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
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
<<<<<<< HEAD
=======
import ec.com.dlc.bunsys.schema.v110.notacredito.Impuesto;
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
import ec.com.dlc.bunsys.schema.v110.notacredito.InfoTributaria;
import ec.com.dlc.bunsys.schema.v110.notacredito.NotaCredito;
import ec.com.dlc.bunsys.schema.v110.notacredito.NotaCredito.Detalles;
import ec.com.dlc.bunsys.schema.v110.notacredito.NotaCredito.Detalles.Detalle;
import ec.com.dlc.bunsys.schema.v110.notacredito.NotaCredito.Detalles.Detalle.Impuestos;
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

<<<<<<< HEAD
//	/**
//	 * Busaca el tipo de conversion del producto selecionado
//	 * 
//	 * @param unidadVentaCodigo
//	 * @param unidadVenta
//	 * @return
//	 */
//	public Tadmconversionunidad conversionArticulo(Integer unidadVentaCodigo,
//			String unidadVenta) {
//		return facturaDao.conversionArticulo(unidadVentaCodigo, unidadVenta);
//	}
=======
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
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d

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
<<<<<<< HEAD
			if(xml==null){
				throw new FacturacionException("PASSWORD INCORRECTO O LA RUTA DE LA FIRMA ES INCORRECTA");
			}
			// guarda en estado de contingencia, genera el archivo y lo firma
			if (tfaccabfactura.getEstadosri().equals("CO")) {System.out.println(xml);
=======
			// guarda en estado de contingencia, genera el archivo y lo firma
			if (tfaccabfactura.getEstadosri().equals("CO")) {
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
				responseService.setEstado(tfaccabfactura.getEstadosri());
				responseService.setComprobante(xml);
				generaComprobantesPDF(responseService, factura, tfaccabfactura,	cliente, tadmcompania);
			}
			if (tfaccabfactura.getEstadosri().equals("FE")) {
				System.out.println("clave acceso......."+factura.getInfoTributaria().getClaveAcceso());
				RecepcionComprobantesService recepcionComprobantesService = new RecepcionComprobantesService(ComprobantesUtil.getInstancia().obtenerURLWSRecep(tadmcompania.getTipoambiente(), tadmcompania.getPk().getCodigocompania()));
				// web service valida el comprobante
				RespuestaSolicitud respuestaSolicitud = recepcionComprobantesService.getRecepcionComprobantesPort().validarComprobante(	xml.getBytes());
				// estado recivido
				System.out.println(respuestaSolicitud.getEstado());
				responseService.setComprobante(xml);
				if (respuestaSolicitud.getEstado().equals("RECIBIDA")) {// Constants.STATE_RECEIVED
<<<<<<< HEAD
					Thread.sleep(4000);
=======
					Thread.sleep(3000);
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
					AutorizacionComprobantesService autorizacionService = new AutorizacionComprobantesService(ComprobantesUtil.getInstancia().obtenerURLWSAutorizacion(tadmcompania.getTipoambiente(), tadmcompania.getPk().getCodigocompania()));
					//respuesta de autorizacion
					RespuestaComprobante respuestaComprobante = autorizacionService.getAutorizacionComprobantesPort().autorizacionComprobante(factura.getInfoTributaria().getClaveAcceso());
					if (respuestaComprobante != null && !respuestaComprobante.getAutorizaciones().getAutorizacion().isEmpty()) {
						for (Autorizacion autorizacion : respuestaComprobante.getAutorizaciones().getAutorizacion()) {
							autorizacion.setComprobante(new StringBuilder("<![CDATA[").append(xml).append("]]>").toString());
							String finalXml = TransformXML.autorizacionToXML(autorizacion);//MarshallerFactory.getInstancia().marshal(autorizacion);
							System.out.println(autorizacion.getEstado());
							responseService.setEstado(autorizacion.getEstado());
							responseService.setComprobante(finalXml);
							completaDatosRetorno(responseService, autorizacion);
							generaComprobantesPDF(responseService, factura,	tfaccabfactura, cliente, tadmcompania);
							break;
						}
					} else if (respuestaComprobante == null	|| respuestaComprobante.getAutorizaciones().getAutorizacion().isEmpty()) {//|| respuestaComprobante.getAutorizaciones().getAutorizacion().isEmpty()
						System.out.println(respuestaComprobante);
						if (respuestaComprobante == null){System.out.println("NO DEVUELVE...respuestaComprobante");}
						if (respuestaComprobante != null && respuestaComprobante.getAutorizaciones().getAutorizacion().isEmpty()){System.out.println("TAMANIO DE LA LISTA..."+respuestaComprobante.getAutorizaciones().getAutorizacion().size());}
						
						responseService = new ResponseServiceDto();
						//responseService.setEstado("NO ENVIADO SRI");
						responseService.setMensajes(new ArrayList<String>());
						String mensajeerror="";
						for (Comprobante mensaje : respuestaSolicitud.getComprobantes().getComprobante()) {
							for (ec.com.dlc.bunsys.webservices.sri.recepcion.Mensaje men : mensaje.getMensajes().getMensaje()) {
								System.out.println("...."+men.getMensaje());
								mensajeerror=men.getMensaje();
								responseService.getMensajes().add(men.getMensaje());
							}
						}
<<<<<<< HEAD
						//responseService.setEstado("AUTORIZADO");
						//responseService.setComprobante(xml);
						//generaComprobantesPDF(responseService, factura,	tfaccabfactura, cliente, tadmcompania);
						throw new FacturacionException("ERROR al consultar al servicio de autorizacion : "+mensajeerror);
=======
						responseService.setEstado("AUTORIZADO");
						responseService.setComprobante(xml);
						generaComprobantesPDF(responseService, factura,	tfaccabfactura, cliente, tadmcompania);
						//throw new FacturacionException("ERROR al consultar al servicio de autorizacion : "+mensajeerror);
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
					}
				} else {
					responseService = new ResponseServiceDto();
					responseService.setEstado(respuestaSolicitud.getEstado());
					responseService.setMensajes(new ArrayList<String>());
					String mensajeerror="";
					for (Comprobante mensaje : respuestaSolicitud.getComprobantes().getComprobante()) {
						for (ec.com.dlc.bunsys.webservices.sri.recepcion.Mensaje men : mensaje.getMensajes().getMensaje()) {
<<<<<<< HEAD
							System.out.println(men.getMensaje()+" "+men.getInformacionAdicional());
							responseService.getMensajes().add(men.getMensaje()+" "+men.getInformacionAdicional());
							mensajeerror=men.getMensaje()+" "+men.getInformacionAdicional();
=======
							System.out.println(men.getMensaje());
							responseService.getMensajes().add(men.getMensaje());
							mensajeerror=men.getMensaje();
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
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
<<<<<<< HEAD
		if(tfaccabfactura.getTfacdetfacturas()==null || tfaccabfactura.getTfacdetfacturas().size()==0){
			throw new FacturacionException("NO TIENE DETALLE LA FACTURA");
		}
=======
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
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
<<<<<<< HEAD
		factura.getInfoFactura().setIdentificacionComprador(cliente.getTsyspersona().getIdentificacion());// "12554"
=======
		factura.getInfoFactura().setIdentificacionComprador(
				cliente.getTsyspersona().getIdentificacion());// "12554"
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
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
<<<<<<< HEAD
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setBaseImponible(BigDecimal.ZERO);// BigDecimal.ZERO// le cabie round(tfaccabfactura.getSubtotalneto())
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setCodigo("2");
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setCodigoPorcentaje("2");// Depende de la Tabla 18 o 19 (12 Denominación del comprobante de venta Numérico 2 Opcional)
=======
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setBaseImponible(round(tfaccabfactura.getSubtotalneto()));// BigDecimal.ZERO
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setCodigo("1");
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setCodigoPorcentaje("12");// (12 Denominación del comprobante de venta Numérico 2 Opcional)
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setDescuentoAdicional(BigDecimal.ZERO);
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setTarifa(BigDecimal.ZERO);
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setValor(BigDecimal.ZERO);
		factura.getInfoFactura().getTotalConImpuestos().getTotalImpuesto().get(0).setValorDevolucionIva(BigDecimal.ZERO);
<<<<<<< HEAD
		factura.setInfoAdicional(new ec.com.dlc.bunsys.schema.v110.factura.Factura.InfoAdicional());
		
//		factura.getInfoAdicional().getCampoAdicional().add(new ec.com.dlc.bunsys.schema.v110.factura.Factura.InfoAdicional.CampoAdicional());
//		factura.getInfoAdicional().getCampoAdicional().get(0).setNombre("Master AWB No. /#");
//		factura.getInfoAdicional().getCampoAdicional().get(0).setValue(tfaccabfactura.getMasterawb());
		
//		factura.getInfoAdicional().getCampoAdicional().add(new ec.com.dlc.bunsys.schema.v110.factura.Factura.InfoAdicional.CampoAdicional());
//		factura.getInfoAdicional().getCampoAdicional().get(1).setNombre("House AWB No. /#");
//		factura.getInfoAdicional().getCampoAdicional().get(1).setValue(tfaccabfactura.getHouseawb());
		
//		 TadmcatalogoPK pkaerolinea = new TadmcatalogoPK();
//		 pkaerolinea.setCodigocatalogo(tfaccabfactura.getAirline());
//		 pkaerolinea.setCodigocompania(tfaccabfactura.getPk().getCodigocompania());
//		 pkaerolinea.setCodigotipocatalogo(tfaccabfactura.getAirlinecodigo());
//		 Tadmcatalogo aerolinea =facturaDao.findById(Tadmcatalogo.class,pkaerolinea);
		 
//		factura.getInfoAdicional().getCampoAdicional().add(new ec.com.dlc.bunsys.schema.v110.factura.Factura.InfoAdicional.CampoAdicional());
//		factura.getInfoAdicional().getCampoAdicional().get(2).setNombre("AIRLINE");
//		factura.getInfoAdicional().getCampoAdicional().get(2).setValue(aerolinea.getDescripcion());
//		
//		factura.getInfoAdicional().getCampoAdicional().add(new ec.com.dlc.bunsys.schema.v110.factura.Factura.InfoAdicional.CampoAdicional());
//		factura.getInfoAdicional().getCampoAdicional().get(3).setNombre("DAE");
//		factura.getInfoAdicional().getCampoAdicional().get(3).setValue(tfaccabfactura.getReferendo());
//		
//		factura.getInfoAdicional().getCampoAdicional().add(new ec.com.dlc.bunsys.schema.v110.factura.Factura.InfoAdicional.CampoAdicional());
//		factura.getInfoAdicional().getCampoAdicional().get(4).setNombre("Marcacion");
//		factura.getInfoAdicional().getCampoAdicional().get(4).setValue(tfaccabfactura.getConsignee());
//		
//		factura.getInfoAdicional().getCampoAdicional().add(new ec.com.dlc.bunsys.schema.v110.factura.Factura.InfoAdicional.CampoAdicional());
//		factura.getInfoAdicional().getCampoAdicional().get(5).setNombre("Consignatario");
//		factura.getInfoAdicional().getCampoAdicional().get(5).setValue(tfaccabfactura.getFixedprice());
		
		
=======
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
		if (tfaccabfactura.getTotaldescuento() != null	&& tfaccabfactura.getTotaldescuento().compareTo(new BigDecimal(0)) > 0) {
			factura.getInfoFactura().setTotalDescuento(round(tfaccabfactura.getTotaldescuento()));// BigDecimal.ZERO
		} else {
			factura.getInfoFactura().setTotalDescuento(BigDecimal.ZERO);//
		}

		factura.getInfoFactura().setTotalImpuestoReembolso(BigDecimal.ZERO);

		factura.getInfoFactura().setTotalSinImpuestos(round(tfaccabfactura.getSubtotalneto()));
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
<<<<<<< HEAD
			detalle.setCantidad(round(detallefactura.getCantidad()));//setCantidad(round(detallefactura.getTotalstems()));//detallefactura.getCantidad()
=======
			detalle.setCantidad(round(detallefactura.getCantidad()));
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
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
			if(cliente!=null && cliente.getTsyspersona()!=null && cliente.getTsyspersona().getCorreo()!=null && cliente.getTsyspersona().getCorreo().trim().length()>2){
				detalle.getDetallesAdicionales().getDetAdicional().get(0).setValor(StringEscapeUtils.escapeJava(cliente.getTsyspersona().getCorreo()));// "dcruz@bupartech.com"
			}else{
<<<<<<< HEAD
				detalle.getDetallesAdicionales().getDetAdicional().get(0).setValor("S/N");
=======
				detalle.getDetallesAdicionales().getDetAdicional().get(0).setValor("dcruz@bupartech.com");// "dcruz@bupartech.com"
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
			}
			
			detalle.setImpuestos(new Factura.Detalles.Detalle.Impuestos());
			detalle.setPrecioTotalSinImpuesto(round(detallefactura.getTotal()));
			detalle.setPrecioUnitario(round(detallefactura.getPreciounitario()));
<<<<<<< HEAD
			
//			TadmcatalogoPK pk = new TadmcatalogoPK();
//			pk.setCodigocatalogo(detallefactura.getUnidadventa());
//			pk.setCodigocompania(detallefactura.getPk().getCodigocompania());
//			pk.setCodigotipocatalogo(detallefactura.getUnidadventacodigo());
//			Tadmcatalogo unidad = facturaDao.findById(Tadmcatalogo.class, pk);
//			detalle.setUnidadMedida(StringEscapeUtils.escapeJava(unidad	.getDescripcion()));// "UND"
			
=======
			TadmcatalogoPK pk = new TadmcatalogoPK();
			pk.setCodigocatalogo(detallefactura.getUnidadventa());
			pk.setCodigocompania(detallefactura.getPk().getCodigocompania());
			pk.setCodigotipocatalogo(detallefactura.getUnidadventacodigo());
			Tadmcatalogo unidad = facturaDao.findById(Tadmcatalogo.class, pk);
			detalle.setUnidadMedida(StringEscapeUtils.escapeJava(unidad	.getDescripcion()));// "UND"
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
			detalle.setImpuestos(new Factura.Detalles.Detalle.Impuestos());
			detalle.getImpuestos().getImpuesto().add(new ec.com.dlc.bunsys.schema.v110.factura.Impuesto());

			detalle.getImpuestos().getImpuesto().get(0).setBaseImponible(round(detallefactura.getTotal()));// ** new BigDecimal(1200.0)

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
			notaCredito.getPk().setCodigocompania(empresa.getPk().getCodigocompania());
			Integer sec = secuenciaService.obtenerSecuenciaComp(notaCredito.getPk().getCodigocompania(), ConstantesSRI.COD_NOTA_CREDITO);
			notaCredito.getPk().setNumerodevoluciones(ComprobantesUtil.getInstancia().getsecuencia(sec.toString(), 9));
<<<<<<< HEAD
			notaCredito.setClaveacceso(secuenciaService.generaClaveAcceso(notaCredito.getFechadevolucion(), empresa.getPk()
					.getCodigocompania(), ConstantesSRI.COD_NOTA_CREDITO,notaCredito.getPk().getNumerodevoluciones()));
			
			facturaDao.saveOrUpdate(notaCredito);
			if(detallesNotaCreditoColl==null || detallesNotaCreditoColl.size()==0){
				throw new FacturacionException("NO TIENE DETALLE LA FACTURA");
			}
=======
			facturaDao.saveOrUpdate(notaCredito);
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
			for (Tfacdetdevolucione tfacdetdevolucione : detallesNotaCreditoColl) {
				tfacdetdevolucione.getPk().setCodigocompania(empresa.getPk().getCodigocompania());
				tfacdetdevolucione.getPk().setNumerodevoluciones(notaCredito.getPk().getNumerodevoluciones());
				facturaDao.saveOrUpdate(tfacdetdevolucione);
			}
			// una vez grabado genero los datos de la facturacion electronica
			NotaCredito sriNotaCredito = new NotaCredito();
			sriNotaCredito.setId("comprobante");
//			sriNotaCredito.setInfoAdicional(new InfoAdicional());
//			sriNotaCredito.getInfoAdicional().
			sriNotaCredito.setInfoNotaCredito(new InfoNotaCredito());
			sriNotaCredito.setInfoTributaria(new InfoTributaria());
			sriNotaCredito.setVersion("1.1.0");

			completaDatosNC(notaCredito, detallesNotaCreditoColl, empresa,
					numeroComprobante, sriNotaCredito);

			String xmlNC = MarshallerFactory.getInstancia().marshal(sriNotaCredito);
<<<<<<< HEAD
			System.out.println("Passs.........."+notaCredito.getAditionalProperty("passwordToken"));
			xmlNC = XmlSignFactory.getXmlDataSign().signXML(xmlNC,new File(ComprobantesUtil.getInstancia().obtenerRutaCertificado(empresa.getPk().getCodigocompania())),notaCredito.getAditionalProperty("passwordToken").toString());
			System.out.println("xml....."+xmlNC);
			if(xmlNC==null){
				throw new FacturacionException("PASSWORD INCORRECTO O LA RUTA DE LA FIRMA ES INCORRECTA");
			}
			System.out.println("ambiente .."+empresa.getTipoambiente()+"....compania....."+ empresa.getPk().getCodigocompania());
			RecepcionComprobantesService recepcionComprobantesService = new RecepcionComprobantesService(ComprobantesUtil.getInstancia().obtenerURLWSRecep(empresa.getTipoambiente(), empresa.getPk().getCodigocompania()));
			RespuestaSolicitud respuestaSolicitud = recepcionComprobantesService.getRecepcionComprobantesPort().validarComprobante(xmlNC.getBytes());
			System.out.println("clave de acceso... "+notaCredito.getClaveacceso());
			System.out.println("respuesta.. "+respuestaSolicitud.getEstado());
=======
			xmlNC = XmlSignFactory.getXmlDataSign().signXML(
					xmlNC,new File(ComprobantesUtil.getInstancia().obtenerRutaCertificado(empresa.getPk().getCodigocompania())),
					notaCredito.getAditionalProperty("passwordToken").toString());
			RecepcionComprobantesService recepcionComprobantesService = new RecepcionComprobantesService(ComprobantesUtil.getInstancia().obtenerURLWSRecep(empresa.getTipoambiente(), empresa.getPk().getCodigocompania()));
			RespuestaSolicitud respuestaSolicitud = recepcionComprobantesService.getRecepcionComprobantesPort().validarComprobante(xmlNC.getBytes());
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
			if (respuestaSolicitud.getEstado().equals("RECIBIDA")) {//Constants.STATE_RECEIVED
				Thread.sleep(3000);
				AutorizacionComprobantesService autorizacionService = new AutorizacionComprobantesService(ComprobantesUtil.getInstancia().obtenerURLWSAutorizacion(empresa.getTipoambiente(), empresa.getPk().getCodigocompania()));
				RespuestaComprobante respuestaComprobante = autorizacionService
						.getAutorizacionComprobantesPort().autorizacionComprobante(sriNotaCredito.getInfoTributaria().getClaveAcceso());
				if (respuestaComprobante != null
						&& !respuestaComprobante.getAutorizaciones().getAutorizacion().isEmpty()) {
					for (Autorizacion autorizacion : respuestaComprobante.getAutorizaciones().getAutorizacion()) {
						autorizacion.setComprobante(new StringBuilder("<![CDATA[").append(xmlNC).append("]]>").toString());
						String finalXml =TransformXML.autorizacionToXML(autorizacion);// MarshallerFactory.getInstancia().marshal(autorizacion);
						responseService = new ResponseServiceDto();
<<<<<<< HEAD
						responseService.setEstado(autorizacion.getEstado());System.out.println("Estado Autorizacion NC.."+autorizacion.getEstado());
=======
						responseService.setEstado(autorizacion.getEstado());
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
						responseService.setComprobante(finalXml);
						completaDatosRetorno(responseService, autorizacion);
						generaComprobantes(responseService, sriNotaCredito,	notaCredito, detallesNotaCreditoColl, empresa);
						break;
					}
				} else if (respuestaComprobante == null
						|| respuestaComprobante.getAutorizaciones().getAutorizacion().isEmpty()) {
					responseService = new ResponseServiceDto();
<<<<<<< HEAD
					//responseService.setEstado("NO ENVIADA");//Constants.STATE_NO_SUBMIT
					//throw new FacturacionException("ERROR al consultar al servicio de autorizacion");
				}
			} else {
//				responseService = new ResponseServiceDto();
//				responseService.setEstado(respuestaSolicitud.getEstado());
//				throw new FacturacionException("El comprobante ha sido devuelto");
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
=======
					responseService.setEstado("NO ENVIADA");//Constants.STATE_NO_SUBMIT
					throw new FacturacionException("ERROR al consultar al servicio de autorizacion");
				}
			} else {
				responseService = new ResponseServiceDto();
				responseService.setEstado(respuestaSolicitud.getEstado());
				throw new FacturacionException("El comprobante ha sido devuelto");
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
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
		sriNotaCredito.getInfoTributaria().setAmbiente(empresa.getTipoambiente());
		sriNotaCredito.getInfoTributaria().setClaveAcceso(secuenciaService.generaClaveAcceso(notaCredito.getFechadevolucion(), empresa.getPk()
						.getCodigocompania(), ConstantesSRI.COD_NOTA_CREDITO,notaCredito.getPk().getNumerodevoluciones()));
		sriNotaCredito.getInfoTributaria().setCodDoc(ConstantesSRI.COD_NOTA_CREDITO);
		sriNotaCredito.getInfoTributaria().setDirMatriz(empresa.getDireccionmatriz());
		sriNotaCredito.getInfoTributaria().setEstab(empresa.getCodigoestablecimiento());
		sriNotaCredito.getInfoTributaria().setPtoEmi(empresa.getCodigopuntoemision());
		sriNotaCredito.getInfoTributaria().setNombreComercial(empresa.getNombrecomercial());
		sriNotaCredito.getInfoTributaria().setRazonSocial(empresa.getRazonsocial());
		sriNotaCredito.getInfoTributaria().setRuc(empresa.getRuc());
		sriNotaCredito.getInfoTributaria().setSecuencial(notaCredito.getPk().getNumerodevoluciones());
		sriNotaCredito.getInfoTributaria().setTipoEmision(ConstantesSRI.COD_EMISION_NORMAL);

		sriNotaCredito.getInfoNotaCredito().setCodDocModificado(ConstantesSRI.COD_FACTURA);
		
		sriNotaCredito.getInfoNotaCredito().setNumDocModificado(empresa.getCodigoestablecimiento()+"-"+ empresa.getCodigopuntoemision() +"-"+ numeroComprobante);

		sriNotaCredito.getInfoNotaCredito().setContribuyenteEspecial(notaCredito.getCodigocliente());
		sriNotaCredito.getInfoNotaCredito().setDirEstablecimiento(empresa.getDireccionestablecimiento());
		sriNotaCredito.getInfoNotaCredito().setFechaEmision(fecha);
		SimpleDateFormat formatDates = new SimpleDateFormat("dd/MM/yyyy");
		
	    sriNotaCredito.getInfoNotaCredito().setFechaEmisionDocSustento(formatDates.format(new Date()));
		sriNotaCredito.getInfoNotaCredito().setIdentificacionComprador(notaCredito.getTfaccliente().getTsyspersona().getIdentificacion());
		sriNotaCredito.getInfoNotaCredito().setMoneda("DOLAR");
		sriNotaCredito.getInfoNotaCredito().setMotivo(notaCredito.getObservacion());
		sriNotaCredito.getInfoNotaCredito().setObligadoContabilidad(ObligadoContabilidad.NO);
		sriNotaCredito.getInfoNotaCredito().setRazonSocialComprador(notaCredito.getTfaccliente().getTsyspersona().getNombres());
		
		TadmcatalogoPK pk1 = new TadmcatalogoPK();
		pk1.setCodigocatalogo(notaCredito.getTfaccliente().getTsyspersona().getTipoid());
		pk1.setCodigocompania(notaCredito.getTfaccliente().getPk().getCodigocompania());
		pk1.setCodigotipocatalogo(notaCredito.getTfaccliente().getTsyspersona().getTipoidcodigo());
		Tadmcatalogo tipoIndentificacion = facturaDao.findById(Tadmcatalogo.class, pk1);
		//factura.getInfoFactura().setTipoIdentificacionComprador(tipoIndentificacion.getValor());// "08"(IDENTIFICACION DELEXTERIOR* 08 - RUC 04 - CEDULA 05 -PASAPORTE 06 -VENTA A CONSUMIDOR FINAL*07)
		sriNotaCredito.getInfoNotaCredito().setTipoIdentificacionComprador(tipoIndentificacion.getValor());//notaCredito.getTfaccliente().getTsyspersona().getTipoid()
		
		sriNotaCredito.getInfoNotaCredito().setTotalSinImpuestos(round(notaCredito.getSubtotalneto()));
<<<<<<< HEAD
		sriNotaCredito.getInfoNotaCredito().setValorModificacion(notaCredito.getTotal());//BigDecimal.ZERO se cambio
=======
		sriNotaCredito.getInfoNotaCredito().setValorModificacion(BigDecimal.ZERO);
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
		sriNotaCredito.getInfoNotaCredito().setTotalConImpuestos(new TotalConImpuestos());
		TotalImpuesto totalImpuestoIVA = new TotalImpuesto();
		totalImpuestoIVA.setCodigo("2");
		totalImpuestoIVA.setCodigoPorcentaje("7");
<<<<<<< HEAD
//		if(sriNotaCredito.getInfoNotaCredito()!=null && sriNotaCredito.getInfoNotaCredito().getTotalSinImpuestos()!=null){
//			totalImpuestoIVA.setBaseImponible(round(sriNotaCredito.getInfoNotaCredito().getTotalSinImpuestos()));
//		}else{
			totalImpuestoIVA.setBaseImponible(BigDecimal.ZERO);
//		}
		
		totalImpuestoIVA.setValor(BigDecimal.ZERO);
		sriNotaCredito.getInfoNotaCredito().getTotalConImpuestos().getTotalImpuesto().add(totalImpuestoIVA);
=======
		if(sriNotaCredito.getInfoNotaCredito()!=null && sriNotaCredito.getInfoNotaCredito().getTotalSinImpuestos()!=null){
			totalImpuestoIVA.setBaseImponible(round(sriNotaCredito.getInfoNotaCredito().getTotalSinImpuestos()));
		}else{
			totalImpuestoIVA.setBaseImponible(BigDecimal.ZERO);
		}
		
		totalImpuestoIVA.setValor(BigDecimal.ZERO);
		sriNotaCredito.getInfoNotaCredito().getTotalConImpuestos()
				.getTotalImpuesto().add(totalImpuestoIVA);
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d

		sriNotaCredito.setDetalles(new Detalles());
		for (Tfacdetdevolucione detdevolucione : detallesNotaCreditoColl) {
			Detalle detalle = new Detalle();
<<<<<<< HEAD
			detalle.setCantidad(detdevolucione.getTotal());//detdevolucione.getCantidad()
=======
			detalle.setCantidad(detdevolucione.getCantidad());
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
			detalle.setCodigoAdicional(detdevolucione.getTinvproducto().getCodigoauxiliar());
			detalle.setCodigoInterno(detdevolucione.getTinvproducto().getPk().getCodigoproductos());
			detalle.setDescripcion(detdevolucione.getTinvproducto().getNombre());
			if(detdevolucione.getDescuento()!=null){
				detalle.setDescuento(round(detdevolucione.getDescuento()));
			}else{
				detalle.setDescuento(BigDecimal.ZERO);
			}
			detalle.setPrecioUnitario(round(detdevolucione.getPreciounitario()));
			//total del articulo - descuento     el total se obtiene diferente a detalle.getPrecioUnitario().multiply(detalle.getCantidad
			detalle.setPrecioTotalSinImpuesto(round(detdevolucione.getTotal().subtract(detalle.getDescuento())));//detalle.getPrecioUnitario().multiply(detalle.getCantidad()).subtract(detalle.getDescuento())));
			detalle.setImpuestos(new Impuestos());
<<<<<<< HEAD
//			Impuesto impuestoIVA = new Impuesto();
//			impuestoIVA.setBaseImponible(round(detdevolucione.getTotal()));
//			impuestoIVA.setValor(BigDecimal.ZERO);
//			// impuestoIVA.setTarifa(new BigDecimal(12));
//			impuestoIVA.setCodigo("2");
//			impuestoIVA.setCodigoPorcentaje("7");
=======
			Impuesto impuestoIVA = new Impuesto();
			impuestoIVA.setBaseImponible(round(detalle.getPrecioTotalSinImpuesto()));
			impuestoIVA.setValor(BigDecimal.ZERO);
			// impuestoIVA.setTarifa(new BigDecimal(12));
			impuestoIVA.setCodigo("2");
			impuestoIVA.setCodigoPorcentaje("7");
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
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
			rutaComprobante = new StringBuilder(File.separator).append(	responseService.getEstado()).toString();
			Path dirComprobante = Paths.get(ComprobantesUtil.getInstancia()
					.obtenerDirectorioNotaCredito(empresa.getPk().getCodigocompania())
					+ rutaComprobante);
			generaNotaCreditoXML(responseService, notaCredito, devolucion,empresa, dirComprobante);
			generaNotaCreditoPDF(responseService, notaCredito, devolucion,detallesDevolucion, dirComprobante, empresa);
		} catch (Throwable e) {
			throw new FacturacionException("ERROR al generar los comprobantes electronicos", e);
		}
	}

	private void generaNotaCreditoXML(ResponseServiceDto responseService,NotaCredito notaCredito, Tfaccabdevolucione devolucion,Tadmcompania empresa, Path dirComprobante)
			throws FacturacionException {
		try {
			Files.createDirectories(dirComprobante);
			Files.write(Paths.get(dirComprobante.toAbsolutePath() + File.separator+ notaCredito.getInfoTributaria().getClaveAcceso()+ ".xml"),
						responseService.getComprobante().getBytes(), StandardOpenOption.CREATE);
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
<<<<<<< HEAD
//			param.put("master", devolucion.getMasterawb());
//			param.put("house", devolucion.getHouseawb());
//			param.put("airline", devolucion.getAirline());
//			param.put("dae", devolucion.getReferendo());
//			param.put("marcacion", devolucion.getConsignee());
//			param.put("consignatario", devolucion.getFixedprice());
=======
			param.put("master", devolucion.getMasterawb());
			param.put("house", devolucion.getHouseawb());
			param.put("airline", devolucion.getAirline());
			param.put("dae", devolucion.getReferendo());
			param.put("marcacion", devolucion.getConsignee());
			param.put("consignatario", devolucion.getFixedprice());
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d

			// se procesa el archivo jasper
			jasperPrint = JasperFillManager.fillReport(jasperReport, param,
					createDatasourceNC(detallesDevolucio));
			// se crea el archivo PDF
			JasperExportManager.exportReportToPdfFile(jasperPrint,dirComprobante.toAbsolutePath() 
							+ File.separator
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
			c.add(new Object[] { 
<<<<<<< HEAD
					detalleDevolucion.getTotal().toString(),//0
=======
					Integer.parseInt(detalleDevolucion.getCantidad().toString()),//0
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
					detalleDevolucion.getTinvproducto().getNombre(),//1
					round(new BigDecimal(detalleDevolucion.getTinvproducto().getPreciounitario())),//2
					round(detalleDevolucion.getTotal()),//3
					detalleDevolucion.getTinvproducto().getPk().getCodigoproductos(),//4
					 });
//			detalleDevolucion.getCodigoproductos(),
//			detalleDevolucion.getTinvproducto().getNombre(),
//			round(detalleDevolucion.getTotalstems()),
//			round(detalleDevolucion.getPreciounitario()),
//			round(detalleDevolucion.getTotal())
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

	private void generaComprobantesPDF(ResponseServiceDto responseService,Factura factura, Tfaccabfactura tfaccabfactura,
			Tfaccliente cliente, Tadmcompania compania)	throws FacturacionException {
		try {
			// Genero xml y pdf
			String rutaComprobante = new StringBuilder(File.separator).append(responseService.getEstado()).toString();
<<<<<<< HEAD
			//System.out.println("ESTADO..."+responseService.getEstado());
=======
			System.out.println("ESTADO..."+responseService.getEstado());
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
			Path dirComprobante = Paths.get(ComprobantesUtil.getInstancia()
					.obtenerDirectorioFacturas(	tfaccabfactura.getPk().getCodigocompania())	+ rutaComprobante);
			System.out.println(dirComprobante);
			generaPDFXML(responseService, factura, compania, dirComprobante);
			pdf(responseService, factura, tfaccabfactura, cliente, compania,dirComprobante);// tfaccabfactura, cliente, compania,
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
			System.out.println("DIRECCION ..."+dirComprobante);
			Files.createDirectories(dirComprobante);
			Files.write(Paths.get(dirComprobante.toAbsolutePath()+ File.separator
					+ factura.getInfoTributaria().getClaveAcceso() + ".xml"),
					responseService.getComprobante().getBytes(),StandardOpenOption.CREATE);
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
        	  param.put("totalGravDoce",round(tfaccabfactura.getIva()));// 
          }else{
        	  param.put("totalGravDoce",new BigDecimal(0));// new BigDecimal(0)
          }
          param.put("importeIva", new BigDecimal(0));
          param.put("total", round(tfaccabfactura.getTotal()));
          //param.put("totalGravCero", new BigDecimal(0));
          param.put("numContribuyente", compania.getNombrecomercial());
<<<<<<< HEAD
          
//          param.put("master", tfaccabfactura.getMasterawb());
//          param.put("house", tfaccabfactura.getHouseawb());
//          TadmcatalogoPK pk = new TadmcatalogoPK();
//		  pk.setCodigocatalogo(tfaccabfactura.getAirline());
//		  pk.setCodigocompania(tfaccabfactura.getPk().getCodigocompania());
//		  pk.setCodigotipocatalogo(tfaccabfactura.getAirlinecodigo());
//		  Tadmcatalogo aerolinea =facturaDao.findById(Tadmcatalogo.class,pk);
//          param.put("airline", aerolinea.getDescripcion());
//          param.put("dae", tfaccabfactura.getReferendo());
//          param.put("marcacion", tfaccabfactura.getConsignee());//marcacion
//          param.put("consignatario", tfaccabfactura.getFixedprice());//consignatario
            //se procesa el archivo jasper
=======
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
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
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
<<<<<<< HEAD
			item.getTotal().toString(),//0  detallefactura.getCantidad()
=======
			Integer.parseInt(item.getCantidad().toString()),//0
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
			item.getTinvproducto().getNombre(),//1
			round(new BigDecimal(item.getTinvproducto().getPreciounitario())),//2
			round(item.getTotal()),//3
			item.getTinvproducto().getPk().getCodigoproductos(),//4
<<<<<<< HEAD
			item.getTinvproducto().getUnidadventa(),//5 unidad
			round(item.getTotal())+""//6 cant
=======
			item.getUnidadventa(),//5 unidad
			round(item.getTotalstems())+""//6 cant tallos
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
			});
		}
		JRDataSource dt = new JRArrayDataSource(c);
		return dt;
	}
	
	public void envioPorLote(List<Tfaccabfactura> facturas,	Collection<Tfaccabdevolucione> devoluciones,Integer codigocompania)throws FacturacionException {
		String rutafirma = ComprobantesUtil.getInstancia().obtenerRutaCertificado(codigocompania);
		Path dirComprobante = Paths.get(ComprobantesUtil.getInstancia()	.obtenerDirectorioFacturas(codigocompania));
		for(Tfaccabfactura factura:facturas){
			File archivo = new File(Paths.get(dirComprobante.toAbsolutePath()+ File.separator+factura.getEstadosri()+File.separator+factura.getClaveacceso()+".xml").toString());
			//BufferedWriter bw;
			if(archivo.exists()) {
				System.out.println("ARCHIVO SI EXISTE");
			} else{
				System.out.println("ARCHIVO NO EXISTE");
			}
			
			factura.setEstadosri("FE");
			facturaDao.update(factura);
		}
		for(Tfaccabdevolucione devolucion:devoluciones){
			String codigoacceso=secuenciaService.generaClaveAcceso(devolucion
					.getFechadevolucion(), devolucion.getPk()
					.getCodigocompania(), ConstantesSRI.COD_NOTA_CREDITO,
					devolucion.getPk().getNumerodevoluciones());
			File archivo = new File(rutafirma+File.separator+devolucion.getEstadosri()+File.separator+codigoacceso+".xml");
			//BufferedWriter bw;
			if(archivo.exists()) {
				System.out.println("ARCHIVO SI EXISTE");
			} else{
				System.out.println("ARCHIVO NO EXISTE");
			}
			
<<<<<<< HEAD
//			AutorizacionComprobantesService autorizacionService = new AutorizacionComprobantesService(ComprobantesUtil.getInstancia().obtenerURLWSAutorizacion(tadmcompania.getTipoambiente(), tadmcompania.getPk().getCodigocompania()));
//			//respuesta de autorizacion
//			RespuestaComprobante respuestaComprobante = autorizacionService.getAutorizacionComprobantesPort().autorizacionComprobanteLote(codigoacceso);
			
=======
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
			devolucion.setEstadosri("FE");
			facturaDao.update(devolucion);
		}
	}
}
