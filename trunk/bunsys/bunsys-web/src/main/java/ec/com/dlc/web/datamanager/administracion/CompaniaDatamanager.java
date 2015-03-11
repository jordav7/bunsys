package ec.com.dlc.web.datamanager.administracion;

import java.io.ByteArrayInputStream;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;
import ec.com.dlc.bunsys.entity.administracion.Tadmparamsri;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.login.LoginDatamanager;

@ManagedBean(name="companiaDatamanager")
@SessionScoped
public class CompaniaDatamanager extends BaseDatamanager{
	
	@ManagedProperty(value="#{loginDatamanager}")
	private LoginDatamanager loginDatamanager;
	
	private StreamedContent strContent;
	
	private Tadmcompania compania;
	
	private Collection<Tadmparamsri> tipoAmbienteColl;

	private boolean exportadora;
	
	@Override
	public String getIdDatamanager() {
		return "companiaDatamanager";
	}
	
	public LoginDatamanager getLoginDatamanager() {
		return loginDatamanager;
	}

	public void setLoginDatamanager(LoginDatamanager loginDatamanager) {
		this.loginDatamanager = loginDatamanager;
	}

	public StreamedContent getStrContent() {
		if(compania.getLogocompania() != null){
			strContent = new DefaultStreamedContent(new ByteArrayInputStream(compania.getLogocompania()), "image/png", "foto.png");
		}
		return strContent;
	}
	
	public void setStrContent(StreamedContent strContent) {
		this.strContent = strContent;
	}

	public Tadmcompania getCompania() {
		return compania;
	}

	public void setCompania(Tadmcompania compania) {
		this.compania = compania;
	}

	public Collection<Tadmparamsri> getTipoAmbienteColl() {
		return tipoAmbienteColl;
	}

	public void setTipoAmbienteColl(Collection<Tadmparamsri> tipoAmbienteColl) {
		this.tipoAmbienteColl = tipoAmbienteColl;
	}

	public boolean isExportadora() {
		return exportadora;
	}

	public void setExportadora(boolean exportadora) {
		this.exportadora = exportadora;
	}
	
	

}
