package com.epam.hlibornet;

import java.lang.reflect.Method;

public class ReflectiveUtil {
	public static void setField(Object obj, String fieldName, Object value) throws Exception{
		String methodName = setMethodName(fieldName);
		for(Method method : obj.getClass().getMethods()){
			if(method.getName().equals(methodName)){
				method.invoke(obj, value);
				return;
			}
		}
	}
	public static String setMethodName(String fieldName){
		return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
	public static String getMethodName(String fieldName){
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
}
