package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Locators.OrangeHRM;
import utils.DriverManager;

public class LoginPage {
	
	
	WebDriverWait wait;
	private final WebDriver driver;
	private final OrangeHRM locators;
	public LoginPage(DriverManager driverManager,OrangeHRM locators)
	{
		this.driver=driverManager.getDriver();
		
		this.locators=locators;
		
		PageFactory.initElements(driver, locators);
		
		wait=new WebDriverWait(driver,Duration.ofSeconds(30));
	}
	
	public void getUrl(String url)
	{
		driver.get(url);
	}
	public void setUserName(String userName)
	{
		wait.until(ExpectedConditions.visibilityOf(locators.userName)).sendKeys(userName);
	}
	
	public void setPassWord(String passWord)
	{
		wait.until(ExpectedConditions.visibilityOf(locators.passWord)).sendKeys(passWord);
	}
	
	public void clickOnLogin()
	{
		wait.until(ExpectedConditions.elementToBeClickable(locators.loginBtn)).click();
	}

}
