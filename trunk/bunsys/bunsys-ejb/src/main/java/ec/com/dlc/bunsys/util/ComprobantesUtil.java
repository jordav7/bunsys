package ec.com.dlc.bunsys.util;

import ec.com.dlc.bunsys.commons.locator.BeanLocator;
import ec.com.dlc.bunsys.commons.resource.BunsysMessages;
import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.bunsys.util.sri.ConstantesSRI;

public class ComprobantesUtil {

	private static ComprobantesUtil instancia = new ComprobantesUtil();
	
	private ComprobantesUtil(){}
	
	public static ComprobantesUtil getInstancia() {
		if(instancia == null){
			instancia = new ComprobantesUtil();
		}
		return instancia;
	}
	
	public Integer obtenerSecuenciaActualFactura(Integer codcompania) {
		BunsysService bunsysService = (BunsysService) new BeanLocator.GlobalJNDIName().withAppName(BunsysMessages.getString("application.name")).withModuleName(BunsysMessages.getString("module.name")).withBeanName("BunsysServiceBean").withBusinessInterface(BunsysService.class).locate();
		Tadmcatalogo factura = bunsysService.obtenerCatalogo(codcompania, 19, ConstantesSRI.COD_FACTURA);
		return Integer.parseInt(factura.getValor());
	}
	
	public Integer obtenerSecuenciaActualNC(Integer codcompania) {
		BunsysService bunsysService = (BunsysService) new BeanLocator.GlobalJNDIName().withAppName(BunsysMessages.getString("application.name")).withModuleName(BunsysMessages.getString("module.name")).withBeanName("BunsysServiceBean").withBusinessInterface(BunsysService.class).locate();
		Tadmcatalogo notaCredito = bunsysService.obtenerCatalogo(codcompania, 19, ConstantesSRI.COD_NOTA_CREDITO);
		return Integer.parseInt(notaCredito.getValor());
	}
	
	public Integer obtenerSecuenciaActualND(Integer codcompania) {
		BunsysService bunsysService = (BunsysService) new BeanLocator.GlobalJNDIName().withAppName(BunsysMessages.getString("application.name")).withModuleName(BunsysMessages.getString("module.name")).withBeanName("BunsysServiceBean").withBusinessInterface(BunsysService.class).locate();
		Tadmcatalogo notaDebito = bunsysService.obtenerCatalogo(codcompania, 19, ConstantesSRI.COD_NOTA_DEBITO);
		return Integer.parseInt(notaDebito.getValor());
	}
	
	public Integer obtenerSecuenciaActualCR(Integer codcompania) {
		BunsysService bunsysService = (BunsysService) new BeanLocator.GlobalJNDIName().withAppName(BunsysMessages.getString("application.name")).withModuleName(BunsysMessages.getString("module.name")).withBeanName("BunsysServiceBean").withBusinessInterface(BunsysService.class).locate();
		Tadmcatalogo comprobante = bunsysService.obtenerCatalogo(codcompania, 19, ConstantesSRI.COD_COMPROBANTE_RET);
		return Integer.parseInt(comprobante.getValor());
	}
}
