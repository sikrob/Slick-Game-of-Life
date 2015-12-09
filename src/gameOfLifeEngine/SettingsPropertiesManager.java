package gameOfLifeEngine;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Properties;
import java.io.*;

public class SettingsPropertiesManager {
	private Properties gameProperties = null;
	
	public SettingsPropertiesManager() {
		try {
			FileInputStream fileInputStream = new FileInputStream("./res/gameOfLifeProperties");
			this.gameProperties = new Properties();
			this.gameProperties.load(fileInputStream);
			fileInputStream.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace(System.out);
		}
	}
	
	public String stringValueForKey(String key) {
		return this.gameProperties.getProperty(key);
	}
}
