package ec.com.dlc.web.menu.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;

@ManagedBean(name="menu")
@ViewScoped
public class Menu extends BaseController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pagina;
	private String proceso;
	
	@Override
	public BaseDatamanager getDatamanager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inicializar() {
		// TODO Auto-generated method stub
		
	}
	
	public void redireccionar(String evento){
		System.out.println("evento...."+evento);	
		pagina=null;
		if(evento.equals("usuario")){
			pagina="usuario/usuario.xhtml";
			proceso="Usuario";
		}else if(evento.equals("empresa")){
			pagina= "administracion/empresa.xhtml";
			proceso="Empresa";
		}else if(evento.equals("documento")){
			pagina="administracion/directorioDocumento.xhtml";
			proceso="Directororio Documentaci\u00f3n";
		}else if(evento.equals("catalogo")){
			pagina="administracion/catalogo.xhtml";
			proceso="Catalogo";
		}else if(evento.equals("secuencia")){
			pagina="parametroFactura/secuencia.xhtml";
			proceso="Secuencia";
		}else if(evento.equals("clientes")){
			pagina="parametroFactura/clientes.xhtml";
			proceso="Clientes";
		}else if(evento.equals("articulos")){
			pagina="parametroFactura/articulos.xhtml";
			proceso="Art\u00edculos";
		}else if(evento.equals("factura")){
			pagina="factura/factura.xhtml";
			proceso="Factura";
		}else if(evento.equals("notacredito")){
			pagina="factura/notaCredito.xhtml";
			proceso="Nota de Cr\u00e9dito";
		}
	}

	public String salir(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpSession session = (HttpSession) externalContext.getSession(Boolean.FALSE);
		externalContext.getSessionMap().clear();
		session.invalidate();
		return "/login?faces-redirect=true";
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	
}
