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

import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.util.Utilitaria;

@ManagedBean(name = "productoLovController")
@ViewScoped
public class ProductoLovController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private BunsysService bunsysService;
	/**
	 * lista de productos
	 */
	private Collection<Tinvproducto>productos;
	
	private Tinvproducto producto;
	
	@PostConstruct
    public void init() {
		try {
			String param = Utilitaria.getLovParameter("parametro");
			//productos = bunsysService.productos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    }
	
	public static void openLov(Map<String, List<String>> params) {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("modal", true);
        options.put("height", 450);
        options.put("width", 800);
        options.put("resizable", false);
        RequestContext.getCurrentInstance().openDialog("/pages/lov/productoLov.xhtml", options, params);
    }
	
	public void setProducto() {
		RequestContext.getCurrentInstance().closeDialog(producto);
    }
     
    public void selectProductFromDialog(Tinvproducto tinvproducto) {
        RequestContext.getCurrentInstance().closeDialog(tinvproducto);
    }

	public Collection<Tinvproducto> getProductos() {
		return productos;
	}

	public void setProductos(Collection<Tinvproducto> productos) {
		this.productos = productos;
	}

	public Tinvproducto getProducto() {
		return producto;
	}

	public void setProducto(Tinvproducto producto) {
		this.producto = producto;
	}
    
}
