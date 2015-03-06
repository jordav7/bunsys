package ec.com.dlc.bunsys.service.parametrizacion;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ec.com.dlc.bunsys.entity.administracion.Tadmcatalogo;
import ec.com.dlc.bunsys.util.FacturacionException;

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
			throw new FacturacionException("");
		} 
		Integer valorSecuencia = Integer.parseInt(secuencia) + 1;
		documento.setValor(valorSecuencia.toString());
		parametrizacionService.guardarCatalogo(documento);
		return Integer.parseInt(documento.getValor());
	}
	
}
