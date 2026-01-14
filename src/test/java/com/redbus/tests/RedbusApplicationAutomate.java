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
		
		//wait.until(ExpectedConditions.elementToBeClickable(from));
		
		By inputFromLocator=By.xpath("//input[@id='srcDest']");
		WebElement inputFrom=wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(inputFromLocator)));
		inputFrom.sendKeys("Hyderabad");
		
		//Select the from result from auto-suggestive dropdown
		Thread.sleep(2000);
		List<WebElement> fromSearchResults=driver.findElements(
									By.xpath("//div[contains(@class,'listHeader')]"));
		
		wait.until(ExpectedConditions.elementToBeClickable(fromSearchResults.get(0)));
		System.out.println(fromSearchResults.size());
		
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
									By.xpath("//div[contains(@class,'listHeader')]"));
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
		By dateLocator=By.xpath("//span[text()='Date of Journey']/following-sibling::span");
		WebElement date=wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(dateLocator)));
		date.click();
		
		while (i > 0) {
			//Thread.sleep(2000);
			By monthYearLocator=By.xpath("//p[contains(@class,'monthYear')]");
			WebElement monthYearText = wait.until(ExpectedConditions.visibilityOf(driver.findElement(monthYearLocator)));
			By nextLocator = By.xpath("//i[contains(@aria-label,'Next')]");
			WebElement next = wait.until(ExpectedConditions.visibilityOf(driver.findElement(nextLocator)));
			
			if (!(monthYearText.getText().contains("May"))) {
				next.click();
				i++;
			}
			else
			{
				System.out.print(monthYearText.getText());
				WebElement day=driver.findElement(By.xpath("//span[text()='18']"));
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
		
		By searchListLocator=By.xpath("//div[contains(@class,'travelsName')]");
		
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
		List<WebElement> searchList=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchListLocator));
		
		for(WebElement busName : searchList)
		{
			if(busName.getText().equalsIgnoreCase("Sri Tulasi Tours and Travels"))
			{
				
				int index=searchList.indexOf(busName)+1;
				js.executeScript("arguments[0].scrollIntoView({behavior:'smooth'})", busName);
				System.out.println("Index is" + index);
				Thread.sleep(5000);
				By viewSeatsLocator= By.xpath("(//button[contains(@class,'viewSeatsBtn')])["+ index +"]");
				WebElement viewSeatsBtn=wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(viewSeatsLocator)));
				viewSeatsBtn.click();
				break;
			}
		}

	}

//	@AfterSuite
//	public static void tearDown() {
//		driver.quit();
//	}

}
