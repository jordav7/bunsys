package ec.com.dlc.web.controller.facturacion.notacredito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.com.dlc.bunsys.common.util.HttpUtils;
import ec.com.dlc.bunsys.common.util.ResponseServiceDto;
import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.pk.TadmcatalogoPK;
import ec.com.dlc.bunsys.entity.administracion.pk.TadmcompaniaPK;
import ec.com.dlc.bunsys.entity.administracion.pk.TadmusuarioPK;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfacdetfactura;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfaccabdevolucionePK;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfacclientePK;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfacdetdevolucionePK;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.inventario.pk.TinvproductoPK;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.bunsys.util.ComprobantesUtil;
import ec.com.dlc.bunsys.util.Utils;
import ec.com.dlc.bunsys.util.log.FacturacionLogger;
import ec.com.dlc.bunsys.util.sri.ConstantesSRI;
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
public class NotaCreditoController
  extends BaseController
{
  @ManagedProperty("#{notaCreditoDatamanager}")
  private NotaCreditoDatamanager notaCreditoDatamanager;
  @ManagedProperty("#{clienteDatamanager}")
  private ClienteDatamanager clienteDatamanager;
  @ManagedProperty("#{articuloDatamanager}")
  private ArticuloDatamanager articuloDatamanager;
  @Inject
  private BunsysService bunsysService;
  
  public BaseDatamanager getDatamanager()
  {
    return this.notaCreditoDatamanager;
  }
  
  @PostConstruct
  public void inicializar()
  {
    Tfaccliente tfaccliente = new Tfaccliente();
    tfaccliente.setTsyspersona(new Tsyspersona());
    this.clienteDatamanager.setClienteserch(tfaccliente);
    
    this.articuloDatamanager.setArticuloSearch(new Tinvproducto());
    
    this.clienteDatamanager.setFormaspagosCatalogo(this.bunsysService.buscarObtenerCatalogos(((TadmusuarioPK)this.clienteDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), 
      ContenidoMessages.getInteger("cod_catalogo_forma_pago")));
    this.clienteDatamanager.setEstadosCatalogo(this.bunsysService.buscarObtenerCatalogos(((TadmusuarioPK)this.clienteDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), 
      ContenidoMessages.getInteger("cod_catalogo_estado_cliente")));
    this.clienteDatamanager.setGruposCatalogo(this.bunsysService.buscarObtenerCatalogos(((TadmusuarioPK)this.clienteDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), 
      ContenidoMessages.getInteger("cod_catalogo_grupo_cliente")));
    this.clienteDatamanager.setTiposCatalogo(this.bunsysService.buscarObtenerCatalogos(((TadmusuarioPK)this.clienteDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), 
      ContenidoMessages.getInteger("cod_catalogo_tipo_cliente")));
    
    this.clienteDatamanager.setClienteComponente(new ClienteComponent(((TadmusuarioPK)this.clienteDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania()));
    
    this.articuloDatamanager.setColorCatalogoColl(this.bunsysService.buscarObtenerCatalogos(((TadmusuarioPK)this.articuloDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_color_articulo")));
    this.articuloDatamanager.setEstadoCatalogoColl(this.bunsysService.buscarObtenerCatalogos(((TadmusuarioPK)this.articuloDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_estado_articulo")));
    this.articuloDatamanager.setArticuloComponente(new ArticuloComponent(((TadmusuarioPK)this.articuloDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania()));
    this.notaCreditoDatamanager.setDetdevolucionesColl(new ArrayList());
    TadmcompaniaPK compPk = new TadmcompaniaPK();
    compPk.setCodigocompania(((TadmusuarioPK)this.notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania());
    this.notaCreditoDatamanager.setCompania(this.bunsysService.buscarCompania(compPk));
    this.notaCreditoDatamanager.setCargueraColl(this.bunsysService.buscarObtenerCatalogos(((TadmusuarioPK)this.notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_cargueras")));
    this.notaCreditoDatamanager.setDistritoVueloColl(this.bunsysService.buscarObtenerCatalogos(((TadmusuarioPK)this.notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_distrito_vuelo")));
    this.notaCreditoDatamanager.setFobColl(this.bunsysService.buscarObtenerCatalogos(((TadmusuarioPK)this.notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_tipo_fob")));
    this.notaCreditoDatamanager.setAerolineaColl(this.bunsysService.buscarObtenerCatalogos(((TadmusuarioPK)this.notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_aerolineas")));
    this.notaCreditoDatamanager.setPicesTypeColl(this.bunsysService.buscarObtenerCatalogos(((TadmusuarioPK)this.notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), ContenidoMessages.getInteger("cod_catalogo_pices_type")));
  }
  
  public void crear() {}
  
  public void busquedaCliente()
  {
    try
    {
      Tfaccliente cliente = this.bunsysService.buscarPorIdentificacion(this.notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getTsyspersona().getIdentificacion());
      if (cliente != null) {
        this.notaCreditoDatamanager.getCabdevoluciones().setTfaccliente(cliente);
      }
    }
    catch (Throwable e)
    {
      MessagesUtil.showErrorMessage("Error al consultar el cliente");
      FacturacionLogger.log.error(e.getMessage(), e);
    }
  }
  
  public void busquedaFactura()
  {
    try
    {
      Tfaccabfactura factura = this.bunsysService.obtenerFactura(((TadmusuarioPK)this.notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), this.notaCreditoDatamanager.getCabdevoluciones().getNumerofactura());
      if (factura == null)
      {
        Integer valorSecuencia = Integer.valueOf(ComprobantesUtil.getInstancia().obtenerSecuenciaActualNC(((TadmusuarioPK)this.notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania()).intValue() + 1);
        this.notaCreditoDatamanager.setCabdevoluciones(new Tfaccabdevolucione());
        this.notaCreditoDatamanager.getCabdevoluciones().setFechadevolucion(new Date());
        this.notaCreditoDatamanager.getCabdevoluciones().setTfaccliente(new Tfaccliente());
        this.notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().setTsyspersona(new Tsyspersona());
        ((TfaccabdevolucionePK)this.notaCreditoDatamanager.getCabdevoluciones().getPk()).setNumerodevoluciones(ComprobantesUtil.getInstancia().getsecuencia(valorSecuencia.toString(), 9));
        this.notaCreditoDatamanager.setDetdevolucionesColl(new ArrayList());
      }
      else
      {
        completaDatosNotaCredito(factura);
      }
    }
    catch (Throwable e)
    {
      MessagesUtil.showErrorMessage("Error al consultar, la factura no existe");
      FacturacionLogger.log.error(e.getMessage(), e);
    }
  }
  
  private void completaDatosNotaCredito(Tfaccabfactura factura)
  {
    if (factura.getArea() != null) {
      this.notaCreditoDatamanager.getCabdevoluciones().setArea(factura.getArea());
    }
    if (factura.getCountrycode() != null) {
      this.notaCreditoDatamanager.getCabdevoluciones().setCountrycode(factura.getCountrycode());
    }
   
    this.notaCreditoDatamanager.getCabdevoluciones().setIva(factura.getIva());
    this.notaCreditoDatamanager.getCabdevoluciones().setPocentajeirbpnr(factura.getPorcentajeirbpnr());
    this.notaCreditoDatamanager.getCabdevoluciones().setPorcentajedesc(factura.getPorcentajedesc());
    this.notaCreditoDatamanager.getCabdevoluciones().setPorcentajeice(factura.getPorcentajeice());
    this.notaCreditoDatamanager.getCabdevoluciones().setPorcentajeiva(factura.getPorcentajeiva());
    this.notaCreditoDatamanager.getCabdevoluciones().setSubtotalbase(factura.getSubtotalbase());
    this.notaCreditoDatamanager.getCabdevoluciones().setSubtotalexcentoiva(factura.getSubtotalexcentoiva());
    this.notaCreditoDatamanager.getCabdevoluciones().setSubtotaliva(factura.getSubtotaliva());
    this.notaCreditoDatamanager.getCabdevoluciones().setSubtotalneto(factura.getSubtotalneto());
    this.notaCreditoDatamanager.getCabdevoluciones().setSubtotalnoiva(factura.getSubtotalnoiva());
    this.notaCreditoDatamanager.getCabdevoluciones().setTotal(factura.getTotal());
    this.notaCreditoDatamanager.getCabdevoluciones().setTotaldescuento(factura.getTotaldescuento());
    this.notaCreditoDatamanager.getCabdevoluciones().setValorice(factura.getValorice());
    this.notaCreditoDatamanager.getCabdevoluciones().setValorirbpnr(factura.getValorirbpnr());
    this.notaCreditoDatamanager.setDetdevolucionesColl(new ArrayList());
    if ((factura.getTfacdetfacturas() != null) && (!factura.getTfacdetfacturas().isEmpty())) {
      for (Tfacdetfactura detalleFactura : factura.getTfacdetfacturas())
      {
        Tfacdetdevolucione detalleDevolucion = new Tfacdetdevolucione();
        detalleDevolucion.setApta(detalleFactura.getAtpa());
        detalleDevolucion.setAptacodigo(detalleFactura.getAtpacodigo());
        detalleDevolucion.setCantidad(detalleFactura.getCantidad());
        detalleDevolucion.addAditionalProperty("cantidadaux", Double.valueOf(1.0D));

        detalleDevolucion.setTotal(detalleFactura.getTotal());
        detalleDevolucion.setCodigoproductos(detalleFactura.getCodigoproductos());
        detalleDevolucion.setDescuento(detalleFactura.getDescuento());
        detalleDevolucion.setIce(detalleFactura.getIce());
        detalleDevolucion.setIcecodigo(detalleFactura.getIcecodigo());
        detalleDevolucion.setIva(detalleFactura.getIva());
        detalleDevolucion.setIvacodigo(detalleFactura.getIvacodigo());
        detalleDevolucion.setIrbpnr(detalleFactura.getIrbpnr());
        detalleDevolucion.setIrbpnrcodigo(detalleFactura.getIrbpnrcodigo());

        detalleDevolucion.setPreciounitario(detalleFactura.getPreciounitario());

        detalleDevolucion.setTadmatpa(detalleFactura.getTadmatpa());
        detalleDevolucion.setTadmice(detalleFactura.getTadmice());
        detalleDevolucion.setTadmirbpnr(detalleFactura.getTadmirbpnr());
        detalleDevolucion.setTadmiva(detalleFactura.getTadmiva());

        detalleDevolucion.setTinvproducto(detalleFactura.getTinvproducto());
        this.notaCreditoDatamanager.getDetdevolucionesColl().add(detalleDevolucion);
      }
    } else {
      MessagesUtil.showErrorMessage("La factura no contiene detalles, no puede existir una factura sin detalles");
    }
  }
  
  public void guardarFirmarEnviar()
  {
    try
    {
      if (!coneccionsri().booleanValue())
      {
        FacesContext.getCurrentInstance().addMessage(null, 
          new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR DE CONECCION", "ERROR DE CONECCION"));
        return;
      }
      ResponseServiceDto responseServiceDto = this.bunsysService.guardarNotaCredito(this.notaCreditoDatamanager.getCabdevoluciones(), this.notaCreditoDatamanager.getDetdevolucionesColl(), this.notaCreditoDatamanager.getCompania(), this.notaCreditoDatamanager.getCabdevoluciones().getNumerofactura());
      StringBuilder mensajes = new StringBuilder("  ");
      if (responseServiceDto != null) {
        mensajes.append(responseServiceDto.getEstado());
      }
      if ((responseServiceDto != null) && (responseServiceDto.getMensajes() != null) && (responseServiceDto.getMensajes().size() > 0)) {
        for (String mensaje : responseServiceDto.getMensajes()) {
          mensajes.append(mensaje);
        }
      }
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, mensajes.toString(), mensajes.toString()));
    }
    catch (Throwable e)
    {
      MessagesUtil.showErrorMessage(e.getMessage());
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ContenidoMessages.getString("msg_error_notacredito") + "  " + e.getMessage(), ContenidoMessages.getString("msg_error_notacredito") + "  " + e.getMessage()));
    }
  }
  
  public Boolean coneccionsri()
  {
    try
    {
      Tadmcatalogo urlAmbienterecepcion = null;
      if (this.notaCreditoDatamanager.getCompania().getTipoambiente().equals(ConstantesSRI.COD_AMBIENTE_PRUEBAS))
      {
        System.out.println("Ambiente de PRUEBA");
        urlAmbienterecepcion = this.bunsysService.obtenerCatalogo(((TadmusuarioPK)this.notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), Integer.valueOf(37), ConstantesSRI.COD_URL_RECEP_PRUEBAS);
      }
      else if (this.notaCreditoDatamanager.getCompania().getTipoambiente().equals(ConstantesSRI.COD_AMBIENTE_PRODUCCION))
      {
        System.out.println("Ambiente de PRODUCCION");
        urlAmbienterecepcion = this.bunsysService.obtenerCatalogo(((TadmusuarioPK)this.notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), Integer.valueOf(37), ConstantesSRI.COD_URL_RECEP_PRODUCCION);
      }
      Tadmcatalogo urlAmbiente = null;
      if (this.notaCreditoDatamanager.getCompania().getTipoambiente().equals(ConstantesSRI.COD_AMBIENTE_PRUEBAS)) {
        urlAmbiente = this.bunsysService.obtenerCatalogo(((TadmusuarioPK)this.notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), Integer.valueOf(37), ConstantesSRI.COD_URL_AUT_PRUEBAS);
      } else if (this.notaCreditoDatamanager.getCompania().getTipoambiente().equals(ConstantesSRI.COD_AMBIENTE_PRODUCCION)) {
        urlAmbiente = this.bunsysService.obtenerCatalogo(((TadmusuarioPK)this.notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), Integer.valueOf(37), ConstantesSRI.COD_URL_AUT_PRODUCCION);
      }
      String coneccion = HttpUtils.connectToServer(urlAmbiente.getValor(), 1000);
      if (coneccion == null) {
        return Boolean.valueOf(false);
      }
      String coneccionrecepcion = HttpUtils.connectToServer(urlAmbienterecepcion.getValor(), 1000);
      if (coneccionrecepcion == null) {
        return Boolean.valueOf(false);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      return Boolean.valueOf(false);
    }
    catch (Throwable e)
    {
      e.printStackTrace();
      return Boolean.valueOf(false);
    }
    return Boolean.valueOf(true);
  }
  
  public void changeDistritoVuelo()
  {
    this.notaCreditoDatamanager.getCabdevoluciones().addAditionalProperty("codigoaerolinea", "");
//    for (Tadmcatalogo item : this.notaCreditoDatamanager.getDistritoVueloColl()) {
//      if (((TadmcatalogoPK)item.getPk()).getCodigocatalogo().equals
//    		  (this.notaCreditoDatamanager.getCabdevoluciones().getDistritovuelo())){
//        this.notaCreditoDatamanager.getCabdevoluciones().addAditionalProperty("codigoaerolinea", item.getValor() + "-");
//        this.notaCreditoDatamanager.getCabdevoluciones().setReferendo(item.getValor() + this.notaCreditoDatamanager.getCabdevoluciones().getReferendo().substring(3));
//      }
//    }
  }
  
  public void agregarDetalle()
  {
    Tfacdetdevolucione detalleDevolucion = new Tfacdetdevolucione();
    detalleDevolucion.setTinvproducto(this.notaCreditoDatamanager.getTinvproducto());
    if ((this.notaCreditoDatamanager.getTinvproducto() == null) || 
      (this.notaCreditoDatamanager.getTinvproducto().getPk() == null) || 
      (((TinvproductoPK)this.notaCreditoDatamanager.getTinvproducto().getPk()).getCodigoproductos() == null))
    {
      MessagesUtil.showErrorMessage(ContenidoMessages.getString("msg_error_seleccione_producto"));
      return;
    }
    if ((this.notaCreditoDatamanager.getCabdevoluciones().getTfaccliente() == null) || (this.notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getPk() == null) || (((TfacclientePK)this.notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getPk()).getCodigocliente() == null))
    {
      MessagesUtil.showErrorMessage(ContenidoMessages.getString("msg_error_sin_cliente"));
      return;
    }
    
    detalleDevolucion.setCantidad(new BigDecimal(1));
    detalleDevolucion.getAditionalProperties().put("cantidadaux", Double.valueOf(1.0D));
    
    detalleDevolucion.setTinvproducto(this.notaCreditoDatamanager.getTinvproducto());
    detalleDevolucion.setCodigoproductos(((TinvproductoPK)this.notaCreditoDatamanager.getTinvproducto().getPk()).getCodigoproductos());
    
    detalleDevolucion.setUnidadventa(this.notaCreditoDatamanager.getTinvproducto().getUnidadventa());
    detalleDevolucion.setUnidadventacodigo(this.notaCreditoDatamanager.getTinvproducto().getUnidadventacodigo());
    
    
    detalleDevolucion.setApta(this.notaCreditoDatamanager.getTinvproducto().getAtpa());
    detalleDevolucion.setAptacodigo(this.notaCreditoDatamanager.getTinvproducto().getAtpacodigo());
    
    detalleDevolucion.setPreciounitario(new BigDecimal(this.notaCreditoDatamanager.getTinvproducto().getPreciounitario().doubleValue()));
    
    ((TfacdetdevolucionePK)detalleDevolucion.getPk()).setCodigocompania(((TadmusuarioPK)this.notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania());
    
    detalleDevolucion.setIva(this.notaCreditoDatamanager.getTinvproducto().getIva());
    detalleDevolucion.setIvacodigo(this.notaCreditoDatamanager.getTinvproducto().getIvacodigo());
    
    detalleDevolucion.setIce(this.notaCreditoDatamanager.getTinvproducto().getIce());
    detalleDevolucion.setIcecodigo(this.notaCreditoDatamanager.getTinvproducto().getIcecodigo());
    
    detalleDevolucion.setIrbpnr(this.notaCreditoDatamanager.getTinvproducto().getIrbpnr());
    detalleDevolucion.setIrbpnrcodigo(this.notaCreditoDatamanager.getTinvproducto().getIrbpnrcodigo());
    
    this.notaCreditoDatamanager.getDetdevolucionesColl().add(detalleDevolucion);
    
    calculos();
    this.notaCreditoDatamanager.setTinvproducto(new Tinvproducto());
  }
  
  public void calculos()
  {
    this.notaCreditoDatamanager.getCabdevoluciones().setTotal(new BigDecimal(0));
    this.notaCreditoDatamanager.getCabdevoluciones().setSubtotalnoiva(new BigDecimal(0));
    this.notaCreditoDatamanager.getCabdevoluciones().setSubtotaliva(new BigDecimal(0));
    this.notaCreditoDatamanager.getCabdevoluciones().setIva(new BigDecimal(0));
    this.notaCreditoDatamanager.getCabdevoluciones().setSubtotalexcentoiva(new BigDecimal(0));
    this.notaCreditoDatamanager.getCabdevoluciones().setSubtotalneto(new BigDecimal(0));
    for (Tfacdetdevolucione detalle : this.notaCreditoDatamanager.getDetdevolucionesColl())
    {
      if ((detalle.getIva().equals("0")) && (detalle.getIvacodigo().equals(Integer.valueOf(9)))) {
        this.notaCreditoDatamanager.getCabdevoluciones().setSubtotalnoiva(this.notaCreditoDatamanager.getCabdevoluciones().getSubtotalnoiva().add(detalle.getTotal()));
      }
      if ((detalle.getIva().equals("2")) && (detalle.getIvacodigo().equals(Integer.valueOf(9))))
      {
        this.notaCreditoDatamanager.getCabdevoluciones().setSubtotaliva(this.notaCreditoDatamanager.getCabdevoluciones().getSubtotaliva().add(detalle.getTotal()));
        if ((this.notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getPorcentajedescuento() != null) && (this.notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getPorcentajedescuento().doubleValue() > 0.0D))
        {
          BigDecimal descuento = detalle.getTotal().multiply(new BigDecimal(this.notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getPorcentajedescuento().doubleValue() / 100.0D));
          
          detalle.setDescuento(descuento);
          
          BigDecimal totaldetallemenosdecuento = detalle.getTotal().subtract(descuento);
          Tadmcatalogo catalogoiva = this.bunsysService.obtenerCatalogo(((TadmusuarioPK)this.notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), detalle.getIvacodigo(), detalle.getIva());
          BigDecimal porcentajeIva = new BigDecimal(catalogoiva.getValor()).divide(new BigDecimal(100));
          
          BigDecimal iva = totaldetallemenosdecuento.multiply(porcentajeIva);
          
          this.notaCreditoDatamanager.getCabdevoluciones().setIva(this.notaCreditoDatamanager.getCabdevoluciones().getIva().add(iva));
        }
        else
        {
          Tadmcatalogo catalogoiva = this.bunsysService.obtenerCatalogo(((TadmusuarioPK)this.notaCreditoDatamanager.getLoginDatamanager().getLogin().getPk()).getCodigocompania(), detalle.getIvacodigo(), detalle.getIva());
          BigDecimal porcentajeIva = new BigDecimal(catalogoiva.getValor()).divide(new BigDecimal(100));
          
          BigDecimal iva = detalle.getTotal().multiply(porcentajeIva);
          this.notaCreditoDatamanager.getCabdevoluciones().setIva(this.notaCreditoDatamanager.getCabdevoluciones().getIva().add(iva));
        }
      }
      if ((detalle.getIva().equals("7")) && (detalle.getIvacodigo().equals(Integer.valueOf(9)))) {
        this.notaCreditoDatamanager.getCabdevoluciones().setSubtotalexcentoiva(detalle.getTotal());
      }
      this.notaCreditoDatamanager.getCabdevoluciones().setSubtotalneto(this.notaCreditoDatamanager.getCabdevoluciones().getSubtotalneto().add(detalle.getTotal()));
    }
    if ((this.notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getPorcentajedescuento() != null) && 
      (this.notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getPorcentajedescuento().doubleValue() > 0.0D)) {
      this.notaCreditoDatamanager.getCabdevoluciones().setTotaldescuento(this.notaCreditoDatamanager.getCabdevoluciones().getSubtotalneto().multiply(
        new BigDecimal(this.notaCreditoDatamanager.getCabdevoluciones().getTfaccliente().getPorcentajedescuento().doubleValue() / 100.0D)));
    } else {
      this.notaCreditoDatamanager.getCabdevoluciones().setTotaldescuento(new BigDecimal(0));
    }
    BigDecimal total = this.notaCreditoDatamanager.getCabdevoluciones().getSubtotalneto().subtract(this.notaCreditoDatamanager.getCabdevoluciones().getTotaldescuento()).add(this.notaCreditoDatamanager.getCabdevoluciones().getIva());
    this.notaCreditoDatamanager.getCabdevoluciones().setTotal(Utils.round(total, Integer.valueOf(2)));
  }
  
  public void cambioPicesType(Tfacdetdevolucione detalle)
  {
    detalle.setPreciounitario(detalle.getPreciounitario());
    calculos();
  }
  
  public void cambioCantidad(Tfacdetdevolucione detalle)
  {
    calculos();
    detalle.getAditionalProperties().put("cantidadaux", detalle.getCantidad());
  }
  
  public void cambioeqfullboxes(Tfacdetdevolucione detalle)
  {
    calculos();
  }
  
  public void cambiounitprice(Tfacdetdevolucione detalle)
  {
    calculos();
  }
  
  public void cambiototal(Tfacdetdevolucione detalle)
  {
    calculos();
  }
  
  public void eliminarArticulo(Tfacdetdevolucione detalleNotaCredito) {}
  
  public NotaCreditoDatamanager getNotaCreditoDatamanager()
  {
    return this.notaCreditoDatamanager;
  }
  
  public void setNotaCreditoDatamanager(NotaCreditoDatamanager notaCreditoDatamanager)
  {
    this.notaCreditoDatamanager = notaCreditoDatamanager;
  }
  
  public ClienteDatamanager getClienteDatamanager()
  {
    return this.clienteDatamanager;
  }
  
  public void setClienteDatamanager(ClienteDatamanager clienteDatamanager)
  {
    this.clienteDatamanager = clienteDatamanager;
  }
  
  public ArticuloDatamanager getArticuloDatamanager()
  {
    return this.articuloDatamanager;
  }
  
  public void setArticuloDatamanager(ArticuloDatamanager articuloDatamanager)
  {
    this.articuloDatamanager = articuloDatamanager;
  }
}
