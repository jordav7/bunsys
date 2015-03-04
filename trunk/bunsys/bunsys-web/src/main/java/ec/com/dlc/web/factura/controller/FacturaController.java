package ec.com.dlc.web.factura.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import ec.com.dlc.bunsys.entity.administracion.Tadmconversionunidad;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccuentasxcobrar;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfacformapago;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
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
			
			
			facturaDataManager.setAerolineasCatalogo(bunsysService.buscarObtenerCatalogos(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_aerolineas")));
			facturaDataManager.setCatalogoPicesType(bunsysService.buscarObtenerCatalogos(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_pices_type")));
			
			facturaDataManager.setInstitucion(bunsysService.buscarObtenerCatalogos(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
                    ContenidoMessages.getInteger("cod_catalogo_institucion_bancaria")));
		} catch (Throwable e) {
			e.getStackTrace();
		}
	}
	
	public void formaPagoEfectivo(){
		facturaDataManager.setFormaPago2(false);
		facturaDataManager.setFormaPago1(true);
	}
	
	public void formaPagoCheque(){
		facturaDataManager.setFormaPago1(false);
		facturaDataManager.setFormaPago2(true);
	}
	
	
	private void pagoCredito(){
		Double valorpagar=facturaDataManager.getTfaccabfactura().getTotal()/(facturaDataManager.getNumeropagos());
		Double suma=0d;
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
			tfaccuentasxcobrar.setValor(new BigDecimal(valorpagar));
			suma=suma+valorpagar;
			//valor factura
			tfaccuentasxcobrar.setValorfactura(new BigDecimal(facturaDataManager.getTfaccabfactura().getTotal()));
			//estado
			tfaccuentasxcobrar.setEstado("P");
			tfaccuentasxcobrar.setEstadocodigo(ContenidoMessages.getInteger("cod_catalogo_estado_cuentaxcobrar"));//32
			//fecha de pago
			Date fechapago= new Date();
			fechapago.setMonth(fechapago.getMonth()+j);
			tfaccuentasxcobrar.setFechapago(fechapago);
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
		tfaccuentasxcobrar.setValor(new BigDecimal(facturaDataManager.getTfaccabfactura().getTotal()-suma));
		//valor factura
		tfaccuentasxcobrar.setValorfactura(new BigDecimal(facturaDataManager.getTfaccabfactura().getTotal()));
		//estado
		tfaccuentasxcobrar.setEstado("P");
		//fecha de pago
		Date fechapago= new Date();
		fechapago.setMonth(fechapago.getMonth()+facturaDataManager.getNumeropagos());
		tfaccuentasxcobrar.setFechapago(fechapago);
		tfaccuentasxcobrar.setEstadocodigo(ContenidoMessages.getInteger("cod_catalogo_estado_cuentaxcobrar"));//32
		
		facturaDataManager.getTfaccabfactura().getTfaccuentasxcobrars().add(tfaccuentasxcobrar);
	}
	
	/**
	 * Metodo de formas de pago en efectivo
	 */
	private void pagoEfectivo(){
		Tfacformapago efectivo=null;
		Tfacformapago cheque=null;
		Tfacformapago transferencia=null;
		Tfacformapago tarjetacredito=null;
		if(facturaDataManager.getEfectivo()!=null && facturaDataManager.getEfectivo()>0){
			efectivo=formaPago("EF",facturaDataManager.getEfectivo());
		}
		if(facturaDataManager.getCheque()!=null && facturaDataManager.getCheque()>0){
			cheque=formaPago("CH",facturaDataManager.getCheque());
			if(facturaDataManager.getInstitucionCheque()!=null && facturaDataManager.getInstitucionCheque().trim().length()>0){
				cheque.setInstitucion(facturaDataManager.getInstitucionCheque());
				cheque.setInstitucioncodigo(ContenidoMessages.getInteger("cod_catalogo_institucion_bancaria"));//29
			}
		}
		if(facturaDataManager.getTransferencia()!=null && facturaDataManager.getTransferencia()>0){
			transferencia=formaPago("TF",facturaDataManager.getTransferencia());
			if(facturaDataManager.getInstitucionTransferencia()!=null && facturaDataManager.getInstitucionTransferencia().trim().length()>0){
				transferencia.setInstitucion(facturaDataManager.getInstitucionTransferencia());
				transferencia.setInstitucioncodigo(ContenidoMessages.getInteger("cod_catalogo_institucion_bancaria"));//29
			}
		}
		if(facturaDataManager.getTarjetaCredito()!=null && facturaDataManager.getTarjetaCredito()>0){
			tarjetacredito=formaPago("TJ",facturaDataManager.getTarjetaCredito());
			if(facturaDataManager.getInstitucionTarjetaCredito()!=null && facturaDataManager.getInstitucionTarjetaCredito().trim().length()>0){
				tarjetacredito.setInstitucion(facturaDataManager.getInstitucionTarjetaCredito());
				tarjetacredito.setInstitucioncodigo(ContenidoMessages.getInteger("cod_catalogo_institucion_bancaria"));//29
			}
		}
		if(efectivo!=null){
			facturaDataManager.getTfaccabfactura().getTfacformapagos().add(efectivo);
		}
		if(cheque!=null){
			facturaDataManager.getTfaccabfactura().getTfacformapagos().add(cheque);
		}
		if(transferencia!=null){
			facturaDataManager.getTfaccabfactura().getTfacformapagos().add(transferencia);
		}
		if(tarjetacredito!=null){
			facturaDataManager.getTfaccabfactura().getTfacformapagos().add(tarjetacredito);
		}
	}
	
	private Tfacformapago formaPago(String tipoPago, Double pago){
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
		tfacformapago.setValor(new BigDecimal(pago));
		return tfacformapago;
	}
	
	private Boolean validarValorCancelar(){
		Double valor=0d;
		if(facturaDataManager.getEfectivo()!=null && facturaDataManager.getEfectivo()>0){
			valor=valor+facturaDataManager.getEfectivo();
		}
		if(facturaDataManager.getCheque()!=null && facturaDataManager.getCheque()>0){
			valor=valor+facturaDataManager.getCheque();
		}
		if(facturaDataManager.getTransferencia()!=null && facturaDataManager.getTransferencia()>0){
			valor=valor+facturaDataManager.getTransferencia();
		}
		if(facturaDataManager.getTarjetaCredito()!=null && facturaDataManager.getTarjetaCredito()>0){
			valor=valor+facturaDataManager.getTarjetaCredito();
		}
		//validamos si es igual al total
		if(valor.equals(facturaDataManager.getTfaccabfactura().getTotal())){
			return true;
		}
		return false;
	}
	
	public String cancelar(){
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
		return "/pages/factura/factura/buscarProformaFactura?faces-redirect=true";
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
		//Busqueda del pices type segun el tipo de unidad de venta del articulo
		Tadmconversionunidad tadmconversionunidad=bunsysService.conversionArticulo(facturaDataManager.getTinvproducto().getUnidadventacodigo(), facturaDataManager.getTinvproducto().getUnidadventa());
		//articulo
		facturaDataManager.getTfacdetfactura().setTinvproducto(facturaDataManager.getTinvproducto());
		facturaDataManager.getTfacdetfactura().setCodigoproductos(facturaDataManager.getTinvproducto().getPk().getCodigoproductos());
		//pices type
		facturaDataManager.getTfacdetfactura().setUnidadventa(facturaDataManager.getTinvproducto().getUnidadventa());
		facturaDataManager.getTfacdetfactura().setUnidadventacodigo(facturaDataManager.getTinvproducto().getUnidadventacodigo());
		//eq full boxes
		facturaDataManager.getTfacdetfactura().setEqfullboxes(tadmconversionunidad.getBoxes());
		//apta
		facturaDataManager.getTfacdetfactura().setAtpa(facturaDataManager.getTinvproducto().getAtpa());
		facturaDataManager.getTfacdetfactura().setAtpacodigo(facturaDataManager.getTinvproducto().getAtpacodigo());
		//nanduna
		facturaDataManager.getTfacdetfactura().setNandina(facturaDataManager.getTinvproducto().getNandina());
		//steamsbunch
		facturaDataManager.getTfacdetfactura().setStemsbunch(tadmconversionunidad.getCantidadbunch());
		//total bunch
		facturaDataManager.getTfacdetfactura().setTotalbunch(tadmconversionunidad.getTotalbunch());
		//total stems
		facturaDataManager.getTfacdetfactura().setTotalstems(facturaDataManager.getTfacdetfactura().getStemsbunch()*facturaDataManager.getTfacdetfactura().getTotalbunch());
		//unit price
		facturaDataManager.getTfacdetfactura().setPreciounitario(facturaDataManager.getTinvproducto().getPreciounitario());
		//total price
		facturaDataManager.getTfacdetfactura().setTotal(facturaDataManager.getTfacdetfactura().getTotalstems()*facturaDataManager.getTfacdetfactura().getPreciounitario());
		//compania
		facturaDataManager.getTfacdetfactura().getPk().setCodigocompania(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		//se aniade a la lista
		facturaDataManager.getTfaccabfactura().getTfacdetfacturas().add(facturaDataManager.getTfacdetfactura());
		calculos();
		facturaDataManager.setTinvproducto(new Tinvproducto());
		facturaDataManager.setTfacdetfactura(new Tfacdetfactura());
	}
	
	/**
	 * calculos totales
	 */
	private void calculos(){
		facturaDataManager.getTfaccabfactura().setTotalpices(0d);
		facturaDataManager.getTfaccabfactura().setTotaleqfullboxes(0d);
		facturaDataManager.getTfaccabfactura().setTotalbunch(0d);
		facturaDataManager.getTfaccabfactura().setTotalstems(0d);
		facturaDataManager.getTfaccabfactura().setTotal(0d);
		for(Tfacdetfactura detalle:facturaDataManager.getTfaccabfactura().getTfacdetfacturas()){
			facturaDataManager.getTfaccabfactura().setTotalpices(detalle.getCantidad()+facturaDataManager.getTfaccabfactura().getTotalpices());
			facturaDataManager.getTfaccabfactura().setTotaleqfullboxes(detalle.getEqfullboxes()+facturaDataManager.getTfaccabfactura().getTotaleqfullboxes());
			
			facturaDataManager.getTfaccabfactura().setTotalbunch(detalle.getTotalbunch()+facturaDataManager.getTfaccabfactura().getTotalbunch());
			facturaDataManager.getTfaccabfactura().setTotalstems(detalle.getTotalstems()+facturaDataManager.getTfaccabfactura().getTotalstems());
			facturaDataManager.getTfaccabfactura().setTotal(detalle.getTotal()+facturaDataManager.getTfaccabfactura().getTotal());
		}
	}
	
	public void cambioPicesType(Tfacdetfactura detalle){
		//Busqueda del pices type segun el tipo de unidad de venta del articulo
		Tadmconversionunidad tadmconversionunidad= bunsysService.conversionArticulo(detalle.getUnidadventacodigo(), detalle.getUnidadventa());
		//pices type
		//proformaDatamanager.getdetalle().setUnidadventa(proformaDatamanager.getTinvproducto().getUnidadventa());
		//eq full boxes
		detalle.setEqfullboxes(tadmconversionunidad.getBoxes());
		//steamsbunch
		detalle.setStemsbunch(tadmconversionunidad.getCantidadbunch());
		//total bunch
		detalle.setTotalbunch(tadmconversionunidad.getTotalbunch());
		//total stems
		detalle.setTotalstems(detalle.getStemsbunch()*detalle.getTotalbunch());
		//unit price
		detalle.setPreciounitario(detalle.getPreciounitario());
		//total price
		detalle.setTotal(detalle.getTotalstems()*detalle.getPreciounitario());
		calculos();
	}
	
	public void cambioTotalPices(Tfacdetfactura detalle){
		calculos();
	}
	public void cambioeqfullboxes(Tfacdetfactura detalle){
		calculos();
	}
	public void cambioStemsBunch(Tfacdetfactura detalle){
		//total stems
		detalle.setTotalstems(detalle.getStemsbunch()*detalle.getTotalbunch());
		//total price
		detalle.setTotal(detalle.getTotalstems()*detalle.getPreciounitario());
		calculos();
	}
	
	public void cambiototalBunch(Tfacdetfactura detalle){
		//total stems
		detalle.setTotalstems(detalle.getStemsbunch()*detalle.getTotalbunch());
		//total price
		detalle.setTotal(detalle.getTotalstems()*detalle.getPreciounitario());
		calculos();
	}
	
	public void cambiototalSteams(Tfacdetfactura detalle){
		detalle.setTotal(detalle.getTotalstems()*detalle.getPreciounitario());
		calculos();
	}
	
	public void cambiounitprice(Tfacdetfactura detalle){
		detalle.setTotal(detalle.getTotalstems()*detalle.getPreciounitario());
		calculos();
	}
	
	public void cambiototal(Tfacdetfactura detalle){
		calculos();
	}
	/**
	 * Metodo para eliminar un registro del detalle de la factura
	 * @param tfacdetfactura
	 */
	public void eliminarArticulo(Tfacdetfactura detalle){
		facturaDataManager.getTfaccabfactura().getTfacdetfacturas().remove(detalle);
		calculos();
	}
	
	public void grabar(){
		try {//valida si es editar
			if(!facturaDataManager.getEditable()){
				//validamos que este la factura
				if(facturaDataManager.getTfaccabfactura()==null){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_error_datos_fatura"), ContenidoMessages.getString("msg_error_datos_fatura")));
					return;
				}
				if(facturaDataManager.getTfaccliente()==null || facturaDataManager.getTfaccliente().getPk()==null || facturaDataManager.getTfaccliente().getPk().getCodigocliente()==null){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_error_sin_cliente"), ContenidoMessages.getString("msg_error_sin_cliente")));
					return;
				}
				if(facturaDataManager.getTfaccabfactura().getTfacdetfacturas()==null || facturaDataManager.getTfaccabfactura().getTfacdetfacturas().size()==0){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_error_sin_detalle"), ContenidoMessages.getString("msg_error_sin_detalle")));
					return;
				}
				if(StringUtils.isNotBlank(facturaDataManager.getTfaccabfactura().getAirline())){
					facturaDataManager.getTfaccabfactura().setAirlinecodigo(ContenidoMessages.getInteger("cod_catalogo_aerolineas"));
				}
				//compania
				facturaDataManager.getTfaccabfactura().getPk().setCodigocompania(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
				//cliente
				facturaDataManager.getTfaccabfactura().setCodigocliente(facturaDataManager.getTfaccliente().getPk().getCodigocliente());
			}
			//verifica la forma de pago
			formaPagoGrabar();
			bunsysService.grabarFactura(facturaDataManager.getTfaccabfactura());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_info_factura"), ContenidoMessages.getString("msg_info_factura")));
		} catch(Throwable e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ContenidoMessages.getString("msg_error_factura"), ContenidoMessages.getString("msg_error_factura")));
		}
	}
	
	public void formaPagoGrabar(){
		System.out.println("INGRESA A GRABAR");
		facturaDataManager.getTfaccabfactura().setTfacformapagos(new ArrayList<Tfacformapago>());
		facturaDataManager.getTfaccabfactura().setTfaccuentasxcobrars(new ArrayList<Tfaccuentasxcobrar>());
		///forma de pago
		if(facturaDataManager.isFormaPago1()){//efectivo
			if(!validarValorCancelar()){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_error_verifique_pago"), ContenidoMessages.getString("msg_error_verifique_pago")));
				return;
			}
			pagoEfectivo();
		}else if(facturaDataManager.isFormaPago2()){//credito
			if(facturaDataManager.getNumeropagos()!=null){
				pagoCredito();
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_error_numero_pagos"), ContenidoMessages.getString("msg_error_numero_pagos")));
				return;
			}
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_error_seleccione_pago"), ContenidoMessages.getString("msg_error_seleccione_pago")));
			return;
		}
	}
	
	public void grabarProcesar(){
		grabar();
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
	
}
