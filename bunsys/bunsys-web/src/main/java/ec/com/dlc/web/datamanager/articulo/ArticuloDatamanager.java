package ec.com.dlc.web.datamanager.articulo;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.web.componentes.ArticuloComponent;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.login.LoginDatamanager;

@ManagedBean(name="articuloDatamanager")
@SessionScoped
public class ArticuloDatamanager extends BaseDatamanager {

	@ManagedProperty(value="#{loginDatamanager}")
	private LoginDatamanager loginDatamanager;
	
	/**
	 * Contiene el listado de art&iacute;culos encontrados en la b&uacute;squeda
	 */
	private Collection<Tinvproducto> productoColl;
	/**
	 * Colores de un art&iacute;culo
	 */
	private Collection<Tadmcatalogo> colorCatalogoColl;
	/**
	 * Estado de un art&iacute;culo
	 */
	private Collection<Tadmcatalogo> estadoCatalogoColl;
	
	/**
	 * Objeto que se manejar&aacute; para las b&uacte;squedas
	 */
	private Tinvproducto articuloSearch;
	
	/**
	 * Componente de art&iacute;culos
	 */
	private ArticuloComponent articuloComponente;
	
	@Override
	public String getIdDatamanager() {
		return "articuloDatamanager";
	}
	
	public Collection<Tinvproducto> getProductoColl() {
		return productoColl;
	}
	public void setProductoColl(Collection<Tinvproducto> productoColl) {
		this.productoColl = productoColl;
	}

	public Tinvproducto getArticuloSearch() {
		return articuloSearch;
	}

	public void setArticuloSearch(Tinvproducto articuloSearch) {
		this.articuloSearch = articuloSearch;
	}

	public Collection<Tadmcatalogo> getColorCatalogoColl() {
		return colorCatalogoColl;
	}

	public void setColorCatalogoColl(Collection<Tadmcatalogo> colorCatalogoColl) {
		this.colorCatalogoColl = colorCatalogoColl;
	}

	public Collection<Tadmcatalogo> getEstadoCatalogoColl() {
		return estadoCatalogoColl;
	}

	public void setEstadoCatalogoColl(Collection<Tadmcatalogo> estadoCatalogoColl) {
		this.estadoCatalogoColl = estadoCatalogoColl;
	}

	public LoginDatamanager getLoginDatamanager() {
		return loginDatamanager;
	}

	public void setLoginDatamanager(LoginDatamanager loginDatamanager) {
		this.loginDatamanager = loginDatamanager;
	}

	public ArticuloComponent getArticuloComponente() {
		return articuloComponente;
	}

	public void setArticuloComponente(ArticuloComponent articuloComponente) {
		this.articuloComponente = articuloComponente;
	}

}
