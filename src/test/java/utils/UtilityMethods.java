package utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import io.cucumber.java.Scenario;

public class UtilityMethods {
	
	
	
	public static void takeScreenshot(WebDriver driver, String name)
	{
		Scenario sc=ScenarioContext.getScenario();
		if(sc==null) return;
		

		if(driver == null) return;
				
		
		byte[] screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		sc.attach(screenshot, "image/png", name);
		
		  // 2) Save to Screenshots folder
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            File folder = new File("Screenshots");
            if (!folder.exists()) folder.mkdirs();

            String safeName = name.replaceAll("[^a-zA-Z0-9-_]", "_");
            File dest = new File(folder, safeName + ".png");

            FileUtils.copyFile(src, dest);

            System.out.println("Screenshot saved at: " + dest.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
	}
}
}
