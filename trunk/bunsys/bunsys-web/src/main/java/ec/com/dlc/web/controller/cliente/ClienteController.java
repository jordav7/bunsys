package ec.com.dlc.web.controller.cliente;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.pk.TfacclientePK;
import ec.com.dlc.bunsys.entity.inventario.Tinvproducto;
import ec.com.dlc.bunsys.entity.inventario.pk.TinvproductoPK;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.entity.seguridad.pk.TsyspersonaPK;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.web.commons.resource.ContenidoMessages;
import ec.com.dlc.web.componentes.ArticuloComponent;
import ec.com.dlc.web.componentes.ClienteComponent;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.articulo.ArticuloDatamanager;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.cliente.ClienteDatamanager;

@ManagedBean
@ViewScoped
public class ClienteController extends BaseController {

	@ManagedProperty(value="#{clienteDatamanager}")
	private ClienteDatamanager clienteDatamanager;
	
	@Inject
	private BunsysService bunsysService;
	
	@Override
	public BaseDatamanager getDatamanager() {
		return clienteDatamanager;
	}

	@Override
	public void inicializar() {
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
	}
	
	public void crearArticulo() {
		clienteDatamanager.getClienteComponente().crear();
		clienteDatamanager.getClienteComponente().getTfaccliente().getPk().setCodigocompania(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		clienteDatamanager.getClienteComponente().getTfaccliente().getTsyspersona().getPk().setCodigocompania(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
	}
	
	public void buscar(){
		clienteDatamanager.setClientesCol(bunsysService.buscarClientes(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(),
				clienteDatamanager.getClienteserch().getTsyspersona().getNombres(), 
				clienteDatamanager.getClienteserch().getTsyspersona().getApellidos(), 
				clienteDatamanager.getClienteserch().getTsyspersona().getIdentificacion()));
	}
	
	
	public void eliminar(Integer codigoPersona){
		TsyspersonaPK personaPk= new TsyspersonaPK();
		personaPk.setCodigocompania(clienteDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania());
		personaPk.setCodigopersona(codigoPersona);
		bunsysService.eliminarCliente(personaPk, ContenidoMessages.getInteger("cod_catalogo_estado_cliente"));
		buscar();
	}

	public ClienteDatamanager getClienteDatamanager() {
		return clienteDatamanager;
	}

	public void setClienteDatamanager(ClienteDatamanager clienteDatamanager) {
		this.clienteDatamanager = clienteDatamanager;
	}
	

}
