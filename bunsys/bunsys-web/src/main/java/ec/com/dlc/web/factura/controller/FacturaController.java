package ec.com.dlc.web.factura.controller;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;

import ec.com.dlc.bunsys.common.util.HttpUtils;
import ec.com.dlc.bunsys.common.util.ResponseServiceDto;
import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;
<<<<<<< HEAD
=======
import ec.com.dlc.bunsys.entity.administracion.Tadmconversionunidad;
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccuentasxcobrar;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfacformapago;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.bunsys.util.FacturacionException;
import ec.com.dlc.bunsys.util.JRArrayDataSource;
import ec.com.dlc.bunsys.util.sri.ConstantesSRI;
import ec.com.dlc.web.commons.resource.ContenidoMessages;
import ec.com.dlc.web.componentes.ArticuloComponent;
import ec.com.dlc.web.componentes.ClienteComponent;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.articulo.ArticuloDatamanager;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.cliente.ClienteDatamanager;
import ec.com.dlc.web.factura.datamanager.FacturaDataManager;

@ManagedBean(name="facturaController")
@ViewScoped
public class FacturaController extends BaseController implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private BunsysService bunsysService;
	
	@ManagedProperty(value="#{facturaDataManager}")
	private FacturaDataManager facturaDataManager;
	
	@ManagedProperty(value="#{clienteDatamanager}")
	private ClienteDatamanager clienteDatamanager;
	
	@ManagedProperty(value="#{articuloDatamanager}")
	private ArticuloDatamanager articuloDatamanager;
	
	@Override
	public BaseDatamanager getDatamanager() {
		return facturaDataManager;
	}
	
	@Override
	public void inicializar() {
		try {
			//componete del cliente
			Tfaccliente tfaccliente= new Tfaccliente();
			tfaccliente.setTsyspersona(new Tsyspersona());
			clienteDatamanager.setClienteserch(tfaccliente);
			//Articulo
			articuloDatamanager.setArticuloSearch(new Tinvproducto());
			facturaDataManager.setTinvproducto(new Tinvproducto());
			//objeto detalle de la proforma
			facturaDataManager.setTfacdetfactura(new Tfacdetfactura());
			//catalogos
			clienteDatamanager.setFormaspagosCatalogo(bunsysService.buscarObtenerCatalogos(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
	                	 ContenidoMessages.getInteger("cod_catalogo_forma_pago")));
			clienteDatamanager.setEstadosCatalogo(bunsysService.buscarObtenerCatalogos(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
			             ContenidoMessages.getInteger("cod_catalogo_estado_cliente")));
			clienteDatamanager.setGruposCatalogo(bunsysService.buscarObtenerCatalogos(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
			             ContenidoMessages.getInteger("cod_catalogo_grupo_cliente")));
			clienteDatamanager.setTiposCatalogo(bunsysService.buscarObtenerCatalogos(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
			             ContenidoMessages.getInteger("cod_catalogo_tipo_cliente")));
			clienteDatamanager.setClienteComponente(new ClienteComponent(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania()));
			
			articuloDatamanager.setColorCatalogoColl(bunsysService.buscarObtenerCatalogos(articuloDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_color_articulo")));
			articuloDatamanager.setEstadoCatalogoColl(bunsysService.buscarObtenerCatalogos(articuloDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_estado_articulo")));
			articuloDatamanager.setArticuloComponente(new ArticuloComponent(articuloDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania()));
			
			//catalogo factura
			facturaDataManager.setAerolineasCatalogo(bunsysService.buscarObtenerCatalogos(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_aerolineas")));
			facturaDataManager.setCatalogoPicesType(bunsysService.buscarObtenerCatalogos(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_pices_type")));
			facturaDataManager.setCatalogofob(bunsysService.buscarObtenerCatalogos(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_tipo_fob")));
			facturaDataManager.setCatalogocarguera(bunsysService.buscarObtenerCatalogos(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_cargueras")));
			facturaDataManager.setCatalogodistritovuelo(bunsysService.buscarObtenerCatalogos(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_distrito_vuelo")));
			facturaDataManager.setInstitucion(bunsysService.buscarObtenerCatalogos(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
                    ContenidoMessages.getInteger("cod_catalogo_institucion_bancaria")));
		} catch (Throwable e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * validaciones del check de forma de pago
	 */
	public void formaPagoEfectivo(){
		facturaDataManager.setFormaPago2(false);
		facturaDataManager.setFormaPago1(true);
	}
	
	/**
	 * validaciones del check de forma de pago
	 */
	public void formaPagoCheque(){
		facturaDataManager.setFormaPago1(false);
		facturaDataManager.setFormaPago2(true);
	}
	
	/**
	 * Metodo si el pago fue a credito,
	 * segun el el numero de pagos se crean el objeto Tfaccuentasxcobrar
	 * y se agrega a la lista de cuentas por cobrar de la cabecera de la factura
	 */
	@SuppressWarnings("deprecation")
	private void pagoCredito(){
		BigDecimal valorpagar=facturaDataManager.getTfaccabfactura().getTotal().divide(new BigDecimal(facturaDataManager.getNumeropagos()),6, BigDecimal.ROUND_HALF_UP);
		BigDecimal suma=new BigDecimal(0);
		for (int j = 1; j < facturaDataManager.getNumeropagos(); j++) {
			Tfaccuentasxcobrar tfaccuentasxcobrar= new Tfaccuentasxcobrar();
			//codigo compania
			tfaccuentasxcobrar.getPk().setCodigocompania(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
			//codigo cliente
			tfaccuentasxcobrar.setCodigocliente(facturaDataManager.getTfaccabfactura().getCodigocliente());
			//numero factura
			tfaccuentasxcobrar.setNumerofactura(facturaDataManager.getTfaccabfactura().getPk().getNumerofactura());
			//numero de pago
			tfaccuentasxcobrar.setNumeropago(j);
			//valor
			tfaccuentasxcobrar.setValor(valorpagar);
			//saldo
			tfaccuentasxcobrar.setSaldo(tfaccuentasxcobrar.getValor());
			suma=suma.add(valorpagar);
			//valor factura
			tfaccuentasxcobrar.setValorfactura(facturaDataManager.getTfaccabfactura().getTotal());
			//estado
			tfaccuentasxcobrar.setEstado("P");
			tfaccuentasxcobrar.setEstadocodigo(ContenidoMessages.getInteger("cod_catalogo_estado_cuentaxcobrar"));//32
			//tipo documento
			tfaccuentasxcobrar.setTipodoc("FAC");
			tfaccuentasxcobrar.setTipodoccodigo(ContenidoMessages.getInteger("cod_tipo_doc_cuentaxcobrar"));//32
			//fecha de la factura
			tfaccuentasxcobrar.setFecharegistro(facturaDataManager.getTfaccabfactura().getFechafactura());
			//fecha de vence
			Date fechavence= new Date();
			fechavence.setMonth(fechavence.getMonth()+j);
			tfaccuentasxcobrar.setFechavence(fechavence);
			
			Date fechaemision= new Date();
			if(j>1){
				fechaemision.setMonth(fechaemision.getMonth()+(j-1));
			}
			fechaemision.setDate(fechaemision.getDate()+1);
			//fecha de emision
			tfaccuentasxcobrar.setFechaemision(fechaemision);
			facturaDataManager.getTfaccabfactura().getTfaccuentasxcobrars().add(tfaccuentasxcobrar);
		}
		Tfaccuentasxcobrar tfaccuentasxcobrar= new Tfaccuentasxcobrar();
		//codigo compania
		tfaccuentasxcobrar.getPk().setCodigocompania(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		//codigo cliente
		tfaccuentasxcobrar.setCodigocliente(facturaDataManager.getTfaccabfactura().getCodigocliente());
		//numero factura
		tfaccuentasxcobrar.setNumerofactura(facturaDataManager.getTfaccabfactura().getPk().getNumerofactura());
		//numero de pago
		tfaccuentasxcobrar.setNumeropago(facturaDataManager.getNumeropagos());
		//valor
		tfaccuentasxcobrar.setValor(facturaDataManager.getTfaccabfactura().getTotal().subtract(suma));
		//saldo
		tfaccuentasxcobrar.setSaldo(tfaccuentasxcobrar.getValor());
		//valor factura
		tfaccuentasxcobrar.setValorfactura(facturaDataManager.getTfaccabfactura().getTotal());
		//estado
		tfaccuentasxcobrar.setEstado("P");
		//tipo documento
		tfaccuentasxcobrar.setTipodoc("FAC");
		tfaccuentasxcobrar.setTipodoccodigo(ContenidoMessages.getInteger("cod_tipo_doc_cuentaxcobrar"));
		//fecha de la factura
		tfaccuentasxcobrar.setFecharegistro(facturaDataManager.getTfaccabfactura().getFechafactura());
		//fecha de pago
		Date fechavence= new Date();
		fechavence.setMonth(fechavence.getMonth()+facturaDataManager.getNumeropagos());
		tfaccuentasxcobrar.setFechavence(fechavence);
		//fecha de emision
		Date fechaemision= new Date();
		fechaemision.setMonth(fechaemision.getMonth()+(facturaDataManager.getNumeropagos()-1));
		fechaemision.setDate(fechaemision.getDate()+1);
		//fecha de emision
		tfaccuentasxcobrar.setFechaemision(fechaemision);
		tfaccuentasxcobrar.setEstadocodigo(ContenidoMessages.getInteger("cod_catalogo_estado_cuentaxcobrar"));//32
		
		facturaDataManager.getTfaccabfactura().getTfaccuentasxcobrars().add(tfaccuentasxcobrar);
	}
	
	/**
	 * Metodo para agregar los objetos de forma de pago en efectivo
	 */
	private void pagoEfectivo(){
		Tfacformapago efectivo=null;
		Tfacformapago cheque=null;
		Tfacformapago transferencia=null;
		Tfacformapago tarjetacredito=null;
		if(facturaDataManager.getEfectivo()!=null && facturaDataManager.getEfectivo().compareTo(new BigDecimal(0))>0){
			efectivo=formaPago("EF",facturaDataManager.getEfectivo());
			facturaDataManager.getTfaccabfactura().getTfacformapagos().add(efectivo);
		}
		if(facturaDataManager.getCheque()!=null && facturaDataManager.getCheque().compareTo(new BigDecimal(0))>0){
			cheque=formaPago("CH",facturaDataManager.getCheque());
			if(facturaDataManager.getInstitucionCheque()!=null && facturaDataManager.getInstitucionCheque().trim().length()>0){
				cheque.setInstitucion(facturaDataManager.getInstitucionCheque());
				cheque.setInstitucioncodigo(ContenidoMessages.getInteger("cod_catalogo_institucion_bancaria"));//29
			}
			facturaDataManager.getTfaccabfactura().getTfacformapagos().add(cheque);
		}
		if(facturaDataManager.getTransferencia()!=null && facturaDataManager.getTransferencia().compareTo(new BigDecimal(0))>0){
			transferencia=formaPago("TF",facturaDataManager.getTransferencia());
			if(facturaDataManager.getInstitucionTransferencia()!=null && facturaDataManager.getInstitucionTransferencia().trim().length()>0){
				transferencia.setInstitucion(facturaDataManager.getInstitucionTransferencia());
				transferencia.setInstitucioncodigo(ContenidoMessages.getInteger("cod_catalogo_institucion_bancaria"));//29
			}
			facturaDataManager.getTfaccabfactura().getTfacformapagos().add(transferencia);
		}
		if(facturaDataManager.getTarjetaCredito()!=null && facturaDataManager.getTarjetaCredito().compareTo(new BigDecimal(0))>0){
			tarjetacredito=formaPago("TJ",facturaDataManager.getTarjetaCredito());
			if(facturaDataManager.getInstitucionTarjetaCredito()!=null && facturaDataManager.getInstitucionTarjetaCredito().trim().length()>0){
				tarjetacredito.setInstitucion(facturaDataManager.getInstitucionTarjetaCredito());
				tarjetacredito.setInstitucioncodigo(ContenidoMessages.getInteger("cod_catalogo_institucion_bancaria"));//29
			}
			facturaDataManager.getTfaccabfactura().getTfacformapagos().add(tarjetacredito);
		}
	}
	
	/**
	 * Metodo para obtener el objeto Tfacformapago segun 
	 * como pague el cliente en efectivo (efectivo, cheque tarjeta,transferencia)
	 * @param tipoPago
	 * @param pago
	 * @return Tfacformapago
	 */
	private Tfacformapago formaPago(String tipoPago, BigDecimal pago){
		Tfacformapago tfacformapago=new Tfacformapago();
		//compania
		tfacformapago.getPk().setCodigocompania(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		//numero factura
		tfacformapago.setNumerofactura(facturaDataManager.getTfaccabfactura().getPk().getNumerofactura());
		//forma de pago
		tfacformapago.setTipoformapago(tipoPago);
		tfacformapago.setTipoformapagocodigo(ContenidoMessages.getInteger("cod_catalogo_tipo_formapago"));//28
		//fecha
		tfacformapago.setFechapago(new Date());
		//nuero de documento
		//efectivo.setNumerodocumento(numerodocumento);
		//valor
		tfacformapago.setValor(pago);
		return tfacformapago;
	}
	
	/**
	 * Metodo que valida el pago en efectivo,
	 * que corresponda al total de la factura
	 * @return Boolean
	 */
	private Boolean validarValorCancelar(){
		BigDecimal valor=new BigDecimal(0);
		if(facturaDataManager.getEfectivo()!=null && facturaDataManager.getEfectivo().compareTo(new BigDecimal(0))>0){
			valor=valor.add(facturaDataManager.getEfectivo());
		}
		if(facturaDataManager.getCheque()!=null && facturaDataManager.getCheque().compareTo(new BigDecimal(0))>0){
			valor=valor.add(facturaDataManager.getCheque());
		}
		if(facturaDataManager.getTransferencia()!=null && facturaDataManager.getTransferencia().compareTo(new BigDecimal(0))>0){
			valor=valor.add(facturaDataManager.getTransferencia());
		}
		if(facturaDataManager.getTarjetaCredito()!=null && facturaDataManager.getTarjetaCredito().compareTo(new BigDecimal(0))>0){
			valor=valor.add(facturaDataManager.getTarjetaCredito());
		}
		//validamos si es igual al total
		if(round(valor).equals(facturaDataManager.getTfaccabfactura().getTotal())){
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo para cancelar la factura y regresa a los filtros
	 * de la factura
	 * @return
	 */
	public String cancelar(){
		inicializarFactura();
		return "/pages/factura/factura/buscarProformaFactura?faces-redirect=true";
	}
		
	/**
	 * inicializa los valores de la factura
	 */
	private void inicializarFactura(){
		//factura cabecera
		Tfaccabfactura tfaccabfactura = new Tfaccabfactura();
		//detalle factura
		tfaccabfactura.setTfacdetfacturas(new ArrayList<Tfacdetfactura>());
		facturaDataManager.setTfaccabfactura(tfaccabfactura);
		//cliente a seleccionar
		Tfaccliente tfaccliente = new Tfaccliente();
		tfaccliente.setTsyspersona(new Tsyspersona());
		facturaDataManager.setTfaccliente(tfaccliente);
		//articulo a seleccionar
		facturaDataManager.setTinvproducto(new Tinvproducto());
		//objeto detalle factura a agregar
		facturaDataManager.setTfacdetfactura(new Tfacdetfactura());
		//variables adicionales
		facturaDataManager.setFormaPago1(false);
		facturaDataManager.setFormaPago2(false);
	}
	
	/**
	 * Metodo para agregar un producto al detalle de la factura
	 */
	public void agregarProducto(){
		if(facturaDataManager.getTinvproducto()==null ||
				facturaDataManager.getTinvproducto().getPk()==null
				|| facturaDataManager.getTinvproducto().getPk().getCodigoproductos()==null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ContenidoMessages.getString("msg_error_seleccione_producto"), ContenidoMessages.getString("msg_error_seleccione_producto")));
			return;
		}
		if(facturaDataManager.getTfaccliente()==null || facturaDataManager.getTfaccliente().getPk()==null || facturaDataManager.getTfaccliente().getPk().getCodigocliente()==null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_error_sin_cliente"), ContenidoMessages.getString("msg_error_sin_cliente")));
			return;
		}
<<<<<<< HEAD
=======
		//Busqueda del pices type segun el tipo de unidad de venta del articulo
		Tadmconversionunidad tadmconversionunidad=bunsysService.conversionArticulo(facturaDataManager.getTinvproducto().getUnidadventacodigo(), facturaDataManager.getTinvproducto().getUnidadventa());
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
		//catidad
		facturaDataManager.getTfacdetfactura().setCantidad(new BigDecimal(1));
		facturaDataManager.getTfacdetfactura().getAditionalProperties().put("cantidadaux", 1d);
		//articulo
		facturaDataManager.getTfacdetfactura().setTinvproducto(facturaDataManager.getTinvproducto());
		facturaDataManager.getTfacdetfactura().setCodigoproductos(facturaDataManager.getTinvproducto().getPk().getCodigoproductos());
<<<<<<< HEAD
		//apta
		facturaDataManager.getTfacdetfactura().setAtpa(facturaDataManager.getTinvproducto().getAtpa());
		facturaDataManager.getTfacdetfactura().setAtpacodigo(facturaDataManager.getTinvproducto().getAtpacodigo());
		//unit price
		facturaDataManager.getTfacdetfactura().setPreciounitario(new BigDecimal(facturaDataManager.getTinvproducto().getPreciounitario()));
		//total price
		facturaDataManager.getTfacdetfactura().setTotal(facturaDataManager.getTfacdetfactura().getCantidad().multiply(facturaDataManager.getTfacdetfactura().getPreciounitario()));
=======
		//pices type
		facturaDataManager.getTfacdetfactura().setUnidadventa(facturaDataManager.getTinvproducto().getUnidadventa());
		facturaDataManager.getTfacdetfactura().setUnidadventacodigo(facturaDataManager.getTinvproducto().getUnidadventacodigo());
		//eq full boxes
		facturaDataManager.getTfacdetfactura().setEqfullboxes(new BigDecimal(tadmconversionunidad.getBoxes()));
		//apta
		facturaDataManager.getTfacdetfactura().setAtpa(facturaDataManager.getTinvproducto().getAtpa());
		facturaDataManager.getTfacdetfactura().setAtpacodigo(facturaDataManager.getTinvproducto().getAtpacodigo());
		//nanduna
		facturaDataManager.getTfacdetfactura().setNandina(facturaDataManager.getTinvproducto().getNandina());
		//steamsbunch
		facturaDataManager.getTfacdetfactura().setStemsbunch(new BigDecimal(tadmconversionunidad.getCantidadbunch()));
		//total bunch
		facturaDataManager.getTfacdetfactura().setTotalbunch(new BigDecimal(tadmconversionunidad.getTotalbunch()));
		//total stems
		facturaDataManager.getTfacdetfactura().setTotalstems(facturaDataManager.getTfacdetfactura().getStemsbunch().multiply(facturaDataManager.getTfacdetfactura().getTotalbunch()));
		//unit price
		facturaDataManager.getTfacdetfactura().setPreciounitario(new BigDecimal(facturaDataManager.getTinvproducto().getPreciounitario()));
		//total price
		facturaDataManager.getTfacdetfactura().setTotal(facturaDataManager.getTfacdetfactura().getTotalstems().multiply(facturaDataManager.getTfacdetfactura().getPreciounitario()));
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
		//compania
		facturaDataManager.getTfacdetfactura().getPk().setCodigocompania(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		//iva
		facturaDataManager.getTfacdetfactura().setIva(facturaDataManager.getTinvproducto().getIva());
		facturaDataManager.getTfacdetfactura().setIvacodigo(facturaDataManager.getTinvproducto().getIvacodigo());
		//ice
		facturaDataManager.getTfacdetfactura().setIce(facturaDataManager.getTinvproducto().getIce());
		facturaDataManager.getTfacdetfactura().setIcecodigo(facturaDataManager.getTinvproducto().getIcecodigo());
		//irbpnr
		facturaDataManager.getTfacdetfactura().setIrbpnr(facturaDataManager.getTinvproducto().getIrbpnr());
		facturaDataManager.getTfacdetfactura().setIrbpnrcodigo(facturaDataManager.getTinvproducto().getIrbpnrcodigo());
		//se aniade a la lista
		facturaDataManager.getTfaccabfactura().getTfacdetfacturas().add(facturaDataManager.getTfacdetfactura());
		
		calculos();
		facturaDataManager.setTinvproducto(new Tinvproducto());
		facturaDataManager.setTfacdetfactura(new Tfacdetfactura());
	}
	
	/**
	 * calculos totales de la cabecera de la factura
	 */
	public void calculos(){
<<<<<<< HEAD
=======
		facturaDataManager.getTfaccabfactura().setTotalpices(new BigDecimal(0));
		facturaDataManager.getTfaccabfactura().setTotaleqfullboxes(new BigDecimal(0));
		facturaDataManager.getTfaccabfactura().setTotalbunch(new BigDecimal(0));
		facturaDataManager.getTfaccabfactura().setTotalstems(new BigDecimal(0));
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
		facturaDataManager.getTfaccabfactura().setTotal(new BigDecimal(0));
		//
		facturaDataManager.getTfaccabfactura().setSubtotalnoiva(new BigDecimal(0));
		facturaDataManager.getTfaccabfactura().setSubtotaliva(new BigDecimal(0));
		facturaDataManager.getTfaccabfactura().setIva(new BigDecimal(0));
		facturaDataManager.getTfaccabfactura().setSubtotalexcentoiva(new BigDecimal(0));
		facturaDataManager.getTfaccabfactura().setSubtotalneto(new BigDecimal(0));
		for(Tfacdetfactura detalle:facturaDataManager.getTfaccabfactura().getTfacdetfacturas()){
<<<<<<< HEAD
=======
			//total pices
			facturaDataManager.getTfaccabfactura().setTotalpices(detalle.getCantidad().add(facturaDataManager.getTfaccabfactura().getTotalpices()));
			//total eqfullboxes
			facturaDataManager.getTfaccabfactura().setTotaleqfullboxes(detalle.getEqfullboxes().add(facturaDataManager.getTfaccabfactura().getTotaleqfullboxes()));
			//total bunch
			facturaDataManager.getTfaccabfactura().setTotalbunch(detalle.getTotalbunch().add(facturaDataManager.getTfaccabfactura().getTotalbunch()));
			//total stems
			facturaDataManager.getTfaccabfactura().setTotalstems(detalle.getTotalstems().add(facturaDataManager.getTfaccabfactura().getTotalstems()));
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
			//calculos
			//--subtotal 0%
			if(detalle.getIva().equals("0") && detalle.getIvacodigo().equals(9)){
				facturaDataManager.getTfaccabfactura().setSubtotalnoiva(facturaDataManager.getTfaccabfactura().getSubtotalnoiva().add(detalle.getTotal()));
			}
			//--sbtotal iva 12%
			if(detalle.getIva().equals("2") && detalle.getIvacodigo().equals(9)){
				facturaDataManager.getTfaccabfactura().setSubtotaliva(facturaDataManager.getTfaccabfactura().getSubtotaliva().add(detalle.getTotal()));
				//validamos si tiene descuento
				if(facturaDataManager.getTfaccliente().getPorcentajedescuento()!=null && facturaDataManager.getTfaccliente().getPorcentajedescuento()>0){
					//descuento =totaldeldetalle  * descuento del cliente%
					BigDecimal descuento=detalle.getTotal().multiply(new BigDecimal(facturaDataManager.getTfaccliente().getPorcentajedescuento()/100));
					//seteo del descuento por articulo descuento
					detalle.setDescuento(descuento);
					//se obtiene el iva sobre el articulo ya quitado el descuento(totaldetalle -descunto)
					BigDecimal totaldetallemenosdecuento=detalle.getTotal().subtract(descuento);
					Tadmcatalogo catalogoiva= bunsysService.obtenerCatalogo(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), detalle.getIvacodigo(), detalle.getIva());
					BigDecimal porcentajeIva=new BigDecimal(catalogoiva.getValor()).divide(new BigDecimal(100));
					//se obtiene el iva del producto (totaldetallemenosdecuento * iva 12%)
					BigDecimal iva=totaldetallemenosdecuento.multiply(porcentajeIva);
					//se suma los ivas que esten calculados menos el descuento
					facturaDataManager.getTfaccabfactura().setIva(facturaDataManager.getTfaccabfactura().getIva().add(iva));
				}else{
					Tadmcatalogo catalogoiva= bunsysService.obtenerCatalogo(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), detalle.getIvacodigo(), detalle.getIva());
					BigDecimal porcentajeIva=new BigDecimal(catalogoiva.getValor()).divide(new BigDecimal(100));
					//iva 12%=totaldetalle * 12%
					BigDecimal iva=detalle.getTotal().multiply(porcentajeIva);
					facturaDataManager.getTfaccabfactura().setIva(facturaDataManager.getTfaccabfactura().getIva().add(iva));
				}
			}
			//--subtotal excento de iva
			if(detalle.getIva().equals("7") && 	detalle.getIvacodigo().equals(9)){
				facturaDataManager.getTfaccabfactura().setSubtotalexcentoiva(detalle.getTotal());
			}
			//--subtotal neto
			facturaDataManager.getTfaccabfactura().setSubtotalneto(facturaDataManager.getTfaccabfactura().getSubtotalneto().add(detalle.getTotal()));
		}
		//calculos finales
		if(facturaDataManager.getTfaccliente().getPorcentajedescuento()!=null &&
				facturaDataManager.getTfaccliente().getPorcentajedescuento()>0){
			//-total descuento
			facturaDataManager.getTfaccabfactura().setTotaldescuento(facturaDataManager.getTfaccabfactura().getSubtotalneto().multiply(
					new BigDecimal(facturaDataManager.getTfaccliente().getPorcentajedescuento()/100)));
		}else{
			facturaDataManager.getTfaccabfactura().setTotaldescuento(new BigDecimal(0));
		}
		//total=totalneto-descuentos+iva
		BigDecimal total=facturaDataManager.getTfaccabfactura().getSubtotalneto().subtract(facturaDataManager.getTfaccabfactura().getTotaldescuento()).add(facturaDataManager.getTfaccabfactura().getIva());
		facturaDataManager.getTfaccabfactura().setTotal(round(total));
	}
	
<<<<<<< HEAD
=======
	/**
	 * Calculos para cuando del catalogo el tipo de piezas QB HB
	 * @param detalle
	 */
	public void cambioPicesType(Tfacdetfactura detalle){
		//Busqueda del pices type segun el tipo de unidad de venta del articulo
		Tadmconversionunidad tadmconversionunidad= bunsysService.conversionArticulo(detalle.getUnidadventacodigo(), detalle.getUnidadventa());
		//pices type
		//proformaDatamanager.getdetalle().setUnidadventa(proformaDatamanager.getTinvproducto().getUnidadventa());
		//eq full boxes
		detalle.setEqfullboxes(new BigDecimal(tadmconversionunidad.getBoxes()));
		//steamsbunch
		detalle.setStemsbunch(new BigDecimal(tadmconversionunidad.getCantidadbunch()));
		//total bunch
		detalle.setTotalbunch(new BigDecimal(tadmconversionunidad.getTotalbunch()));
		//total stems
		detalle.setTotalstems(detalle.getStemsbunch().multiply(detalle.getTotalbunch()));
		//unit price
		detalle.setPreciounitario(detalle.getPreciounitario());
		//total price
		detalle.setTotal(detalle.getTotalstems().multiply(detalle.getPreciounitario()));
		calculos();
	}
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
	
	/**
	 * Calculos cuando se cambia la cantidad del articulo
	 * @param detalle
	 */
	public void cambioCantidad(Tfacdetfactura detalle){
		//seteo
<<<<<<< HEAD
		//calculos
		//total price = total * preciounitario
		detalle.setTotal(detalle.getCantidad().multiply(detalle.getPreciounitario()));
=======
		//eq full boxes=cantidad *eqfullboxes
		if(detalle.getCantidad().compareTo(new BigDecimal(detalle.getAditionalProperties().get("cantidadaux").toString()))>0){
			//eqfullboxes= catidad * la cantidad
			detalle.setEqfullboxes(detalle.getEqfullboxes().multiply(detalle.getCantidad()));
			//total bunch = cantidad * totalbunch
			detalle.setTotalbunch(detalle.getTotalbunch().multiply(detalle.getCantidad()));
		}else if(detalle.getCantidad().compareTo(new BigDecimal(detalle.getAditionalProperties().get("cantidadaux").toString()))<0){
			detalle.setEqfullboxes(detalle.getEqfullboxes().divide(new BigDecimal(detalle.getAditionalProperties().get("cantidadaux").toString())));
			//total bunch
			detalle.setTotalbunch(detalle.getTotalbunch().divide(new BigDecimal(detalle.getAditionalProperties().get("cantidadaux").toString())));
		}
		//calculos
		//total stems
		detalle.setTotalstems(detalle.getStemsbunch().multiply(detalle.getTotalbunch()));
		//total price = totalstems * preciounitario
		detalle.setTotal(detalle.getTotalstems().multiply(detalle.getPreciounitario()));
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
		calculos();
		detalle.getAditionalProperties().put("cantidadaux",detalle.getCantidad());
	}

<<<<<<< HEAD
	
	public void cambiounitprice(Tfacdetfactura detalle){
		detalle.setTotal(detalle.getCantidad().multiply(detalle.getPreciounitario()));
=======
	public void cambioeqfullboxes(Tfacdetfactura detalle){
		calculos();
	}
	
	public void cambioStemsBunch(Tfacdetfactura detalle){
		//total stems= stembunch * totalbunch
		detalle.setTotalstems(detalle.getStemsbunch().multiply(detalle.getTotalbunch()));
		//total price = totalstems * preciounitario
		detalle.setTotal(detalle.getTotalstems().multiply(detalle.getPreciounitario()));
		calculos();
	}
	
	public void cambiototalBunch(Tfacdetfactura detalle){
		if(detalle.getAditionalProperty("aux")==null){
			detalle.addAditionalProperty("aux", 1);
			detalle.addAditionalProperty("totalbunchaux", detalle.getTotalbunch());
			detalle.addAditionalProperty("totalstemsaux", detalle.getTotalstems());
			detalle.addAditionalProperty("totalaux", detalle.getTotal());
		}else{
			detalle.setTotalbunch(new BigDecimal(detalle.getAditionalProperty("totalbunchaux").toString()));
			detalle.setTotalstems(new BigDecimal(detalle.getAditionalProperty("totalstemsaux").toString()));
			detalle.setTotal(new BigDecimal(detalle.getAditionalProperty("totalaux").toString()));
		}
		if(detalle.getCajas()!=null && detalle.getCajas().compareTo(new BigDecimal(0))>0){
			//	Bunch box * Total pices * Steam bunch= Total Steams
			detalle.setTotalstems(detalle.getCajas().multiply(detalle.getCantidad()).multiply(detalle.getStemsbunch()));
			//total bunch = bunchbox * total pices
			detalle.setTotalbunch(detalle.getCajas().multiply(detalle.getCantidad()));
					
//			//totalbunch * cajas
//			detalle.setTotalbunch(detalle.getTotalbunch().multiply(detalle.getCajas()));
//			//total stems= stembunch * totalbunch
//			detalle.setTotalstems(detalle.getStemsbunch().multiply(detalle.getTotalbunch()));
			
			//total price = totalstems * preciounitario
			detalle.setTotal(detalle.getTotalstems().multiply(detalle.getPreciounitario()));

		}
		calculos();
	}
	
	public void cambiototalSteams(Tfacdetfactura detalle){
		//total price = totalstems * preciounitario
		detalle.setTotal(detalle.getTotalstems().multiply(detalle.getPreciounitario()));
		calculos();
	}
	
	public void cambiounitprice(Tfacdetfactura detalle){
		detalle.setTotal(detalle.getTotalstems().multiply(detalle.getPreciounitario()));
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
		calculos();
	}
	
	public void cambiototal(Tfacdetfactura detalle){
		calculos();
	}
	
	public void seleccionarAerolinea(){
		facturaDataManager.getTfaccabfactura().addAditionalProperty("codaerolinea", "");
<<<<<<< HEAD
//		for(Tadmcatalogo item:facturaDataManager.getCatalogodistritovuelo()){
//			if(item.getPk().getCodigocatalogo().equals(facturaDataManager.getTfaccabfactura().getDistritovuelo())){
//				facturaDataManager.getTfaccabfactura().addAditionalProperty("codaerolinea", item.getValor()+"-");
//			}
//		}
=======
		for(Tadmcatalogo item:facturaDataManager.getCatalogodistritovuelo()){
			if(item.getPk().getCodigocatalogo().equals(facturaDataManager.getTfaccabfactura().getDistritovuelo())){
				facturaDataManager.getTfaccabfactura().addAditionalProperty("codaerolinea", item.getValor()+"-");
			}
		}
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
	}
	/**
	 * Metodo para eliminar un registro del detalle de la factura
	 * @param tfacdetfactura
	 */
	public void eliminarArticulo(Tfacdetfactura detalle){
		if(detalle.getPk().getCodigodetfactura()!=null){
			facturaDataManager.getDetfacturaEliminar().add(detalle);
		}
		facturaDataManager.getTfaccabfactura().getTfacdetfacturas().remove(detalle);
		calculos();
	}
	
	public Boolean validacionesGrabar(){
		//validamos que este la factura
		if(facturaDataManager.getTfaccabfactura()==null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_error_datos_fatura"), ContenidoMessages.getString("msg_error_datos_fatura")));
			return false;
		}
		if(facturaDataManager.getTfaccliente()==null || facturaDataManager.getTfaccliente().getPk()==null || facturaDataManager.getTfaccliente().getPk().getCodigocliente()==null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_error_sin_cliente"), ContenidoMessages.getString("msg_error_sin_cliente")));
			return false;
		}
		if(facturaDataManager.getTfaccabfactura().getTfacdetfacturas()==null || facturaDataManager.getTfaccabfactura().getTfacdetfacturas().size()==0){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_error_sin_detalle"), ContenidoMessages.getString("msg_error_sin_detalle")));
			return false;
		}
<<<<<<< HEAD
		
		if(StringUtils.isNotBlank(facturaDataManager.getTfaccabfactura().getFob())){
			facturaDataManager.getTfaccabfactura().setFobcodigo(ContenidoMessages.getInteger("cod_catalogo_tipo_fob"));
		}
=======
		if(StringUtils.isNotBlank(facturaDataManager.getTfaccabfactura().getAirline())){
			facturaDataManager.getTfaccabfactura().setAirlinecodigo(ContenidoMessages.getInteger("cod_catalogo_aerolineas"));
		}
		if(StringUtils.isNotBlank(facturaDataManager.getTfaccabfactura().getFob())){
			facturaDataManager.getTfaccabfactura().setFobcodigo(ContenidoMessages.getInteger("cod_catalogo_tipo_fob"));
		}
		if(StringUtils.isNotBlank(facturaDataManager.getTfaccabfactura().getCarguera())){
			facturaDataManager.getTfaccabfactura().setCargueracodigo(ContenidoMessages.getInteger("cod_catalogo_cargueras"));
		}
		if(StringUtils.isNotBlank(facturaDataManager.getTfaccabfactura().getDistritovuelo())){
			facturaDataManager.getTfaccabfactura().setDistritovuelocodigo(ContenidoMessages.getInteger("cod_catalogo_distrito_vuelo"));
		}
		
		facturaDataManager.getTfaccabfactura().setReferendo(facturaDataManager.getTfaccabfactura().getAditionalProperty("codaerolinea")+facturaDataManager.getTfaccabfactura().getReferendo());
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
		//compania
		facturaDataManager.getTfaccabfactura().getPk().setCodigocompania(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		//cliente
		facturaDataManager.getTfaccabfactura().setCodigocliente(facturaDataManager.getTfaccliente().getPk().getCodigocliente());
		//inicializa las listas de las formas de pago
		facturaDataManager.getTfaccabfactura().setTfacformapagos(new ArrayList<Tfacformapago>());
		facturaDataManager.getTfaccabfactura().setTfaccuentasxcobrars(new ArrayList<Tfaccuentasxcobrar>());
		///forma de pago
		if(facturaDataManager.isFormaPago1()){//efectivo
			if(!validarValorCancelar()){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_error_verifique_pago"), ContenidoMessages.getString("msg_error_verifique_pago")));
				return false;
			}
			pagoEfectivo();
		}else if(facturaDataManager.isFormaPago2()){//credito
			if(facturaDataManager.getNumeropagos()!=null){
				pagoCredito();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_error_numero_pagos"), ContenidoMessages.getString("msg_error_numero_pagos")));
				return false;
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_error_seleccione_pago"), ContenidoMessages.getString("msg_error_seleccione_pago")));
			return false;
		}
		return true;
	}
	
	
	public void grabarSinFirma(){
		try{
			//verifica los datos y la forma de pago
			if(validacionesGrabar()){
				facturaDataManager.getTfaccabfactura().setEstadosri("SF");
				facturaDataManager.getTfaccabfactura().setEstadosricodigo(ContenidoMessages.getInteger("cod_catalogo_estado_factura_sri"));
				bunsysService.grabarFactura(facturaDataManager.getTfaccabfactura(),facturaDataManager.getAccionAux(),facturaDataManager.getDetfacturaEliminar(),facturaDataManager.getTadmcompania(),facturaDataManager.getTfaccliente());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_info_factura"), ContenidoMessages.getString("msg_info_factura")));	
			}else{
				return;
			}
		} catch(Throwable e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ContenidoMessages.getString("msg_error_factura"), ContenidoMessages.getString("msg_error_factura")));
		}
	}
	
	public void grabarFirmarEnviar(){
		try{
			if(!coneccionsri()){
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR DE CONECCION", "ERROR DE CONECCION"));
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('dialogGrabar').show();");
				return;
			}
			if(facturaDataManager.getPassword()!=null && facturaDataManager.getPassword().trim().length()>0){
				facturaDataManager.getTfaccabfactura().addAditionalProperty("passwordToken", facturaDataManager.getPassword());
			}
			//verifica los datos y la forma de pago
			if(validacionesGrabar()){
				facturaDataManager.getTfaccabfactura().setEstadosri("FE");
				facturaDataManager.getTfaccabfactura().setEstadosricodigo(ContenidoMessages.getInteger("cod_catalogo_estado_factura_sri"));
				ResponseServiceDto responseServiceDto=bunsysService.grabarFactura(facturaDataManager.getTfaccabfactura(),facturaDataManager.getAccionAux(),facturaDataManager.getDetfacturaEliminar(),facturaDataManager.getTadmcompania(),facturaDataManager.getTfaccliente());
				StringBuilder mensajes=new StringBuilder(responseServiceDto.getEstado()+"  ");
				for(String mensaje:responseServiceDto.getMensajes()){
					mensajes.append(mensaje);
				}
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensajes.toString(), mensajes.toString()));	
			}else{
				return;
			}
		} catch(Throwable e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ContenidoMessages.getString("msg_error_factura")+"  "+e.getMessage(), ContenidoMessages.getString("msg_error_factura")+"  "+e.getMessage()));
		}
		//reporte
		//this.pdf( facturaDataManager.getTfaccabfactura(), facturaDataManager.getTfaccliente(), facturaDataManager.getTadmcompania(), "C:/Users/LuisH/Desktop/RESPALDO FACTURAEL/facturas/CO");
	}
	
	public void firmarEnviar(){
		System.out.println("Firmar Enviar");
		if(!coneccionsri()){
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR DE CONECCION", "ERROR DE CONECCION"));
			return;
		}
		if(facturaDataManager.getPassword()!=null && facturaDataManager.getPassword().trim().length()>0){
			facturaDataManager.getTfaccabfactura().addAditionalProperty("passwordToken", facturaDataManager.getPassword());
		}
		//verifica los datos y la forma de pago
		if(validacionesGrabar()){
			facturaDataManager.getTfaccabfactura().setEstadosri("FE");
			facturaDataManager.getTfaccabfactura().setEstadosricodigo(ContenidoMessages.getInteger("cod_catalogo_estado_factura_sri"));
			ResponseServiceDto responseServiceDto=bunsysService.grabarFactura(facturaDataManager.getTfaccabfactura(),facturaDataManager.getAccionAux(),facturaDataManager.getDetfacturaEliminar(),facturaDataManager.getTadmcompania(),facturaDataManager.getTfaccliente());
			StringBuilder mensajes=new StringBuilder(responseServiceDto.getEstado()+"  ");
			for(String mensaje:responseServiceDto.getMensajes()){
				mensajes.append(mensaje);
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensajes.toString(), mensajes.toString()));	
		}
	}
	
	public void grabarContingencia(){
		try{
			if(facturaDataManager.getPassword()!=null && facturaDataManager.getPassword().trim().length()>0){
				facturaDataManager.getTfaccabfactura().addAditionalProperty("passwordToken", facturaDataManager.getPassword());
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "INGRESE LA CLAVE", "INGRESE LA CLAVE"));
				return;
			}
			//verifica los datos y la forma de pago
			if(validacionesGrabar()){
				facturaDataManager.getTfaccabfactura().setEstadosri("CO");
				facturaDataManager.getTfaccabfactura().setEstadosricodigo(ContenidoMessages.getInteger("cod_catalogo_estado_factura_sri"));
				ResponseServiceDto responseServiceDto=  bunsysService.grabarFactura(facturaDataManager.getTfaccabfactura(),facturaDataManager.getAccionAux(),facturaDataManager.getDetfacturaEliminar(),facturaDataManager.getTadmcompania(),facturaDataManager.getTfaccliente());
				StringBuilder mensajes=new StringBuilder();
				if(responseServiceDto!=null && responseServiceDto.getMensajes()!=null){
					for(String mensaje:responseServiceDto.getMensajes()){
						mensajes.append(mensaje);
					}
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,mensajes.toString(), mensajes.toString()));	
				}
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Se Guarda la FActura", "Se Guarda la FActura"));	
			}else{
				return;
			}
		} catch(Throwable e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ContenidoMessages.getString("msg_error_factura"), ContenidoMessages.getString("msg_error_factura")));
		}
	}	
	
	
	public void buscarProforma(){
		try {
			System.out.println("numero proforma.."+facturaDataManager.getNumeroproforma());
			facturaDataManager.setEditable(Boolean.FALSE);
			facturaDataManager.setAccionAux("G");
			if(facturaDataManager.getNumeroproforma()!=null && facturaDataManager.getNumeroproforma().trim().length()>0){
				List<Tfaccabproforma> proformas=bunsysService.cabeceraProformas(facturaDataManager.getNumeroproforma());
				if(proformas!=null && proformas.size()>0){
					selecionaProforma(proformas.get(0));
				}else{
					inicializarFactura();
				}
			}else{
				inicializarFactura();
			}
		} catch (FacturacionException e) {
			e.printStackTrace();
		}
	}
	
	private String selecionaProforma(Tfaccabproforma tfaccabproforma){
		try {
			//factura cabecera
			Tfaccabfactura tfaccabfactura = new Tfaccabfactura();
			//numero proforma
			tfaccabfactura.setNumeroproforma(tfaccabproforma.getPk().getNumeroproforma());
			//cliente
			Tfaccliente tfaccliente = new Tfaccliente();
			tfaccliente.setTsyspersona(new Tsyspersona());
			tfaccabfactura.setTfaccliente(tfaccliente);
<<<<<<< HEAD
=======
			//aerolinea
			tfaccabfactura.setTadmairline(new Tadmcatalogo());
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
			//detalle factura
			tfaccabfactura.setTfacdetfacturas(new ArrayList<Tfacdetfactura>());
			facturaDataManager.setTfaccabfactura(tfaccabfactura);
			
			//proforma
			tfaccabproforma.setTfacdetproformas(new ArrayList<Tfacdetproforma>());
			tfaccabproforma.setTfacdetproformas(bunsysService.detalleProformas(tfaccabproforma.getPk().getNumeroproforma()));
			//cliente
			facturaDataManager.setTfaccliente(tfaccabproforma.getTfaccliente());
			//seteamos la cabecera de la FACTURA
			facturaDataManager.getTfaccabfactura().getPk().setCodigocompania(tfaccabproforma.getPk().getCodigocompania());
<<<<<<< HEAD
=======
			//aerolinea
			facturaDataManager.getTfaccabfactura().setAirline(tfaccabproforma.getAirline());
			facturaDataManager.getTfaccabfactura().setAirlinecodigo(tfaccabproforma.getAirlinecodigo());
			facturaDataManager.getTfaccabfactura().setTadmairline(tfaccabproforma.getTadmairline());
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
			//cliente de la factura
			facturaDataManager.getTfaccabfactura().setCodigocliente(tfaccabproforma.getCodigocliente());
			facturaDataManager.getTfaccabfactura().setTfaccliente(tfaccabproforma.getTfaccliente());
			//forma de pago
			if(tfaccabproforma.getTfaccliente()!=null){
				if(tfaccabproforma.getTfaccliente().getFormapago()!=null && tfaccabproforma.getTfaccliente().getFormapago().equals("C")){
					facturaDataManager.setFormaPago1(true);//efectivo
					facturaDataManager.setFormaPago2(false);//credito
				}else{
					facturaDataManager.setFormaPago1(false);//efectivo
					facturaDataManager.setFormaPago2(true);//credito
				}
			}
			
			facturaDataManager.getTfaccabfactura().setEstado("A");
			facturaDataManager.getTfaccabfactura().setEstadocodigo(tfaccabproforma.getEstadocodigo());
			
<<<<<<< HEAD
			facturaDataManager.getTfaccabfactura().setCountrycode(tfaccabproforma.getCountrycode());
			facturaDataManager.getTfaccabfactura().setArea(tfaccabproforma.getArea());
			
=======
			facturaDataManager.getTfaccabfactura().setFarmcode(tfaccabproforma.getFarmcode());
			facturaDataManager.getTfaccabfactura().setCountrycode(tfaccabproforma.getCountrycode());
			facturaDataManager.getTfaccabfactura().setArea(tfaccabproforma.getArea());
			facturaDataManager.getTfaccabfactura().setMasterawb(tfaccabproforma.getMasterawm());
			facturaDataManager.getTfaccabfactura().setHouseawb(tfaccabproforma.getHouseawb());
			facturaDataManager.getTfaccabfactura().setDae(tfaccabproforma.getDae());
			facturaDataManager.getTfaccabfactura().setConsignee(tfaccabproforma.getConsignee());
			facturaDataManager.getTfaccabfactura().setFixedprice(tfaccabproforma.getFixedprice());
			facturaDataManager.getTfaccabfactura().setReferendo(tfaccabproforma.getReferendum());
			
			facturaDataManager.getTfaccabfactura().setTotalpices(new BigDecimal(tfaccabproforma.getTotalpices()));
			facturaDataManager.getTfaccabfactura().setTotaleqfullboxes(new BigDecimal(tfaccabproforma.getTotaleqfullboxes()));
			facturaDataManager.getTfaccabfactura().setTotalbunch(new BigDecimal(tfaccabproforma.getTotalbunch()));
			facturaDataManager.getTfaccabfactura().setTotalstems(new BigDecimal(tfaccabproforma.getTotalstems()));
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
			facturaDataManager.getTfaccabfactura().setTotal(tfaccabproforma.getTotal());
			//subtotal
			facturaDataManager.getTfaccabfactura().setSubtotalneto(tfaccabproforma.getSubtotalneto());
			facturaDataManager.getTfaccabfactura().setSubtotaliva(tfaccabproforma.getSubtotaliva());
			facturaDataManager.getTfaccabfactura().setSubtotalbase(tfaccabproforma.getSubtotalbase());
			facturaDataManager.getTfaccabfactura().setSubtotalnoiva(tfaccabproforma.getSubtotalnoiva());
			//facturaDataManager.getTfaccabfactura().setSubtotalexcentoiva(tfaccabproforma.getsubtotalexcentoiva);
			facturaDataManager.getTfaccabfactura().setPorcentajedesc(tfaccabproforma.getPorcentajedesc());
			facturaDataManager.getTfaccabfactura().setTotaldescuento(tfaccabproforma.getTotaldescuento());
			facturaDataManager.getTfaccabfactura().setPorcentajeice(tfaccabproforma.getPorcentajeice());
			facturaDataManager.getTfaccabfactura().setValorice(tfaccabproforma.getValorice());
			facturaDataManager.getTfaccabfactura().setPorcentajeirbpnr(tfaccabproforma.getPocentajeirbpnr());
			facturaDataManager.getTfaccabfactura().setValorirbpnr(tfaccabproforma.getValorirbpnr());
			facturaDataManager.getTfaccabfactura().setPorcentajeiva(tfaccabproforma.getPorcentajeiva());
			facturaDataManager.getTfaccabfactura().setIva(tfaccabproforma.getIva());
			//numeroproforma 
	//		  fecha date,
			
			//seteamos el detalle
			facturaDataManager.getTfaccabfactura().setTfacdetfacturas(new ArrayList<Tfacdetfactura>());
			for(Tfacdetproforma detproforma: tfaccabproforma.getTfacdetproformas()){
				Tfacdetfactura detalleFactura= new Tfacdetfactura();
				detalleFactura.getPk().setCodigocompania(detproforma.getPk().getCodigocompania());
<<<<<<< HEAD
=======
				detalleFactura.setUnidadventa(detproforma.getUnidadventa());
				detalleFactura.setUnidadventacodigo(detproforma.getUnidadventacodigo());
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
				detalleFactura.setIva(detproforma.getIva());
				detalleFactura.setIvacodigo(detproforma.getIvacodigo());
				detalleFactura.setIce(detproforma.getIce());
				detalleFactura.setIcecodigo(detproforma.getIcecodigo());
				detalleFactura.setIrbpnr(detproforma.getIrbpnr());
				detalleFactura.setIrbpnrcodigo(detproforma.getIrbpnrcodigo());
				detalleFactura.setAtpa(detproforma.getAtpa());
				detalleFactura.setAtpacodigo(detproforma.getAtpacodigo());
				//producto
				detalleFactura.setCodigoproductos(detproforma.getCodigoproductos());
				detalleFactura.setTinvproducto(detproforma.getTinvproducto());
				
				detalleFactura.setCantidad(new BigDecimal(detproforma.getCantidad()));
				detalleFactura.getAditionalProperties().put("cantidadaux", detproforma.getCantidad());
				detalleFactura.setPreciounitario(new BigDecimal(detproforma.getPreciounitario()));
				detalleFactura.setDescuento(detproforma.getDescuento());
<<<<<<< HEAD
=======
				detalleFactura.setNandina(detproforma.getNandina());
				detalleFactura.setEqfullboxes(new BigDecimal(detproforma.getEqfullboxes()));
				detalleFactura.setStemsbunch(new BigDecimal(detproforma.getStemsbunch()));
				detalleFactura.setTotalbunch(new BigDecimal(detproforma.getTotalbunch()));
				detalleFactura.setTotalstems(new BigDecimal(detproforma.getTotalstems()));
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
				detalleFactura.setTotal(new BigDecimal(detproforma.getTotal()));
				  //numerofactura
				facturaDataManager.getTfaccabfactura().getTfacdetfacturas().add(detalleFactura);
			}
			return "/pages/factura/factura/factura?faces-redirect=true";
		} catch (FacturacionException e) {
			e.printStackTrace();
			return null;
		}
	}

<<<<<<< HEAD
=======
	
	
	
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
	public static BigDecimal round(BigDecimal d) {
		  int mode = BigDecimal.ROUND_UP ;
		  return d.setScale(2, mode);
	}
	
	public Boolean coneccionsri(){
		try {
			//URL urlWS = null;
			Tadmcatalogo urlAmbienterecepcion = null;
			
			if(facturaDataManager.getTadmcompania().getTipoambiente().equals(ConstantesSRI.COD_AMBIENTE_PRUEBAS)){
				System.out.println("Ambiente de PRUEBA");
				urlAmbienterecepcion	= bunsysService.obtenerCatalogo(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), 37, ConstantesSRI.COD_URL_RECEP_PRUEBAS);
			} else if (facturaDataManager.getTadmcompania().getTipoambiente().equals(ConstantesSRI.COD_AMBIENTE_PRODUCCION)) {
				System.out.println("Ambiente de PRODUCCION");
				urlAmbienterecepcion = bunsysService.obtenerCatalogo(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), 37, ConstantesSRI.COD_URL_RECEP_PRODUCCION);
			}
			//urlWS = new URL(urlAmbienterecepcion.getValor());
			Tadmcatalogo urlAmbiente = null;
			if(facturaDataManager.getTadmcompania().getTipoambiente().equals(ConstantesSRI.COD_AMBIENTE_PRUEBAS)){
				urlAmbiente	= bunsysService.obtenerCatalogo(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), 37, ConstantesSRI.COD_URL_AUT_PRUEBAS);
			} else if (facturaDataManager.getTadmcompania().getTipoambiente().equals(ConstantesSRI.COD_AMBIENTE_PRODUCCION)) {
				urlAmbiente = bunsysService.obtenerCatalogo(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), 37, ConstantesSRI.COD_URL_AUT_PRODUCCION);
			}
			//urlWS = new URL(urlAmbiente.getValor());
			String coneccion=HttpUtils.connectToServer(urlAmbiente.getValor(), 1000);//"https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantes?wsdl"
			if(coneccion==null){
				return false;
			}
			String coneccionrecepcion=HttpUtils.connectToServer(urlAmbienterecepcion.getValor(), 1000);//"https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantes?wsdl"
			if(coneccionrecepcion==null){
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}catch(Throwable e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public FacturaDataManager getFacturaDataManager() {
		return facturaDataManager;
	}

	public void setFacturaDataManager(FacturaDataManager facturaDataManager) {
		this.facturaDataManager = facturaDataManager;
	}

	public ClienteDatamanager getClienteDatamanager() {
		return clienteDatamanager;
	}

	public void setClienteDatamanager(ClienteDatamanager clienteDatamanager) {
		this.clienteDatamanager = clienteDatamanager;
	}

	public ArticuloDatamanager getArticuloDatamanager() {
		return articuloDatamanager;
	}

	public void setArticuloDatamanager(ArticuloDatamanager articuloDatamanager) {
		this.articuloDatamanager = articuloDatamanager;
	}

	@SuppressWarnings("static-access")
	private void pdf1(Tfaccabfactura tfaccabfactura, Tfaccliente cliente,Tadmcompania compania,String dirComprobante){
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
          param.put("razonProvee", compania.getRazonsocial());
          param.put("razonCliente", cliente.getTsyspersona().getNombres()+" "+cliente.getTsyspersona().getApellidos());
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
          if(tfaccabfactura.getSubtotaliva()!=null){
        	  param.put("totalGravDoce",tfaccabfactura.getSubtotaliva());// 
          }else{
        	  param.put("totalGravDoce",new BigDecimal(0));// new BigDecimal(0)
          }
          param.put("importeIva", new BigDecimal(0));
          param.put("total", tfaccabfactura.getTotal());
          //param.put("totalGravCero", new BigDecimal(0));
          param.put("numContribuyente", compania.getNombrecomercial());
<<<<<<< HEAD
=======
          param.put("master", tfaccabfactura.getMasterawb());
          param.put("house", tfaccabfactura.getHouseawb());
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
//          TadmcatalogoPK pk = new TadmcatalogoPK();
//		  pk.setCodigocatalogo(tfaccabfactura.getAirline());
//		  pk.setCodigocompania(tfaccabfactura.getPk().getCodigocompania());
//		  pk.setCodigotipocatalogo(tfaccabfactura.getAirlinecodigo());
//		  Tadmcatalogo aerolinea =facturaDao.findById(Tadmcatalogo.class,pk);
          param.put("airline", "");//aerolinea.getDescripcion()
<<<<<<< HEAD
          param.put("marcacion", "");
=======
          param.put("dae", tfaccabfactura.getReferendo());
          param.put("marcacion", "");
          param.put("consignatario", tfaccabfactura.getConsignee());
>>>>>>> 6aad317a82996c5469498a3307afc7abb7c3e40d
          //se procesa el archivo jasper
          jasperPrint = JasperFillManager.fillReport(jasperReport, param, this.createDatasourceDet(tfaccabfactura.getTfacdetfacturas()) );
          //se crea el archivo PDF
          JasperExportManager.exportReportToPdfFile(jasperPrint, dirComprobante + File.separator +  tfaccabfactura.getClaveacceso() +".pdf");
        }
        catch (JRException ex)
        {
          System.err.println( "Error iReport: " + ex.getMessage() );
        }
	}
	
	private static JRDataSource createDatasourceDet(Collection<Tfacdetfactura> detalleList){
		Collection<Object[]> c = new ArrayList<Object[]>();
		for(Tfacdetfactura item:detalleList){
			c.add(new Object[]{ Integer.parseInt(item.getCantidad().toString()),
					item.getTinvproducto().getNombre(),
					round(new BigDecimal(item.getTinvproducto().getPreciounitario())),
					round(item.getTotal()),
					Integer.parseInt(item.getTinvproducto().getPk().getCodigoproductos())});
		}
//		c.add(new Object[]{new Integer(2), "DES PRODUCTO 1",new BigDecimal(0.06),new BigDecimal(3.00),new Integer(200)});
		JRDataSource dt = new JRArrayDataSource(c);
		return dt;
	}
}
