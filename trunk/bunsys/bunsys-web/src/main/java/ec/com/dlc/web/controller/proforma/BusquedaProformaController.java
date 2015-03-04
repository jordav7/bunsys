package ec.com.dlc.web.controller.proforma;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetproforma;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.bunsys.util.FacturacionException;
import ec.com.dlc.web.commons.resource.ContenidoMessages;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.factura.notacredito.BusquedaNotaCreditoDatamanager;
import ec.com.dlc.web.datamanager.factura.notacredito.NotaCreditoDatamanager;
import ec.com.dlc.web.datamanager.proforma.BusquedaProformaDatamanager;
import ec.com.dlc.web.datamanager.proforma.ProformaDatamanager;

@ManagedBean
@ViewScoped
public class BusquedaProformaController extends BaseController {

	@ManagedProperty(value="#{busquedaProformaDatamanager}")
	private BusquedaProformaDatamanager busquedaProformaDatamanager;
	
	@ManagedProperty(value="#{proformaDatamanager}")
	private ProformaDatamanager proformaDatamanager;
	
	@Inject
	private BunsysService bunsysService;
	
	@Override
	public BaseDatamanager getDatamanager() {
		return busquedaProformaDatamanager;
	}

	@Override
	public void inicializar() {

	}
	
	public void listarProformas(){
		try {
			System.out.println("numero proforma.."+busquedaProformaDatamanager.getNumeroproforma());
			busquedaProformaDatamanager.setTfaccabproformaList(bunsysService.cabeceraProformas(busquedaProformaDatamanager.getNumeroproforma()));
		} catch (FacturacionException e) {
			e.printStackTrace();
		}
	}
	
	public String crear() {
		Tfaccabproforma tfaccabproforma = new Tfaccabproforma();
		tfaccabproforma.setCountrycode(ContenidoMessages.getString("cod_country"));
		tfaccabproforma.setArea(ContenidoMessages.getString("cod_area"));
		tfaccabproforma.setFecha(new Date());
		tfaccabproforma.setTfacdetproformas(new ArrayList<Tfacdetproforma>());
		proformaDatamanager.setTfaccabproforma(tfaccabproforma);
		//cliente
		//objeto cliente 
		Tfaccliente tfacclienteG= new Tfaccliente();
		tfacclienteG.setTsyspersona(new Tsyspersona());
		proformaDatamanager.setTfaccliente(tfacclienteG);
		proformaDatamanager.setAccionAux("G");
		//lista de elimar
		proformaDatamanager.setDetproformasEliminar(new ArrayList<Tfacdetproforma>());
		return "/pages/factura/proforma/proforma?faces-redirect=true";
	}
	
	public String editar(Tfaccabproforma tfaccabproforma){
		tfaccabproforma.setTfacdetproformas(bunsysService.detalleProformas(tfaccabproforma.getPk().getNumeroproforma()));
		proformaDatamanager.setTfaccabproforma(tfaccabproforma);
		proformaDatamanager.getTfaccabproforma().setTfacdetproformas(bunsysService.detalleProformas(tfaccabproforma.getPk().getNumeroproforma()));
		//cliente
		proformaDatamanager.setTfaccliente(tfaccabproforma.getTfaccliente());
		proformaDatamanager.setAccionAux("E");
		//lista de elimar
		proformaDatamanager.setDetproformasEliminar(new ArrayList<Tfacdetproforma>());
		return "/pages/factura/proforma/proforma?faces-redirect=true";
	}

	public BusquedaProformaDatamanager getBusquedaProformaDatamanager() {
		return busquedaProformaDatamanager;
	}

	public void setBusquedaProformaDatamanager(
			BusquedaProformaDatamanager busquedaProformaDatamanager) {
		this.busquedaProformaDatamanager = busquedaProformaDatamanager;
	}

	public BunsysService getBunsysService() {
		return bunsysService;
	}

	public void setBunsysService(BunsysService bunsysService) {
		this.bunsysService = bunsysService;
	}

	public ProformaDatamanager getProformaDatamanager() {
		return proformaDatamanager;
	}

	public void setProformaDatamanager(ProformaDatamanager proformaDatamanager) {
		this.proformaDatamanager = proformaDatamanager;
	}

}
