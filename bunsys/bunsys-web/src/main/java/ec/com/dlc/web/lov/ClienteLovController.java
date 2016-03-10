package ec.com.dlc.web.lov;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.util.Utilitaria;

@ManagedBean(name = "clienteLovController")
@ViewScoped
public class ClienteLovController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private BunsysService bunsysService;
	/**
	 * lista de productos
	 */
	private Collection<Tfaccliente>clientes;
	
	private Tfaccliente cliente;
	
	@PostConstruct
    public void init() {
		try {
			String param = Utilitaria.getLovParameter("parametro");
			//clientes = bunsysService.clientes();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	public static void openLov(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("height", 450);
        options.put("width", 700);
        options.put("resizable", false);
        RequestContext.getCurrentInstance().openDialog("/pages/lov/clienteLov.xhtml", options, params);
    }
	
	public void setProducto() {
		RequestContext.getCurrentInstance().closeDialog(cliente);
    }
     
    public void selectObjwctFromDialog(Tfaccliente tfaccliente) {
        RequestContext.getCurrentInstance().closeDialog(tfaccliente);
    }

	public Collection<Tfaccliente> getClientes() {
		return clientes;
	}

	public void setClientes(Collection<Tfaccliente> clientes) {
		this.clientes = clientes;
	}

	public Tfaccliente getCliente() {
		return cliente;
	}

	public void setCliente(Tfaccliente cliente) {
		this.cliente = cliente;
	}

    
}
