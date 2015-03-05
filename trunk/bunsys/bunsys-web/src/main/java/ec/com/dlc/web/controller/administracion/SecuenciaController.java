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
import ec.com.dlc.web.datamanager.administracion.SecuenciaDatamanager;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.util.jsf.MessagesUtil;

@ManagedBean
@ViewScoped
public class SecuenciaController extends BaseController {

	@ManagedProperty(value="#{secuenciaDatamanager}")
	private SecuenciaDatamanager secuenciaDatamanager;
	
	@Inject
	private BunsysService bunsysService;
	
	@Override
	public BaseDatamanager getDatamanager() {
		return secuenciaDatamanager;
	}

	@Override
	public void inicializar() {
		this.secuenciaDatamanager.setSecuenciasColl(bunsysService.buscarObtenerCatalogos(secuenciaDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), 19));
		if(this.secuenciaDatamanager.getSecuenciasColl() == null || this.secuenciaDatamanager.getSecuenciasColl().isEmpty()){
			this.createNewSequences();
		}
	}
	
	private void createNewSequences(){
		this.secuenciaDatamanager.setSecuenciasColl(new ArrayList<Tadmcatalogo>());
		Tadmcatalogo factura = new Tadmcatalogo();
		factura.getPk().setCodigocatalogo("FAC");
		factura.getPk().setCodigocompania(secuenciaDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		factura.getPk().setCodigotipocatalogo(19);
		factura.setDescripcion(ContenidoMessages.getString("lbl_factura"));
		secuenciaDatamanager.getSecuenciasColl().add(factura);
		
		Tadmcatalogo notaCredito = new Tadmcatalogo();
		notaCredito.getPk().setCodigocatalogo("NCE");
		notaCredito.getPk().setCodigocompania(secuenciaDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		notaCredito.getPk().setCodigotipocatalogo(19);
		notaCredito.setDescripcion(ContenidoMessages.getString("lbl_notas_credito"));
		secuenciaDatamanager.getSecuenciasColl().add(notaCredito);
		
		Tadmcatalogo comprobante = new Tadmcatalogo();
		comprobante.getPk().setCodigocatalogo("COM");
		comprobante.getPk().setCodigocompania(secuenciaDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		comprobante.getPk().setCodigotipocatalogo(19);
		comprobante.setDescripcion(ContenidoMessages.getString("lbl_comprobantes_ret"));
		secuenciaDatamanager.getSecuenciasColl().add(comprobante);
		
		Tadmcatalogo notaDebito = new Tadmcatalogo();
		notaDebito.getPk().setCodigocatalogo("NDE");
		notaDebito.getPk().setCodigocompania(secuenciaDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		notaDebito.getPk().setCodigotipocatalogo(19);
		notaDebito.setDescripcion(ContenidoMessages.getString("lbl_notas_debito"));
		secuenciaDatamanager.getSecuenciasColl().add(notaDebito);
	}
	
	public void guardar() {
		try {
			bunsysService.guardarCatalogos(secuenciaDatamanager.getSecuenciasColl());
		} catch (Throwable e) {
			MessagesUtil.showErrorMessage(e.getMessage());
		}
	}

	public SecuenciaDatamanager getSecuenciaDatamanager() {
		return secuenciaDatamanager;
	}

	public void setSecuenciaDatamanager(SecuenciaDatamanager secuenciaDatamanager) {
		this.secuenciaDatamanager = secuenciaDatamanager;
	}

}
