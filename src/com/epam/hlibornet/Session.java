package com.epam.hlibornet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.hlibornet.model.PreparedModel;
import com.epam.hlibornet.model.Table;


public class Session {
	private Connection connection;
	private PreparedModel preparedModel;
	public Session(Connection connection, PreparedModel preparedModel) {
		super();
		this.connection = connection;
		this.preparedModel = preparedModel;
	}

	public List getAll(Class<?> clazz) throws Exception{
		List result = new ArrayList<>();
		Table table = preparedModel.findTable(clazz);
		String sql = "SELECT * " + 
				"FROM " + table.getName() + 
				";";
		PreparedStatement statement = connection.prepareStatement(sql);
		for(ResultSet resultSet = statement.executeQuery();
				resultSet.next();){
			Object one = table.createOne(resultSet);
			result.add(one);
		}
		return result;
	}
	
	public void save(Object object){
		
	}
	
	public void update(Object object){
		
	}
	
	
	public void delete(Object object){
		
	}
	public void commit() throws SQLException{
		connection.commit();
	}
	public void roolback() throws SQLException{
		connection.rollback();
	}
	
}
