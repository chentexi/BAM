package com.trent.common.utils.beanMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: Trent
 * @Date: 2022/7/29 10:47
 * @program: BAM
 * @Description:
 */
public class BeanMapUtils<T>{
	private static BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
	private static final String SERIAL_VERSION_UID = "serialVersionUID";
	
	public BeanMapUtils() {
	}
	
	private static void registerConvert() {
		beanUtilsBean.getConvertUtils().deregister(Date.class);
		beanUtilsBean.getConvertUtils().register(new SqlDateConverter((Object)null), Date.class);
		beanUtilsBean.getConvertUtils().deregister(java.util.Date.class);
		beanUtilsBean.getConvertUtils().register(new BeanMapUtils.StringDateConverter(), java.util.Date.class);
		beanUtilsBean.getConvertUtils().deregister(Timestamp.class);
		beanUtilsBean.getConvertUtils().register(new SqlTimestampConverter((Object)null), Timestamp.class);
		beanUtilsBean.getConvertUtils().deregister(Time.class);
		beanUtilsBean.getConvertUtils().register(new SqlTimeConverter((Object)null), Time.class);
		beanUtilsBean.getConvertUtils().register(new IntegerConverter((Object)null), Integer.class);
		beanUtilsBean.getConvertUtils().register(new FloatConverter((Object)null), Float.class);
		beanUtilsBean.getConvertUtils().register(new DoubleConverter((Object)null), Double.class);
		beanUtilsBean.getConvertUtils().register(new LongConverter((Object)null), Long.class);
	}
	
	public static <T> T map2Bean(Class<T> type, Map<String, Object> map) throws Exception {
		registerConvert();
		T obj = type.newInstance();
		BeanUtils.populate(obj, map);
		return obj;
	}
	
	public static Map<String, Object> bean2Map(Object bean) throws Exception {
		return describe(bean);
	}
	
	private static Map<String, Object> describe(Object bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (bean == null) {
			return new HashMap(0);
		} else {
			HashMap description;
			int var5;
			if (bean instanceof DynaBean ) {
				DynaProperty[] descriptors = ((DynaBean)bean).getDynaClass().getDynaProperties();
				description = new HashMap(descriptors.length);
				DynaProperty[] var3 = descriptors;
				int var4 = descriptors.length;
				
				for(var5 = 0; var5 < var4; ++var5) {
					DynaProperty descriptor = var3[var5];
					String name = descriptor.getName();
					if (!Objects.equals(name, "class")) {
						description.put(name, beanUtilsBean.getPropertyUtils().getProperty(bean, name));
					}
				}
			} else {
				PropertyDescriptor[] descriptors = beanUtilsBean.getPropertyUtils().getPropertyDescriptors(bean);
				Class<?> clazz = bean.getClass();
				description = new HashMap(descriptors.length);
				PropertyDescriptor[] var11 = descriptors;
				var5 = descriptors.length;
				
				for(int var12 = 0; var12 < var5; ++var12) {
					PropertyDescriptor descriptor = var11[var12];
					String name = descriptor.getName();
					if ( MethodUtils.getAccessibleMethod(clazz, descriptor.getReadMethod()) != null && !Objects.equals(name, "class")) {
						description.put(name, beanUtilsBean.getPropertyUtils().getProperty(bean, name));
					}
				}
			}
			
			return description;
		}
	}
	
	public static <T> void extend(T destObj, T... srcObjs) {
		if (srcObjs != null && srcObjs.length != 0 && destObj != null) {
			for(int i = 0; i < srcObjs.length; ++i) {
				merge(destObj, srcObjs[i]);
			}
			
		}
	}
	
	public static <T> void merge(T destObj, T srcObj) {
		if (srcObj != null && destObj != null) {
			Field[] fs = srcObj.getClass().getDeclaredFields();
			Class destClass = destObj.getClass();
			
			for(int i = 0; i < fs.length; ++i) {
				try {
					String fieldName = fs[i].getName();
					fs[i].setAccessible(true);
					Object value = fs[i].get(srcObj);
					if (null != value) {
						Method method = destClass.getDeclaredMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), fs[i].getType());
						method.invoke(destObj, value);
					}
					
					fs[i].setAccessible(false);
				} catch (Exception var8) {
					var8.printStackTrace();
				}
			}
			
		}
	}
	
	public static <T> Object getBeanValue(Serializable object, String key) throws Exception {
		Field[] fields = object.getClass().getDeclaredFields();
		Field[] var3 = fields;
		int var4 = fields.length;
		
		for(int var5 = 0; var5 < var4; ++var5) {
			Field field = var3[var5];
			if (!"serialVersionUID".equals(field.getName()) && field.getName().equals(key)) {
				return field.get(key);
			}
		}
		
		return null;
	}
	
	static final class StringDateConverter implements Converter{
		StringDateConverter() {
		}
		
		public Object convert(Class arg0, Object arg1) {
			if (arg1 == null) {
				return null;
			} else if (!(arg1 instanceof String)) {
				return arg1;
			} else {
				String str = (String)arg1;
				if (str.trim().equals("")) {
					return null;
				} else {
					SimpleDateFormat sdf = null;
					if (str.length() == 28) {
						sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
					} else if (str.length() == 19 && str.contains("-") && str.contains(":")) {
						sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					} else {
						sdf = new SimpleDateFormat("yyyy-MM-dd");
					}
					
					try {
						return sdf.parse(str);
					} catch ( ParseException var6) {
						throw new RuntimeException(var6);
					}
				}
			}
		}
	}
}
