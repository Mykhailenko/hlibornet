package com.epam.hlibornet.model;

public enum Type {
	INT, VARCHAR, FLOAT, ENTITY, COLLECTION;
	
	Type(){
		this.clz = null;
	}
	private Class<?> clz;
	public Class<?> getClz() {
		return clz;
	}
	public void setClz(Class<?> clz) {
		this.clz = clz;
	}
	
}
