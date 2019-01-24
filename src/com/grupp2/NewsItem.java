package com.grupp2;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewsItem {
    //TODO: Add a source here. DB id? Text? TBD...
    private String newsScanDate;
    private String newsText;
    private String newsLink;

    public NewsItem(String newsText, String newsLink) {
        newsScanDate = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        this.newsText = newsText;
        this.newsLink = newsLink;
    }

    public String getNewsScanDate() {
        return newsScanDate;
    }

    public String getNewsText() {
        return newsText;
    }

    public void setNewsText(String newsText) {
        this.newsText = newsText;
    }

    public String getNewsLink() {
        return newsLink;
    }

    public void setNewsLink(String newsLink) {
        this.newsLink = newsLink;
    }
}
