package ec.com.dlc.bunsys.dao.generic.base.impl;

import java.util.Collection;

import javax.inject.Inject;

import ec.com.dlc.bunsys.dao.exception.DaoExcepcion;
import ec.com.dlc.bunsys.dao.generic.base.CommonDao;
import ec.com.dlc.bunsys.dao.generic.common.GenericDao;
import ec.com.dlc.bunsys.entity.common.EntityCommonImpl;

public class CommonDaoImpl implements CommonDao {
	
	@Inject
	private GenericDao<EntityCommonImpl> genericDao;
	
	@Override
	public <T extends EntityCommonImpl> void create(EntityCommonImpl entity) throws DaoExcepcion{
		genericDao.create(entity);
	}

	@Override
	public <T extends EntityCommonImpl> void update(EntityCommonImpl entity) throws DaoExcepcion{
		genericDao.update(entity);
	}

	@Override
	public <T extends EntityCommonImpl> void delete(EntityCommonImpl entity) throws DaoExcepcion{
		genericDao.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends EntityCommonImpl> Collection<T> findObjects(EntityCommonImpl templateEntity) throws DaoExcepcion{
		return (Collection<T>) genericDao.findObjects(templateEntity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends EntityCommonImpl> T findObject(EntityCommonImpl templateEntity) throws DaoExcepcion{
		return (T) genericDao.findObject(templateEntity);
	}

	@Override
	public GenericDao<EntityCommonImpl> getGenericDao() {
		return genericDao;
	}

}
