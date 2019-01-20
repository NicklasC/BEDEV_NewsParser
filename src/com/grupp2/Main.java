package com.grupp2;

import java.io.IOException;

public class Main {

    static boolean runNicklasDevCode = true;

    public static void main(String[] args) throws IOException {


        System.out.println("main started");

        if (runNicklasDevCode) {
            // Prerequisite: Download and associate project with jsour.org jar (Can be downloaded at https://jsoup.org/download)
            GetNews.getNewsFromSource("http://www.dn.se");
        }
    }
}