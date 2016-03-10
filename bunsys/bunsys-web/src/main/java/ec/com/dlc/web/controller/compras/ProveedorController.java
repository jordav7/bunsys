package ec.com.dlc.web.controller.compras;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.com.dlc.bunsys.entity.cuentasxpagar.Tcxpproveedor;
import ec.com.dlc.bunsys.entity.cuentasxpagar.pk.TcxpproveedorPK;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.commons.resource.ContenidoMessages;
import ec.com.dlc.web.componentes.ProveedorComponent;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.compras.ProveedorDatamanager;

@ManagedBean
@ViewScoped
public class ProveedorController extends BaseController{

	@ManagedProperty(value="#{proveedorDatamanager}")
	private ProveedorDatamanager proveedorDatamanager;
	
	
	@Inject
	private BunsysService bunsysService;
	
	@Override
	public BaseDatamanager getDatamanager() {
		return proveedorDatamanager;
	}

	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
		proveedorDatamanager.setProveedorSearch(new Tcxpproveedor());
		proveedorDatamanager.getProveedorSearch().setTsyspersona(new Tsyspersona());
		proveedorDatamanager.setTipoIdColl(bunsysService.buscarObtenerCatalogos(proveedorDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_tipoid_persona")));
		proveedorDatamanager.setGrupoProvColl(bunsysService.buscarObtenerCatalogos(proveedorDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_grupo_prov")));
		proveedorDatamanager.setEstadoProvColl(bunsysService.buscarObtenerCatalogos(proveedorDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_estado_persona")));
		proveedorDatamanager.setProveedorComponente(new ProveedorComponent(proveedorDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania()));
	}
	
	public void crearProveedor() {
		proveedorDatamanager.getProveedorComponente().crear();
		proveedorDatamanager.getProveedorComponente().getProveedor().getPk().setCodigocompania(proveedorDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
	}

	public void buscar() {
		proveedorDatamanager.setProveedorColl(bunsysService.buscarObtenerProveedores(proveedorDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
							proveedorDatamanager.getProveedorSearch().getPk().getCodigoproveedor(),
							proveedorDatamanager.getProveedorSearch().getTsyspersona().getTipoid(), 
							ContenidoMessages.getInteger("cod_catalogo_tipoid_persona"), 
							proveedorDatamanager.getProveedorSearch().getTsyspersona().getIdentificacion(),
							proveedorDatamanager.getProveedorSearch().getTsyspersona().getNombres(), 
							proveedorDatamanager.getProveedorSearch().getTsyspersona().getApellidos(),
							proveedorDatamanager.getProveedorSearch().getGrupoproveedor(), 
							ContenidoMessages.getInteger("cod_catalogo_grupo_prov"), 
							proveedorDatamanager.getProveedorSearch().getTsyspersona().getEstado(),
							ContenidoMessages.getInteger("cod_catalogo_estado_persona")));
	}
	
	public void eliminar(String codigoProveedor) {
		TcxpproveedorPK proveedorPk = new TcxpproveedorPK();
		proveedorPk.setCodigocompania(proveedorDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		proveedorPk.setCodigoproveedor(codigoProveedor);
		
		bunsysService.eliminarProveedor(proveedorPk, ContenidoMessages.getInteger("cod_catalogo_estado_persona"));
		buscar();
	}
	
	public ProveedorDatamanager getProveedorDatamanager() {
		return proveedorDatamanager;
	}

	public void setProveedorDatamanager(ProveedorDatamanager proveedorDatamanager) {
		this.proveedorDatamanager = proveedorDatamanager;
	}

	
}
