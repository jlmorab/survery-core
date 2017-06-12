package edu.uvm.survery.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

public class PersistenceUtils {

	protected final static Log logger = LogFactory.getLog(PersistenceUtils.class);
	
	public static <T> T getFirstResult(List<T> list) {
		if(list.size() > 0) {
			return (T) list.get(0);
		} else {
			return null;
		}
	}
	
	public static Long getRowsInQuery(EntityManager entityManager, String nameTable) {
		return getRowsInQuery(entityManager, nameTable, null);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Long getRowsInQuery(EntityManager entityManager, String nameTable, String condition) {
		try {
			String query = "SELECT COUNT(1) AS records " +
						   "FROM " + nameTable +
						   (condition != null ? " WHERE " + condition : "");
			
			Session session = entityManager.unwrap(Session.class);
			org.hibernate.Query q = session.createSQLQuery(query);
			q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<Object> data = q.list();
			for(Object object : data) {
				Map row = (Map)object;
				BigInteger num = (BigInteger) row.get("records");
				return num.longValue();
			}
			return -1L;
 		} catch(Exception ex) {
			return -1L;
		}
	}
	
	public static boolean validateDataChange(Object model, Object bean, boolean update) {
		
		Boolean result = false;
		
		try {
			if(model.getClass() == bean.getClass()) {
				
				Class<?> c = model.getClass();
				Field[] fields = c.getDeclaredFields();
				
				for(Field field : fields) {
					try {
						String useName = Character.toUpperCase(field.getName().charAt(0)) + field.getName().substring(1);
						Method getM1 = model.getClass().getMethod("get" + useName, new Class[] {});
						Method getM2 = bean.getClass().getMethod("get" + useName, new Class[] {});
						
						Object r1 = getM1.invoke(model, new Object[] {}); 	r1 = checkIsDate(r1);
						Object r2 = getM2.invoke(bean, new Object[] {});	r2 = checkIsDate(r2);
						
						if( (r1!=null && !r1.equals(r2)) || (r1==null && r2!=null)) {
							if(!sameStringValue(r1,r2)) {
								result = true;
								if(update) {
									PropertyAccessor myAccessor = PropertyAccessorFactory.forDirectFieldAccess(model);
									myAccessor.setPropertyValue(field.getName(), r2);
								}
							}
						}
					} catch(Exception ex) {}
				}
			}
		} catch(Exception ex) {}
		
		return result;
	}
	
	private static Object checkIsDate(Object object) {
		try {
			if((object instanceof Date) || (object instanceof Timestamp)) {
				SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String dateString = ft.format(object);
				Date date = ft.parse(dateString);
				return date;
			}
		} catch(Exception ex) {}
		return object;
	}
	
	private static boolean sameStringValue(Object model, Object bean) {
		try {
			if(model instanceof String && bean instanceof String) {
				if( model.toString().trim().toLowerCase() == bean.toString().trim().toLowerCase() ) {
					return true;
				}
			}
		} catch(Exception ex) {}
		return false;
	}
}
