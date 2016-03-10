package ec.com.dlc.web.controller.cobranza;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;
import org.primefaces.extensions.util.MessageUtils;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccuentasxcobrar;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.bunsys.util.FacturacionException;
import ec.com.dlc.web.commons.resource.ContenidoMessages;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.cobranza.RegistrarCobroDatamanager;
import ec.com.dlc.web.util.jsf.MessagesUtil;

@ManagedBean
@ViewScoped
public class RegistrarCobroController extends BaseController{

	@ManagedProperty("#{registroCobroDatamanager}")
	private RegistrarCobroDatamanager regCobroDatamanager;
	
	@Inject
	private BunsysService bunsysService;
	
	@Override
	public BaseDatamanager getDatamanager() {
		return regCobroDatamanager;
	}

	@Override
	public void inicializar() {
		regCobroDatamanager.setCxcRegistro(new Tfaccuentasxcobrar());
		regCobroDatamanager.getCxcRegistro().setTfaccabfactura(new Tfaccabfactura());
		regCobroDatamanager.getCxcRegistro().setTfaccliente(new Tfaccliente());
		regCobroDatamanager.getCxcRegistro().getTfaccliente().setTsyspersona(new Tsyspersona());
		regCobroDatamanager.getCxcRegistro().setTadmestado(new Tadmcatalogo());
		regCobroDatamanager.getCxcRegistro().setFechapago(new Date());
		regCobroDatamanager.setSelCxc(false);
	}
	
	public void buscar() {
		Tfaccliente clientes = bunsysService.buscarPorIdentificacion(regCobroDatamanager.getCxcRegistro().getTfaccliente().getTsyspersona().getIdentificacion());
		regCobroDatamanager.getCxcRegistro().getTfaccliente().getTsyspersona().setApellidos(clientes.getTsyspersona().getApellidos());
		regCobroDatamanager.getCxcRegistro().getTfaccliente().getTsyspersona().setNombres(clientes.getTsyspersona().getNombres());
		regCobroDatamanager.setCxcPendientesColl(bunsysService.obtenerCuentasPorCobrar(regCobroDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), 
				clientes.getPk().getCodigocliente()));
		regCobroDatamanager.setTotal(BigDecimal.ZERO);
		regCobroDatamanager.setSelCxc(false);
		
		for (Tfaccuentasxcobrar cxc : regCobroDatamanager.getCxcPendientesColl()) {
			cxc.setValor(cxc.getSaldo());
			cxc.addAditionalProperty("saldoCxc", cxc.getSaldo());
			cxc.addAditionalProperty("editCxc", true);
			cxc.addAditionalProperty("seleccionado", false);
		}
	}
	
	private void selectedCxc(Tfaccuentasxcobrar cxc){
		BigDecimal saldo =BigDecimal.ZERO;
		if(cxc.getValor().compareTo(BigDecimal.ZERO)>0 ){
			saldo =cxc.getSaldo().subtract(cxc.getValor());
			
			if(saldo.compareTo(cxc.getSaldo()) <= 0 && saldo.compareTo(BigDecimal.ZERO)>= 0){
				cxc.addAditionalProperty("saldoCxc", saldo);
			}else{
				cxc.addAditionalProperty("saldoCxc", BigDecimal.ZERO);
				cxc.setValor(cxc.getSaldo());
				throw new FacturacionException("El valor de la cuenta por cobrar no puede ser mayor a la deuda total");
			}
		}else{
			cxc.setValor(cxc.getSaldo());
			throw new FacturacionException("El valor de la cuenta por cobrar debe ser mayor que cero");
		}
	}
	
	private void unselectedCxc(Tfaccuentasxcobrar cxc) {
		cxc.addAditionalProperty("saldoCxc", cxc.getSaldo());
		cxc.setValor(cxc.getSaldo());
	}
	
	private void totalizar() {
		BigDecimal total = BigDecimal.ZERO;
		for (Tfaccuentasxcobrar cxc : regCobroDatamanager.getCxcPendientesColl()) {
			if((boolean) cxc.getAditionalProperty("seleccionado")){
				total = total.add(cxc.getValor());
			}
		}
		
		regCobroDatamanager.setTotal(total);
	}
	
	public void seleccionarTodos() {
		try{
			for (Tfaccuentasxcobrar cxc : regCobroDatamanager.getCxcPendientesColl()) {
				if(regCobroDatamanager.isSelCxc()){
					cxc.addAditionalProperty("editCxc", false);
					cxc.addAditionalProperty("seleccionado", true);
					this.selectedCxc(cxc);
				}else{
					cxc.addAditionalProperty("editCxc", true);
					cxc.addAditionalProperty("seleccionado", false);
					this.unselectedCxc(cxc);
				}
			}
			
			this.totalizar();
			
    	}catch(Throwable e){
    		MessagesUtil.showErrorMessage(e.getMessage());
    	}
		
	}
	
	public void selectedRegCxc(Tfaccuentasxcobrar cxcPend) {
		try{
			if((boolean) cxcPend.getAditionalProperty("seleccionado")){
				cxcPend.addAditionalProperty("editCxc", false);
				this.selectedCxc(cxcPend);
			}else{
				cxcPend.addAditionalProperty("editCxc", true);
				this.unselectedCxc(cxcPend);
			}
			
			this.totalizar();
    	}catch(Throwable e){
    		e.printStackTrace();
    		MessagesUtil.showErrorMessage(e.getMessage());
    	}
		
	}
	
	public void editValor(Tfaccuentasxcobrar cxc) {
		try{
			this.selectedRegCxc(cxc);
    	}catch(Throwable e){
    		MessagesUtil.showErrorMessage(e.getMessage());
    	}
	}
	
	public void guardar() {
		List<Tfaccuentasxcobrar> cxcCollAb = new ArrayList<Tfaccuentasxcobrar>();
		try {
			for (Tfaccuentasxcobrar cxc : regCobroDatamanager.getCxcPendientesColl()) {
				if((boolean) cxc.getAditionalProperty("seleccionado")){
					cxc.setSaldo(new BigDecimal(cxc.getAditionalProperty("saldoCxc").toString()));
					cxcCollAb.add(cxc);
				}
			}
			
			bunsysService.registrarPago(cxcCollAb, regCobroDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), 
					ContenidoMessages.getInteger("cod_catalogo_estado_cuentaxcobrar") , ContenidoMessages.getInteger("cod_tipo_doc_cuentaxcobrar"),
					regCobroDatamanager.getCxcRegistro().getNumdoc(), regCobroDatamanager.getCxcRegistro().getConcepto(), regCobroDatamanager.getCxcRegistro().getFechapago());
			
			MessagesUtil.showErrorMessage("Pago exitoso");
		} catch (Exception e) {
			MessagesUtil.showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public RegistrarCobroDatamanager getRegCobroDatamanager() {
		return regCobroDatamanager;
	}

	public void setRegCobroDatamanager(RegistrarCobroDatamanager regCobroDatamanager) {
		this.regCobroDatamanager = regCobroDatamanager;
	}
	
	

}
