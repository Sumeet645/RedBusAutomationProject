package runners;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/resources/features",
						glue={"stepDefinitions","hooks"},
						plugin= 
							{
								"pretty",
						        "html:target/cucumber-html-report.html",
						        "json:target/cucumber.json",
						        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
							},
							tags="${cucumber.filter.tags}",
						monochrome=true,
						dryRun=false)
public class TestRunner extends AbstractTestNGCucumberTests {
	
	@DataProvider(parallel=false)
	public Object[][] scenarios()
	{
		return super.scenarios();
	}
	
	

}
