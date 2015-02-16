package ec.com.dlc.web.datamanager.factura.notacredito;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetdevolucione;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;

@ManagedBean(name="notaCreditoDatamanager")
@SessionScoped
public class NotaCreditoDatamanager extends BaseDatamanager {

	private Tfaccabdevolucione cabdevoluciones;
	
	private Collection<Tfacdetdevolucione> detdevolucionesColl;
	private Collection<Tadmcatalogo> catalogoEstadoColl;
	
	@Override
	public String getIdDatamanager() {
		return "notaCreditoDatamanager";
	}

	public Tfaccabdevolucione getCabdevoluciones() {
		return cabdevoluciones;
	}

	public void setCabdevoluciones(Tfaccabdevolucione cabdevoluciones) {
		this.cabdevoluciones = cabdevoluciones;
	}

	public Collection<Tfacdetdevolucione> getDetdevolucionesColl() {
		return detdevolucionesColl;
	}

	public void setDetdevolucionesColl(
			Collection<Tfacdetdevolucione> detdevolucionesColl) {
		this.detdevolucionesColl = detdevolucionesColl;
	}

	public Collection<Tadmcatalogo> getCatalogoEstadoColl() {
		return catalogoEstadoColl;
	}

	public void setCatalogoEstadoColl(Collection<Tadmcatalogo> catalogoEstadoColl) {
		this.catalogoEstadoColl = catalogoEstadoColl;
	}

}
