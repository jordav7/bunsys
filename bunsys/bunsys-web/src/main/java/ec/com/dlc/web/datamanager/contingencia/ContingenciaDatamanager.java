package ec.com.dlc.web.datamanager.contingencia;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.administracion.Tadmparamsri;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.login.LoginDatamanager;

@ManagedBean
@SessionScoped
public class ContingenciaDatamanager extends BaseDatamanager {

	@ManagedProperty(value="#{loginDatamanager}")
	private LoginDatamanager loginDatamanager;
	
	
	/**
	 * lista de facturas
	 */
	private List<Tfaccabfactura>cabfacturasList;
	
	/**
	 * Cabecera devoluci&oacute;n
	 */
	private Collection<Tfaccabdevolucione> cabdevoluciones;
	
	private Date fechamin;
	private Date fechamax;

	@Override
	public String getIdDatamanager() {
		return "contingenciaDatamanager";
	}

	public LoginDatamanager getLoginDatamanager() {
		return loginDatamanager;
	}

	public void setLoginDatamanager(LoginDatamanager loginDatamanager) {
		this.loginDatamanager = loginDatamanager;
	}


	public List<Tfaccabfactura> getCabfacturasList() {
		return cabfacturasList;
	}

	public void setCabfacturasList(List<Tfaccabfactura> cabfacturasList) {
		this.cabfacturasList = cabfacturasList;
	}

	public Collection<Tfaccabdevolucione> getCabdevoluciones() {
		return cabdevoluciones;
	}

	public void setCabdevoluciones(Collection<Tfaccabdevolucione> cabdevoluciones) {
		this.cabdevoluciones = cabdevoluciones;
	}

	public Date getFechamin() {
		return fechamin;
	}

	public void setFechamin(Date fechamin) {
		this.fechamin = fechamin;
	}

	public Date getFechamax() {
		return fechamax;
	}

	public void setFechamax(Date fechamax) {
		this.fechamax = fechamax;
	}

}
