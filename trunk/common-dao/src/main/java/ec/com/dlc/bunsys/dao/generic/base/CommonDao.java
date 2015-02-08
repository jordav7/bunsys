package ec.com.dlc.bunsys.dao.generic.base;

import java.util.Collection;

import ec.com.dlc.bunsys.dao.exception.DaoExcepcion;
import ec.com.dlc.bunsys.dao.generic.common.GenericDao;
import ec.com.dlc.bunsys.entity.base.BasePK;
import ec.com.dlc.bunsys.entity.common.EntityCommonImpl;

/**
 * DAO principal a ser manejado por la aplicaci&oacute;n, abstrae de manejar varios DAO al mismo tiempo
 * @author DAVID
 *
 */
public interface CommonDao {

	/**
	 * Persiste una entidad 
	 * @param entity
	 */
	<T extends EntityCommonImpl> void create(EntityCommonImpl entity) throws DaoExcepcion;
	
	/**
	 * Actualiza una entidad
	 * @param entity
	 */
	<T extends EntityCommonImpl> void update(EntityCommonImpl entity) throws DaoExcepcion;
	
	/**
	 * Remueve una entidad
	 * @param entity
	 */
	<T extends EntityCommonImpl> void delete(EntityCommonImpl entity) throws DaoExcepcion;
	
	/**
	 * Hace una b&uacute;squeda a trav&eacute;s de una plantilla
	 * @param templateEntity plantilla que hace las veces de criterio de b&uacute;squeda
	 * @return retorna una colecci&oacute;n de objetos encontrados seg&uacute;n el criterio enviado
	 */
	<T extends EntityCommonImpl> Collection<T> findObjects(EntityCommonImpl templateEntity) throws DaoExcepcion;
	
	/**
	 * Busca una entidad seg&uacute;n el criterio y retorna una sola entidad
	 * @param templateEntity plantilla de b&uacute;squeda
	 * @return Una entidad seg&uacute;n el criterio enviado
	 */
	<T extends EntityCommonImpl> T findObject(EntityCommonImpl templateEntity) throws DaoExcepcion;
	
	/**
	 * Busca un objeto por su id
	 * @param clazz
	 * @param pk
	 * @return
	 * @throws DaoExcepcion
	 */
	<T> T findById(Class<T> clazz, BasePK pk) throws DaoExcepcion;
	
	/**
	 * Retorna el DAO gen&eacute;rico
	 * @return
	 */
	GenericDao<EntityCommonImpl> getGenericDao();
}
