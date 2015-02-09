package ec.com.dlc.bunsys.commons.listener;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PrePersist;
import javax.sql.DataSource;

import ec.com.dlc.bunsys.commons.annotations.SequenceDatabase;
import ec.com.dlc.bunsys.commons.util.ReflectionUtil;
import ec.com.dlc.bunsys.entity.base.BaseEntity;

/**
 * Listener que agrega el valor correspondiente de la PK
 * @author dcruz
 *
 */
public class GenerateIdListener {
	
	@Resource(name="java:jboss/datasources/bunsysDS")
	private DataSource datasource;

	/**
	 * Almacena el valor correspondiente de la clave autogenerada en el campo correspondiente
	 * @param entity
	 */
	@PrePersist
	public void prePersist(Object entity){
		try {
			Context context = new InitialContext();
			datasource = (DataSource) context.lookup("java:jboss/datasources/bunsysDS");
			if(entity instanceof BaseEntity){
				BaseEntity<?> baseEntity = (BaseEntity<?>)entity;
				Field[] camposPk = baseEntity.getPk().getClass().getDeclaredFields();
				for (Field campo : camposPk) {
					if(campo.isAnnotationPresent(SequenceDatabase.class)){
						SequenceDatabase sequenceDatabase = campo.getAnnotation(SequenceDatabase.class);
						switch (sequenceDatabase.typeGenerator()) {
						case SEQUENCE:{
								Statement statement = datasource.getConnection().createStatement();
								ResultSet resultSet = statement.executeQuery("SELECT NEXTVAL('"+sequenceDatabase.sequenceName()+"')");
								Integer nextVal = null;
								while (resultSet.next()) {
									nextVal = resultSet.getInt(1);
								}
								ReflectionUtil.methodSetInvoke(baseEntity.getPk(), campo.getName(), nextVal);
							}
							break;
						default:
							break;
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
