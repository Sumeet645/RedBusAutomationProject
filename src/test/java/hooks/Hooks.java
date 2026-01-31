package hooks;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import pages.LoginPage;
import stepDefinitions.LoginFunctionality;
import utils.ConfigReader;
import utils.DriverManager;
import utils.ScenarioContext;
import utils.UtilityMethods;
import utils.Log;

public class Hooks {

	@Before
	public void setUp(Scenario scenario) {
		ScenarioContext.setScenario(scenario);

		DriverManager.initialiseDriver();
		Log.info("Driver initialized");
		
		String env=ConfigReader.get("env");
		String url=ConfigReader.get(env+".url");
		
		DriverManager.getDriver().get(url);

	}

	@After
	public void tearDown(Scenario scenario) {

		WebDriver driver = DriverManager.getDriver();

		try {
			if (scenario.isFailed()) {
				UtilityMethods.takeScreenshot(driver, "Scenario Failed .." + scenario.getName());
			}

			else {
				Log.info("Sceanrio Passed" + scenario.getName());
			}
		}

		finally {
			DriverManager.quitDriver();
			ScenarioContext.clear();
		}
	}

}
