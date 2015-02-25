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

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccuentasxcobrar;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfacformapago;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.commons.resource.ContenidoMessages;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.factura.datamanager.FacturaDataManager;

@ManagedBean(name="facturaController")
@ViewScoped

public class FacturaController extends BaseController implements Serializable{
	private static final long serialVersionUID = 1L;

	@Inject
	private BunsysService bunsysService;
	
	@ManagedProperty(value="#{facturaDataManager}")
	private FacturaDataManager facturaDataManager;
	
	@Override
	public BaseDatamanager getDatamanager() {
		return facturaDataManager;
	}
	
	@Override
	public void inicializar() {
		try {
			System.out.println("inicializa la factura");
			//cliente
			Tfaccabfactura tfaccabfactura = new Tfaccabfactura();
			Tfaccliente tfaccliente = new Tfaccliente();
			tfaccliente.setTsyspersona(new Tsyspersona());
			tfaccabfactura.setTfaccliente(tfaccliente);
			//aerolinea
			tfaccabfactura.setTadmairline(new Tadmcatalogo());
			facturaDataManager.setTfaccabfactura(tfaccabfactura);
			facturaDataManager.setTfaccabproformaList(new ArrayList<Tfaccabproforma>());
			facturaDataManager.setFormaPago1(false);
			facturaDataManager.setFormaPago2(false);
			facturaDataManager.setInstitucion(bunsysService.buscarObtenerCatalogos(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
                    ContenidoMessages.getInteger("cod_catalogo_institucion_bancaria")));
		} catch (Throwable e) {
			e.getStackTrace();
		}
	}

