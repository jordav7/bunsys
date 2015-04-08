package ec.com.dlc.web.controller.contingencia;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import ec.com.dlc.bunsys.entity.facturacion.Tfaccabdevolucione;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.bunsys.util.FacturacionException;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.contingencia.ContingenciaDatamanager;

@ManagedBean
@ViewScoped
public class ContingenciaController extends BaseController  {

	
	@ManagedProperty(value="#{contingenciaDatamanager}")
	private ContingenciaDatamanager contingenciaDatamanager;
	
	@Inject
	private BunsysService bunsysService;
	
	@Override
	public BaseDatamanager getDatamanager() {
		return contingenciaDatamanager;
	}

	@Override
	public void inicializar() 
	{
		contingenciaDatamanager.setCabfacturasList(new ArrayList<Tfaccabfactura>());
		contingenciaDatamanager.setCabdevoluciones(new ArrayList<Tfaccabdevolucione>());
	}

	public ContingenciaDatamanager getContingenciaDatamanager() {
		return contingenciaDatamanager;
	}

	public void setContingenciaDatamanager(
			ContingenciaDatamanager contingenciaDatamanager) {
		this.contingenciaDatamanager = contingenciaDatamanager;
	}
	
	public void listar(){
		listarfacturas();
		listarnotascredito();
	}
	
	public void listarfacturas(){
		try {
			contingenciaDatamanager.setCabfacturasList(	bunsysService.cabeceraFacturas(null,"CO",contingenciaDatamanager.getFechamin(),contingenciaDatamanager.getFechamax()));
		} catch (FacturacionException e) {
			e.printStackTrace();
		}
	}
	
	public void listarnotascredito(){
		try {
			Tfaccabdevolucione tfaccabdevolucione= new Tfaccabdevolucione();
			tfaccabdevolucione.setEstadosri("CO");
			Tsyspersona tsyspersona= new Tsyspersona();
			contingenciaDatamanager.setCabdevoluciones(bunsysService.buscarNotasCredito(contingenciaDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), tfaccabdevolucione, tsyspersona));
		} catch (FacturacionException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarporlote(){
		System.out.println("Envio por lote");
	}
}
