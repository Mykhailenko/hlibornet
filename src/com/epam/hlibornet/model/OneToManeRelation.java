package com.epam.hlibornet.model;

import java.text.MessageFormat;

public class OneToManeRelation {
	private String fromTableName;
	private String toTableName;
	private String fromFieldName;
	private String toFieldName;
	
	@Override
	public String toString() {
		return MessageFormat
				.format("OneToManeRelation [fromTableName={0}, toTableName={1}, fromFieldName={2}, toFieldName={3}]",
						fromTableName, toTableName, fromFieldName, toFieldName);
	}
	public String getFromTableName() {
		return fromTableName;
	}
	public void setFromTableName(String fromTableName) {
		this.fromTableName = fromTableName;
	}
	public String getToTableName() {
		return toTableName;
	}
	public void setToTableName(String toTableName) {
		this.toTableName = toTableName;
	}
	public String getFromFieldName() {
		return fromFieldName;
	}
	public void setFromFieldName(String fromFieldName) {
		this.fromFieldName = fromFieldName;
	}
	public String getToFieldName() {
		return toFieldName;
	}
	public void setToFieldName(String toFieldName) {
		this.toFieldName = toFieldName;
	}
	
}
