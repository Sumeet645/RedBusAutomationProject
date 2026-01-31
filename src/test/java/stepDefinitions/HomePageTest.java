 package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.OrangeHRM.DashboardPage;

public class HomePageTest {
	
	private final LoginPage loginPage;
	private final DashboardPage dashBoardPage;
	
	public HomePageTest(LoginPage loginPage,DashboardPage dashBoardPage)
	{
		this.loginPage=loginPage;
		this.dashBoardPage=dashBoardPage;
	}
	
	@When("User Logins into OrangeHRM website")
	public void user_logins_into_orangeHRM_website()
	{
		loginPage.getUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		loginPage.setUserName("Admin");
		loginPage.setPassWord("admin123");
		loginPage.clickOnLogin();
	}
	
	@Then("User clicks on Performance Tab")
	public void user_clicks_on_performance_tab()
	{
		dashBoardPage.clickOnPerformanceTab();
	}

}
