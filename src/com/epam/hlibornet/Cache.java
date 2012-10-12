package com.epam.hlibornet;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cache {
	private Map<Class<?>, Map<Object, Object>> content = new HashMap<>();
	
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	public synchronized void put(Object id, Object val){
		if(!content.containsKey(val.getClass())){
			content.put(val.getClass(), new LinkedHashMap(){
				@Override
				protected boolean removeEldestEntry(java.util.Map.Entry eldest) {
					return size() > 500;
				} 
			});
		}
		Map<Object, Object> map = content.get(val.getClass());
		map.put(id, val);
	}
	
	public synchronized Object get(Object id, Class<?> clz){
		if(content.containsKey(clz)){
			Map<Object, Object> map = content.get(clz);
			return map.get(id);
		}else{
			return null;
		}
	}
}
