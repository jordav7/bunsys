package ec.com.dlc.web.factura.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetproforma;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfaccabfacturaPK;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfacclientePK;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfacdetfacturaPK;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.inventario.pk.TinvproductoPK;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.factura.datamanager.FacturaDataManager;
import ec.com.dlc.web.lov.ClienteLovController;
import ec.com.dlc.web.lov.ProductoLovController;

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
		System.out.println(tfaccabproforma);
		System.out.println(tfaccabproforma.getPk());
		System.out.println(tfaccabproforma.getPk().getNumeroproforma());
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
		System.out.println("nombres----------------------"+facturaDataManager.getTfaccabfactura().getTfaccliente().getTsyspersona().getNombres());
		//
		facturaDataManager.getTfaccabfactura().setEstado("A");
		facturaDataManager.getTfaccabfactura().setEstadocodigo(tfaccabproforma.getEstadocodigo());
		
		facturaDataManager.getTfaccabfactura().setFarmcode(tfaccabproforma.getFarmcode());
		facturaDataManager.getTfaccabfactura().setCountrycode(tfaccabproforma.getCountrycode());
		facturaDataManager.getTfaccabfactura().setArea(tfaccabproforma.getArea());
		facturaDataManager.getTfaccabfactura().setMasterawm(tfaccabproforma.getMasterawm());
		facturaDataManager.getTfaccabfactura().setHouseawb(tfaccabproforma.getHouseawb());
		facturaDataManager.getTfaccabfactura().setDae(tfaccabproforma.getDae());
		facturaDataManager.getTfaccabfactura().setConsignee(tfaccabproforma.getConsignee());
		facturaDataManager.getTfaccabfactura().setFixedprice(tfaccabproforma.getFixedprice());
		facturaDataManager.getTfaccabfactura().setReferendum(tfaccabproforma.getReferendum());
		
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
		facturaDataManager.getTfaccabfactura().setPocentajeirbpnr(tfaccabproforma.getPocentajeirbpnr());
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
	}
	
	public void formaPagoCheque(){
		facturaDataManager.setFormaPago1(false);
	}
	
	public void grabar(){
		bunsysService.grabarFactura(facturaDataManager.getTfaccabfactura());
	}
	
	public void cancelar(){
		
	}
			
	public FacturaDataManager getFacturaDataManager() {
		return facturaDataManager;
	}

	public void setFacturaDataManager(FacturaDataManager facturaDataManager) {
		this.facturaDataManager = facturaDataManager;
	}
	
}
