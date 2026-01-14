package pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Locators.RedBus;
import utils.DriverManager;
import utils.Log;
import utils.UtilityMethods;

public class BusResultsPage {
	
	private final WebDriver driver;
	private final RedBus locators;
	WebDriverWait wait;
	JavascriptExecutor js;
	
	public BusResultsPage(DriverManager driverManager,RedBus locators)
	{
		this.driver=driverManager.getDriver();
		this.locators=locators;
		PageFactory.initElements(driver,locators);
		wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		js=(JavascriptExecutor)driver;
		
	}
	
	public void verifyListOfBuses()
	{
		wait.until(ExpectedConditions.visibilityOfAllElements(locators.searchListLocator));
		System.out.println("Total Buses avaialable for the route on the expected date " + locators.searchListLocator.size());	
		while(true)
		{
			wait.until(ExpectedConditions.visibilityOfAllElements(locators.searchListLocator));
			Log.info("Bus Lists");
			UtilityMethods.takeScreenshot("Bus Lists");
			
			if(!(locators.endOfTheList.isEmpty()))
			{
				break;
			}
			
			js.executeScript("arguments[0].scrollIntoView({behavior:'smooth'})",locators.searchListLocator.get(locators.searchListLocator.size()-3));
		}
	}
	
	public void selectBus(String busName) throws Exception
	{
		int busIsPresent=0;
		wait.until(ExpectedConditions.visibilityOfAllElements(locators.searchListLocator));
		for(WebElement buses : locators.searchListLocator)
		{
			if(buses.getText().equalsIgnoreCase(busName))
			{
				System.out.println(buses.getText());
				int index=locators.searchListLocator.indexOf(buses)+1;
				int newIndex=index-1;
				//js.executeScript("arguments[0].scrollIntoView({behavior:'smooth'})", buses);
				System.out.println("Index is" + index);
				Thread.sleep(5000);
				By viewSeatsLocator= By.xpath("(//button[contains(@class,'viewSeatsBtn')])["+ index +"]");
				By newViewSeatsLocator= By.xpath("(//button[contains(@class,'viewSeatsBtn')])["+ newIndex +"]");
				WebElement viewSeatsBtn=wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(viewSeatsLocator)));
				js.executeScript("arguments[0].scrollIntoView({behavior:'smooth'})", driver.findElement(newViewSeatsLocator));
				Thread.sleep(2000);
				viewSeatsBtn.click();
				Log.info("Total Seats");
				UtilityMethods.takeScreenshot("Total Seats");
				busIsPresent++;
				break;
			}
		}
		
		if(busIsPresent==0)
		{
			Log.info("Buses doesn't run on particular date");
			Assert.fail("No bus named" + " " + busName + " " + "present");
		}
	}
	
	public void selectSeat(String busName,String desiredSeatNumber) throws Exception
	{
		String seatNumber="";
		wait.until(ExpectedConditions.visibilityOf(locators.busName));
		Assert.assertEquals(locators.busName.getText(), busName);
		
		for(WebElement seatList : locators.sleeperSeatsList)
		{
			String seatID=seatList.getAttribute("id");
					
			if(seatID.contains("W") || seatID.contains("M"))
			{
				seatNumber=seatID.substring(1,2);
				System.out.println("Seat Number is " + seatNumber);
			}
			else
			{
				seatNumber=seatID.substring(1);
				System.out.println("Seat Number is " + seatNumber);
			}
			
			if(seatNumber.equalsIgnoreCase(desiredSeatNumber) && Integer.parseInt(desiredSeatNumber)<=locators.totalSleeperSeats.size())
			{
				seatList.click();
				Log.info("Seat "+ seatNumber + "selected");
				UtilityMethods.takeScreenshot("Seat "+ seatNumber + "selected");
				System.out.println("Seat "+ seatNumber + "selected");
				break;
			}
			
		}
		
		wait.until(ExpectedConditions.visibilityOf(locators.SelectBrdngAndDrpngPtsBtn));
		Thread.sleep(1000);
		locators.SelectBrdngAndDrpngPtsBtn.click();
		
	}
	
	public void selectBoardingAndDroppingPoint(String boardingPoint,String droppingPoint) throws InterruptedException
	{
		Thread.sleep(1000);
		
		for(WebElement boardingPoints : locators.selectBoardingPoint)
		{
			if(boardingPoints.getText().contains(boardingPoint))
			{
				boardingPoints.click();
				Log.info("Boarding Point "+ boardingPoints.getText() +"selected");
				UtilityMethods.takeScreenshot("Boarding Point "+ boardingPoints.getText() +"selected");
				System.out.println("Boarding Point "+ boardingPoints.getText() + "selected");
				break;
			}
		}
		
		wait.until(ExpectedConditions.visibilityOfAllElements(locators.selectDroppingPoint));
		
		for(WebElement droppingPoints : locators.selectBoardingPoint)
		{
			if(droppingPoints.getText().contains(droppingPoint))
			{
				droppingPoints.click();
				Log.info("Dropping Point "+ droppingPoints.getText() +"selected");
				UtilityMethods.takeScreenshot("Dropping Point "+ droppingPoints.getText() +"selected");
				System.out.println("Dropping Point "+ droppingPoints.getText() + "selected");
				break;
			}
		}
		
		wait.until(ExpectedConditions.visibilityOf(locators.fillPassengerDetailsBtn));
		locators.fillPassengerDetailsBtn.click();
		
	}
	
	public void enterPassengerDetails(String name, String age, String Gender)
	{
		wait.until(ExpectedConditions.visibilityOf(locators.enterName));
		locators.enterName.sendKeys(name);
		locators.enterAge.sendKeys(age);
		
		for(WebElement selectGender : locators.selectGender)
		{
			if(selectGender.getAttribute("aria-Label").equalsIgnoreCase(Gender) && selectGender.getAttribute("aria-selected").equalsIgnoreCase("false"))
			{
				selectGender.click();
				Log.info("Gender" + selectGender.getText() + "Selected");
				UtilityMethods.takeScreenshot("Gender "+ selectGender.getText() +" Selected");
			}
			else
			{
				System.out.println("Gender is already been selected");
				Log.info("Gender" + selectGender.getText() + " already Selected");
				UtilityMethods.takeScreenshot("Gender "+ selectGender.getText() +" already Selected");
			}
		}
		
		js.executeScript("arguments[0].scrollIntoView({beahvior:'smooth'})",locators.selectFreeCancellation);
		locators.selectFreeCancellation.click();
		wait.until(ExpectedConditions.visibilityOf(locators.selectRedbusAssuranceOption));
		locators.selectRedbusAssuranceOption.click();
	}
	
	public void makePayment()
	{
		wait.until(ExpectedConditions.visibilityOf(locators.continueBookingBtn));
		locators.continueBookingBtn.click();
		
	}

}
