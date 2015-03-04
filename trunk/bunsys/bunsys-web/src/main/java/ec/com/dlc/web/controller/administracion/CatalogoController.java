package ec.com.dlc.web.controller.administracion;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.administracion.CatalogoDatamanager;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.util.jsf.MessagesUtil;

@ManagedBean
@ViewScoped
public class CatalogoController extends BaseController {

	@ManagedProperty(value="#{catalogoDatamanager}")
	private CatalogoDatamanager catalogoDatamanager;
	
	@Inject
	private BunsysService bunsysService;
	
	@Override
	public BaseDatamanager getDatamanager() {
		return catalogoDatamanager;
	}

	@Override
	public void inicializar() {
		this.catalogoDatamanager.setCatalogoBusq(new Tadmcatalogo());
		this.catalogoDatamanager.setTipoCatalogoColl(bunsysService.obtenerTiposCatalogo());
	}
	
	public void buscar() {
		try {
			Collection<Tadmcatalogo> catalogoColl = bunsysService.obtenerCatalogos(catalogoDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), catalogoDatamanager.getCatalogoBusq());
			catalogoDatamanager.setCatalogoColl(catalogoColl);
		} catch (Throwable e) {
			MessagesUtil.showErrorMessage(e.getMessage());
		}
	}
	
	public void crear() {
		catalogoDatamanager.setCatalogo(new Tadmcatalogo());
	}
	
	public void eliminar() {
		bunsysService.eliminarCatalogo(catalogoDatamanager.getCatalogo());
	}

	public void guardar() {
		bunsysService.guardarCatalogo(catalogoDatamanager.getCatalogo());
	}
	
	public CatalogoDatamanager getCatalogoDatamanager() {
		return catalogoDatamanager;
	}

	public void setCatalogoDatamanager(CatalogoDatamanager catalogoDatamanager) {
		this.catalogoDatamanager = catalogoDatamanager;
	}

}
