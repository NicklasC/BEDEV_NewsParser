package com.grupp2;

import java.util.Date;

public class NewsItem {

    private Date newsScanDate;
    private String newsText;
    private String newsLink;

    public NewsItem(Date newsScanDate, String newsText, String newsLink) {
        this.newsScanDate = newsScanDate;
        this.newsText = newsText;
        this.newsLink = newsLink;
    }

    public Date getNewsScanDate() {
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
