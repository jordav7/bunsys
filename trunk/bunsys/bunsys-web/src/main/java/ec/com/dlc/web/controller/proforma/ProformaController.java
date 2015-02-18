package ec.com.dlc.web.controller.proforma;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import ec.com.dlc.bunsys.entity.administracion.Tadmconversionunidad;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetproforma;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.commons.resource.ContenidoMessages;
import ec.com.dlc.web.componentes.ArticuloComponent;
import ec.com.dlc.web.componentes.ClienteComponent;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.articulo.ArticuloDatamanager;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.cliente.ClienteDatamanager;
import ec.com.dlc.web.datamanager.login.LoginDatamanager;
import ec.com.dlc.web.datamanager.proforma.ProformaDatamanager;

@ManagedBean(name="proformaController")
@SessionScoped
public class ProformaController extends BaseController{

	@ManagedProperty(value="#{loginDatamanager}")
	private LoginDatamanager loginDatamanager;
	
	@ManagedProperty(value="#{proformaDatamanager}")
	private ProformaDatamanager proformaDatamanager;
	
	@ManagedProperty(value="#{clienteDatamanager}")
	private ClienteDatamanager clienteDatamanager;
	
	@ManagedProperty(value="#{articuloDatamanager}")
	private ArticuloDatamanager articuloDatamanager;

	@Inject
	private BunsysService bunsysService;
	
	@Override
	public BaseDatamanager getDatamanager() {
		return proformaDatamanager;
	}

