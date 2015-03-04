package ec.com.dlc.web.factura.datamanager;

import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabproforma;
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
	private String numeroproforma;
	
	/**
	 * lista de proformas
	 */
	private List<Tfaccabproforma>tfaccabproformaList;
	
	/**
	 * Objeto para realizar la busqueda de la proforma
	 */
	private Tfaccabproforma tfaccabproformaSerch;
	
	private Collection<Tfaccabdevolucione> notasCreditoColl;
	
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

	public Tfaccabproforma getTfaccabproformaSerch() {
		return tfaccabproformaSerch;
	}

	public void setTfaccabproformaSerch(Tfaccabproforma tfaccabproformaSerch) {
		this.tfaccabproformaSerch = tfaccabproformaSerch;
	}

	public Collection<Tfaccabdevolucione> getNotasCreditoColl() {
		return notasCreditoColl;
	}

	public void setNotasCreditoColl(Collection<Tfaccabdevolucione> notasCreditoColl) {
		this.notasCreditoColl = notasCreditoColl;
	}

	public String getNumeroproforma() {
		return numeroproforma;
	}

	public void setNumeroproforma(String numeroproforma) {
		this.numeroproforma = numeroproforma;
	}

	public List<Tfaccabproforma> getTfaccabproformaList() {
		return tfaccabproformaList;
	}

	public void setTfaccabproformaList(List<Tfaccabproforma> tfaccabproformaList) {
		this.tfaccabproformaList = tfaccabproformaList;
	}



}
