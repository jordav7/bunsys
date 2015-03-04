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

	}
	
	public void listarProformas(){
		try {
			System.out.println("numero proforma.."+busquedaProformaFacturaDatamanager.getNumeroproforma());
			busquedaProformaFacturaDatamanager.setTfaccabproformaList(bunsysService.cabeceraProformas(busquedaProformaFacturaDatamanager.getNumeroproforma()));
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
	
	public String selecionaProforma(Tfaccabproforma tfaccabproforma){
		try {
			//factura cabecera
			Tfaccabfactura tfaccabfactura = new Tfaccabfactura();
			//cliente
			Tfaccliente tfaccliente = new Tfaccliente();
			tfaccliente.setTsyspersona(new Tsyspersona());
			tfaccabfactura.setTfaccliente(tfaccliente);
			//aerolinea
			tfaccabfactura.setTadmairline(new Tadmcatalogo());
			//detalle factura
			tfaccabfactura.setTfacdetfacturas(new ArrayList<Tfacdetfactura>());
			facturaDataManager.setTfaccabfactura(tfaccabfactura);
			
			facturaDataManager.setEditable(Boolean.TRUE);
			facturaDataManager.setAccionAux("E");
			//proforma
			tfaccabproforma.setTfacdetproformas(new ArrayList<Tfacdetproforma>());
			tfaccabproforma.setTfacdetproformas(bunsysService.detalleProformas(tfaccabproforma.getPk().getNumeroproforma()));
			//cliente
			facturaDataManager.setTfaccliente(tfaccabproforma.getTfaccliente());
			//seteamos la cabecera de la FACTURA
			facturaDataManager.getTfaccabfactura().getPk().setCodigocompania(tfaccabproforma.getPk().getCodigocompania());
			//aerolinea
			facturaDataManager.getTfaccabfactura().setAirline(tfaccabproforma.getAirline());
			facturaDataManager.getTfaccabfactura().setAirlinecodigo(tfaccabproforma.getAirlinecodigo());
			facturaDataManager.getTfaccabfactura().setTadmairline(tfaccabproforma.getTadmairline());
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
				detalleFactura.getPk().setCodigocompania(detproforma.getPk().getCodigocompania());
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
