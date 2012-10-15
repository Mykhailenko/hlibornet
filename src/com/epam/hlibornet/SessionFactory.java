package com.epam.hlibornet;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.epam.hlibornet.annotation.BlackJack;
import com.epam.hlibornet.annotation.Id;
import com.epam.hlibornet.annotation.ManyToMany;
import com.epam.hlibornet.annotation.ManyToOne;
import com.epam.hlibornet.annotation.Nullable;
import com.epam.hlibornet.annotation.OneToMany;
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
		loadModel();
		loadDriver();
	}

	private void loadDriver() throws ClassNotFoundException {
		Class.forName(configuration.getDialect().getDriverName());
	}
	
	@SuppressWarnings("static-access")
	private void loadModel() throws Exception {
		LOGGER.info("[start load model]");
		FileSystemClassScanner scanner = new FileSystemClassScanner();
		for(Class<?> clz : scanner.getClasses(configuration.getScanPackage())){
			if(isNotSkip(clz)){
				loadClass(clz);
			}
		}
		LOGGER.info("[end load model]");
	}
	private boolean isNotSkip(Class<?> clz){
		return clz.getAnnotation(BlackJack.class) != null;
	}
	private boolean isNotSkip(Field field){
		return field.getAnnotation(Transient.class) == null;
	}
	private void loadClass(Class<?> clz){
		BlackJack blackJack = clz.getAnnotation(BlackJack.class);
		if(blackJack.name().equals("")){
			loadClassAssociatedWithTable(clz, clz.getSimpleName().toLowerCase());
		}else{
			loadClassAssociatedWithTable(clz, blackJack.name());
		}
	}
	private void loadClassAssociatedWithTable(Class<?> clz, String tableName){
		LOGGER.info("[start loadlass: " + clz.getSimpleName() + "]");
		Table table = new Table();
		table.setClazz(clz);
		table.setName(clz.getSimpleName().toLowerCase());
		for(Field field : clz.getDeclaredFields()){
			if(isNotSkip(field)){
				if(isSimpleField(field)){
					table.addColumn(getColumn(field));
				}else if(isOneToMane(field)){
					
				}
			}
		}
		preparedModel.getTables().add(table);
		LOGGER.info("[added table: " + table.toString() + "]");
		LOGGER.info("[end loadlass: " + clz.getSimpleName() + "]");
		
	}
	private boolean isOneToMane(Field field) {
		// TODO Auto-generated method stub
		return false;
	}

	private Column getColumn(Field field){
		Column column = new Column();
		column.setName(getColumnName(field));
		column.setNullable(isNullable(field));
		column.setPrimary(isPrimaryKey(field));
		column.setType(TypeConverter.convert(field.getType(), configuration.getScanPackage()));
		return column;
	}
	private String getColumnName(Field field){
		com.epam.hlibornet.annotation.Column column = field.getAnnotation(com.epam.hlibornet.annotation.Column.class);
		if(column == null || column.name().equals("")){
			return field.getName();
		}else {
			return column.name();
		}
		
	}
	private boolean isSimpleField(Field field){
		return field.getAnnotation(OneToMany.class) == null && 
				field.getAnnotation(ManyToOne.class) == null && 
				field.getAnnotation(ManyToMany.class) == null &&
				!TypeConverter.implCollection(field.getType().getClass());
	}
	private boolean isNullable(Field field){
		return field.getAnnotation(Nullable.class) != null;
	}
	private boolean isPrimaryKey(Field field){
		return field.getAnnotation(Id.class) != null;
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
