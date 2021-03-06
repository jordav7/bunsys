package ec.com.dlc.bunsys.dao.general;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.com.dlc.bunsys.dao.generic.base.impl.CommonDaoImpl;

/**
 * DAO principal del m&oacute;dulo de facturaci&oacute;n
 * @author DAVID
 *
 */
public class GeneralDao extends CommonDaoImpl{
	
	@PersistenceContext(unitName="bunsys-pu")
	protected EntityManager entityManager;
	
	@PostConstruct
	private void init(){
		this.getGenericDao().setEntityManager(entityManager);
	}

}
