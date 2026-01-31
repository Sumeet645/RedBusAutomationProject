package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	
	private static final Properties props=new Properties();
	static {
	try {
		InputStream is=ConfigReader.class.getClassLoader().getResourceAsStream("config.properties");
		
		if(is==null)
		{
			throw new RuntimeException("Config file not found");
		}
		
		props.load(is);
	}
	catch(Exception e)
	{
		throw new RuntimeException("Failed to load config file");
	}
}
	
	public static String get(String key)
	{
		return props.getProperty(key);
	}
}
