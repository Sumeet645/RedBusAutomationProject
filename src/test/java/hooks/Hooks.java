package hooks;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pages.LoginPage;
import stepDefinitions.LoginFunctionality;
import utils.DriverManager;
import utils.ScenarioContext;
import utils.UtilityMethods;
import utils.Log;

public class Hooks {
	
	private final DriverManager driverManager;
	
	public Hooks(DriverManager driverManager)
	{
		this.driverManager=driverManager;
	}
	
	@Before
	public void setUp(Scenario scenario)
	{
		ScenarioContext.setScenario(scenario);
		driverManager.initialiseDriver();
        System.out.println("Driver initialized");
       
	}
	
	@After
	public void tearDown(Scenario scenario)
	{
		try {
			if(scenario.isFailed())
			{
				UtilityMethods.takeScreenshot("Scenario Failed .." + scenario.getName());
			}
			else
			{
				Log.info("Sceanrio Passed" + scenario.getName());
			}
		}
		
		finally {
			//driverManager.quitDriver();
			ScenarioContext.clear();
		}
	}

}
