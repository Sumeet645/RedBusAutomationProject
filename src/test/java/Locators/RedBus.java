package Locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RedBus {
	
	/////Homepage////
	
	@FindBy(xpath="(//div[contains(@class,'srcDestWrapper')])[1]")
	public WebElement FromLocator;
	
	@FindBy(xpath="//input[@id='srcinput']")
	public WebElement inputFromLocator;
	
	@FindBy(xpath="//div[contains(@class,'listHeader')]")
	public List<WebElement> fromSearchResults;
	
	@FindBy(xpath="//input[@id='destinput']")
	public WebElement inputTo;
	
	@FindBy(xpath="//div[contains(@class,'listHeader')]")
	public List<WebElement> toSearchResults;
	
	@FindBy(xpath="//div[contains(@class,'dateInputWrapper')]")
	public WebElement dateLocator;
	
	@FindBy(xpath="//p[contains(@class,'monthYear')]")
	public WebElement monthYearLocator;
	
	@FindBy(xpath="//i[contains(@aria-label,'Next')]")
	public WebElement nextLocator;
	
	@FindBy(xpath="//button[text()='Search buses']")
	public WebElement searchBusLocator;
	
	@FindBy(xpath="//span[contains(@class,'subtitle')]")
	public WebElement subtitleLocator;
	
	@FindBy(xpath="//div[contains(@class,'travelsName')]")
	public List<WebElement> searchListLocator;
	
	@FindBy(xpath="//*[text()='End of list']")
	public List<WebElement> endOfTheList;
	
	@FindBy(xpath="//h3[contains(@class,'busDetailsOpName')]")
	public WebElement busName;
	
	@FindBy(xpath="//span[contains(@aria-label,'seat status available') and contains (@aria-label,'upper')] ")
	public List<WebElement> sleeperSeatsList;
	
	@FindBy(xpath="//span[contains (@aria-label,'sleeper')] ")
	public List<WebElement> totalSleeperSeats;
	
	@FindBy(xpath="//button[contains(@aria-label,'Select boarding & dropping points') ] ")
	public WebElement SelectBrdngAndDrpngPtsBtn;
	
	@FindBy(xpath="//div[contains(@class,'rightContent')] /div/div[contains(@class,'name')]")
	public List<WebElement> selectBoardingPoint;
	
	@FindBy(xpath="//div[contains(@aria-label,'Dropping')]/div/div/following-sibling::div/div/div[contains(@class,'name')]")
	public List<WebElement> selectDroppingPoint;
	
	@FindBy(xpath="//button[@aria-label='Fill passenger details']")
	public WebElement fillPassengerDetailsBtn;
	
	@FindBy(xpath="//input[@aria-label='Name *']")
	public WebElement enterName;
	
	@FindBy(xpath="//input[@aria-label='Age *']")
	public WebElement enterAge;
	
	@FindBy(xpath="//div[contains(@class,'toggleGroup')]")
	public List<WebElement> selectGender;
	
	@FindBy(xpath="//div[contains(@aria-label,'add Free')]")
	public WebElement selectFreeCancellation;
	
	@FindBy(xpath="//div[contains(@aria-label,'add redBus')]")
	public WebElement selectRedbusAssuranceOption;
	
	@FindBy(xpath="//button[contains(@aria-label,'Continue booking')]")
	public WebElement continueBookingBtn;

}
