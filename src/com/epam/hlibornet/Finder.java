package com.epam.hlibornet;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

public class Finder {
	public static Object find(Collection<?> collection,
			Object arg, String ... fieldNames) throws Exception{
		for(Iterator<?> it = collection.iterator();
				it.hasNext(); ){
			Object obj = it.next();
			Object lastValue = obj;
			for(String fieldName : fieldNames){
				lastValue = getFieldValue(obj, fieldName);
			}
			if(arg.equals(lastValue)){
					return obj;
			}
		}
		return null;
	}
	private static Object getFieldValue(Object obj, String fieldName) throws Exception {
		String methodName = ReflectiveUtil.getMethodName(fieldName);
		for(Method m : obj.getClass().getMethods()){
			if(m.getName().equals(methodName)){
				return m.invoke(obj);
			}
		}
		return null;
	}
}
