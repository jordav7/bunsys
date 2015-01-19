package ec.com.dlc.bunsys.facade.impl;

import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import ec.com.dlc.bunsys.entity.administracion.Tadmcompania;
import ec.com.dlc.bunsys.facade.BunsysService;
import ec.com.dlc.bunsys.service.facturacion.FacturacionService;

@Stateless
@TransactionAttribute(REQUIRES_NEW)
public class BunsysServiceBean implements BunsysService {

	@EJB
	private FacturacionService facturacionService;
	
	@Override
	public Collection<Tadmcompania> obtenerCompanias() {
		return facturacionService.obtenerCompania();
	}

}
