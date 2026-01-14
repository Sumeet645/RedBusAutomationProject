package pages.OrangeHRM;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Locators.OrangeHRM;
import utils.DriverManager;

public class DashboardPage {
	
	WebDriver driver;
	OrangeHRM locators;
	WebDriverWait wait;
	public DashboardPage(DriverManager driverManager,OrangeHRM locators)
	{
		driver=driverManager.getDriver();
		this.locators=locators;
		PageFactory.initElements(driver, locators);
		wait=new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	
	public void clickOnPerformanceTab()
	{
		wait.until(ExpectedConditions.elementToBeClickable(locators.Performance)).click();
	}

}
