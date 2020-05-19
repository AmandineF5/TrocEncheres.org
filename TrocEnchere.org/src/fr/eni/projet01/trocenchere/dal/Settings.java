
// LESLIE

package fr.eni.projet01.trocenchere.dal;

import java.io.IOException;
import java.util.Properties;

public class Settings {
	private static Properties properties;
	
	static {
		properties = new Properties();
		try {
			properties.load(Settings.class.getResourceAsStream("Settings.properties.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
