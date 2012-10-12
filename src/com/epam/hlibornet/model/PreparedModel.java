package com.epam.hlibornet.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.epam.hlibornet.Finder;

public class PreparedModel {
	private Set<Table> tables = new HashSet<>();
	private Set<ForeignKey> foreignKeys = new HashSet<>();
	
	
	public List<ForeignKey> findFKsToThisTable(Table table) throws Exception{
		return Finder.findAll(foreignKeys, table, "to", "parent");
	}
	public List<ForeignKey> findFKsFromThisTable(Table table) throws Exception{
		return Finder.findAll(foreignKeys, table, "from", "parent");
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
