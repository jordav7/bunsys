package ec.com.dlc.web.datamanager.proforma;

import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetproforma;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.login.LoginDatamanager;
@ManagedBean(name="proformaDatamanager")
@SessionScoped
public class ProformaDatamanager extends BaseDatamanager {

	@Override
	public String getIdDatamanager() {
		return "proformaDatamanager";
	}

	@ManagedProperty(value="#{loginDatamanager}")
	private LoginDatamanager loginDatamanager;
	
	/**
	 * Objeto para realizar la busqueda de la proforma
	 */
	private Tfaccabproforma tfaccabproformaSerch;
	
	/**
	 * Objeto cabecera a ser guardar
	 */
	private Tfaccabproforma tfaccabproforma;
	/**
	 * Objeto detalle a ser guardado
	 */
	private Tfacdetproforma tfacdetproforma;
	/**
	 * Cliente
	 */
	private Tfaccliente tfaccliente;
	/**
	 * articulo
	 */
	private Tinvproducto tinvproducto;
	
	/**
	 * Aerolineas
	 */
	private Collection<Tadmcatalogo> aerolineasCatalogo;
	/**
	 * atributo para saber si actualiza o graba
	 */
	private String accionAux;
	/**
	 * atributo para realizar la busqueda de las proformas
	 */
	private String numeroproforma;
	/**
	 * lista de proformas
	 */
	private List<Tfaccabproforma>tfaccabproformaList;
	
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

	public Tfaccabproforma getTfaccabproforma() {
		return tfaccabproforma;
	}

	public void setTfaccabproforma(Tfaccabproforma tfaccabproforma) {
		this.tfaccabproforma = tfaccabproforma;
	}

	public Tfacdetproforma getTfacdetproforma() {
		return tfacdetproforma;
	}

	public void setTfacdetproforma(Tfacdetproforma tfacdetproforma) {
		this.tfacdetproforma = tfacdetproforma;
	}

	public Collection<Tadmcatalogo> getAerolineasCatalogo() {
		return aerolineasCatalogo;
	}

	public void setAerolineasCatalogo(Collection<Tadmcatalogo> aerolineasCatalogo) {
		this.aerolineasCatalogo = aerolineasCatalogo;
	}

	public Tfaccliente getTfaccliente() {
		return tfaccliente;
	}

	public void setTfaccliente(Tfaccliente tfaccliente) {
		this.tfaccliente = tfaccliente;
	}

	public Tinvproducto getTinvproducto() {
		return tinvproducto;
	}

	public void setTinvproducto(Tinvproducto tinvproducto) {
		this.tinvproducto = tinvproducto;
	}

	public String getAccionAux() {
		return accionAux;
	}

	public void setAccionAux(String accionAux) {
		this.accionAux = accionAux;
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
