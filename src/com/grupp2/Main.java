package com.grupp2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.grupp2.utils.QueryUtils;

public class Main {

    // Added booleans to enable/disable others code while developing to make it easier. Disable at some point.
    static boolean runNicklasDevCode = true;

    public static void main(String[] args) 
    		throws IOException, ClassNotFoundException, SQLException {
    	
    	QueryUtils dbQuery = new QueryUtils();
    	System.out.println("main started");

    	dbQuery.connect();
    	// Call method to check connection.
    	boolean connectedToDb = dbQuery.checkIfDbConnected(); 
    	if(connectedToDb) {
    		System.out.println("Connected to DB");
    	} else {
			System.out.println("Not connected to DB");
		}
			
		if (runNicklasDevCode) {
	        // Prerequisite: Download and associate project with jsour.org jar (Can be downloaded at https://jsoup.org/download)

			// Example usage of GetNews.getNewsFromDN method. GetNews.getNewsFromGP works the same way.
			ArrayList<NewsItem> newsArrayList=GetNews.getNewsFromDN();
			for(NewsItem newsItem:newsArrayList){
				System.out.println(newsItem.getNewsLink());
				System.out.println(newsItem.getNewsText());
			}
		}
    }
}
