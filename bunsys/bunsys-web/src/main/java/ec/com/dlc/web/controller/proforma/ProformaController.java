package ec.com.dlc.web.controller.proforma;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import ec.com.dlc.bunsys.entity.facturacion.Tfaccabproforma;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
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
import ec.com.dlc.web.datamanager.proforma.ProformaDatamanager;
import ec.com.dlc.web.util.jsf.MessagesUtil;

@ManagedBean(name="proformaController")
@SessionScoped
public class ProformaController extends BaseController{

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
		
		//componete del cliente
		Tfaccliente tfaccliente= new Tfaccliente();
		tfaccliente.setTsyspersona(new Tsyspersona());
		clienteDatamanager.setClienteserch(tfaccliente);
		//Articulo
		articuloDatamanager.setArticuloSearch(new Tinvproducto());
		proformaDatamanager.setTinvproducto(new Tinvproducto());
		//objeto detalle de la proforma
		proformaDatamanager.setTfacdetproforma(new Tfacdetproforma());
		//catalogos
		clienteDatamanager.setFormaspagosCatalogo(bunsysService.buscarObtenerCatalogos(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
                	 ContenidoMessages.getInteger("cod_catalogo_forma_pago")));
		clienteDatamanager.setEstadosCatalogo(bunsysService.buscarObtenerCatalogos(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
		             ContenidoMessages.getInteger("cod_catalogo_estado_cliente")));
		clienteDatamanager.setGruposCatalogo(bunsysService.buscarObtenerCatalogos(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
		             ContenidoMessages.getInteger("cod_catalogo_grupo_cliente")));
		clienteDatamanager.setTiposCatalogo(bunsysService.buscarObtenerCatalogos(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
		             ContenidoMessages.getInteger("cod_catalogo_tipo_cliente")));
		
		clienteDatamanager.setClienteComponente(new ClienteComponent(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania()));
		
