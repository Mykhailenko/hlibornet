package com.epam.hlibornet.model;

import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import com.epam.hlibornet.Finder;
import com.epam.hlibornet.ReflectiveUtil;

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
	
	public Object createOne(ResultSet resultSet) throws Exception {
		Object noobie = clazz.newInstance();
		for(Column column : columns){
			switch (column.getType()) {
			case INT:
				int int1 = resultSet.getInt(column.getName());
				ReflectiveUtil.setField(noobie, column.getName(), int1);
				break;
			case VARCHAR:
				String string = resultSet.getString(column.getName());
				ReflectiveUtil.setField(noobie, column.getName(), string);
				break;
			default:
				break;
			}
		}
		return noobie;
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
