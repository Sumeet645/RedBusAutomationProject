package com.redbus.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedbusApplicationAutomate {

	protected static WebDriver driver;

	@BeforeMethod
	public static void navigateToRedbus() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.get("https://www.redbus.in/");

		driver.manage().window().maximize();

		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);

	}

	@Test
	public static void searchBuses() throws InterruptedException {
		System.out.println(driver.getCurrentUrl());
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		
		By fromLocator=By.xpath("//div[text()='From']");
		WebElement from = wait.until(ExpectedConditions.visibilityOf(driver.findElement(fromLocator)));
	

		// From
		wait.until(ExpectedConditions.elementToBeClickable(from));
		from.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(from));
		
		By inputFromLocator=By.xpath("//input[@id='srcDest']");
		WebElement inputFrom=wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(inputFromLocator)));
		inputFrom.sendKeys("Hyderabad");
		
		//Select the from result from auto-suggestive dropdown
		Thread.sleep(2000);
		List<WebElement> fromSearchResults=driver.findElements(
									By.xpath("//div[@class='searchCategory___993266']/div[contains(@class,'listItem___9a15c0')]/div/div/div"));
		
		wait.until(ExpectedConditions.elementToBeClickable(fromSearchResults.get(0)));
		System.out.println(fromSearchResults.get(0));
		
		for(WebElement results : fromSearchResults)
		{
			if(results.getText().contains("Hyderabad"))
			{
				results.click();
				break;
			}
		}
		
		//To
		
		Thread.sleep(2000);
		WebElement inputTo=driver.findElement(By.xpath("//input[@id='srcDest']"));
		inputTo.sendKeys("Bhubaneswar");
		
		//Select the to result from auto-suggestive dropdown
		Thread.sleep(2000);
		
		List<WebElement> toSearchResults=driver.findElements(
									By.xpath("//div[@class='searchCategory___993266']/div[contains(@class,'listItem___9a15c0')]/div/div/div[@class='listHeader___90a8b7']"));
		wait.until(ExpectedConditions.elementToBeClickable(toSearchResults.get(0)));
		
		for(WebElement results : toSearchResults)
		{
			if(results.getText().contains("Baramunda Bus Terminus, Bhubaneswar"))
			{
				results.click();
				break;
			}
		}
		
		driver.findElement(By.xpath("//h2[text()='Offers for you']")).click();
		// DatePicker
		int i = 1;
		By dateLocator=By.xpath("//span[@class='doj___e69938']");
		WebElement date=wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(dateLocator)));
		date.click();
		
		while (i > 0) {
			By monthYearLocator=By.xpath("//p[contains(@class,'monthYear')]");
			WebElement monthYearText = wait.until(ExpectedConditions.visibilityOf(driver.findElement(monthYearLocator)));
			By nextLocator = By.xpath("//i[contains(@aria-label,'Next')]");
			WebElement next = wait.until(ExpectedConditions.visibilityOf(driver.findElement(nextLocator)));
			
			if (!(monthYearText.getText().contains("October"))) {
				next.click();
				i++;
			}
			else
			{
				System.out.print(monthYearText.getText());
				WebElement day=driver.findElement(By.xpath("//span[text()='24']"));
				day.click();
				break;
				
			}
		}
		
		//Search Buses
		By seachBusesLocator=By.xpath("//button[text()='Search buses']");
		WebElement searchBuses=wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(seachBusesLocator)));
		searchBuses.click();
		
		//List
		//By busFoundLocator=By.xpath("//div[contains(@class,'busesFoundText')]");
		//WebElement busFoundText=wait.until(ExpectedConditions.visibilityOf(driver.findElement(busFoundLocator)));
		
		By subtitleLocator=By.xpath("//span[contains(@class,'subtitle')]");
		WebElement subtitleLocatorText=wait.until(ExpectedConditions.visibilityOf(driver.findElement(subtitleLocator)));
		
		By searchListLocator=By.xpath("//div[contains(@class,'ind-search')]/ul/li");
		
		JavascriptExecutor js=((JavascriptExecutor)driver);
		
		
		while(true)
		{
			List<WebElement> searchList=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchListLocator));
			List<WebElement> endofTheList=(driver.findElements(By.xpath("//*[text()='End of list']")));
			
			if(!(endofTheList.isEmpty()))
			{
				break;
			}
			
			js.executeScript("arguments[0].scrollIntoView({behavior:'smooth'})",searchList.get(searchList.size()-3));
		}
		
		//By endOfListLocator=By.xpath("")

	}

	@AfterSuite
	public static void tearDown() {
		driver.quit();
	}

}
