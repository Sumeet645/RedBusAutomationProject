package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.Scenario;


public class Log {
	
	
	private static final Logger log=LogManager.getRootLogger();
	
	private Log() {}
	
	public static void info(String message)
	{
		log.info(message);
		Scenario sc=ScenarioContext.getScenario();
		
		if(sc!=null)
			sc.log(message);
	}
	
	public static void error(String message)
	{
		log.error(message);
		Scenario sc=ScenarioContext.getScenario();
		
		if(sc!=null)
			sc.log("ERROR " + sc.getName());
	}
	
	public static void debug(String message)
	{
		log.debug(message);
		Scenario sc=ScenarioContext.getScenario();
		
		if(sc!=null)
			sc.log("DEBUG " + sc.getName());
	}
	
	public static void infoWithScreenShot(String message)
	{
		log.info(message);
		UtilityMethods.takeScreenshot(message.replace(" ", "_"));
	}


}
