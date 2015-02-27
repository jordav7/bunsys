package ec.com.dlc.web.datamanager.cobranza;

import java.math.BigDecimal;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccuentasxcobrar;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.login.LoginDatamanager;

@ManagedBean(name="registroCobroDatamanager")
@SessionScoped
public class RegistrarCobroDatamanager extends BaseDatamanager{
	@ManagedProperty(value="#{loginDatamanager}")
	private LoginDatamanager loginDatamanager;
	
	private Tfaccliente clienteSearch;
	
	private Tfaccuentasxcobrar cxcRegistro;
	
	private Collection<Tfaccuentasxcobrar> cxcPendientesColl;
	
	private boolean selCxc;
	
	private BigDecimal total;
	
	
	@Override
	public String getIdDatamanager() {
		return "registroCobroDatamanager";
	}

	public LoginDatamanager getLoginDatamanager() {
		return loginDatamanager;
	}

	public void setLoginDatamanager(LoginDatamanager loginDatamanager) {
		this.loginDatamanager = loginDatamanager;
	}

	public Tfaccliente getClienteSearch() {
		return clienteSearch;
	}

	public void setClienteSearch(Tfaccliente clienteSearch) {
		this.clienteSearch = clienteSearch;
	}

	public Tfaccuentasxcobrar getCxcRegistro() {
		return cxcRegistro;
	}

	public void setCxcRegistro(Tfaccuentasxcobrar cxcRegistro) {
		this.cxcRegistro = cxcRegistro;
	}

	public Collection<Tfaccuentasxcobrar> getCxcPendientesColl() {
		return cxcPendientesColl;
	}

	public void setCxcPendientesColl(
			Collection<Tfaccuentasxcobrar> cxcPendientesColl) {
		this.cxcPendientesColl = cxcPendientesColl;
	}

	public boolean isSelCxc() {
		return selCxc;
	}

	public void setSelCxc(boolean selCxc) {
		this.selCxc = selCxc;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	
}
