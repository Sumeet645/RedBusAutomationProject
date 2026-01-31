package pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import Locators.RedBus;
import utils.DriverManager;

public abstract class BasePage {
	
	protected WebDriver driver;
	protected RedBus locators;
	protected WebDriverWait wait;
	protected JavascriptExecutor js;
	
	protected BasePage()
	{
		driver=DriverManager.getDriver();
		this.locators=new RedBus();
		PageFactory.initElements(driver, locators);
		wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		js=(JavascriptExecutor)driver;
	}
	
	

}
