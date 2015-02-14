package ec.com.dlc.web.controller.administracion;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;

import ec.com.dlc.bunsys.entity.administracion.pk.TadmcompaniaPK;
import ec.com.dlc.bunsys.facade.BunsysService;
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
	}
	
	public void actualizarCompania() {
		bunsysService.actualizarCompania(companiaDatamanager.getCompania());
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
			FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
