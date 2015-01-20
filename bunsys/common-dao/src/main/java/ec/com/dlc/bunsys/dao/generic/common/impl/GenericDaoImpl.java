package ec.com.dlc.bunsys.dao.generic.common.impl;

import java.util.Collection;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.LockTimeoutException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PessimisticLockException;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;
import javax.persistence.RollbackException;
import javax.persistence.TransactionRequiredException;

import ec.com.dlc.bunsys.dao.exception.DaoExcepcion;
import ec.com.dlc.bunsys.dao.generic.common.GenericDao;
import ec.com.dlc.bunsys.dao.util.DaoMessages;
import ec.com.dlc.bunsys.entity.common.EntityCommonImpl;

public class GenericDaoImpl<T extends EntityCommonImpl> implements GenericDao<T> {

	private static final long serialVersionUID = 6002577036917452662L;

	private EntityManager entityManager;
	
	@Override
	public void create(T entity) throws DaoExcepcion{
		try {
			entityManager.clear();
			entityManager.persist(entity);
			entityManager.flush();
		} catch (Exception e) {
			throw convertToDaoException(e);
		}
	}

	@Override
	public void update(T entity) throws DaoExcepcion{
		try {
			entityManager.clear();
			entityManager.merge(entity);
			entityManager.flush();
		} catch (Exception e) {
			throw convertToDaoException(e);
		}
	}

	@Override
	public void delete(T entity) throws DaoExcepcion{
		try {
			entityManager.clear();
			entityManager.remove(entity);
			entityManager.flush();
		} catch (Exception e) {
			throw convertToDaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<T> findObjects(T templateEntity) throws DaoExcepcion{
		try{
			Query query = entityManager.createQuery("SELECT o FROM "+templateEntity.getClass().getName() + " o");
			return query.getResultList();
		} catch (Exception e){
			throw convertToDaoException(e);
		}
	}

	@Override
	public T findObject(T templateEntity) throws DaoExcepcion{
		try {
			return null;
		} catch (Exception e) {
			throw convertToDaoException(e);
		}
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private DaoExcepcion convertToDaoException(Exception e){
		if(e instanceof NonUniqueResultException){
			return new DaoExcepcion(DaoMessages.getProperty("error.persistence.nonuniqueresult"), e);
		} else if (e instanceof EntityExistsException) {
			return new DaoExcepcion(DaoMessages.getProperty("error.persistence.entityexists"), e);
		} else if (e instanceof OptimisticLockException) {
			return new DaoExcepcion(DaoMessages.getProperty("error.persistence.optimisticlock"), e);
		} else if (e instanceof LockTimeoutException) {
			return new DaoExcepcion(DaoMessages.getProperty("error.persistence.optimisticlock"), e);
		} else if (e instanceof NoResultException) {
			return new DaoExcepcion(DaoMessages.getProperty("error.persistence.noresult"), e);
		} else if (e instanceof PessimisticLockException) {
			return new DaoExcepcion(DaoMessages.getProperty("error.persistence.optimisticlock"), e);
		} else if (e instanceof QueryTimeoutException) {
			return new DaoExcepcion(DaoMessages.getProperty("error.persistence.querytimeout"), e);
		} else if (e instanceof RollbackException) {
			return new DaoExcepcion(DaoMessages.getProperty("error.persistence.rollback"), e);
		} else if (e instanceof TransactionRequiredException) {
			return new DaoExcepcion(DaoMessages.getProperty("error.persistence.transactionrequired"), e);
		}
		return new DaoExcepcion(e);
	}
}