	public void listarProformas(){
		try {
			facturaDataManager.setTfaccabproformaList(bunsysService.cabeceraProformas(facturaDataManager.getNumeroproforma()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void selecionaProforma(Tfaccabproforma tfaccabproforma){
		tfaccabproforma.setTfacdetproformas(new ArrayList<Tfacdetproforma>());
		tfaccabproforma.setTfacdetproformas(bunsysService.detalleProformas(tfaccabproforma.getPk().getNumeroproforma()));
		
		//seteamos la cabecera
		facturaDataManager.getTfaccabfactura().getPk().setCodigocompania(tfaccabproforma.getPk().getCodigocompania());
		//aerolinea
		facturaDataManager.getTfaccabfactura().setAirline(tfaccabproforma.getAirline());
		facturaDataManager.getTfaccabfactura().setAirlinecodigo(tfaccabproforma.getAirlinecodigo());
		facturaDataManager.getTfaccabfactura().setTadmairline(tfaccabproforma.getTadmairline());
		//cliente
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
		
		facturaDataManager.getTfaccabfactura().setFarmcode(tfaccabproforma.getFarmcode());
		facturaDataManager.getTfaccabfactura().setCountrycode(tfaccabproforma.getCountrycode());
		facturaDataManager.getTfaccabfactura().setArea(tfaccabproforma.getArea());
		facturaDataManager.getTfaccabfactura().setMasterawb(tfaccabproforma.getMasterawm());
		facturaDataManager.getTfaccabfactura().setHouseawb(tfaccabproforma.getHouseawb());
		facturaDataManager.getTfaccabfactura().setDae(tfaccabproforma.getDae());
		facturaDataManager.getTfaccabfactura().setConsignee(tfaccabproforma.getConsignee());
		facturaDataManager.getTfaccabfactura().setFixedprice(tfaccabproforma.getFixedprice());
		facturaDataManager.getTfaccabfactura().setReferendo(tfaccabproforma.getReferendum());
		
		facturaDataManager.getTfaccabfactura().setTotalpices(tfaccabproforma.getTotalpices());
		facturaDataManager.getTfaccabfactura().setTotaleqfullboxes(tfaccabproforma.getTotaleqfullboxes());
		facturaDataManager.getTfaccabfactura().setTotalbunch(tfaccabproforma.getTotalbunch());
		facturaDataManager.getTfaccabfactura().setTotalstems(tfaccabproforma.getTotalstems());
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
			detalleFactura.getPk().setCodigocompania(detalleFactura.getPk().getCodigocompania());
			detalleFactura.setUnidadventa(detproforma.getUnidadventa());
			detalleFactura.setUnidadventacodigo(detproforma.getUnidadventacodigo());
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
			
			detalleFactura.setCantidad(detproforma.getCantidad());
			detalleFactura.setPreciounitario(detproforma.getPreciounitario());
			detalleFactura.setDescuento(detproforma.getDescuento());
			detalleFactura.setNandina(detproforma.getNandina());
			detalleFactura.setEqfullboxes(detproforma.getEqfullboxes());
			detalleFactura.setStemsbunch(detproforma.getStemsbunch());
			detalleFactura.setTotalbunch(detproforma.getTotalbunch());
			detalleFactura.setTotalstems(detproforma.getTotalstems());
			detalleFactura.setTotal(detproforma.getTotal());
			  //numerofactura
			facturaDataManager.getTfaccabfactura().getTfacdetfacturas().add(detalleFactura);
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
	
	public void grabar(){
		System.out.println("INGRESA A GRABAR");
		//validamos que este la factura
		if(facturaDataManager.getTfaccabfactura()==null ||
				facturaDataManager.getTfaccabfactura().getTfacdetfacturas()==null ||
				facturaDataManager.getTfaccabfactura().getTfacdetfacturas().size()==0){
			FacesContext.getCurrentInstance().addMessage
			(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SELECCIONE UNA PROFORMA", "SELECCIONE UNA PROFORMA"));
			return;
		}
		///forma de pago
		if(facturaDataManager.isFormaPago1()){//efectivo
			if(!validarValorCancelar()){
				FacesContext.getCurrentInstance().addMessage
				(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "VERIFIQUE EL VALOR A PAGAR", "VERIFIQUE EL VALOR A PAGAR"));
				return;
			}
			pagoEfectivo();
		}else{
			pagoCredito();
		}
		bunsysService.grabarFactura(facturaDataManager.getTfaccabfactura());
	}
	
	private void pagoCredito(){
		Double valorpagar=facturaDataManager.getTfaccabfactura().getTotal()/(facturaDataManager.getNumeropagos());
		facturaDataManager.getTfaccabfactura().setTfaccuentasxcobrars(new ArrayList<Tfaccuentasxcobrar>());
		Double suma=0d;
		for (int j = 1; j < facturaDataManager.getNumeropagos()-1; j++) {
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
			tfaccuentasxcobrar.setEstado("PEN");
			tfaccuentasxcobrar.setEstadocodigo(ContenidoMessages.getInteger("cod_catalogo_estado_cuentaxcobrar"));//32
			
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
		tfaccuentasxcobrar.setEstado("PEN");
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
		facturaDataManager.getTfaccabfactura().setTfacformapagos(new ArrayList<Tfacformapago>());
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
	
	public void cancelar(){
		//cliente
		Tfaccabfactura tfaccabfactura = new Tfaccabfactura();
		Tfaccliente tfaccliente = new Tfaccliente();
		tfaccliente.setTsyspersona(new Tsyspersona());
		tfaccabfactura.setTfaccliente(tfaccliente);
		//aerolinea
		tfaccabfactura.setTadmairline(new Tadmcatalogo());
		facturaDataManager.setTfaccabfactura(tfaccabfactura);
		facturaDataManager.setTfaccabproformaList(new ArrayList<Tfaccabproforma>());
		facturaDataManager.setFormaPago1(false);
		facturaDataManager.setFormaPago2(false);
	}
			
	public FacturaDataManager getFacturaDataManager() {
		return facturaDataManager;
	}

	public void setFacturaDataManager(FacturaDataManager facturaDataManager) {
		this.facturaDataManager = facturaDataManager;
	}
	
}
