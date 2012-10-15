package com.epam.hlibornet.model;

import java.text.MessageFormat;

public class ManyToManyRelation {
	private String linkTableName;
	private String ownTableName;
	private String ownTableField;
	private String otherTableName;
	private String otherTableField;
	
	@Override
	public String toString() {
		return MessageFormat
				.format("ManyToManyRelation [linkTableName={0}, ownTableName={1}, ownTableField={2}, otherTableName={3}, otherTableField={4}]",
						linkTableName, ownTableName, ownTableField, otherTableName, otherTableField);
	}

	public String getLinkTableName() {
		return linkTableName;
	}

	public void setLinkTableName(String linkTableName) {
		this.linkTableName = linkTableName;
	}

	public String getOwnTableName() {
		return ownTableName;
	}

	public void setOwnTableName(String ownTableName) {
		this.ownTableName = ownTableName;
	}

	public String getOwnTableField() {
		return ownTableField;
	}

	public void setOwnTableField(String ownTableField) {
		this.ownTableField = ownTableField;
	}

	public String getOtherTableName() {
		return otherTableName;
	}

	public void setOtherTableName(String otherTableName) {
		this.otherTableName = otherTableName;
	}

	public String getOtherTableField() {
		return otherTableField;
	}

	public void setOtherTableField(String otherTableField) {
		this.otherTableField = otherTableField;
	}
	
}
