package com.grupp2;

import java.io.IOException;

public class Main {

    // Added booleans to enable/disable others code while developing to make it easier. Disable at some point.
    static boolean runNicklasDevCode = true;

    public static void main(String[] args) throws IOException {


        System.out.println("main started");

        if (runNicklasDevCode) {
            // Prerequisite: Download and associate project with jsoup.org jar (Can be downloaded at https://jsoup.org/download)
            GetNews.getNewsFromSource("http://www.dn.se");
        }
    }
}