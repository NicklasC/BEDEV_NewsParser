package com.grupp2;

public class NewsItem {

    private String sourceName;
    private String sourceURL;
    private String newsText;
    private String newsLink;

    public NewsItem(String sourceName, String sourceURL, String newsText, String newsLink) {
        this.sourceName=sourceName;
        this.sourceURL=sourceURL;
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

    public String getSourceName() {
        return sourceName;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }
}
