package ec.com.dlc.web.factura.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.pk.TadmcompaniaPK;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfacformapago;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.bunsys.util.ComprobantesUtil;
import ec.com.dlc.bunsys.util.FacturacionException;
import ec.com.dlc.web.commons.resource.ContenidoMessages;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.factura.datamanager.BusquedaProformaFacturaDatamanager;
import ec.com.dlc.web.factura.datamanager.FacturaDataManager;

@ManagedBean
@ViewScoped
public class BusquedaProformaFacturaController extends BaseController {

	@ManagedProperty(value="#{busquedaProformaFacturaDatamanager}")
	private BusquedaProformaFacturaDatamanager busquedaProformaFacturaDatamanager;
	
	@ManagedProperty(value="#{facturaDataManager}")
	private FacturaDataManager facturaDataManager;
	
	@Inject
	private BunsysService bunsysService;
	
	@Override
	public BaseDatamanager getDatamanager() {
		return busquedaProformaFacturaDatamanager;
	}

	@Override
	public void inicializar() 
	{
		TadmcompaniaPK companiaPk= new TadmcompaniaPK();
		companiaPk.setCodigocompania(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		facturaDataManager.setTadmcompania(bunsysService.buscarCompania(companiaPk));
		
		busquedaProformaFacturaDatamanager.setTfaccabfacturasList(new ArrayList<Tfaccabfactura>());
		//catalogos
		busquedaProformaFacturaDatamanager.setTadmparamsriList(bunsysService.parametroSri(ContenidoMessages.getInteger("cod_catalogo_estado_factura_sri")));
	}
	
	public void listarfacturas(){
		try {
			System.out.println("numero proforma.."+busquedaProformaFacturaDatamanager.getNumerofactura());
			busquedaProformaFacturaDatamanager.setTfaccabfacturasList(
					bunsysService.cabeceraFacturas(busquedaProformaFacturaDatamanager.getNumerofactura(),busquedaProformaFacturaDatamanager.getCodigoparamsri(),
							busquedaProformaFacturaDatamanager.getFechamin(),busquedaProformaFacturaDatamanager.getFechamax()));
		} catch (FacturacionException e) {
			e.printStackTrace();
		}
	}
	
	public String crear() {
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
		facturaDataManager.setNumeropagos(null);
		facturaDataManager.setCheque(null);;
		facturaDataManager.setEfectivo(null);;
		facturaDataManager.setTransferencia(null);;
		facturaDataManager.setTarjetaCredito(null);;
		facturaDataManager.setInstitucionCheque(null);
		facturaDataManager.setInstitucionTransferencia(null);
		facturaDataManager.setInstitucionTarjetaCredito(null);
		facturaDataManager.setNumeroproforma(null);
	
		facturaDataManager.setAccionAux("G");
		facturaDataManager.setEditable(Boolean.FALSE);
		//numero de factura
		facturaDataManager.getTfaccabfactura().getPk().setNumerofactura(ComprobantesUtil.getInstancia().getsecuencia(
				Integer.parseInt(bunsysService.obtenerCatalogo(facturaDataManager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
						ContenidoMessages.getInteger("cod_catalogo_codigo_sec"),"01").getValor())+1+"",9));
		facturaDataManager.setDetfacturaEliminar(new ArrayList<Tfacdetfactura>());
		return "/pages/factura/factura/factura?faces-redirect=true";
	}
	
	public String selecionaFactura(Tfaccabfactura tfaccabfactura){
		try {
			//factura cabecera
			facturaDataManager.setTfaccabfactura(tfaccabfactura);
			//recorte
			String numero =facturaDataManager.getTfaccabfactura().getPk().getNumerofactura().replace(facturaDataManager.getTadmcompania().getCodigoestablecimiento(), "");
			numero=numero.replace(facturaDataManager.getTadmcompania().getCodigopuntoemision(), "");
			facturaDataManager.getTfaccabfactura().getPk().setNumerofactura(numero);
			
			if(facturaDataManager.getTfaccabfactura().getEstadosri().equals("SF")){
				facturaDataManager.setEditable(Boolean.TRUE);
			}else{
				facturaDataManager.setEditable(Boolean.FALSE);
			}
			
			facturaDataManager.setAccionAux("E");
			//detalle factura
			facturaDataManager.getTfaccabfactura().setTfacdetfacturas(bunsysService.detalleFacturas(tfaccabfactura.getPk().getNumerofactura()));
			for(Tfacdetfactura detalle: facturaDataManager.getTfaccabfactura().getTfacdetfacturas()){
				detalle.getAditionalProperties().put("cantidadaux",1d);
			}
			//cliente
			facturaDataManager.setTfaccliente(tfaccabfactura.getTfaccliente());
			//variables adicionales
			//consulta la forma de pago
			List<Tfacformapago> tfacformapagos =  bunsysService.tfacformapagos(facturaDataManager.getTfaccabfactura().getPk().getCodigocompania(), facturaDataManager.getTfaccabfactura().getPk().getNumerofactura());
			//forma de pago
				if(tfacformapagos!=null && tfacformapagos.size()>0){
					facturaDataManager.setFormaPago1(true);//efectivo
					facturaDataManager.setFormaPago2(false);//credito
					for(Tfacformapago formapago :tfacformapagos){
						if("EF".equals(formapago.getTipoformapago())){
							facturaDataManager.setEfectivo(formapago.getValor());
						}else if ("CH".equals(formapago.getTipoformapago())){
							facturaDataManager.setCheque(formapago.getValor());
							facturaDataManager.setInstitucionCheque(formapago.getInstitucion());
						}else if("TF".equals(formapago.getTipoformapago())){
							facturaDataManager.setTransferencia(formapago.getValor());
							facturaDataManager.setInstitucionTransferencia(formapago.getInstitucion());
						}else{
							facturaDataManager.setTarjetaCredito(formapago.getValor());
							facturaDataManager.setInstitucionTarjetaCredito(formapago.getInstitucion());
						}
					}
				}else{
					facturaDataManager.setFormaPago1(false);//efectivo
					facturaDataManager.setFormaPago2(true);//credito
					facturaDataManager.setNumeropagos(null);
				}
			facturaDataManager.setNumeroproforma(null);
			facturaDataManager.setDetfacturaEliminar(new ArrayList<Tfacdetfactura>());
			return "/pages/factura/factura/factura?faces-redirect=true";
		} catch (FacturacionException e) {
			e.printStackTrace();
			return null;
		}

	}


	public BunsysService getBunsysService() {
		return bunsysService;
	}

	public void setBunsysService(BunsysService bunsysService) {
		this.bunsysService = bunsysService;
	}

	public BusquedaProformaFacturaDatamanager getBusquedaProformaFacturaDatamanager() {
		return busquedaProformaFacturaDatamanager;
	}

	public void setBusquedaProformaFacturaDatamanager(
			BusquedaProformaFacturaDatamanager busquedaProformaFacturaDatamanager) {
		this.busquedaProformaFacturaDatamanager = busquedaProformaFacturaDatamanager;
	}

	public FacturaDataManager getFacturaDataManager() {
		return facturaDataManager;
	}

	public void setFacturaDataManager(FacturaDataManager facturaDataManager) {
		this.facturaDataManager = facturaDataManager;
	}


}
