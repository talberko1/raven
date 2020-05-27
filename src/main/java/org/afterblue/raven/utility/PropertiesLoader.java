package org.afterblue.raven.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
	public static Properties loadPropertyFile(String file) {
		Properties configuration = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream(file);
			configuration.load(input);
			input.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return configuration;
	}
	
}
