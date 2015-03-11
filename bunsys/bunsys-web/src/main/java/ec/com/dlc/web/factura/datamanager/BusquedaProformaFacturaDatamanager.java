package ec.com.dlc.web.factura.datamanager;

import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.administracion.Tadmparamsri;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.login.LoginDatamanager;

@ManagedBean
@SessionScoped
public class BusquedaProformaFacturaDatamanager extends BaseDatamanager {

	@ManagedProperty(value="#{loginDatamanager}")
	private LoginDatamanager loginDatamanager;
	
	/**
	 * atributo para realizar la busqueda de las proformas
	 */
	private String numerofactura;
	
	private String codigoparamsri;
	
	/**
	 * lista de facturas
	 */
	private List<Tfaccabfactura>tfaccabfacturasList;
	
	private Collection<Tadmparamsri> tadmparamsriList;
	
	
	
	@Override
	public String getIdDatamanager() {
		return "busquedaProformaDatamanager";
	}

	public LoginDatamanager getLoginDatamanager() {
		return loginDatamanager;
	}

	public void setLoginDatamanager(LoginDatamanager loginDatamanager) {
		this.loginDatamanager = loginDatamanager;
	}

	public List<Tfaccabfactura> getTfaccabfacturasList() {
		return tfaccabfacturasList;
	}

	public void setTfaccabfacturasList(List<Tfaccabfactura> tfaccabfacturasList) {
		this.tfaccabfacturasList = tfaccabfacturasList;
	}

	public String getCodigoparamsri() {
		return codigoparamsri;
	}

	public void setCodigoparamsri(String codigoparamsri) {
		this.codigoparamsri = codigoparamsri;
	}

	public Collection<Tadmparamsri> getTadmparamsriList() {
		return tadmparamsriList;
	}

	public void setTadmparamsriList(Collection<Tadmparamsri> tadmparamsriList) {
		this.tadmparamsriList = tadmparamsriList;
	}

	public String getNumerofactura() {
		return numerofactura;
	}

	public void setNumerofactura(String numerofactura) {
		this.numerofactura = numerofactura;
	}


}
