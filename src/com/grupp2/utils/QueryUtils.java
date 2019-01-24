package com.grupp2.utils;

import java.io.IOException;
import java.sql.*;

import com.grupp2.connection.ConnectMySQL;

public class QueryUtils {
	static Connection connection = null;
	static ResultSet resultSet = null;
	static Statement statement = null;
	
	// Connecting to DB
	public void connect() throws ClassNotFoundException, SQLException, IOException {
		connection = ConnectMySQL.connectMySql();
		statement = connection.createStatement();
	}
	
	// This method can be used to check the status of connection.
		public boolean checkIfDbConnected() 
				throws ClassNotFoundException, SQLException, IOException {
			if (connection.isValid(1)) {
				return true;
			}
			return false;
		}
	
	// Close connection
	public static void closeConnection() throws SQLException, Exception{
		resultSet.close();
		statement.close();
		ConnectMySQL.closeConnection(connection);
	}
}