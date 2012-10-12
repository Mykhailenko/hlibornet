package com.epam.hlibornet.model;

import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import com.epam.hlibornet.Finder;
import com.epam.hlibornet.ReflectiveUtil;
import com.epam.hlibornet.Session;

public class Table {
	private Class<?> clazz;
	private String name;
	private Set<Column> columns = new HashSet<>();
	
	
	public Column findColumn(String name) throws Exception{
		return (Column) Finder.find(columns, name, "name");
	}
	
	public void addColumn(Column column){
		column.setParent(this);
		columns.add(column);
	}
	
	

	public Set<Column> getColumns() {
		return columns;
	}
	public void setColumns(Set<Column> columns) {
		this.columns = columns;
	}
	public Class<?> getClazz() {
		return clazz;
	}
	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return MessageFormat.format(
				"Table [clazz={0}, name={1}, columns={2}, foreignKeys={3}]",
				clazz, name, columns);
	}
}
