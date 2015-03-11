package ec.com.dlc.web.factura.controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetproforma;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
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
	public void inicializar() {
		busquedaProformaFacturaDatamanager.setTfaccabfacturasList(new ArrayList<Tfaccabfactura>());
		//catalogos
		busquedaProformaFacturaDatamanager.setTadmparamsriList(bunsysService.parametroSri(ContenidoMessages.getInteger("cod_catalogo_estado_factura_sri")));
	}
	
	public void listarfacturas(){
		try {
			System.out.println("numero proforma.."+busquedaProformaFacturaDatamanager.getNumerofactura());
			busquedaProformaFacturaDatamanager.setTfaccabfacturasList(
					bunsysService.cabeceraFacturas(busquedaProformaFacturaDatamanager.getNumerofactura(),busquedaProformaFacturaDatamanager.getCodigoparamsri()));
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
		facturaDataManager.setAccionAux("G");
		facturaDataManager.setEditable(Boolean.FALSE);
		return "/pages/factura/factura/factura?faces-redirect=true";
	}
	
	public String selecionaFactura(Tfaccabfactura tfaccabfactura){
		try {
			//factura cabecera
			facturaDataManager.setTfaccabfactura(tfaccabfactura);
			facturaDataManager.setEditable(Boolean.TRUE);
			facturaDataManager.setAccionAux("E");
			//proforma
			tfaccabfactura.setTfacdetfacturas(bunsysService.detalleFacturas(tfaccabfactura.getPk().getNumerofactura()));
			//cliente
			facturaDataManager.setTfaccliente(tfaccabfactura.getTfaccliente());
			//forma de pago
			if(tfaccabfactura.getTfaccliente()!=null){
				if(tfaccabfactura.getTfaccliente().getFormapago()!=null && tfaccabfactura.getTfaccliente().getFormapago().equals("C")){
					facturaDataManager.setFormaPago1(true);//efectivo
					facturaDataManager.setFormaPago2(false);//credito
				}else{
					facturaDataManager.setFormaPago1(false);//efectivo
					facturaDataManager.setFormaPago2(true);//credito
				}
			}
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
