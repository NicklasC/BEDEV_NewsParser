package com.grupp2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.grupp2.utils.QueryUtils;

public class Main {

    // Added booleans to enable/disable others code while developing to make it easier. Disable at some point.
    static boolean runNicklasDevCode = true;
    static ArrayList<NewsItem> newsArrayList;
    static String sourceName;
    static String sourceUrl;

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
			sourceName = "DN";
			sourceUrl = "www.dn.se";
			newsArrayList=GetNews.getNewsFromDN();
			QueryUtils.saveNewsInDB(newsArrayList, sourceName, sourceUrl);
			
			// Get data from GP
			sourceName = "GP";
			sourceUrl = "www.gp.se";
			newsArrayList=GetNews.getNewsFromGP();
			QueryUtils.saveNewsInDB(newsArrayList, sourceName, sourceUrl);
			
			/*
			for(NewsItem newsItem:newsArrayList){
				System.out.println(newsItem.getNewsLink());
				System.out.println(newsItem.getNewsText());
			}
			*/
		}
    }
}
