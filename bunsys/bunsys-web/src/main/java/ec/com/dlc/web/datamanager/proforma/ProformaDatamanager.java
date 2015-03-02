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
	 * Aerolineas
	 */
	private Collection<Tadmcatalogo> catalogoPicesType;
	/**
	 * atributo para saber si actualiza o graba
	 */
	private String accionAux;

	private Collection<Tfacdetproforma>detproformasEliminar;
	

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

	public Collection<Tfacdetproforma> getDetproformasEliminar() {
		return detproformasEliminar;
	}

	public void setDetproformasEliminar(
			Collection<Tfacdetproforma> detproformasEliminar) {
		this.detproformasEliminar = detproformasEliminar;
	}

	public Collection<Tadmcatalogo> getCatalogoPicesType() {
		return catalogoPicesType;
	}

	public void setCatalogoPicesType(Collection<Tadmcatalogo> catalogoPicesType) {
		this.catalogoPicesType = catalogoPicesType;
	}

}
