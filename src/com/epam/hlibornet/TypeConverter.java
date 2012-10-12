package com.epam.hlibornet;

import com.epam.hlibornet.model.Type;

public class TypeConverter {
	public static Type convert(Class<?> clz){
		if(clz == Integer.class || clz == int.class){
			return Type.INT;
		}else if(clz == String.class){
			return Type.VARCHAR;
		}else{
			throw new UnsupportedOperationException();
		}
	}
}
