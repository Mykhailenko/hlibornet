package com.epam.hlibornet;

import java.sql.Connection;

public class JDBCConnectionHolder {
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	
	public static void set(Connection connection){
		threadLocal.set(connection);
	}
	
	public static void unset(){
		threadLocal.remove();
	}
	
	public static Connection get(){
		return threadLocal.get();
	}
}
