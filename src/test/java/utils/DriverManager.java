package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import stepDefinitions.SearchBusTest;

public class DriverManager {
	
	private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	private static final Logger log= LogManager.getLogger(SearchBusTest.class);
	
	public static void initialiseDriver()
	{
		log.info("Intialising Driver");
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();

        // CI runners (GitHub Actions) need headless + flags
        if ("true".equalsIgnoreCase(System.getenv("CI"))) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
        }
        
		driver.set(new ChromeDriver());
	}
	
	public static WebDriver getDriver()
	{
		
		return driver.get();
	}
	
	public static WebDriver quitDriver()
	{
		log.info("Quitting Driver");
		driver.get().quit();
		return driver.get();
	}

}
