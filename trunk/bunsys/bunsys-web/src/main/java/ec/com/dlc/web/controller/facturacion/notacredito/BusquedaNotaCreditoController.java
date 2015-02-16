package ec.com.dlc.web.controller.facturacion.notacredito;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.factura.notacredito.BusquedaNotaCreditoDatamanager;
import ec.com.dlc.web.datamanager.factura.notacredito.NotaCreditoDatamanager;

@ManagedBean
@ViewScoped
public class BusquedaNotaCreditoController extends BaseController {

	@ManagedProperty(value="#{busquedaNotaCreditoDatamanager}")
	private BusquedaNotaCreditoDatamanager busquedaNotaCreditoDatamanager;
	
	@ManagedProperty(value="#{notaCreditoDatamanager}")
	private NotaCreditoDatamanager notaCreditoDatamanager;
	
	@Inject
	private BunsysService bunsysService;
	
	@Override
	public BaseDatamanager getDatamanager() {
		return busquedaNotaCreditoDatamanager;
	}

	@Override
	public void inicializar() {
		this.busquedaNotaCreditoDatamanager.setCabNotaCreditoSeach(new Tfaccabdevolucione());
		this.busquedaNotaCreditoDatamanager.getCabNotaCreditoSeach().setTfaccliente(new Tfaccliente());
		this.busquedaNotaCreditoDatamanager.getCabNotaCreditoSeach().getTfaccliente().setTsyspersona(new Tsyspersona());
	}
	
	/**
	 * Realiza la b&uacute;squeda
	 */
	public void buscar() {
		busquedaNotaCreditoDatamanager.setNotasCreditoColl(bunsysService.buscarNotasCredito(null, busquedaNotaCreditoDatamanager.getCabNotaCreditoSeach(), busquedaNotaCreditoDatamanager.getCabNotaCreditoSeach().getTfaccliente().getTsyspersona()));
	}
	
	public String crearNotaCredito() {
		notaCreditoDatamanager.setCabdevoluciones(new Tfaccabdevolucione());
		notaCreditoDatamanager.getCabdevoluciones().setTfaccliente(new Tfaccliente());
		notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().setTsyspersona(new Tsyspersona());
		return "/pages/factura/notaCredito/notaCredito?faces-redirect=true";
	}

	public BusquedaNotaCreditoDatamanager getBusquedaNotaCreditoDatamanager() {
		return busquedaNotaCreditoDatamanager;
	}

	public void setBusquedaNotaCreditoDatamanager(
			BusquedaNotaCreditoDatamanager busquedaNotaCreditoDatamanager) {
		this.busquedaNotaCreditoDatamanager = busquedaNotaCreditoDatamanager;
	}

	public NotaCreditoDatamanager getNotaCreditoDatamanager() {
		return notaCreditoDatamanager;
	}

	public void setNotaCreditoDatamanager(
			NotaCreditoDatamanager notaCreditoDatamanager) {
		this.notaCreditoDatamanager = notaCreditoDatamanager;
	}

}
