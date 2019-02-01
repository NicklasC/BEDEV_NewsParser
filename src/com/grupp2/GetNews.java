package com.grupp2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class GetNews {
    public static ArrayList<NewsItem> getNewsFromDN() throws IOException {

        final String sourceName="DN";
        final String sourceURL="http://www.dn.se";

        String newsLink;
        String newsText;

        // newsArrayList will contain all news to be returned to be stored in the Database.
        ArrayList<NewsItem> newsArrayList = new ArrayList<>();

        // Fetching all anchor elements from source.
        ArrayList<Elements> newsElementsArrayList = getAnchorElementsFromSource(sourceURL);

        // Iterating all achor elements
        for (Elements link : newsElementsArrayList) {
            if (link.hasClass("teaser")) { //If link contains this class, it is a news link we want to return information on
                newsLink = link.attr("abs:href"); //getting the anchor link
                //print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 100));
                Elements text = link.select("h1");//Filtering out the header part within the link that contains the new title text we want to display (We do not want photographer text)
                newsText = text.text();

                newsArrayList.add(new NewsItem(sourceName, sourceURL,newsText, newsLink));//adding a new NewsItem object to the newsArrayList containing the collected information.
            }
        }
        return newsArrayList;
    }


    public static ArrayList<NewsItem> getNewsFromGP() throws IOException {

        final String sourceName="GP";
        final String sourceURL="http://www.gp.se";

        String newsLink;
        String newsText;

        // newsArrayList will contain all news to be returned to be stored in the Database.
        ArrayList<NewsItem> newsArrayList = new ArrayList<>();

        // Fetching all anchor elements from source.
        ArrayList<Elements> newsElementsArrayList = getAnchorElementsFromSource("http://www.gp.se");

        // Iterating all achor elements
        for (Elements link : newsElementsArrayList) {
            if (link.hasClass("c-teaser__link")) { //If link contains this class, it is a news link we want to return information on
                newsLink = link.attr("abs:href"); //getting the anchor link
                //print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 100));
                Elements text = link.select("h2");//Filtering out the header part within the link that contains the new title text we want to display
                newsText = text.text();

                newsArrayList.add(new NewsItem(sourceName,sourceURL,newsText, newsLink));//adding a new NewsItem object to the newsArrayList containing the collected information.
            }
        }
        return newsArrayList;
    }


    private static ArrayList<Elements> getAnchorElementsFromSource(String sourceURL) throws IOException {
        ArrayList<Elements> anchorElementsArrayList = new ArrayList<>();

        //print("Fetching %s...", url);
        Document doc = Jsoup.connect(sourceURL).get();
        Elements links = doc.select("a[href]");

        // Anchorlinks retrieved. Adding raw scan to ArrayList.
        for (Element link : links) {
            //print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 70));
            anchorElementsArrayList.add(new Elements(link));
        }
        return anchorElementsArrayList;
    }

    // Saving method, could be used when further developing getNews.. methods
    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }

    // Saving method, could be used when further developing getNews.. methods
    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width - 1) + ".";
        else
            return s;
    }
}
