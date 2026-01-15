package pages;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Locators.RedBus;
import utils.DriverManager;
import utils.Log;
import utils.UtilityMethods;



public class HomePage {
	
	WebDriverWait wait;
	private final WebDriver driver;
	private final RedBus locators;
	JavascriptExecutor js;
	public HomePage(DriverManager driverManager, RedBus locators)
	{
		driver=driverManager.getDriver();
		this.locators=locators;
		PageFactory.initElements(driver, locators);
		wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		js=(JavascriptExecutor)driver;
	}
	
	public void getUrl(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
	
	}
	
	public String getPageTitle()
	{
		String title=driver.getTitle();
		return title;
		
	}
	
	public void inputFromField(String originCity)
	{
		wait.until(ExpectedConditions.elementToBeClickable(locators.FromLocator));
		locators.FromLocator.click();
		wait.until(ExpectedConditions.elementToBeClickable(locators.inputFromLocator));
		locators.inputFromLocator.sendKeys(originCity);
	}
	
	public void selectBoardingPoint(String boardingPoint) throws InterruptedException
	{
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfAllElements(locators.fromSearchResults));
		
		for(WebElement boarding : locators.fromSearchResults )
		{
			if(boarding.getText().contains(boardingPoint))
			{
				boarding.click();
				Log.info("Boarding Point " + boardingPoint + " Selected");
				UtilityMethods.takeScreenshot(driver,"Dropping Point " + boardingPoint + " Selected");
				break;
			}
		}
	}
	
	public void inputToField(String destinationCity)
	{
		wait.until(ExpectedConditions.elementToBeClickable(locators.inputTo));
		locators.inputTo.sendKeys(destinationCity);
		
	}
	
	public void selectDroppingPoint(String droppingPoint) throws Exception
	{
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfAllElements(locators.toSearchResults));
		System.out.println(locators.toSearchResults.size());
		for(WebElement drop : locators.toSearchResults )
		{
			System.out.println(drop.getText());
			if(drop.getText().contains(droppingPoint))
			{
				drop.click();
				Log.info("Dropping Point " + droppingPoint + " Selected");
				UtilityMethods.takeScreenshot(driver,"Dropping Point " + droppingPoint + " Selected");
				break;
			}
		}
		
		driver.findElement(By.xpath("//h2[text()='Offers for you']")).click();
	}
	
	public void selectTravelDate(String month)
	{
		wait.until(ExpectedConditions.elementToBeClickable(locators.dateLocator));
		locators.dateLocator.click();
		
		int i=1;
		
		while(i>0)
		{
			wait.until(ExpectedConditions.visibilityOf(locators.monthYearLocator));
			wait.until(ExpectedConditions.visibilityOf(locators.nextLocator));
			
			if(!(locators.monthYearLocator.getText().contains(month)))
			{
				locators.nextLocator.click();

			}
			else
			{
				WebElement day=driver.findElement(By.xpath("//span[text()='1']"));
				day.click();
				Log.info("Specified Date Selected");
				UtilityMethods.takeScreenshot(driver,"Specified Date Selected");
				break;
			}
			
		}
	}
	
	public void searchBus()
	{
		wait.until(ExpectedConditions.elementToBeClickable(locators.searchBusLocator));
		locators.searchBusLocator.click();
		Log.info("Clicked on Search Bus button");
		UtilityMethods.takeScreenshot(driver,"Clicked on Search Bus button");
		System.out.println("Clicked on Search Bus button");
	}
	
	
	
	
	
	
	
	
	

}
