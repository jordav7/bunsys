package ec.com.dlc.web.controller.administracion;

import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.commons.resource.ContenidoMessages;
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
		catalogoDatamanager.getCatalogo().getPk().setCodigotipocatalogo(catalogoDatamanager.getCatalogoBusq().getPk().getCodigotipocatalogo());
	}
	
	public void eliminar() {
		try {
			bunsysService.eliminarCatalogo(catalogoDatamanager.getCatalogo());
		} catch (Throwable e) {
			MessagesUtil.showErrorMessage(e.getMessage());
		}
	}

	public void guardar() {
		try{
			catalogoDatamanager.getCatalogo().getPk().setCodigocompania(catalogoDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
			catalogoDatamanager.getCatalogo().setEstado("A");
			bunsysService.guardarCatalogo(catalogoDatamanager.getCatalogo());
			MessagesUtil.showInfoMessage(ContenidoMessages.getString("msg_info_catalogo_guardado"));
		} catch (Throwable e) {
			MessagesUtil.showErrorMessage(e.getMessage());
		}
	}
	
	public CatalogoDatamanager getCatalogoDatamanager() {
		return catalogoDatamanager;
	}

	public void setCatalogoDatamanager(CatalogoDatamanager catalogoDatamanager) {
		this.catalogoDatamanager = catalogoDatamanager;
	}

}
