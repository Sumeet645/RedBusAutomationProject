package stepDefinitions;

import org.openqa.selenium.WebDriver;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;

public class LoginFunctionality {
	
	WebDriver driver;
	private final LoginPage loginPage;
	
	public LoginFunctionality(LoginPage loginPage)
	{
		this.loginPage=loginPage;
	}
	
	@Given("User is on login page")
	public void user_is_on_login_page()
	{
		loginPage.getUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		System.out.println("User is on Login Page");
	}
	
	@When("User enters username")
	public void user_enters_username() {
		
		loginPage.setUserName("Admin");
		
	}
	@When("User enters password")
	public void user_enters_password() {
	   loginPage.setPassWord("admin123");
	}
	@When("Clicks on Submit")
	public void clicks_on_submit() {
	    
		loginPage.clickOnLogin();
	}
	@Then("User navigates to HomePage")
	public void user_navigates_to_home_page() {

		System.out.println("User is on Home Page");
	}

}
