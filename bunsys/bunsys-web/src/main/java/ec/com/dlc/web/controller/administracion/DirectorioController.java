package ec.com.dlc.web.controller.administracion;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.commons.resource.ContenidoMessages;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.administracion.DirectorioDatamanager;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.util.jsf.MessagesUtil;

@ManagedBean
@ViewScoped
public class DirectorioController extends BaseController {

	@ManagedProperty(value="#{directorioDatamanager}")
	private DirectorioDatamanager directorioDatamanager;
	
	@Inject
	private BunsysService bunsysService;
	
	@Override
	public BaseDatamanager getDatamanager() {
		return directorioDatamanager;
	}

	@Override
	public void inicializar() {
		this.directorioDatamanager.setDirectoriosColl(bunsysService.buscarObtenerCatalogos(directorioDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), 18));
		if(this.directorioDatamanager.getDirectoriosColl() == null || this.directorioDatamanager.getDirectoriosColl().isEmpty()){
			this.createNewDirectories();
		}
	}
	
	private void createNewDirectories(){
		directorioDatamanager.setDirectoriosColl(new ArrayList<Tadmcatalogo>());
		Tadmcatalogo factura = new Tadmcatalogo();
		factura.getPk().setCodigocatalogo("FAC");
		factura.getPk().setCodigocompania(directorioDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		factura.getPk().setCodigotipocatalogo(18);
		factura.setDescripcion(ContenidoMessages.getString("lbl_factura"));
		directorioDatamanager.getDirectoriosColl().add(factura);
		
		Tadmcatalogo notaCredito = new Tadmcatalogo();
		notaCredito.getPk().setCodigocatalogo("NCE");
		notaCredito.getPk().setCodigocompania(directorioDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		notaCredito.getPk().setCodigotipocatalogo(18);
		notaCredito.setDescripcion(ContenidoMessages.getString("lbl_notas_credito"));
		directorioDatamanager.getDirectoriosColl().add(notaCredito);
		
		Tadmcatalogo comprobante = new Tadmcatalogo();
		comprobante.getPk().setCodigocatalogo("COM");
		comprobante.getPk().setCodigocompania(directorioDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		comprobante.getPk().setCodigotipocatalogo(18);
		comprobante.setDescripcion(ContenidoMessages.getString("lbl_comprobantes_ret"));
		directorioDatamanager.getDirectoriosColl().add(comprobante);
		
		Tadmcatalogo notaDebito = new Tadmcatalogo();
		notaDebito.getPk().setCodigocatalogo("NDE");
		notaDebito.getPk().setCodigocompania(directorioDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		notaDebito.getPk().setCodigotipocatalogo(18);
		notaDebito.setDescripcion(ContenidoMessages.getString("lbl_notas_debito"));
		directorioDatamanager.getDirectoriosColl().add(notaDebito);
	}
	
	/**
	 * Guarda los cambios en la base de datos
	 */
	public void guardar() {
		try {
			bunsysService.guardarDirectorios(directorioDatamanager.getDirectoriosColl());
		} catch (Throwable e) {
			MessagesUtil.showErrorMessage(e.getMessage());
		}
	}

	public DirectorioDatamanager getDirectorioDatamanager() {
		return directorioDatamanager;
	}

	public void setDirectorioDatamanager(DirectorioDatamanager directorioDatamanager) {
		this.directorioDatamanager = directorioDatamanager;
	}

}
