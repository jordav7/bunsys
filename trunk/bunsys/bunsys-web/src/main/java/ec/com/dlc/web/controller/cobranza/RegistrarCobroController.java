package ec.com.dlc.web.controller.cobranza;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.context.RequestContext;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccabfactura;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccliente;
import ec.com.dlc.bunsys.entity.facturacion.Tfaccuentasxcobrar;
import ec.com.dlc.bunsys.entity.seguridad.Tsyspersona;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.bunsys.util.FacturacionException;
import ec.com.dlc.web.controller.base.BaseController;
import ec.com.dlc.web.datamanager.base.BaseDatamanager;
import ec.com.dlc.web.datamanager.cobranza.RegistrarCobroDatamanager;

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
	}
	
	public void buscar() {
		Tfaccliente clientes = bunsysService.buscarPorIdentificacion(regCobroDatamanager.getCxcRegistro().getTfaccliente().getTsyspersona().getIdentificacion());
		regCobroDatamanager.getCxcRegistro().getTfaccliente().getTsyspersona().setApellidos(clientes.getTsyspersona().getApellidos());
		regCobroDatamanager.getCxcRegistro().getTfaccliente().getTsyspersona().setNombres(clientes.getTsyspersona().getNombres());
		regCobroDatamanager.setCxcPendientesColl(bunsysService.obtenerCuentasPorCobrar(regCobroDatamanager.getLoginDatamanager().getLogin().getPk().getCodigocompania(), 
				clientes.getPk().getCodigocliente()));
		
		regCobroDatamanager.setTotal(BigDecimal.ZERO);
	}
	
	private void chequearDetalle(){
		BigDecimal total = BigDecimal.ZERO, saldo =BigDecimal.ZERO,  signo = BigDecimal.ONE;
		
		for (Tfaccuentasxcobrar cxc : regCobroDatamanager.getCxcPendientesColl()) {
			if(cxc.getValor().compareTo(BigDecimal.ZERO)>0 ){
				saldo =cxc.getSaldo().subtract(cxc.getValor());
				
				if(saldo.compareTo(BigDecimal.ZERO) >= 0){
					cxc.setSaldo(saldo);
				}else{
					throw new FacturacionException("El valor de la cuenta por cobrar no puede ser mayor a la deuda total");
				}
			}else{
				throw new FacturacionException("El valor de la cuenta por cobrar debe ser mayor que cero");
			}
			signo = (boolean) cxc.getAditionalProperty("seleccionado")?signo:new BigDecimal(-1);
			total = total.add((cxc.getValor().multiply(signo)));
		}
		
		regCobroDatamanager.setTotal(total);
	}
	
	public void seleccionarTodos() {
		try{
			for (Tfaccuentasxcobrar cxc : regCobroDatamanager.getCxcPendientesColl()) {
				cxc.addAditionalProperty("seleccionado", regCobroDatamanager.isSelCxc());
			}
			
			if(regCobroDatamanager.isSelCxc()){
				this.chequearDetalle();
			}else{
				regCobroDatamanager.setTotal(BigDecimal.ZERO);
			}
			
    	}catch(Throwable e){
    		FacesContext.getCurrentInstance().validationFailed();
    		RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
    	}
		
	}
	
	public void seleccionarCxc() {
		try{
			this.chequearDetalle();
    	}catch(Throwable e){
    		FacesContext.getCurrentInstance().validationFailed();
    		RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
    	}
		
	}
	
	public void editarValor() {
		try{
			this.chequearDetalle();
    	}catch(Throwable e){
    		FacesContext.getCurrentInstance().validationFailed();
    		RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
    	}
	}
	

	public RegistrarCobroDatamanager getRegCobroDatamanager() {
		return regCobroDatamanager;
	}

	public void setRegCobroDatamanager(RegistrarCobroDatamanager regCobroDatamanager) {
		this.regCobroDatamanager = regCobroDatamanager;
	}
	
	

}
