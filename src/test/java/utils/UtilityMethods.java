package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import io.cucumber.java.Scenario;

public class UtilityMethods {
	
	
	
	public static void takeScreenshot(String name)
	{
		Scenario sc=ScenarioContext.getScenario();
		if(sc==null) return;
		
		WebDriver driver=DriverManager.getDriver();
				
		
		byte[] screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		sc.attach(screenshot, "image/png", name);
	}
}
