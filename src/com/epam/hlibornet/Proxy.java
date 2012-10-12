package com.epam.hlibornet;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy implements InvocationHandler{

	private Object object;
	
	public Proxy(Object object) {
		super();
		this.object = object;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		return method.invoke(object, args);
	}

}
