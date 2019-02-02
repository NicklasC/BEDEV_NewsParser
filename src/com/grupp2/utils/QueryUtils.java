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
	public static void saveNewsInDB(ArrayList<NewsItem> newsArrayList) throws SQLException {
		String database = "use news;";
		String insertSqlQuery = " INSERT INTO datasource (name, starturl) " + 
				"SELECT * FROM (SELECT ?, ?) AS tmp " + 
				"WHERE NOT EXISTS ( " + 
				"    SELECT name FROM datasource WHERE name = ? " + 
				") LIMIT 1;" + 
				"insert into scandata " + 
				"(id, sourcetext, sourcelink, date) " + 
				"values ((select id from datasource where name = ?), ?, ?, now());";
		
		try {
			preparedStatement = connection.prepareStatement(database+insertSqlQuery);
			for (NewsItem newsItem : newsArrayList) {
				preparedStatement.setString(1, newsItem.getSourceName());
				preparedStatement.setString(2, newsItem.getSourceURL());
				preparedStatement.setString(3, newsItem.getSourceName());
				preparedStatement.setString(4, newsItem.getSourceName());
				preparedStatement.setString(5, newsItem.getNewsText());
				preparedStatement.setString(6, newsItem.getNewsLink());
				preparedStatement.execute();
			}
		} finally {
			preparedStatement.close();
			newsArrayList.clear();
		}
	}
	
	// Delete all content in scandata table
	public static void deleteContentDB() throws SQLException {
		String database = "use news;";
		String deleteSqlQuery = " DELETE scandata FROM scandata JOIN datasource USING(id);";
		try {
			preparedStatement = connection.prepareStatement(database+deleteSqlQuery);
			preparedStatement.execute();
		} finally {
			preparedStatement.close();
		}
	}
	
	// Select data from databas to view
	public static void getDataFromDB() throws SQLException {
		String database = "use news;";
		String viewSqlQuery = " SELECT name, sourcetext, sourcelink " + 
				"FROM datasource JOIN scandata USING(id) " +
				"order by date desc;";
		try {
			preparedStatement = connection.prepareStatement(database+viewSqlQuery);
			preparedStatement.execute();
			
			// Save data in som kind of object here to return to GUI presentation
			
			
		} finally {
			preparedStatement.close();
		}
	}
}