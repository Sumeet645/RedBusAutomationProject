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

		// Only in CI (GitHub Actions)
		if ("true".equalsIgnoreCase(System.getenv("CI"))) {
		    options.addArguments("--headless=new");
		    options.addArguments("--no-sandbox");
		    options.addArguments("--disable-dev-shm-usage");
		    options.addArguments("--window-size=1920,1080");
		    options.addArguments("user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36");


		}

				driver.set(new ChromeDriver(options));

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
