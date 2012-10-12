package com.epam.hlibornet;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.hlibornet.annotation.BlackJack;
import com.epam.hlibornet.annotation.Transient;
import com.epam.hlibornet.model.Column;
import com.epam.hlibornet.model.PreparedModel;
import com.epam.hlibornet.model.Table;

public class SessionFactory {
	private static final Logger LOGGER = Logger.getLogger(SessionFactory.class);
	
	private static SessionFactory instance;
	private Configuration configuration;
	private PreparedModel preparedModel;
	private SessionFactory(Configuration configuration) throws Exception{
		this.configuration = configuration;
		this.preparedModel = loadModel();
		KeyExplorer.fillFK(getConnection(), preparedModel, configuration);
		Class.forName("com.mysql.jdbc.Driver");
	}
	
	@SuppressWarnings("static-access")
	private PreparedModel loadModel() throws ClassNotFoundException {
		LOGGER.debug("loadModel");
		PreparedModel model = new PreparedModel();
		FileSystemClassScanner scanner = new FileSystemClassScanner();
		for(Class<?> cls : scanner.getClassesInPackage(configuration.getScanPackage())){
			BlackJack blackJack = cls.getAnnotation(BlackJack.class);
			if(blackJack != null){
				if(blackJack.name().equals("")){
					LOGGER.debug("find class: " + cls.getSimpleName());
					Table table = new Table();
					table.setClazz(cls);
					table.setName(cls.getSimpleName().toLowerCase());
					for(Field field : cls.getDeclaredFields()){
						if(field.getAnnotation(Transient.class) == null){
							Column column = new Column();
							column.setName(field.getName());
							column.setNullable(false);// TODO nullable
							column.setPrimary(false); // TODO primary
							column.setType(TypeConverter.convert(field.getType()));
							LOGGER.debug("find field: " + field.getName());
							LOGGER.debug("added column: " + column.toString());
							table.addColumn(column);
						}
					}
					LOGGER.debug("added table: " + table.toString());
					model.getTables().add(table);
				}
			}
		}
		return model;
	}

	public static SessionFactory getInstance(Configuration configuration) throws Exception{
		if(instance == null){
			instance = new SessionFactory(configuration);
		}
		return instance;
	}
	
	public Session openSession() throws SQLException{
		return new Session(getConnection(), preparedModel);
	}
	private Connection getConnection() throws SQLException{
		return DriverManager.getConnection(configuration.getUrl() + configuration.getBdName(), 
				configuration.getUsername(), configuration.getPassword());
	}
}
