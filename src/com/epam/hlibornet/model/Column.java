package com.epam.hlibornet.model;

import java.lang.reflect.Field;
import java.text.MessageFormat;

public class Column {
	private Field field;
	private String name;
	private Type type;
	private boolean nullable;
	private boolean primary;
	private Table parent;
	private boolean internal;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public boolean isNullable() {
		return nullable;
	}
	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}
	public boolean isPrimary() {
		return primary;
	}
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public Table getParent() {
		return parent;
	}
	public void setParent(Table parent) {
		this.parent = parent;
	}
	
	public boolean isInternal() {
		return internal;
	}
	public void setInternal(boolean internal) {
		this.internal = internal;
	}
	@Override
	public String toString() {
		return MessageFormat.format(
				"Column [name={0}, type={1}, nullable={2}, primary={3}]",
				name, type, nullable, primary);
	}
	
}
