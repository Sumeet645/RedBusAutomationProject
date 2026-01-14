
Feature: Verify the E2E Search Buses flow of Redbus

Background: 
 Given  User navigates to the Redbus URL

@Smoke
Scenario: Verify user search buses
  Given User is at the Redbus homepage
  When User enters origin city in the from input field
  And User enters destination city in the to input field
  And User selects a valid onward journey date
  And User clicks on Search Buses button
  Then Available buses should be displayed for the selected route
  
  @Smoke @SearchBuses 
 Scenario: Verify user can complete bus booking
 Given User has searched buses from origin to destination for a valid date
  When User selects a bus from the available list
  And User selects an available seat
  And User enters passenger details and contact information
  And User proceeds to payment
 