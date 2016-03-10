package ec.com.dlc.web.datamanager.factura.notacredito;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;

@ManagedBean
@SessionScoped
public class BusquedaNotaCreditoDatamanager extends BaseDatamanager {

	private Tfaccabdevolucione cabNotaCreditoSeach;
	
	private Collection<Tfaccabdevolucione> notasCreditoColl;
	
	@Override
	public String getIdDatamanager() {
		return "busquedaNotaCreditoDatamanager";
	}

	public Tfaccabdevolucione getCabNotaCreditoSeach() {
		return cabNotaCreditoSeach;
	}

	public void setCabNotaCreditoSeach(Tfaccabdevolucione cabNotaCreditoSeach) {
		this.cabNotaCreditoSeach = cabNotaCreditoSeach;
	}

	public Collection<Tfaccabdevolucione> getNotasCreditoColl() {
		return notasCreditoColl;
	}

	public void setNotasCreditoColl(Collection<Tfaccabdevolucione> notasCreditoColl) {
		this.notasCreditoColl = notasCreditoColl;
	}

}
