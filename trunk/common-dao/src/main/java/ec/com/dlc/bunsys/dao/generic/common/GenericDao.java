package ec.com.dlc.bunsys.dao.generic.common;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.EntityManager;

import ec.com.dlc.bunsys.dao.exception.DaoExcepcion;
import ec.com.dlc.bunsys.entity.base.BasePK;
import ec.com.dlc.bunsys.entity.common.EntityCommonImpl;

/**
 * DAO general manejado mendiante el api JPA
 * @author DAVID
 *
 * @param <T> representa un entidad del modelo
 */
public interface GenericDao<T extends EntityCommonImpl> extends Serializable {

	/**
	 * Persiste una entidad 
	 * @param entity
	 * @throws DaoExcepcion 
	 */
	void create(T entity) throws DaoExcepcion;
	
	/**
	 * Crea o actualiza una entidad
	 * @param entity
	 * @throws DaoExcepcion
	 */
	void saveOrUpdate(T entity) throws DaoExcepcion;
	
	/**
	 * Actualiza una entidad
	 * @param entity
	 * @throws DaoExcepcion 
	 */
	void update(T entity) throws DaoExcepcion;
	
	/**
	 * Remueve una entidad
	 * @param entity
	 * @throws DaoExcepcion 
	 */
	void delete(T entity) throws DaoExcepcion;
	
	/**
	 * Hace una b&uacute;squeda a trav&eacute;s de una plantilla
	 * @param templateEntity plantilla que hace las veces de criterio de b&uacute;squeda
	 * @return retorna una colecci&oacute;n de objetos encontrados seg&uacute;n el criterio enviado
	 * @throws DaoExcepcion 
	 */
	Collection<T> findObjects(T templateEntity) throws DaoExcepcion;
	
	/**
	 * Busca una entidad seg&uacute;n el criterio y retorna una sola entidad
	 * @param templateEntity plantilla de b&uacute;squeda
	 * @return Una entidad seg&uacute;n el criterio enviado
	 * @throws DaoExcepcion 
	 */
	T findObject(T templateEntity) throws DaoExcepcion;
	
	/**
	 * Retorno un objeto por su clave primaria
	 * @param clazz
	 * @param pk
	 * @return
	 * @throws DaoExcepcion
	 */
	<V> V findById(Class<V> clazz, BasePK pk) throws DaoExcepcion;
	
	/**
	 * Se coloca en {@link EntityManager} del datasource respectivo
	 * @param entityManager
	 */
	void setEntityManager(EntityManager entityManager);
}
