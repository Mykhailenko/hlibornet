package com.epam.hlibornet;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;


public class TransactionManager {
	private static final Logger LOGGER = Logger.getLogger(TransactionManager.class);
	private DataSource ds;
	@SuppressWarnings("unchecked")
	public <T> T doInTransaction(TransactionOperation<?> transactionOperation) throws SQLException{
		Connection connection = null;
		try {
			connection = ds.getConnection();
			connection.setTransactionIsolation(transactionOperation.transactionLevel());
			connection.setAutoCommit(false);
			JDBCConnectionHolder.set(connection);
			T returned = null;
			try{
				returned = (T) transactionOperation.execute();
			}catch (Exception e) {
				throw new SQLException(e);
			}
			JDBCConnectionHolder.unset();
			connection.commit();
			return returned;
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw e1;
			}
			throw e;
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				throw e;
			}
		}
	}
}
