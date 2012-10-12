package com.epam.hlibornet;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import test.entity.Student;

import com.epam.hlibornet.model.Column;
import com.epam.hlibornet.model.ForeignKey;
import com.epam.hlibornet.model.PreparedModel;
import com.epam.hlibornet.model.Table;


public class Session {
	private static final Logger LOGGER = Logger.getLogger(Session.class);
			
	private Connection connection;
	private PreparedModel preparedModel;
	public Session(Connection connection, PreparedModel preparedModel) {
		super();
		this.connection = connection;
		this.preparedModel = preparedModel;
	}
	
	public Object getById(Class<?> clazz, int id) throws Exception{
		Table table = preparedModel.findTable(clazz);
		String sql = "SELECT * " +
				"FROM " + table.getName() + 
				";";
		PreparedStatement statement = connection.prepareStatement(sql);
		LOGGER.debug(statement);
		ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()){
			return createOne(resultSet, table);
		}else{
			return null;
		}
	}
	public List getAll(Class<?> clazz) throws Exception{
		List result = new ArrayList<>();
		Table table = preparedModel.findTable(clazz);
		String sql = "SELECT * " + 
				"FROM " + table.getName() + 
				";";
		PreparedStatement statement = connection.prepareStatement(sql);
		LOGGER.debug(statement);
		for(ResultSet resultSet = statement.executeQuery();
				resultSet.next();){
			Object one = createOne(resultSet, table);
			result.add(one);
		}
		return result;
	}
	public Object createOne(ResultSet resultSet, Table table) throws Exception {
		Object noobie = table.getClazz().newInstance();
		for(Column column : table.getColumns()){
			if(column.isInternal()){
				continue;
			}
			switch (column.getType()) {
			case INT:
				int int1 = resultSet.getInt(column.getName());
				ReflectiveUtil.setField(noobie, column.getName(), int1);
				break;
			case VARCHAR:
				String string = resultSet.getString(column.getName());
				ReflectiveUtil.setField(noobie, column.getName(), string);
				break;
			case FLOAT:
				float float1 = resultSet.getFloat(column.getName());
				ReflectiveUtil.setField(noobie, column.getName(), float1);
				break;
			case ENTITY:
				int entityID = resultSet.getInt(column.getName());
				Object byId = getById(column.getType().getClz(), entityID);
				ReflectiveUtil.setField(noobie, column.getName(), byId);
				break;
			default:
				break;
			}
		}
		for(ForeignKey fk : preparedModel.findFKsToThisTable(table)){
			Field finded = Session.findCollectionFieldForTable(noobie, fk.getFrom().getField().getClass());
			if(finded != null){
//				finded.set(noobie, getAll(fk.getFrom().getField().getClass(), fk.getFrom().getName() + " = "))
			}
		}
		
		return noobie;
	}
	
	public List getAll(Class<?> clz, String wherePart) throws Exception{ 
		List result = new ArrayList<>();
		Table table = preparedModel.findTable(clz);
		String sql = "SELECT * " + 
				"FROM " + table.getName() + " " +
				"WHERE " + wherePart + ";";
		PreparedStatement statement = connection.prepareStatement(sql);
		LOGGER.debug(statement);
		for(ResultSet resultSet = statement.executeQuery();
				resultSet.next();){
			Object one = createOne(resultSet, table);
			result.add(one);
		}
		return result;
	}
	private static Field findCollectionFieldForTable(Object noobie, Class<?> clz) throws Exception{
		for(Field field : noobie.getClass().getDeclaredFields()){
			if(TypeConverter.implCollection(field.getType()) &&
					TypeConverter.getGenericType(field).equals(clz)){
				return field;
			}
		}
		return null;
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
