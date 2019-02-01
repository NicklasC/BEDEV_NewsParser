package com.grupp2.utils;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import com.grupp2.NewsItem;
import com.grupp2.connection.ConnectMySQL;

public class QueryUtils {
	static Connection connection = null;
	//static ResultSet resultSet = null;
	static Statement statement = null;
	static PreparedStatement preparedStatement = null;
	
	// Connecting to DB
	public static void connect() throws ClassNotFoundException, SQLException, IOException {
		connection = ConnectMySQL.connectMySql();
		statement = connection.createStatement();
	}
	
	// This method can be used to check the status of connection.
	public static boolean checkIfDbConnected() throws ClassNotFoundException, SQLException, IOException {
		if (connection.isValid(1)) {
			return true;
		}
		return false;
	}
	
	// Close connection
	public static void closeConnection() throws SQLException, Exception{
		//resultSet.close();
		statement.close();
		ConnectMySQL.closeConnection(connection);
	}
	
	// Insert data into database
	public static void saveNewsInDB(ArrayList<NewsItem> newsArrayList, String sourceName, String sourceUrl) throws SQLException {
		String database = "use news;";
		String insertSqlQuery = " INSERT INTO datasource (name, starturl) " + 
				"SELECT * FROM (SELECT '"+sourceName+"', '"+sourceUrl+"') AS tmp " + 
				"WHERE NOT EXISTS ( " + 
				"    SELECT name FROM datasource WHERE name = '"+sourceName+"' " + 
				") LIMIT 1;" + 
				"insert into scandata " + 
				"(id, sourcetext, sourcelink, date) " + 
				"values ((select id from datasource where name = '"+sourceName+"'), ?, ?, now());";
		
		try {
			preparedStatement = connection.prepareStatement(database+insertSqlQuery);
			for (NewsItem newsItem : newsArrayList) {
				preparedStatement.setString(1, newsItem.getNewsText());
				preparedStatement.setString(2, newsItem.getNewsLink());
				preparedStatement.execute();
			}
		} finally {
			preparedStatement.close();
			newsArrayList.clear();
		}
	}
}