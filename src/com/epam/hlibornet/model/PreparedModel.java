package com.epam.hlibornet.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.epam.hlibornet.Finder;

public class PreparedModel {
	private Set<Table> tables = new HashSet<>();
	private Set<OneToManeRelation> oneToManeRelations = new HashSet<>();
	private Set<ManyToManyRelation> manyToManyRelations = new HashSet<>();
	
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

	public Set<OneToManeRelation> getOneToManeRelations() {
		return oneToManeRelations;
	}

	public void setOneToManeRelations(Set<OneToManeRelation> oneToManeRelations) {
		this.oneToManeRelations = oneToManeRelations;
	}

	public Set<ManyToManyRelation> getManyToManyRelations() {
		return manyToManyRelations;
	}

	public void setManyToManyRelations(Set<ManyToManyRelation> manyToManyRelations) {
		this.manyToManyRelations = manyToManyRelations;
	}

	
	
}
