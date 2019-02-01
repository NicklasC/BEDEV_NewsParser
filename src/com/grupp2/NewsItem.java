package com.grupp2;

public class NewsItem {

    private String newsText;
    private String newsLink;

    public NewsItem(String newsText, String newsLink) {
        this.newsText = newsText;
        this.newsLink = newsLink;
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
