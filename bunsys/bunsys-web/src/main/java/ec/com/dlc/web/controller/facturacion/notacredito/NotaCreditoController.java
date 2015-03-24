package ec.com.dlc.web.controller.facturacion.notacredito;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.com.dlc.bunsys.entity.administracion.pk.TadmcompaniaPK;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetfactura;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.bunsys.util.log.FacturacionLogger;
import ec.com.dlc.web.commons.resource.ContenidoMessages;
import ec.com.dlc.web.componentes.ArticuloComponent;
import ec.com.dlc.web.componentes.ClienteComponent;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.articulo.ArticuloDatamanager;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.cliente.ClienteDatamanager;
import ec.com.dlc.web.datamanager.factura.notacredito.NotaCreditoDatamanager;
import ec.com.dlc.web.util.jsf.MessagesUtil;

@ManagedBean(name="notaCreditoController")
@ViewScoped
public class NotaCreditoController extends BaseController {

	@ManagedProperty(value="#{notaCreditoDatamanager}")
	private NotaCreditoDatamanager notaCreditoDatamanager;
	
	@ManagedProperty(value="#{clienteDatamanager}")
	private ClienteDatamanager clienteDatamanager;
	
	@ManagedProperty(value="#{articuloDatamanager}")
	private ArticuloDatamanager articuloDatamanager;
	
	@Inject
	private BunsysService bunsysService;
	
	@Override
	public BaseDatamanager getDatamanager() {
		return notaCreditoDatamanager;
	}

