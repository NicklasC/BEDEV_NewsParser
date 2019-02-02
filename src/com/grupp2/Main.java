package com.grupp2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.grupp2.utils.QueryUtils;

public class Main {

    // Added booleans to enable/disable others code while developing to make it easier. Disable at some point.
    static boolean runNicklasDevCode = true;
    static ArrayList<NewsItem> newsArrayList;
    
    public static void main(String[] args) 
    		throws IOException, ClassNotFoundException, SQLException {
    	
    	//QueryUtils dbQuery = new QueryUtils();
    	System.out.println("main started");

    	QueryUtils.connect();
    	// Call method to check connection.
    	boolean connectedToDb = QueryUtils.checkIfDbConnected(); 
    	if(connectedToDb) {
    		System.out.println("Connected to DB");
    	} else {
			System.out.println("Not connected to DB");
		}
			
		if (runNicklasDevCode) {
	        // Prerequisite: Download and associate project with jsour.org jar (Can be downloaded at https://jsoup.org/download)
			//GetNews.getNewsFromDN();
			
			// Get data from DN
			newsArrayList=GetNews.getNewsFromDN();
			QueryUtils.saveNewsInDB(newsArrayList);
			
			// Get data from GP
			newsArrayList=GetNews.getNewsFromGP();
			QueryUtils.saveNewsInDB(newsArrayList);
			
			// Select all data order by date
			QueryUtils.getDataFromDB();
			
			// Delete all content in scandata table
			//QueryUtils.deleteContentDB();
			
			/*
			for(NewsItem newsItem:newsArrayList){
				System.out.println(newsItem.getNewsLink());
				System.out.println(newsItem.getNewsText());
			}
			*/
		}
    }
}
