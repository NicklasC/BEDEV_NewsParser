package com.grupp2.utils;

public class ConnectionProperties {
	private String url;
	private String ssl;
	private String multiQuery;
	private String timezone;
	private String user;
	private String password;
	
	public ConnectionProperties() {
		
	}
	
	public ConnectionProperties(String url, String ssl, String multiQuery, String timezone, String user, String password) {
		this.url = url;
		this.ssl = ssl;
		this.multiQuery = multiQuery;
		this.timezone = timezone;
		this.user = user;
		this.password = password;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getSsl() {
		return ssl;
	}
	
	public void setSsl(String ssl) {
		this.ssl = ssl;
	}
	
	public String getMultiQuery() {
		return multiQuery;
	}
	
	public void setMultiQuery(String multiQuery) {
		this.multiQuery = multiQuery;
	}
	
	public String getTimezone() {
		return timezone;
	}
	
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}