		proformaDatamanager.setAerolineasCatalogo(bunsysService.buscarObtenerCatalogos(proformaDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_aerolineas")));
		proformaDatamanager.setCatalogoPicesType(bunsysService.buscarObtenerCatalogos(proformaDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_pices_type")));
		
		articuloDatamanager.setColorCatalogoColl(bunsysService.buscarObtenerCatalogos(articuloDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_color_articulo")));
		articuloDatamanager.setEstadoCatalogoColl(bunsysService.buscarObtenerCatalogos(articuloDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_estado_articulo")));
		articuloDatamanager.setArticuloComponente(new ArticuloComponent(articuloDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania()));
		
	}

	
	/**
	 * Metodo para agregar un producto al detalle de la factura
	 */
	public void agregarProducto(){
		if(proformaDatamanager.getTinvproducto()==null ||
				proformaDatamanager.getTinvproducto().getPk()==null
				|| proformaDatamanager.getTinvproducto().getPk().getCodigoproductos()==null){
			FacesContext context = FacesContext.getCurrentInstance();  
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"AGREGAR PRODUCTO","ESCOJA UN PRODUCTO"));
			return;
		}
		if(proformaDatamanager.getTfaccliente()==null || proformaDatamanager.getTfaccliente().getPk()==null || proformaDatamanager.getTfaccliente().getPk().getCodigocliente()==null){
			MessagesUtil.showErrorMessage("Ingrese el cliente"); 
			return;
		}
		proformaDatamanager.getTfacdetproforma().setCantidad(1d);
		proformaDatamanager.getTfacdetproforma().getAditionalProperties().put("cantidadaux",1d);
		//articulo
		proformaDatamanager.getTfacdetproforma().setTinvproducto(proformaDatamanager.getTinvproducto());
		proformaDatamanager.getTfacdetproforma().setCodigoproductos(proformaDatamanager.getTinvproducto().getPk().getCodigoproductos());
		//apta
		proformaDatamanager.getTfacdetproforma().setAtpa(proformaDatamanager.getTinvproducto().getAtpa());
		proformaDatamanager.getTfacdetproforma().setAtpacodigo(proformaDatamanager.getTinvproducto().getAtpacodigo());
		//unit price
		proformaDatamanager.getTfacdetproforma().setPreciounitario(proformaDatamanager.getTinvproducto().getPreciounitario());
		//total price
		proformaDatamanager.getTfacdetproforma().setTotal(proformaDatamanager.getTfacdetproforma().getTotal()*proformaDatamanager.getTfacdetproforma().getPreciounitario());
		//compania
		proformaDatamanager.getTfacdetproforma().getPk().setCodigocompania(articuloDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		//iva
		proformaDatamanager.getTfacdetproforma().setIva(proformaDatamanager.getTinvproducto().getIva());
		proformaDatamanager.getTfacdetproforma().setIvacodigo(proformaDatamanager.getTinvproducto().getIvacodigo());
		//ice
		proformaDatamanager.getTfacdetproforma().setIce(proformaDatamanager.getTinvproducto().getIce());
		proformaDatamanager.getTfacdetproforma().setIcecodigo(proformaDatamanager.getTinvproducto().getIcecodigo());
		//ibpnr
		proformaDatamanager.getTfacdetproforma().setIrbpnr(proformaDatamanager.getTinvproducto().getIrbpnr());
		proformaDatamanager.getTfacdetproforma().setIrbpnrcodigo(proformaDatamanager.getTinvproducto().getIrbpnrcodigo());
		//se aniade a la lista
		proformaDatamanager.getTfaccabproforma().getTfacdetproformas().add(proformaDatamanager.getTfacdetproforma());
		calculos();
		proformaDatamanager.setTinvproducto(new Tinvproducto());
		proformaDatamanager.setTfacdetproforma(new Tfacdetproforma());
	}
	
	/**
	 * calculos totales
	 */
	public void calculos(){
		proformaDatamanager.getTfaccabproforma().setTotal(new BigDecimal(0));
		//
		proformaDatamanager.getTfaccabproforma().setSubtotalnoiva(new BigDecimal(0));
		proformaDatamanager.getTfaccabproforma().setSubtotaliva(new BigDecimal(0));
		proformaDatamanager.getTfaccabproforma().setIva(new BigDecimal(0));
		proformaDatamanager.getTfaccabproforma().setSubtotalexcentoiva(new BigDecimal(0));
		proformaDatamanager.getTfaccabproforma().setSubtotalneto(new BigDecimal(0));
		for(Tfacdetproforma tfacdetproforma:proformaDatamanager.getTfaccabproforma().getTfacdetproformas()){
			//calculos
			//--subtotal 0%
			if(tfacdetproforma.getIva().equals("0") && tfacdetproforma.getIvacodigo().equals(9)){
				proformaDatamanager.getTfaccabproforma().setSubtotalnoiva(proformaDatamanager.getTfaccabproforma().getSubtotalnoiva().add(new BigDecimal(tfacdetproforma.getTotal())));
			}
			//--sbtotal iva 12%
			if(	tfacdetproforma.getIva().equals("2") && tfacdetproforma.getIvacodigo().equals(9)){
				proformaDatamanager.getTfaccabproforma().setSubtotaliva( proformaDatamanager.getTfaccabproforma().getSubtotaliva().add(new BigDecimal(tfacdetproforma.getTotal())));
				//validamos si tiene descuento
				if(proformaDatamanager.getTfaccliente().getPorcentajedescuento()!=null && proformaDatamanager.getTfaccliente().getPorcentajedescuento()>0){
					//descuento subtotal * %descuento
					BigDecimal descuento=proformaDatamanager.getTfaccabproforma().getSubtotaliva().multiply(new BigDecimal(proformaDatamanager.getTfaccliente().getPorcentajedescuento()/100));
					tfacdetproforma.setDescuento(descuento);
					//total -descunto
					Double totaldesc=tfacdetproforma.getTotal()-descuento.doubleValue();
					BigDecimal descuentoMenTotal=new BigDecimal(totaldesc);
					//descuentoMenTotal * iva 12%
					BigDecimal iva=descuentoMenTotal.multiply(new BigDecimal(0.12));
					//se suma
					proformaDatamanager.getTfaccabproforma().setIva(proformaDatamanager.getTfaccabproforma().getIva().add(iva));
				}else{
					//iva 12%
					Double iva=tfacdetproforma.getTotal()*0.12;
					proformaDatamanager.getTfaccabproforma().setIva(proformaDatamanager.getTfaccabproforma().getIva().add(new BigDecimal(iva)));
				}
			}
			//--subtotal excento de iva
			if(tfacdetproforma.getIva().equals("7") && tfacdetproforma.getIvacodigo().equals(9)){
				proformaDatamanager.getTfaccabproforma().setSubtotalexcentoiva(proformaDatamanager.getTfaccabproforma().getSubtotalexcentoiva().add(new BigDecimal(tfacdetproforma.getTotal())));
			}
			//--subtotal neto
			proformaDatamanager.getTfaccabproforma().setSubtotalneto(proformaDatamanager.getTfaccabproforma().getSubtotalneto().add(new BigDecimal(tfacdetproforma.getTotal())));
		}
		//calculos finales
		if(proformaDatamanager.getTfaccliente().getPorcentajedescuento()!=null &&
				proformaDatamanager.getTfaccliente().getPorcentajedescuento()>0){
			//-total descuento
			proformaDatamanager.getTfaccabproforma().setTotaldescuento(proformaDatamanager.getTfaccabproforma().getSubtotalneto().multiply(
					new BigDecimal(proformaDatamanager.getTfaccliente().getPorcentajedescuento()/100)));
			//porcentaje decuento
			proformaDatamanager.getTfaccabproforma().setPorcentajedesc(new BigDecimal(proformaDatamanager.getTfaccliente().getPorcentajedescuento()));
		}else{
			proformaDatamanager.getTfaccabproforma().setTotaldescuento(new BigDecimal(0));
		}
		//total=totalneto-descuentos+iva
		BigDecimal total=proformaDatamanager.getTfaccabproforma().getSubtotalneto().subtract(proformaDatamanager.getTfaccabproforma().getTotaldescuento()).add(proformaDatamanager.getTfaccabproforma().getIva());
		proformaDatamanager.getTfaccabproforma().setTotal(total);
	}
	
	public void cambioCantidad(Tfacdetproforma detalle){
		//calculos
		//total price
		detalle.setTotal(detalle.getTotal()*detalle.getPreciounitario());
		calculos();
		detalle.getAditionalProperties().put("cantidadaux",detalle.getCantidad());
	}
	
	public void cambioeqfullboxes(Tfacdetproforma tfacdetproforma){
		calculos();
	}
	
	public void cambiounitprice(Tfacdetproforma tfacdetproforma){
		tfacdetproforma.setTotal(tfacdetproforma.getTotal()*tfacdetproforma.getPreciounitario());
		calculos();
	}
	
	public void cambiototal(Tfacdetproforma tfacdetproforma){
		calculos();
	}
	/**
	 * Metodo para eliminar un registro del detalle de la factura
	 * @param tfacdetfactura
	 */
	public void eliminarArticulo(Tfacdetproforma tfacdetproforma){
		if(tfacdetproforma.getPk().getCodigodetalleprof()!=null){
			proformaDatamanager.getDetproformasEliminar().add(tfacdetproforma);
		}
		proformaDatamanager.getTfaccabproforma().getTfacdetproformas().remove(tfacdetproforma);
		calculos();
	}
	
	
	public void grabar(){
		try {
			if(proformaDatamanager.getTfaccliente()==null || proformaDatamanager.getTfaccliente().getPk()==null || proformaDatamanager.getTfaccliente().getPk().getCodigocliente()==null){
				MessagesUtil.showErrorMessage("Ingrese el cliente"); 
				return;
			}
			if(proformaDatamanager.getTfaccabproforma().getTfacdetproformas()==null || proformaDatamanager.getTfaccabproforma().getTfacdetproformas().size()==0){
				MessagesUtil.showErrorMessage("No ha ingresado el detalle de la factura");
				return;
			}
			//compania
			proformaDatamanager.getTfaccabproforma().getPk().setCodigocompania(articuloDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
			
			//cliente
			proformaDatamanager.getTfaccabproforma().setCodigocliente(proformaDatamanager.getTfaccliente().getPk().getCodigocliente());
			bunsysService.guardarProforma(proformaDatamanager.getTfaccabproforma(),proformaDatamanager.getAccionAux(),proformaDatamanager.getDetproformasEliminar());
			proformaDatamanager.setDetproformasEliminar(new ArrayList<Tfacdetproforma>());
			MessagesUtil.showInfoMessage(ContenidoMessages.getString("msg_info_proforma"));
		} catch(Throwable e){
			MessagesUtil.showErrorMessage( ContenidoMessages.getString("msg_error_proforma"));
		}
	}
	
	public String cancelar(){
		Tfaccabproforma tfaccabproforma = new Tfaccabproforma();
		tfaccabproforma.setCountrycode(ContenidoMessages.getString("cod_country"));
		tfaccabproforma.setArea(ContenidoMessages.getString("cod_area"));
		tfaccabproforma.setTfacdetproformas(new ArrayList<Tfacdetproforma>());
		proformaDatamanager.setTfaccabproforma(tfaccabproforma);
		//inicializa el objeto de busqueda
		//proformaDatamanager.setTfaccabproformaSerch(new Tfaccabproforma());
		proformaDatamanager.setTinvproducto(new Tinvproducto());
		//objeto detalle de la proforma
		proformaDatamanager.setTfacdetproforma(new Tfacdetproforma());
		return "/pages/factura/proforma/buscarProforma?faces-redirect=true";
	}
	
//	public static BigDecimal round(BigDecimal d) {
//		  int mode = BigDecimal.ROUND_UP ;
//		  return d.setScale(2, mode);
//		}
	
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