	@Override
	@PostConstruct
	public void inicializar() {
//		if(!notaCreditoDatamanager.isEdicion()){
//			notaCreditoDatamanager.setCabdevoluciones(new Tfaccabdevolucione());
//			notaCreditoDatamanager.getCabdevoluciones().setTfaccliente(new Tfaccliente());
//			notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().setTsyspersona(new Tsyspersona());
//		}
		Tfaccliente tfaccliente= new Tfaccliente();
		tfaccliente.setTsyspersona(new Tsyspersona());
		clienteDatamanager.setClienteserch(tfaccliente);
		//Articulo
		articuloDatamanager.setArticuloSearch(new Tinvproducto());
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
		
		articuloDatamanager.setColorCatalogoColl(bunsysService.buscarObtenerCatalogos(articuloDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_color_articulo")));
		articuloDatamanager.setEstadoCatalogoColl(bunsysService.buscarObtenerCatalogos(articuloDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_estado_articulo")));
		articuloDatamanager.setArticuloComponente(new ArticuloComponent(articuloDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania()));
		this.notaCreditoDatamanager.setDetdevolucionesColl(new ArrayList<Tfacdetdevolucione>());
		TadmcompaniaPK compPk = new TadmcompaniaPK();
		compPk.setCodigocompania(notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		this.notaCreditoDatamanager.setCompania(bunsysService.buscarCompania(compPk));
	}
	
	public void crear() {
	}
	
	public void busquedaCliente() {
		try{
			Tfaccliente cliente = bunsysService.buscarPorIdentificacion(this.notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getTsyspersona().getIdentificacion());
			if(cliente != null){
				this.notaCreditoDatamanager.getCabdevoluciones().setTfaccliente(cliente);
			} 
		} catch (Throwable e) {
			MessagesUtil.showErrorMessage("Error al consultar el cliente");
			FacturacionLogger.log.error(e.getMessage(), e);
		}
	}
	
	public void busquedaFactura() {
		try{
			Tfaccabfactura factura = bunsysService.obtenerFactura(notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), notaCreditoDatamanager.getCabdevoluciones().getNumerofactura());
			completaDatosNotaCredito(factura);
		} catch (Throwable e) {
			MessagesUtil.showErrorMessage("Error al consultar, la factura no existe");
			FacturacionLogger.log.error(e.getMessage(), e);
		}
	}
	
	private void completaDatosNotaCredito(Tfaccabfactura factura){
		notaCreditoDatamanager.getCabdevoluciones().setIva(factura.getIva());
		notaCreditoDatamanager.getCabdevoluciones().setPocentajeirbpnr(factura.getPorcentajeirbpnr());
		notaCreditoDatamanager.getCabdevoluciones().setPorcentajedesc(factura.getPorcentajedesc());
		notaCreditoDatamanager.getCabdevoluciones().setPorcentajeice(factura.getPorcentajeice());
		notaCreditoDatamanager.getCabdevoluciones().setPorcentajeiva(factura.getPorcentajeiva());
		notaCreditoDatamanager.getCabdevoluciones().setSubtotalbase(factura.getSubtotalbase());
		notaCreditoDatamanager.getCabdevoluciones().setSubtotalexcentoiva(factura.getSubtotalexcentoiva());
		notaCreditoDatamanager.getCabdevoluciones().setSubtotaliva(factura.getSubtotaliva());
		notaCreditoDatamanager.getCabdevoluciones().setSubtotalneto(factura.getSubtotalneto());
		notaCreditoDatamanager.getCabdevoluciones().setSubtotalnoiva(factura.getSubtotalnoiva());
		notaCreditoDatamanager.getCabdevoluciones().setTotal(factura.getTotal());
		notaCreditoDatamanager.getCabdevoluciones().setTotaldescuento(factura.getTotaldescuento());
		notaCreditoDatamanager.getCabdevoluciones().setValorice(factura.getValorice());
		notaCreditoDatamanager.getCabdevoluciones().setValorirbpnr(factura.getValorirbpnr());
		if(factura.getTfacdetfacturas() != null && !factura.getTfacdetfacturas().isEmpty()){
			for (Tfacdetfactura detalleFactura : factura.getTfacdetfacturas()) {
				Tfacdetdevolucione detalleDevolucion = new Tfacdetdevolucione();
				detalleDevolucion.setApta(detalleFactura.getAtpa());
				detalleDevolucion.setAptacodigo(detalleFactura.getAtpacodigo());
				detalleDevolucion.setCantidad(detalleFactura.getCantidad());
				detalleDevolucion.setCodigoproductos(detalleFactura.getCodigoproductos());
				detalleDevolucion.setDescuento(detalleFactura.getDescuento());
				detalleDevolucion.setIce(detalleFactura.getIce());
				detalleDevolucion.setIcecodigo(detalleFactura.getIcecodigo());
				detalleDevolucion.setIva(detalleFactura.getIva());
				detalleDevolucion.setIvacodigo(detalleFactura.getIvacodigo());
				detalleDevolucion.setIrbpnr(detalleFactura.getIrbpnr());
				detalleDevolucion.setIrbpnrcodigo(detalleFactura.getIrbpnrcodigo());
				detalleDevolucion.setNandina(detalleFactura.getNandina());
				detalleDevolucion.setPreciounitario(detalleFactura.getPreciounitario());
				detalleDevolucion.setTadmatpa(detalleFactura.getTadmatpa());
				detalleDevolucion.setTadmice(detalleFactura.getTadmice());
				detalleDevolucion.setTadmirbpnr(detalleFactura.getTadmirbpnr());
				detalleDevolucion.setTadmiva(detalleFactura.getTadmiva());
				detalleDevolucion.setTadmunidadventa(detalleFactura.getTadmunidadventa());
				detalleDevolucion.setTinvproducto(detalleFactura.getTinvproducto());
				notaCreditoDatamanager.getDetdevolucionesColl().add(detalleDevolucion);
			}
		} else{
			MessagesUtil.showErrorMessage("La factura no contiene detalles, no puede existir una factura sin detalles");
		}
	}

	public NotaCreditoDatamanager getNotaCreditoDatamanager() {
		return notaCreditoDatamanager;
	}

	public void setNotaCreditoDatamanager(
			NotaCreditoDatamanager notaCreditoDatamanager) {
		this.notaCreditoDatamanager = notaCreditoDatamanager;
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
