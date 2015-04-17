package ec.com.dlc.web.controller.facturacion.notacredito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmconversionunidad;
import ec.com.dlc.bunsys.entity.administracion.pk.TadmcompaniaPK;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetfactura;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.bunsys.util.ComprobantesUtil;
import ec.com.dlc.bunsys.util.Utils;
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
		this.notaCreditoDatamanager.setCargueraColl(bunsysService.buscarObtenerCatalogos(notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_cargueras")));
		this.notaCreditoDatamanager.setDistritoVueloColl(bunsysService.buscarObtenerCatalogos(notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_distrito_vuelo")));
		this.notaCreditoDatamanager.setFobColl(bunsysService.buscarObtenerCatalogos(notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_tipo_fob")));
		this.notaCreditoDatamanager.setAerolineaColl(bunsysService.buscarObtenerCatalogos(notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_aerolineas")));
		this.notaCreditoDatamanager.setPicesTypeColl(bunsysService.buscarObtenerCatalogos(notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_pices_type")));
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
			if(factura == null){
				Integer valorSecuencia = ComprobantesUtil.getInstancia().obtenerSecuenciaActualNC(notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
				notaCreditoDatamanager.setCabdevoluciones(new Tfaccabdevolucione());
				notaCreditoDatamanager.getCabdevoluciones().setFechadevolucion(new Date());
				notaCreditoDatamanager.getCabdevoluciones().setTfaccliente(new Tfaccliente());
				notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().setTsyspersona(new Tsyspersona());
				notaCreditoDatamanager.getCabdevoluciones().getPk().setNumerodevoluciones(ComprobantesUtil.getInstancia().getsecuencia(valorSecuencia.toString(), 9));
				notaCreditoDatamanager.setDetdevolucionesColl(new ArrayList<Tfacdetdevolucione>());
			} else {
				completaDatosNotaCredito(factura);
			}
		} catch (Throwable e) {
			MessagesUtil.showErrorMessage("Error al consultar, la factura no existe");
			FacturacionLogger.log.error(e.getMessage(), e);
		}
	}
	
	private void completaDatosNotaCredito(Tfaccabfactura factura){
		if(factura.getAirline() != null){
			notaCreditoDatamanager.getCabdevoluciones().setAirline(factura.getAirline());
			notaCreditoDatamanager.getCabdevoluciones().setAirlinecodigo(factura.getAirlinecodigo());
		}
		if(factura.getArea() != null){
			notaCreditoDatamanager.getCabdevoluciones().setArea(factura.getArea());
		}
		if(factura.getCarguera() != null){
			notaCreditoDatamanager.getCabdevoluciones().setCarguera(factura.getCarguera());
			notaCreditoDatamanager.getCabdevoluciones().setCargueracodigo(factura.getCargueracodigo());
		}
		if(factura.getConsignee() != null){
			notaCreditoDatamanager.getCabdevoluciones().setConsignee(factura.getConsignee());
		}
		if(factura.getCountrycode() != null){
			notaCreditoDatamanager.getCabdevoluciones().setCountrycode(factura.getCountrycode());
		}
		if(factura.getDae() != null){
			notaCreditoDatamanager.getCabdevoluciones().setDae(factura.getDae());
		}
		if(factura.getDistritovuelo() != null){
			notaCreditoDatamanager.getCabdevoluciones().setDistritovuelo(factura.getDistritovuelo());
			notaCreditoDatamanager.getCabdevoluciones().setDistritovuelocodigo(factura.getDistritovuelocodigo());
			notaCreditoDatamanager.getCabdevoluciones().addAditionalProperty("codigoaerolinea", factura.getTadmdistritovuelo().getValor() + "-");
		}
		if(factura.getFarmcode() != null){
			notaCreditoDatamanager.getCabdevoluciones().setFarmcode(factura.getFarmcode());
		}
		if(factura.getFechaembarque() != null){
			notaCreditoDatamanager.getCabdevoluciones().setFechaembarque(factura.getFechaembarque());
		}
		if(factura.getFixedprice() != null){
			notaCreditoDatamanager.getCabdevoluciones().setFixedprice(factura.getFixedprice());
		}
		if(factura.getFob() != null){
			notaCreditoDatamanager.getCabdevoluciones().setFob(factura.getFob());
			notaCreditoDatamanager.getCabdevoluciones().setFobcodigo(factura.getFobcodigo());
		}
		if(factura.getFreightforwarder() != null){
			notaCreditoDatamanager.getCabdevoluciones().setFreightforwarder(factura.getFreightforwarder());
		}
		if(factura.getHouseawb() != null){
			notaCreditoDatamanager.getCabdevoluciones().setHouseawb(factura.getHouseawb());
		}
		if(factura.getMasterawb() != null){
			notaCreditoDatamanager.getCabdevoluciones().setMasterawb(factura.getMasterawb());
		}
		if(factura.getReferendo() != null){
			notaCreditoDatamanager.getCabdevoluciones().setReferendo(factura.getReferendo());
		}
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
		notaCreditoDatamanager.setDetdevolucionesColl(new ArrayList<Tfacdetdevolucione>());
		if(factura.getTfacdetfacturas() != null && !factura.getTfacdetfacturas().isEmpty()){
			for (Tfacdetfactura detalleFactura : factura.getTfacdetfacturas()) {
				Tfacdetdevolucione detalleDevolucion = new Tfacdetdevolucione();
				detalleDevolucion.setApta(detalleFactura.getAtpa());
				detalleDevolucion.setAptacodigo(detalleFactura.getAtpacodigo());
				detalleDevolucion.setCantidad(detalleFactura.getCantidad());
				detalleDevolucion.addAditionalProperty("cantidadaux", 1D);
				detalleDevolucion.setTotalstems(detalleFactura.getTotalstems());
				detalleDevolucion.setEqfullboxes(detalleFactura.getEqfullboxes());
				detalleDevolucion.setStemsbunch(detalleFactura.getStemsbunch());
				detalleDevolucion.setTotalbunch(detalleFactura.getTotalbunch());
				detalleDevolucion.setTotal(detalleFactura.getTotal());
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
				detalleDevolucion.setUnidadventa(detalleFactura.getUnidadventa());
				detalleDevolucion.setUnidadventacodigo(detalleFactura.getUnidadventacodigo());
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
	
	public void guardarFirmarEnviar() {
		try {
			bunsysService.guardarNotaCredito(notaCreditoDatamanager.getCabdevoluciones(), notaCreditoDatamanager.getDetdevolucionesColl(), notaCreditoDatamanager.getCompania(), notaCreditoDatamanager.getCabdevoluciones().getNumerofactura());
		} catch (Throwable e) {
			MessagesUtil.showErrorMessage(e.getMessage());
		}
	}
	
	public void changeDistritoVuelo() {
		notaCreditoDatamanager.getCabdevoluciones().addAditionalProperty("codigoaerolinea", "");
		for(Tadmcatalogo item:notaCreditoDatamanager.getDistritoVueloColl()){
			if(item.getPk().getCodigocatalogo().equals(notaCreditoDatamanager.getCabdevoluciones().getDistritovuelo())){
				notaCreditoDatamanager.getCabdevoluciones().addAditionalProperty("codigoaerolinea", item.getValor()+"-");
			}
		}
	}
	
	public void agregarDetalle() {
		Tfacdetdevolucione detalleDevolucion = new Tfacdetdevolucione();
		detalleDevolucion.setTinvproducto(notaCreditoDatamanager.getTinvproducto());
		if(notaCreditoDatamanager.getTinvproducto()==null ||
				notaCreditoDatamanager.getTinvproducto().getPk()==null
				|| notaCreditoDatamanager.getTinvproducto().getPk().getCodigoproductos()==null){
			MessagesUtil.showErrorMessage(ContenidoMessages.getString("msg_error_seleccione_producto"));
			return;
		}
		if(notaCreditoDatamanager.getCabdevoluciones().getTfaccliente()==null || notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getPk()==null || notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getPk().getCodigocliente()==null){
			MessagesUtil.showErrorMessage(ContenidoMessages.getString("msg_error_sin_cliente"));
			return;
		}
		//Busqueda del pices type segun el tipo de unidad de venta del articulo
		Tadmconversionunidad tadmconversionunidad=bunsysService.conversionArticulo(notaCreditoDatamanager.getTinvproducto().getUnidadventacodigo(), notaCreditoDatamanager.getTinvproducto().getUnidadventa());
		//catidad
		detalleDevolucion.setCantidad(new BigDecimal(1));
		detalleDevolucion.getAditionalProperties().put("cantidadaux", 1d);
		//articulo
		detalleDevolucion.setTinvproducto(notaCreditoDatamanager.getTinvproducto());
		detalleDevolucion.setCodigoproductos(notaCreditoDatamanager.getTinvproducto().getPk().getCodigoproductos());
		//pices type
		detalleDevolucion.setUnidadventa(notaCreditoDatamanager.getTinvproducto().getUnidadventa());
		detalleDevolucion.setUnidadventacodigo(notaCreditoDatamanager.getTinvproducto().getUnidadventacodigo());
		//eq full boxes
		detalleDevolucion.setEqfullboxes(new BigDecimal(tadmconversionunidad.getBoxes()));
		//apta
		detalleDevolucion.setApta(notaCreditoDatamanager.getTinvproducto().getAtpa());
		detalleDevolucion.setAptacodigo(notaCreditoDatamanager.getTinvproducto().getAtpacodigo());
		//nanduna
		detalleDevolucion.setNandina(notaCreditoDatamanager.getTinvproducto().getNandina());
		//steamsbunch
		detalleDevolucion.setStemsbunch(new BigDecimal(tadmconversionunidad.getCantidadbunch()));
		//total bunch
		detalleDevolucion.setTotalbunch(new BigDecimal(tadmconversionunidad.getTotalbunch()));
		//total stems
		detalleDevolucion.setTotalstems(detalleDevolucion.getStemsbunch().multiply(detalleDevolucion.getTotalbunch()));
		//unit price
		detalleDevolucion.setPreciounitario(new BigDecimal(notaCreditoDatamanager.getTinvproducto().getPreciounitario()));
		//total price
		detalleDevolucion.setTotal(detalleDevolucion.getTotalstems().multiply(detalleDevolucion.getPreciounitario()));
		//compania
		detalleDevolucion.getPk().setCodigocompania(notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		//iva
		detalleDevolucion.setIva(notaCreditoDatamanager.getTinvproducto().getIva());
		detalleDevolucion.setIvacodigo(notaCreditoDatamanager.getTinvproducto().getIvacodigo());
		//ice
		detalleDevolucion.setIce(notaCreditoDatamanager.getTinvproducto().getIce());
		detalleDevolucion.setIcecodigo(notaCreditoDatamanager.getTinvproducto().getIcecodigo());
		//irbpnr
		detalleDevolucion.setIrbpnr(notaCreditoDatamanager.getTinvproducto().getIrbpnr());
		detalleDevolucion.setIrbpnrcodigo(notaCreditoDatamanager.getTinvproducto().getIrbpnrcodigo());
		//se aniade a la lista
		notaCreditoDatamanager.getDetdevolucionesColl().add(detalleDevolucion);
		
		calculos();
		notaCreditoDatamanager.setTinvproducto(new Tinvproducto());
	}
	
	public void calculos(){
		notaCreditoDatamanager.getCabdevoluciones().setTotalpices(new BigDecimal(0));
		notaCreditoDatamanager.getCabdevoluciones().setTotaleqfullboxes(new BigDecimal(0));
		notaCreditoDatamanager.getCabdevoluciones().setTotalbunch(new BigDecimal(0));
		notaCreditoDatamanager.getCabdevoluciones().setTotalstems(new BigDecimal(0));
		notaCreditoDatamanager.getCabdevoluciones().setTotal(new BigDecimal(0));
		//
		notaCreditoDatamanager.getCabdevoluciones().setSubtotalnoiva(new BigDecimal(0));
		notaCreditoDatamanager.getCabdevoluciones().setSubtotaliva(new BigDecimal(0));
		notaCreditoDatamanager.getCabdevoluciones().setIva(new BigDecimal(0));
		notaCreditoDatamanager.getCabdevoluciones().setSubtotalexcentoiva(new BigDecimal(0));
		notaCreditoDatamanager.getCabdevoluciones().setSubtotalneto(new BigDecimal(0));
		for(Tfacdetdevolucione detalle:notaCreditoDatamanager.getDetdevolucionesColl()){
			//total pices
			notaCreditoDatamanager.getCabdevoluciones().setTotalpices(detalle.getCantidad().add(notaCreditoDatamanager.getCabdevoluciones().getTotalpices()));
			//total eqfullboxes
			notaCreditoDatamanager.getCabdevoluciones().setTotaleqfullboxes(detalle.getEqfullboxes().add(notaCreditoDatamanager.getCabdevoluciones().getTotaleqfullboxes()));
			//total bunch
			notaCreditoDatamanager.getCabdevoluciones().setTotalbunch(detalle.getTotalbunch().add(notaCreditoDatamanager.getCabdevoluciones().getTotalbunch()));
			//total stems
			notaCreditoDatamanager.getCabdevoluciones().setTotalstems(detalle.getTotalstems().add(notaCreditoDatamanager.getCabdevoluciones().getTotalstems()));
			//calculos
			//--subtotal 0%
			if(detalle.getIva().equals("0") && detalle.getIvacodigo().equals(9)){
				notaCreditoDatamanager.getCabdevoluciones().setSubtotalnoiva(notaCreditoDatamanager.getCabdevoluciones().getSubtotalnoiva().add(detalle.getTotal()));
			}
			//--sbtotal iva 12%
			if(detalle.getIva().equals("2") && detalle.getIvacodigo().equals(9)){
				notaCreditoDatamanager.getCabdevoluciones().setSubtotaliva(notaCreditoDatamanager.getCabdevoluciones().getSubtotaliva().add(detalle.getTotal()));
				//validamos si tiene descuento
				if(notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getPorcentajedescuento()!=null && notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getPorcentajedescuento()>0){
					//descuento =totaldeldetalle  * descuento del cliente%
					BigDecimal descuento=detalle.getTotal().multiply(new BigDecimal(notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getPorcentajedescuento()/100));
					//seteo del descuento por articulo descuento
					detalle.setDescuento(descuento);
					//se obtiene el iva sobre el articulo ya quitado el descuento(totaldetalle -descunto)
					BigDecimal totaldetallemenosdecuento=detalle.getTotal().subtract(descuento);
					Tadmcatalogo catalogoiva= bunsysService.obtenerCatalogo(notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), detalle.getIvacodigo(), detalle.getIva());
					BigDecimal porcentajeIva=new BigDecimal(catalogoiva.getValor()).divide(new BigDecimal(100));
					//se obtiene el iva del producto (totaldetallemenosdecuento * iva 12%)
					BigDecimal iva=totaldetallemenosdecuento.multiply(porcentajeIva);
					//se suma los ivas que esten calculados menos el descuento
					notaCreditoDatamanager.getCabdevoluciones().setIva(notaCreditoDatamanager.getCabdevoluciones().getIva().add(iva));
				}else{
					Tadmcatalogo catalogoiva= bunsysService.obtenerCatalogo(notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), detalle.getIvacodigo(), detalle.getIva());
					BigDecimal porcentajeIva=new BigDecimal(catalogoiva.getValor()).divide(new BigDecimal(100));
					//iva 12%=totaldetalle * 12%
					BigDecimal iva=detalle.getTotal().multiply(porcentajeIva);
					notaCreditoDatamanager.getCabdevoluciones().setIva(notaCreditoDatamanager.getCabdevoluciones().getIva().add(iva));
				}
			}
			//--subtotal excento de iva
			if(detalle.getIva().equals("7") && 	detalle.getIvacodigo().equals(9)){
				notaCreditoDatamanager.getCabdevoluciones().setSubtotalexcentoiva(detalle.getTotal());
			}
			//--subtotal neto
			notaCreditoDatamanager.getCabdevoluciones().setSubtotalneto(notaCreditoDatamanager.getCabdevoluciones().getSubtotalneto().add(detalle.getTotal()));
		}
		//calculos finales
		if(notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getPorcentajedescuento()!=null &&
				notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getPorcentajedescuento()>0){
			//-total descuento
			notaCreditoDatamanager.getCabdevoluciones().setTotaldescuento(notaCreditoDatamanager.getCabdevoluciones().getSubtotalneto().multiply(
					new BigDecimal(notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getPorcentajedescuento()/100)));
		}else{
			notaCreditoDatamanager.getCabdevoluciones().setTotaldescuento(new BigDecimal(0));
		}
		//total=totalneto-descuentos+iva
		BigDecimal total=notaCreditoDatamanager.getCabdevoluciones().getSubtotalneto().subtract(notaCreditoDatamanager.getCabdevoluciones().getTotaldescuento()).add(notaCreditoDatamanager.getCabdevoluciones().getIva());
		notaCreditoDatamanager.getCabdevoluciones().setTotal(Utils.round(total, 2));
	}
	
	public void cambioPicesType(Tfacdetdevolucione detalle){
		//Busqueda del pices type segun el tipo de unidad de venta del articulo
		Tadmconversionunidad tadmconversionunidad= bunsysService.conversionArticulo(detalle.getUnidadventacodigo(), detalle.getUnidadventa());
		//pices type
		//proformaDatamanager.getdetalle().setUnidadventa(proformaDatamanager.getTinvproducto().getUnidadventa());
		//eq full boxes
		detalle.setEqfullboxes(new BigDecimal(tadmconversionunidad.getBoxes()));
		//steamsbunch
		detalle.setStemsbunch(new BigDecimal(tadmconversionunidad.getCantidadbunch()));
		//total bunch
		detalle.setTotalbunch(new BigDecimal(tadmconversionunidad.getTotalbunch()));
		//total stems
		detalle.setTotalstems(detalle.getStemsbunch().multiply(detalle.getTotalbunch()));
		//unit price
		detalle.setPreciounitario(detalle.getPreciounitario());
		//total price
		detalle.setTotal(detalle.getTotalstems().multiply(detalle.getPreciounitario()));
		calculos();
	}
	
	/**
	 * Calculos cuando se cambia la cantidad del articulo
	 * @param detalle
	 */
	public void cambioCantidad(Tfacdetdevolucione detalle){
		//seteo
		//eq full boxes=cantidad *eqfullboxes
		if(detalle.getCantidad().compareTo(new BigDecimal(detalle.getAditionalProperties().get("cantidadaux").toString()))>0){
			//eqfullboxes= catidad * la cantidad
			detalle.setEqfullboxes(detalle.getEqfullboxes().multiply(detalle.getCantidad()));
			//total bunch = cantidad * totalbunch
			detalle.setTotalbunch(detalle.getTotalbunch().multiply(detalle.getCantidad()));
		}else if(detalle.getCantidad().compareTo(new BigDecimal(detalle.getAditionalProperties().get("cantidadaux").toString()))<0){
			detalle.setEqfullboxes(detalle.getEqfullboxes().divide(new BigDecimal(detalle.getAditionalProperties().get("cantidadaux").toString())));
			//total bunch
			detalle.setTotalbunch(detalle.getTotalbunch().divide(new BigDecimal(detalle.getAditionalProperties().get("cantidadaux").toString())));
		}
		//calculos
		//total stems
		detalle.setTotalstems(detalle.getStemsbunch().multiply(detalle.getTotalbunch()));
		//total price = totalstems * preciounitario
		detalle.setTotal(detalle.getTotalstems().multiply(detalle.getPreciounitario()));
		calculos();
		detalle.getAditionalProperties().put("cantidadaux",detalle.getCantidad());
	}

	public void cambioeqfullboxes(Tfacdetdevolucione detalle){
		calculos();
	}
	
	public void cambioStemsBunch(Tfacdetdevolucione detalle){
		//total stems= stembunch * totalbunch
		detalle.setTotalstems(detalle.getStemsbunch().multiply(detalle.getTotalbunch()));
		//total price = totalstems * preciounitario
		detalle.setTotal(detalle.getTotalstems().multiply(detalle.getPreciounitario()));
		calculos();
	}
	
	public void cambiototalBunch(Tfacdetdevolucione detalle){
		if(detalle.getAditionalProperty("aux")==null){
			detalle.addAditionalProperty("aux", 1);
			detalle.addAditionalProperty("totalbunchaux", detalle.getTotalbunch());
			detalle.addAditionalProperty("totalstemsaux", detalle.getTotalstems());
			detalle.addAditionalProperty("totalaux", detalle.getTotal());
		}else{
			detalle.setTotalbunch(new BigDecimal(detalle.getAditionalProperty("totalbunchaux").toString()));
			detalle.setTotalstems(new BigDecimal(detalle.getAditionalProperty("totalstemsaux").toString()));
			detalle.setTotal(new BigDecimal(detalle.getAditionalProperty("totalaux").toString()));
		}
		if(detalle.getCajas()!=null && detalle.getCajas().compareTo(new BigDecimal(0))>0){
			//	Bunch box * Total pices * Steam bunch= Total Steams
			detalle.setTotalstems(detalle.getCajas().multiply(detalle.getCantidad()).multiply(detalle.getStemsbunch()));
			//total bunch = bunchbox * total pices
			detalle.setTotalbunch(detalle.getCajas().multiply(detalle.getCantidad()));
			//total price = totalstems * preciounitario
			detalle.setTotal(detalle.getTotalstems().multiply(detalle.getPreciounitario()));
		}
		calculos();
//		//totalbunch * cajas
//		detalle.setTotalbunch(detalle.getTotalbunch().multiply(detalle.getCajas()));
//		//total stems= stembunch * totalbunch
//		detalle.setTotalstems(detalle.getStemsbunch().multiply(detalle.getTotalbunch()));
//		//total price = totalstems * preciounitario
//		detalle.setTotal(detalle.getTotalstems().multiply(detalle.getPreciounitario()));
//		calculos();
	}
	
	public void cambiototalSteams(Tfacdetdevolucione detalle){
		//total price = totalstems * preciounitario
		detalle.setTotal(detalle.getTotalstems().multiply(detalle.getPreciounitario()));
		calculos();
	}
	
	public void cambiounitprice(Tfacdetdevolucione detalle){
		detalle.setTotal(detalle.getTotalstems().multiply(detalle.getPreciounitario()));
		calculos();
	}
	
	public void cambiototal(Tfacdetdevolucione detalle){
		calculos();
	}
	
	public void eliminarArticulo(Tfacdetdevolucione detalleNotaCredito) {
		
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
