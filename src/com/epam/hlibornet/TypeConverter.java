package com.epam.hlibornet;

import java.awt.geom.Arc2D.Float;
import java.lang.reflect.Field;

import com.epam.hlibornet.model.Type;

public class TypeConverter {
	public static Type convert(Class<?> clz, String base){
		if(clz == Integer.class || clz == int.class ||
				clz == Long.class || clz == long.class){
			return Type.INT;
		}else if(clz == Float.class || clz == float.class ||
				clz == Double.class || clz == double.class){
			return Type.FLOAT;
		}else if(clz == String.class){
			return Type.VARCHAR;
		}else if(clz.getCanonicalName().startsWith(base)){
			Type t = Type.ENTITY;
			t.setClz(clz);
			return t;
		}else{
			throw new UnsupportedOperationException();
		}
	}
	public static boolean implCollection(Class<?> clz){
		for (int i = 0; i < clz.getInterfaces().length; i++) {
			Class<?> class1 = clz.getInterfaces()[i];
			if(class1.getCanonicalName().equals("java.util.Collection")){
				return true;
			}else{
				if(implCollection(class1)){
					return true;
				}
			}
		}
		return false;
	}
	
	public static Class<?> getGenericType(Field field) throws Exception{
		String string = field.getGenericType().toString();
		int index0 = string.indexOf("<");
		int index1 = string.indexOf(">");
		String name = string.substring(index0 + 1 , index1);
		if(!name.contains(",") && !name.contains("<")){
			return Class.forName(name);
		}else{
			return null;
		}
	}
}
