package ec.com.dlc.web.controller.administracion;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;

import ec.com.dlc.bunsys.entity.administracion.pk.TadmcompaniaPK;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.commons.resource.ContenidoMessages;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.administracion.CompaniaDatamanager;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;

@ManagedBean
@ViewScoped
public class CompaniaController extends BaseController {
	@ManagedProperty(value="#{companiaDatamanager}")
	private CompaniaDatamanager companiaDatamanager;
	
	@Inject
	private BunsysService bunsysService;
	
	
	@Override
	public BaseDatamanager getDatamanager() {
		// TODO Auto-generated method stub
		return companiaDatamanager;
	}

	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
		TadmcompaniaPK companiaPk = new TadmcompaniaPK();
		companiaPk.setCodigocompania(companiaDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		companiaDatamanager.setCompania(bunsysService.buscarCompania(companiaPk));
		companiaDatamanager.setTipoAmbienteColl(bunsysService.buscarObtenerCatSri(ContenidoMessages.getInteger("cod_catalogo_tipo_amb")));
		
		if(StringUtils.isNotBlank(companiaDatamanager.getCompania().getTipocompania()) && companiaDatamanager.getCompania().getTipocompania().equalsIgnoreCase("E")){
			companiaDatamanager.setExportadora(true);
		}else{
			companiaDatamanager.setExportadora(false);
		}
	}
	
	public void actualizarCompania() {
		try {
			if(companiaDatamanager.isExportadora()){
				companiaDatamanager.getCompania().setTipocompania("E");
			}else{
				companiaDatamanager.getCompania().setTipocompania("N");
			}
			bunsysService.actualizarCompania(companiaDatamanager.getCompania());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro guardado correctamente", "Registro guardado correctamente"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().validationFailed();
    		RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		}
		
	}

	public CompaniaDatamanager getCompaniaDatamanager() {
		return companiaDatamanager;
	}

	public void setCompaniaDatamanager(CompaniaDatamanager companiaDatamanager) {
		this.companiaDatamanager = companiaDatamanager;
	}

	public void uploadLogo(FileUploadEvent event) {
        try {
        	companiaDatamanager.getCompania().setLogocompania(IOUtils.toByteArray(event.getFile().getInputstream()));
			companiaDatamanager.setStrContent(new DefaultStreamedContent(event.getFile().getInputstream(), event.getFile().getContentType(), event.getFile().getFileName()));
			FacesMessage message = new FacesMessage("Imagen cargada correctamente", event.getFile().getFileName() + " is uploaded.");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
