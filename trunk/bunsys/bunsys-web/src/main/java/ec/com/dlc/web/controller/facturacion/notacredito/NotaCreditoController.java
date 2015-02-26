package ec.com.dlc.web.controller.facturacion.notacredito;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetdevolucione;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.factura.notacredito.NotaCreditoDatamanager;

@ManagedBean(name="notaCreditoController")
@ViewScoped
public class NotaCreditoController extends BaseController {

	@ManagedProperty(value="#{notaCreditoDatamanager}")
	private NotaCreditoDatamanager notaCreditoDatamanager;
	
	@Inject
	private BunsysService bunsysService;
	
	@Override
	public BaseDatamanager getDatamanager() {
		return notaCreditoDatamanager;
	}

	@Override
	public void inicializar() {
//		this.notaCreditoDatamanager.setCabdevoluciones(new Tfaccabdevolucione());
		this.notaCreditoDatamanager.setDetdevolucionesColl(new ArrayList<Tfacdetdevolucione>());
	}
	
	public void crear() {
	}
	
	public void busquedaCliente() {
		Tfaccliente cliente = bunsysService.buscarPorIdentificacion(this.notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getTsyspersona().getIdentificacion());
		if(cliente != null){
			this.notaCreditoDatamanager.getCabdevoluciones().setTfaccliente(cliente);
		} 
	}

	public NotaCreditoDatamanager getNotaCreditoDatamanager() {
		return notaCreditoDatamanager;
	}

	public void setNotaCreditoDatamanager(
			NotaCreditoDatamanager notaCreditoDatamanager) {
		this.notaCreditoDatamanager = notaCreditoDatamanager;
	}

}
