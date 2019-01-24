
// This class handle the connection to database
// Properties like url, ssl, user, password mm. comming from db.properties file 

package com.grupp2.connection;

import java.io.*;
import java.sql.*;

import com.grupp2.utils.ConnectionProperties;
import com.grupp2.utils.GetPropertyValues;

public class ConnectMySQL {
	public static Connection connectMySql() 
			throws SQLException, ClassNotFoundException, IOException{
		
		ConnectionProperties cProp = new ConnectionProperties();
		GetPropertyValues.getPropValues(cProp);
		final String url = cProp.getUrl();
		final String ssl = cProp.getSsl();
		final String multiQuery = cProp.getMultiQuery();
		final String timezone = cProp.getTimezone();
		final String user = cProp.getUser();
		final String password = cProp.getPassword();

		return connect(url, ssl, multiQuery, timezone, user, password);
	}
		
	public static Connection connect(String url, String ssl, String multiQuery, String timezone, String user, String password) 
			throws SQLException, ClassNotFoundException {
    	Connection conn = DriverManager.getConnection(url + ssl + multiQuery + timezone, user, password);
    	return conn;
    }

	public static void closeConnection(Connection conn) throws SQLException {
		conn.close();
	}
}