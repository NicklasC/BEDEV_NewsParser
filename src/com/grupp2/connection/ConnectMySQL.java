package com.grupp2.connection;

import java.sql.*;

public class ConnectMySQL {
	public static Connection connectMySql() throws SQLException, ClassNotFoundException{
		final String url = "jdbc:mysql://localhost/";
		final String ssl = "?useSSL=false";
		final String multiQuery = "&allowMultiQueries=true";
		final String timezone = "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET";
		final String user = "root";
		final String password = "#(Zebbe2004)";
		
		return connect(url, ssl, multiQuery, timezone, user, password);
		}
		
		public static Connection connect(String url, String ssl, String multiQuery, String timezone, String user, String password) throws SQLException, ClassNotFoundException {
	    	Connection connect = DriverManager.getConnection(url + ssl + multiQuery + timezone, user, password);
	    	return connect;
	    }
		
		public static void closeConnection(Connection connect) throws Exception{
			try {
				connect.close();
			} catch (Exception e) {
			}
		}
}