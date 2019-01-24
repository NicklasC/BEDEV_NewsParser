
// This class get´s the content of db.properties file and save it in the cProp object.

package com.grupp2.utils;

import java.io.*;
import java.util.Properties;

public class GetPropertyValues {

	public static ConnectionProperties getPropValues(ConnectionProperties cProp) 
			throws IOException {
		FileInputStream readProperties = new FileInputStream
				("src/com/grupp2/resources/db.properties");
		Properties properties = new Properties();
		if(readProperties != null) {
			try {
				properties.load(readProperties);
				cProp.setUrl(properties.getProperty("jdbc.url"));
				cProp.setSsl(properties.getProperty("jdbc.ssl"));
				cProp.setMultiQuery(properties.getProperty("jdbc.multiQuery"));
				cProp.setTimezone(properties.getProperty("jdbc.timezone"));
				cProp.setUser(properties.getProperty("jdbc.username"));
				cProp.setPassword(properties.getProperty("jdbc.password"));
			} catch (Exception e) {
				throw new FileNotFoundException
					("Property file not found in the classpath");
			} finally {
				readProperties.close();
			}
		}
		return cProp;
	}
}