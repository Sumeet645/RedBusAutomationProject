package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import stepDefinitions.SearchBusTest;

public final class DriverManager {
	

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	private static final Logger log = LogManager.getLogger(SearchBusTest.class);
	
	private DriverManager(){}
	

	public static void initialiseDriver() {
		
		String browser = ConfigReader.get("browser");
		boolean headless = Boolean.parseBoolean(ConfigReader.get("headless"));
		boolean isCI = "true".equalsIgnoreCase(System.getenv("CI"));

		log.info("Intialising Driver");

		if ("chrome".equalsIgnoreCase(browser)) {
			
			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--window-size=1920,1080");

			if (headless || isCI) {
				options.addArguments("--headless=new");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments(
						"user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/136.0.0.0 Safari/537.36");

			}
			driver.set(new ChromeDriver(options));
		}
	}

	public static WebDriver getDriver() {

		WebDriver d = driver.get();

		try {
			if (d == null) {
				throw new IllegalStateException("Webdriver not initialised. Ensure @Before runs first");
			}
		} finally {
			log.info("Driver started successfully");
		}
		return d;
	}

	public static void quitDriver() {

		WebDriver d = driver.get();
		if (d != null) {
			log.info("Quitting Driver");
			d.quit();
			driver.remove();
			;
		}
	}

}