	@Override
	public void inicializar() {
		Tfaccabproforma tfaccabproforma = new Tfaccabproforma();
		tfaccabproforma.setTfacdetproformas(new ArrayList<Tfacdetproforma>());
		proformaDatamanager.setTfaccabproforma(tfaccabproforma);
		//inicializa el objeto de busqueda
		proformaDatamanager.setTfaccabproformaSerch(new Tfaccabproforma());
		
		proformaDatamanager.setAerolineasCatalogo(bunsysService.buscarObtenerCatalogos(loginDatamanager.getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_aerolineas")));
		
		//componete del cliente
		Tfaccliente tfaccliente= new Tfaccliente();
		tfaccliente.setTsyspersona(new Tsyspersona());
		clienteDatamanager.setClienteserch(tfaccliente);
		clienteDatamanager.setFormaspagosCatalogo(bunsysService.buscarObtenerCatalogos(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
				                                                                       ContenidoMessages.getInteger("cod_catalogo_forma_pago")));
		clienteDatamanager.setEstadosCatalogo(bunsysService.buscarObtenerCatalogos(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
				                                                                    ContenidoMessages.getInteger("cod_catalogo_estado_cliente")));
		clienteDatamanager.setGruposCatalogo(bunsysService.buscarObtenerCatalogos(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
				                                                                  ContenidoMessages.getInteger("cod_catalogo_grupo_cliente")));
		clienteDatamanager.setTiposCatalogo(bunsysService.buscarObtenerCatalogos(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
				                                                                 ContenidoMessages.getInteger("cod_catalogo_tipo_cliente")));
		clienteDatamanager.setClienteComponente(new ClienteComponent(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania()));
		//objeto cliente 
		Tfaccliente tfacclienteG= new Tfaccliente();
		tfacclienteG.setTsyspersona(new Tsyspersona());
		proformaDatamanager.setTfaccliente(tfacclienteG);
		
		//Articulo
		articuloDatamanager.setArticuloSearch(new Tinvproducto());
		articuloDatamanager.setColorCatalogoColl(bunsysService.buscarObtenerCatalogos(articuloDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_color_articulo")));
		articuloDatamanager.setEstadoCatalogoColl(bunsysService.buscarObtenerCatalogos(articuloDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_estado_articulo")));
		articuloDatamanager.setArticuloComponente(new ArticuloComponent(articuloDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania()));
	
		proformaDatamanager.setTinvproducto(new Tinvproducto());
		
		//objeto detalle de la proforma
		proformaDatamanager.setTfacdetproforma(new Tfacdetproforma());
		proformaDatamanager.setAccionAux("G");
	}

	
	/**
	 * Metodo para agregar un producto al detalle de la factura
	 */
	public void agregarProducto(){
		//Busqueda del pices type segun el tipo de unidad de venta del articulo
		Tadmconversionunidad tadmconversionunidad=bunsysService.conversionArticulo(proformaDatamanager.getTinvproducto().getUnidadventacodigo(), proformaDatamanager.getTinvproducto().getUnidadventa());
		//articulo
		proformaDatamanager.getTfacdetproforma().setTinvproducto(proformaDatamanager.getTinvproducto());
		//pices type
		proformaDatamanager.getTfacdetproforma().setUnidadventa(proformaDatamanager.getTinvproducto().getUnidadventa());
		proformaDatamanager.getTfacdetproforma().setUnidadventacodigo(proformaDatamanager.getTinvproducto().getUnidadventacodigo());
		//eq full boxes
		proformaDatamanager.getTfacdetproforma().setEqfullboxes(tadmconversionunidad.getBoxes());
		//apta
		proformaDatamanager.getTfacdetproforma().setAtpa(proformaDatamanager.getTinvproducto().getAtpa());
		proformaDatamanager.getTfacdetproforma().setAtpacodigo(proformaDatamanager.getTinvproducto().getAtpacodigo());
		//nanduna
		proformaDatamanager.getTfacdetproforma().setNandina(proformaDatamanager.getTinvproducto().getNandina());
		//steamsbunch
		proformaDatamanager.getTfacdetproforma().setStemsbunch(tadmconversionunidad.getCantidadbunch());
		//total bunch
		proformaDatamanager.getTfacdetproforma().setTotalbunch(tadmconversionunidad.getTotalbunch());
		//total stems
		proformaDatamanager.getTfacdetproforma().setTotalstems(proformaDatamanager.getTfacdetproforma().getStemsbunch()*proformaDatamanager.getTfacdetproforma().getTotalbunch());
		//unit price
		proformaDatamanager.getTfacdetproforma().setPreciounitario(proformaDatamanager.getTinvproducto().getPreciounitario());
		//total price
		proformaDatamanager.getTfacdetproforma().setTotal(proformaDatamanager.getTfacdetproforma().getTotalstems()*proformaDatamanager.getTfacdetproforma().getPreciounitario());
		//compania
		proformaDatamanager.getTfacdetproforma().getPk().setCodigocompania(articuloDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		//se aniade a la lista
		proformaDatamanager.getTfaccabproforma().getTfacdetproformas().add(proformaDatamanager.getTfacdetproforma());
		calculos();
		proformaDatamanager.setTinvproducto(new Tinvproducto());
		proformaDatamanager.setTfacdetproforma(new Tfacdetproforma());
	}
	
	/**
	 * calculos totales
	 */
	private void calculos(){
		proformaDatamanager.getTfaccabproforma().setTotalpices(0d);
		proformaDatamanager.getTfaccabproforma().setTotaleqfullboxes(0d);
		proformaDatamanager.getTfaccabproforma().setTotalbunch(0d);
		proformaDatamanager.getTfaccabproforma().setTotalstems(0d);
		proformaDatamanager.getTfaccabproforma().setTotal(0d);
		for(Tfacdetproforma tfacdetproforma:proformaDatamanager.getTfaccabproforma().getTfacdetproformas()){
			proformaDatamanager.getTfaccabproforma().setTotalpices(tfacdetproforma.getCantidad()+proformaDatamanager.getTfaccabproforma().getTotalpices());
			proformaDatamanager.getTfaccabproforma().setTotaleqfullboxes(tfacdetproforma.getEqfullboxes()+proformaDatamanager.getTfaccabproforma().getTotaleqfullboxes());
			
			proformaDatamanager.getTfaccabproforma().setTotalbunch(tfacdetproforma.getTotalbunch()+proformaDatamanager.getTfaccabproforma().getTotalbunch());
			proformaDatamanager.getTfaccabproforma().setTotalstems(tfacdetproforma.getTotalstems()+proformaDatamanager.getTfaccabproforma().getTotalstems());
			proformaDatamanager.getTfaccabproforma().setTotal(tfacdetproforma.getTotal()+proformaDatamanager.getTfaccabproforma().getTotal());
		}
	}
	
	/**
	 * Metodo para eliminar un registro del detalle de la factura
	 * @param tfacdetfactura
	 */
	public void eliminarArticulo(Tfacdetproforma tfacdetproforma){
		proformaDatamanager.getTfaccabproforma().getTfacdetproformas().remove(tfacdetproforma);
		calculos();
	}
	
	
	public void grabar(){
		try {
			if(StringUtils.isNotBlank(proformaDatamanager.getTfaccabproforma().getAirline())){
				proformaDatamanager.getTfaccabproforma().setAirlinecodigo(ContenidoMessages.getInteger("cod_catalogo_aerolineas"));
			}
			proformaDatamanager.getTfaccabproforma().getPk().setCodigocompania(articuloDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
			bunsysService.guardarProforma(proformaDatamanager.getTfaccabproforma(),proformaDatamanager.getAccionAux());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ContenidoMessages.getString("msg_info_proforma"), ContenidoMessages.getString("msg_info_proforma")));
		} catch(Throwable e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ContenidoMessages.getString("msg_error_proforma"), ContenidoMessages.getString("msg_error_proforma")));
		}
	}
	
	public void cancelar(){
		Tfaccabproforma tfaccabproforma = new Tfaccabproforma();
		tfaccabproforma.setTfacdetproformas(new ArrayList<Tfacdetproforma>());
		proformaDatamanager.setTfaccabproforma(tfaccabproforma);
		//inicializa el objeto de busqueda
		proformaDatamanager.setTfaccabproformaSerch(new Tfaccabproforma());
		proformaDatamanager.setTinvproducto(new Tinvproducto());
		//objeto detalle de la proforma
		proformaDatamanager.setTfacdetproforma(new Tfacdetproforma());
		//objeto cliente 
		Tfaccliente tfacclienteG= new Tfaccliente();
		tfacclienteG.setTsyspersona(new Tsyspersona());
		proformaDatamanager.setTfaccliente(tfacclienteG);
	}
	
	public LoginDatamanager getLoginDatamanager() {
		return loginDatamanager;
	}

	public void setLoginDatamanager(LoginDatamanager loginDatamanager) {
		this.loginDatamanager = loginDatamanager;
	}

	public ProformaDatamanager getProformaDatamanager() {
		return proformaDatamanager;
	}

	public void setProformaDatamanager(ProformaDatamanager proformaDatamanager) {
		this.proformaDatamanager = proformaDatamanager;
	}

	public ClienteDatamanager getClienteDatamanager() {
		return clienteDatamanager;
	}

	public void setClienteDatamanager(ClienteDatamanager clienteDatamanager) {
		this.clienteDatamanager = clienteDatamanager;
	}

	public ArticuloDatamanager getArticuloDatamanager() {
		return articuloDatamanager;
	}

	public void setArticuloDatamanager(ArticuloDatamanager articuloDatamanager) {
		this.articuloDatamanager = articuloDatamanager;
	}

	
}
