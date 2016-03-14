/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.dlc.bunsys.commons.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.Collator;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

/**
 * Clase utilitaria para manipular atributos de las clases
 * @author dcruz
 */
public class ReflectionUtil {
    
    private static final String GET = "get";
    private static final String SET = "set";
    private static final String DOT = "\\.";
    private static final String SIMPLE_PACKAGE = "java.lang";
    
    /**
     * Se obtiene el valor del campo de un objeto en base a su m&eacute;todo GET
     * @param value
     * @param fieldName
     * @return
     */
    public static Object methodGetInvoke(Object value, String fieldName) {
        Object resultValue = null;
        try {
        	if(value != null && StringUtils.isNotBlank(fieldName)){
	            int pos = 0;
	            resultValue = value;
	            String[] prefixArray = fieldName.split(DOT);
	            while(resultValue != null && pos < prefixArray.length){
	                Class<?> clazz = resultValue.getClass();
	                Method method = clazz.getMethod(GET.concat(StringUtils.capitalize(prefixArray[pos])), new Class<?>[0]);
	                resultValue = method.invoke(resultValue, new Object[0]);
	                pos++;
	            }
        	}
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ReflectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ReflectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReflectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ReflectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ReflectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultValue;
    }
    
    /**
     * Setea el valor de un atributo en un determinado objeto en base a su m&eacute;todo SET
     * @param object
     * @param methodName
     * @param value
     */
    public static void methodSetInvoke(Object object, String methodName, Object value){
        try {
            Class<?> clazz = object.getClass();
            Method methodGet = clazz.getMethod(GET.concat(StringUtils.capitalize(methodName)), new Class<?>[0]);
            Class<?> returnType = methodGet.getReturnType();
            Method method = clazz.getMethod(SET.concat(StringUtils.capitalize(methodName)), returnType);
            method.invoke(object, value);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(ReflectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ReflectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ReflectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ReflectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(ReflectionUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Determina si un {@link Field} es est&aacute;tico
     * @param field
     * @return <tt>true</tt> si es est&aacute;tico <tt>false</tt> en caso contrario
     */
    public static boolean isStaticField(Field field) {
		return Modifier.isStatic(field.getModifiers());
	}
    
    /**
     * Determina si la clase es un primitivo es simple es decir pertenece al paquete java.lang
     * @param clazz
     * @return <tt>true</tt> si la clase es simple <tt>false</tt> en caso contrario
     */
    public static boolean isSampleObject(Class<?> clazz){
        return clazz.getName().startsWith(SIMPLE_PACKAGE) || clazz.getName().startsWith("[L".concat(SIMPLE_PACKAGE));
    }
    
    /**
     * Retorna la comparaci&oacute;n entre dos valores
     * @param o1
     * @param o2
     * @return
     */
    public static int compareValue(Object o1, Object o2) {
    	int compare = 0;
    	if (o1 == null && o2 != null) {
    		compare = 1;
		}else if (o1 != null && o2 == null) {
			compare = -1;
		}else if(o1 != null && o2 != null){
			if(o1 instanceof Character && o2 instanceof Character){
				Character val1 = (Character) o1;
				Character val2 = (Character) o2;
				compare = val1.compareTo(val2);
			}else if (o1 instanceof String && o2 instanceof String) {
				String val1 = (String) o1;
				String val2 = (String) o2;
				if (StringUtils.isNumeric(val1) && StringUtils.isNumeric(val2)) {
					Integer valx1 = Integer.parseInt(val1);
					Integer valx2 = Integer.parseInt(val2);
					compare = valx1.compareTo(valx2);
				} else {
					Collator ordererAccent = Collator.getInstance(new Locale("es"));
					compare = ordererAccent.compare(val1.toUpperCase(), val2.toUpperCase());
				}
			}else if(o1 instanceof Short && o2 instanceof Short){
				Short val1 = (Short) o1;
				Short val2 = (Short) o2;
				compare = val1.compareTo(val2);
			}else if (o1 instanceof Integer && o2 instanceof Integer) {
				Integer val1 = (Integer) o1;
				Integer val2 = (Integer) o2;
				compare = val1.compareTo(val2);
			}else if (o1 instanceof BigDecimal && o2 instanceof BigDecimal) {
				BigDecimal val1 = (BigDecimal) o1;
				BigDecimal val2 = (BigDecimal) o2;
				compare = val1.compareTo(val2);
			}else if (o1 instanceof Long && o2 instanceof Long) {
				Long val1 = (Long) o1;
				Long val2 = (Long) o2;
				compare = val1.compareTo(val2);
			}else if(o1 instanceof Float && o2 instanceof Float){
				Float val1 = (Float) o1;
				Float val2 = (Float) o2;
				compare = val1.compareTo(val2);
			}else if (o1 instanceof Double && o2 instanceof Double) {
				Double val1 = (Double) o1;
				Double val2 = (Double) o2;
				compare = val1.compareTo(val2);
			}else if (o1 instanceof Date && o2 instanceof Date) {
				Date val1 = (Date) o1;
				Date val2 = (Date) o2;
				compare = val1.compareTo(val2);
			}else if (o1 instanceof Timestamp && o2 instanceof Timestamp) {
				Timestamp val1 = (Timestamp) o1;
				Timestamp val2 = (Timestamp) o2;
				compare = val1.compareTo(val2);
			}else if(o1 instanceof Time && o2 instanceof Time){
				Time val1 = (Time) o1;
				Time val2 = (Time) o2;
				compare = val1.compareTo(val2);
			}else if(o1 instanceof Boolean && o2 instanceof Boolean){
				Boolean val1 = (Boolean) o1;
				Boolean val2 = (Boolean) o2;
				compare = val1.compareTo(val2);
			}
		}
		return compare;
	}
}
