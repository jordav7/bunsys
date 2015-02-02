package ec.com.dlc.web.controller.articulo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.articulo.ArticuloDatamanager;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;

@ManagedBean
@ViewScoped
public class ArticuloController extends BaseController {

	@ManagedProperty(value="#{articuloDatamanager}")
	private ArticuloDatamanager articuloDatamanager;
	
	@Override
	public BaseDatamanager getDatamanager() {
		// TODO Auto-generated method stub
		return articuloDatamanager;
	}

	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
		articuloDatamanager.setArticuloSearch(new Tinvproducto());
	}

	public ArticuloDatamanager getArticuloDatamanager() {
		return articuloDatamanager;
	}

	public void setArticuloDatamanager(ArticuloDatamanager articuloDatamanager) {
		this.articuloDatamanager = articuloDatamanager;
	}

}