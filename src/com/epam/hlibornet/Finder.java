package com.epam.hlibornet;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Finder {
	
	public static List findAll(Collection<?> collection,
			Object arg, String ... fieldNames) throws Exception{
		List result = new ArrayList();
		for(Iterator<?> it = collection.iterator();
				it.hasNext(); ){
			Object obj = it.next();
			Object lastValue = obj;
			for(String fieldName : fieldNames){
				lastValue = getFieldValue(obj, fieldName);
			}
			if(arg.equals(lastValue)){
				result.add(obj);
			}
		}
		return result;
	}
	public static List findAll(Collection [] collection,
			Object arg, String ... fieldNames) throws Exception{
		List result = new ArrayList();
		for (int i = 0; i < collection.length; i++) {
			Object obj = collection[i];
			Object lastValue = obj;
			for(String fieldName : fieldNames){
				lastValue = getFieldValue(obj, fieldName);
			}
			if(arg.equals(lastValue)){
				result.add(obj);
			}
		}
		return result;
	}
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
	public static Object find(Object [] collection,
			Object arg, String ... fieldNames) throws Exception{
		for (int i = 0; i < collection.length; i++) {
			Object obj = collection[i];
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
