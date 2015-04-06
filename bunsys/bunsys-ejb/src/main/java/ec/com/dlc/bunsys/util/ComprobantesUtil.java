package ec.com.dlc.bunsys.util;

import org.apache.commons.lang.StringUtils;

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
	
	public String getsecuencia(String secuencia,int tamanio){
		return StringUtils.leftPad(secuencia, tamanio, '0');
    }
	
	public String obtenerDirectorioFacturas(Integer codcompania) {
		BunsysService bunsysService = (BunsysService) new BeanLocator.GlobalJNDIName().withAppName(BunsysMessages.getString("application.name")).withModuleName(BunsysMessages.getString("module.name")).withBeanName("BunsysServiceBean").withBusinessInterface(BunsysService.class).locate();
		Tadmcatalogo factura = bunsysService.obtenerCatalogo(codcompania, 18, ConstantesSRI.COD_FACTURA);
		return factura.getValor();
	}
	
	public String obtenerDirectorioNotaCredito(Integer codcompania) {
		BunsysService bunsysService = (BunsysService) new BeanLocator.GlobalJNDIName().withAppName(BunsysMessages.getString("application.name")).withModuleName(BunsysMessages.getString("module.name")).withBeanName("BunsysServiceBean").withBusinessInterface(BunsysService.class).locate();
		Tadmcatalogo notacredito = bunsysService.obtenerCatalogo(codcompania, 18, ConstantesSRI.COD_NOTA_CREDITO);
		return notacredito.getValor();
	}
	
	public String obtenerDirectorioGuiaRemision(Integer codcompania) {
		BunsysService bunsysService = (BunsysService) new BeanLocator.GlobalJNDIName().withAppName(BunsysMessages.getString("application.name")).withModuleName(BunsysMessages.getString("module.name")).withBeanName("BunsysServiceBean").withBusinessInterface(BunsysService.class).locate();
		Tadmcatalogo guiaremision = bunsysService.obtenerCatalogo(codcompania, 18, ConstantesSRI.COD_GUIA_REMISION);
		return guiaremision.getValor();
	}
	
	public String obtenerDirectorioNotaDebito(Integer codcompania) {
		BunsysService bunsysService = (BunsysService) new BeanLocator.GlobalJNDIName().withAppName(BunsysMessages.getString("application.name")).withModuleName(BunsysMessages.getString("module.name")).withBeanName("BunsysServiceBean").withBusinessInterface(BunsysService.class).locate();
		Tadmcatalogo notadebito = bunsysService.obtenerCatalogo(codcompania, 18, ConstantesSRI.COD_NOTA_DEBITO);
		return notadebito.getValor();
	}
	
	public String obtenerDirectorioComprobanteRet(Integer codcompania) {
		BunsysService bunsysService = (BunsysService) new BeanLocator.GlobalJNDIName().withAppName(BunsysMessages.getString("application.name")).withModuleName(BunsysMessages.getString("module.name")).withBeanName("BunsysServiceBean").withBusinessInterface(BunsysService.class).locate();
		Tadmcatalogo comprobanteRet = bunsysService.obtenerCatalogo(codcompania, 18, ConstantesSRI.COD_COMPROBANTE_RET);
		return comprobanteRet.getValor();
	}
	
	public String obtenerRutaCertificado(Integer codcompania) {
		BunsysService bunsysService = (BunsysService) new BeanLocator.GlobalJNDIName().withAppName(BunsysMessages.getString("application.name")).withModuleName(BunsysMessages.getString("module.name")).withBeanName("BunsysServiceBean").withBusinessInterface(BunsysService.class).locate();
		Tadmcatalogo rutaCertificado = bunsysService.obtenerCatalogo(codcompania, 37, "RUTACERTIFICADO");
		return rutaCertificado.getValor();
	}
}
