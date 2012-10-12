package com.epam.hlibornet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.epam.hlibornet.model.Column;
import com.epam.hlibornet.model.ForeignKey;
import com.epam.hlibornet.model.PreparedModel;
import com.epam.hlibornet.model.Table;
import com.epam.hlibornet.model.Type;

public class KeyExplorer {
	private static final Logger LOGGER = Logger.getLogger(KeyExplorer.class);

	public static void fillFK(Connection connection,
			PreparedModel preparedModel, Configuration configuration)
			throws Exception {
		String inPart = "(";
		for (Table table : preparedModel.getTables()) {
			inPart += "'" + table.getName() + "',";
		}
		inPart = inPart.substring(0, inPart.length() - 1);
		inPart += ")";
		String sql = "SELECT " + "table_name, column_name, constraint_name ,"
				+ "referenced_table_name, referenced_column_name "
				+ "FROM information_schema.key_column_usage "
				+ "WHERE table_name IN " + inPart + " "
				+ "OR referenced_table_name IN " + inPart + ";";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		for (ResultSet resultSet = preparedStatement.executeQuery(); resultSet
				.next();) {
			String tableName = resultSet.getString("table_name");
			String columnName = resultSet.getString("column_name");
			String constraintName = resultSet.getString("constraint_name");
			String referencedTableName = resultSet
					.getString("referenced_table_name");
			String referencedColumnName = resultSet
					.getString("referenced_column_name");
			Column baseColumn = preparedModel.findTable(tableName).findColumn(
					columnName);
			if ("PRIMARY".equals(constraintName)) {
				baseColumn.setPrimary(true);
			} else if (!constraintName.endsWith("_UNIQUE")) {
				Table findTable = preparedModel.findTable(referencedTableName);
				Column column = findTable.findColumn(referencedColumnName);
				if (column != null) {
					ForeignKey foreignKey = new ForeignKey();
					foreignKey.setFrom(baseColumn);
					foreignKey.setTo(column);
					foreignKey.setName(constraintName);
					LOGGER.info(foreignKey.toString());
					preparedModel.getForeignKeys().add(foreignKey);
				} else {
					Column internal = new Column();
					internal.setInternal(true);
					internal.setName(referencedColumnName);
					internal.setParent(findTable);
					internal.setType(Type.COLLECTION);
				}

			}

		}
	}
}
