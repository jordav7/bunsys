package ec.com.dlc.web.datamanager.cliente;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.web.componentes.ClienteComponent;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.login.LoginDatamanager;

@ManagedBean(name="clienteDatamanager")
@SessionScoped
public class ClienteDatamanager extends BaseDatamanager{

	@ManagedProperty(value="#{loginDatamanager}")
	private LoginDatamanager loginDatamanager;

	private Collection<Tfaccliente> clientesCol; 
	/**
	 * objeto para el manjo de la busqueda
	 */
	private Tfaccliente  clienteserch;
	/**
	 * tipos de cliente
	 */
	private Collection<Tadmcatalogo> tiposCatalogo;
	/**
	 * Estados del cliente
	 */
	private Collection<Tadmcatalogo> estadosCatalogo;
	/**
	 * Formas de pago del cliente
	 */
	private Collection<Tadmcatalogo> formaspagosCatalogo;
	
	private Collection<Tadmcatalogo> gruposCatalogo;
	
	private ClienteComponent clienteComponente;
	
	@Override
	public String getIdDatamanager() {
		return "clienteDatamanager";
	}
	public LoginDatamanager getLoginDatamanager() {
		return loginDatamanager;
	}
	public void setLoginDatamanager(LoginDatamanager loginDatamanager) {
		this.loginDatamanager = loginDatamanager;
	}
	public Tfaccliente getClienteserch() {
		return clienteserch;
	}
	public void setClienteserch(Tfaccliente clienteserch) {
		this.clienteserch = clienteserch;
	}
	public Collection<Tfaccliente> getClientesCol() {
		return clientesCol;
	}
	public void setClientesCol(Collection<Tfaccliente> clientesCol) {
		this.clientesCol = clientesCol;
	}
	public Collection<Tadmcatalogo> getTiposCatalogo() {
		return tiposCatalogo;
	}
	public void setTiposCatalogo(Collection<Tadmcatalogo> tiposCatalogo) {
		this.tiposCatalogo = tiposCatalogo;
	}
	public Collection<Tadmcatalogo> getEstadosCatalogo() {
		return estadosCatalogo;
	}
	public void setEstadosCatalogo(Collection<Tadmcatalogo> estadosCatalogo) {
		this.estadosCatalogo = estadosCatalogo;
	}
	public Collection<Tadmcatalogo> getFormaspagosCatalogo() {
		return formaspagosCatalogo;
	}
	public void setFormaspagosCatalogo(Collection<Tadmcatalogo> formaspagosCatalogo) {
		this.formaspagosCatalogo = formaspagosCatalogo;
	}
	public Collection<Tadmcatalogo> getGruposCatalogo() {
		return gruposCatalogo;
	}
	public void setGruposCatalogo(Collection<Tadmcatalogo> gruposCatalogo) {
		this.gruposCatalogo = gruposCatalogo;
	}
	public ClienteComponent getClienteComponente() {
		return clienteComponente;
	}
	public void setClienteComponente(ClienteComponent clienteComponente) {
		this.clienteComponente = clienteComponente;
	}
	
	
}
