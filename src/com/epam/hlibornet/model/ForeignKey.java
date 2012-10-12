package com.epam.hlibornet.model;

import java.text.MessageFormat;

public class ForeignKey {
	private String name;
	
	private Column from;
	
	private Column to;

	@Override
	public String toString() {
		return MessageFormat.format("ForeignKey [name={0}, from={1}, to={2}]",
				name, from.getName(), to.getName());
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Column getFrom() {
		return from;
	}
	public void setFrom(Column from) {
		this.from = from;
	}
	public Column getTo() {
		return to;
	}
	public void setTo(Column to) {
		this.to = to;
	}
	
}
