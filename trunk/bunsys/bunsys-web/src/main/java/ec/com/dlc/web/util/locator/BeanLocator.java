package ec.com.dlc.web.util.locator;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

/**
 * Crea un service locator para ubicar EJB's e invocarlo dentro de la aplicaci&oacute;n
 * @author dcruz
 *
 */
public class BeanLocator {

	public static class GlobalJNDIName {
		private StringBuilder componentName;
		private static final String PREFIX_CALLER = "java:global";
		private static final String SEPARATOR = "/";
		private String appName;
		private String moduleName;
		private String beanName;
		private Class businessInterface;
		
		public GlobalJNDIName(){
			
		}
		
		public GlobalJNDIName withAppName(String appName) {
            this.appName = appName;
            return this;
        }

        public GlobalJNDIName withModuleName(String moduleName) {
            this.moduleName = moduleName;
            return this;
        }

        public GlobalJNDIName withBeanName(String beanName) {
            this.beanName = beanName;
            return this;
        }
        
        public GlobalJNDIName withBusinessInterface(Class businessInterface) {
			this.businessInterface = businessInterface;
			return this;
		}
        
       //some builder methods omitted
      

        private String computeBeanName(Class beanClass) {
        	return null;
        }

        private boolean isNotEmpty(String name){
            return (name != null && !name.isEmpty());
        }

        public String asString() {
        	//construction
        	this.componentName = new StringBuilder(PREFIX_CALLER);
        	this.componentName = componentName.append(SEPARATOR).append(appName);
        	this.componentName = componentName.append(SEPARATOR).append(moduleName);
        	this.componentName = componentName.append(SEPARATOR).append(beanName);
        	this.componentName = componentName.append("!").append(businessInterface.getName());
        	return componentName.toString();
        }

        public <T>  T locate(Class clazz) {
            return BeanLocator.lookup(clazz, asString());
        }

        public Object locate() {
             return BeanLocator.lookup(asString());
        }
	}
	
	@SuppressWarnings("unchecked")
	public static <T>  T lookup(Class clazz, String jndiName) {
        Object bean = lookup(jndiName);
        return (T) clazz.cast(PortableRemoteObject.narrow(bean, clazz));
	}

	public static Object lookup(String jndiName) {
	    Context context = null;
	    try {
	        context = new InitialContext();
	        return context.lookup(jndiName);
	    } catch (NamingException ex) {
	        throw new IllegalStateException("Cannot connect to bean: " + jndiName + " Reason: " + ex, ex.getCause());
		} finally {
		    try {
		        context.close();
		    } catch (NamingException ex) {
		        throw new IllegalStateException("Cannot close InitialContext. Reason: " + ex, ex.getCause());
        }
    }
}
}
