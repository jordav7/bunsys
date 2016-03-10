package ec.com.dlc.bunsys.service.parametrizacion;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;
import ec.com.dlc.bunsys.entity.administracion.pk.TadmcompaniaPK;
import ec.com.dlc.bunsys.util.FacturacionException;
import ec.com.dlc.bunsys.util.sri.ConstantesSRI;
import ec.com.dlc.bunsys.util.sri.ModuleUtil;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class SecuenciaService {

	@EJB
	private ParametrizacionService parametrizacionService;
	
	public Integer obtenerSecuenciaComp(Integer codCompania, String codComprobante) throws FacturacionException {
		//obtengo la secuencia y actualizo su valor
		Tadmcatalogo documento = parametrizacionService.obtenerCatalogo(codCompania, 19, codComprobante);
		String secuencia = documento.getValor();
		if(secuencia == null){
			throw new FacturacionException("La secuencia no debe ser nula");
		} 
		Integer valorSecuencia = Integer.parseInt(secuencia) + 1;
		documento.setValor(valorSecuencia.toString());
		parametrizacionService.guardarCatalogo(documento);
		return Integer.parseInt(documento.getValor());
	}
	
	public String generaClaveAcceso(Date fecha, Integer codigocompania,String numerocmprobante)throws FacturacionException {
		return generaClaveAcceso(fecha, codigocompania, ConstantesSRI.COD_FACTURA, numerocmprobante);
	}
	
	public String generaClaveAcceso(Date fecha, Integer codigocompania, String tipoComprobante, String numerocmprobante) throws FacturacionException {
		String claveacceso=null;
		//fecha
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		String fechaEmision = format.format(fecha);
		TadmcompaniaPK companiaPk = new TadmcompaniaPK();
		companiaPk.setCodigocompania(codigocompania);
		Tadmcompania compania= parametrizacionService.buscarCompania(companiaPk);
		String serie =compania.getCodigoestablecimiento()+compania.getCodigopuntoemision();//serie = codigoestablecimiento+codigopuntodeemision
		String codigoNumerico="12345678";
		
		claveacceso=  fechaEmision + tipoComprobante + compania.getRuc() + compania.getTipoambiente() +
				      serie + numerocmprobante + codigoNumerico + ConstantesSRI.COD_EMISION_NORMAL;
		//verificador
		int digitoVerificador = ModuleUtil.obtenerModulo11(claveacceso);
		return claveacceso + digitoVerificador;
	}
}
