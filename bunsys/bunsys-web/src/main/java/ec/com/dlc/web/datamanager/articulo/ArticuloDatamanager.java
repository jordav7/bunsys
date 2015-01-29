package ec.com.dlc.web.datamanager.articulo;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;

@ManagedBean(name="articuloDatamanager")
@SessionScoped
public class ArticuloDatamanager extends BaseDatamanager {

	/**
	 * Contiene el listado de art&iacute;culos encontrados en la b&uacute;squeda
	 */
	private Collection<Tinvproducto> productoColl;
	
	/**
	 * Objeto que se manejar&aacute; para las b&uacte;squedas
	 */
	private Tinvproducto articuloSearch;
	
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

}
