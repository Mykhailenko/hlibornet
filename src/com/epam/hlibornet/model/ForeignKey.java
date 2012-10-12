package com.epam.hlibornet.model;

public class ForeignKey {
	private String name;
	
	private Column from;
	
	private Column to;
	
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
