package stepDefinitions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BusResultsPage;
import pages.HomePage;
import utils.Log;

public class SearchBusTest {

	private final HomePage homepage;
	private final BusResultsPage busResultsPage;
	private static final Logger log= LogManager.getLogger(SearchBusTest.class);

	
	public SearchBusTest(HomePage homepage,BusResultsPage busResultsPage) {
		this.homepage = homepage;
		this.busResultsPage=busResultsPage;
	}
	
	@Given("User navigates to the Redbus URL")
	public void user_navigates_to_redbus() {
	    homepage.getUrl("https://www.redbus.in/");
	    Log.infoWithScreenShot("Redbus Homepage");
	}

	@Given("User is at the Redbus homepage")
	public void user_is_at_the_redbus_homepage() {
		String title = homepage.getPageTitle();
		log.info("Homepage title is " + title);
	}

	@When("User enters origin city in the from input field")
	public void user_enters_in_the_from_input_field() throws Exception {

		homepage.inputFromField("Hyderabad");
		homepage.selectBoardingPoint("Kukatpally");
		log.info("Origin city selected...");
	}

	@When("User enters destination city in the to input field")
	public void user_enters_in_the_to_input_field() throws Exception {

		homepage.inputToField("Bhubaneswar");
		homepage.selectDroppingPoint("Baramunda Bus Terminus, Bhubaneswar");
		log.info("Destination city selected...");
	}

	@When("User selects a valid onward journey date")
	public void user_selects_a_valid_onward_journey_date() {

		homepage.selectTravelDate("February");
		log.info("Date selected");
	}

	@When("User clicks on Search Buses button")
	public void user_clicks_on_search_buses_button() {

		homepage.searchBus();
	}

	@Then("Available buses should be displayed for the selected route")
	public void available_buses_should_be_displayed_for_the_selected_route() throws Exception {

		String busName="Sri Tulasi Tours and Travels";
		busResultsPage.verifyListOfBuses();
		busResultsPage.selectBus(busName);
		busResultsPage.selectSeat(busName, "3");
		busResultsPage.selectBoardingAndDroppingPoint("Kukatpally", "Bhubaneswar");
		busResultsPage.enterPassengerDetails("Sumeet Satapathy", "28", "Female");
		busResultsPage.makePayment();
		
		
	}
	
	@Given("User has searched buses from origin to destination for a valid date")
	public void user_has_searched_buses_from_origin_to_destination_for_a_valid_date() throws Exception {
		
		String title = homepage.getPageTitle();
		log.info("Homepage title is " + title);
		
		homepage.inputFromField("Hyderabad");
		homepage.selectBoardingPoint("Kukatpally");
		log.info("Origin city selected...");
		
		
		homepage.inputToField("Bhubaneswar");
		homepage.selectDroppingPoint("Baramunda Bus Terminus, Bhubaneswar");
		log.info("Destination city selected...");
		
		homepage.selectTravelDate("February");
		log.info("Date selected");
		
		homepage.searchBus();
		
		
	   
	}
	@When("User selects a bus from the available list")
	public void user_selects_a_bus_from_the_available_list() throws Exception {
		
		String busName="Sri Tulasi Tours and Travels";
		busResultsPage.verifyListOfBuses();
		busResultsPage.selectBus(busName);
		log.info("Bus Selected ");
	   
	}
	@When("User selects an available seat")
	public void user_selects_an_available_seat() throws Exception {
		
		String busName="Sri Tulasi Tours and Travels";
		busResultsPage.selectSeat(busName, "3");
		log.info("Seat Selected ");
		busResultsPage.selectBoardingAndDroppingPoint("Kukatpally", "Bhubaneswar");
		log.info("Boarding and Dropping point Selected ");
	   
	}
	@When("User enters passenger details and contact information")
	public void user_enters_passenger_details_and_contact_information() {
		
		busResultsPage.enterPassengerDetails("Sumeet Satapathy", "28", "Female");
		log.info("Details entered ");
	    
	}
	@When("User proceeds to payment")
	public void user_proceeds_to_payment() {
		
		busResultsPage.makePayment();
	   
	}

}
