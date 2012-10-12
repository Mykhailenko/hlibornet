package com.epam.hlibornet.model;

import java.util.HashSet;
import java.util.Set;

import com.epam.hlibornet.Finder;

public class PreparedModel {
	private Set<Table> tables = new HashSet<>();
	private Set<ForeignKey> foreignKeys = new HashSet<>();
	
	
	public ForeignKey findFKToThisTable(Table table) throws Exception{
		return (ForeignKey) Finder.find(foreignKeys, table, "to", "parent");
	}
	public ForeignKey findFKFromThisTable(Table table) throws Exception{
		return (ForeignKey) Finder.find(foreignKeys, table, "from", "parent");
	}
	public Table findTable(String name) throws Exception{
		return (Table) Finder.find(tables, name, "name");
	}
	
	public Table findTable(Class<?> clazz) throws Exception{
		return (Table) Finder.find(tables, clazz, "clazz");
	}
	
	
	public Set<Table> getTables() {
		return tables;
	}


	public void setTables(Set<Table> tables) {
		this.tables = tables;
	}


	public Set<ForeignKey> getForeignKeys() {
		return foreignKeys;
	}

	public void setForeignKeys(Set<ForeignKey> foreignKeys) {
		this.foreignKeys = foreignKeys;
	}
	
}
