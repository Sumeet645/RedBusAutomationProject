Feature:Login Scenario

Scenario:Test Login with Correct Username and Password

Given User is on login page
When User enters username
And User enters password
And Clicks on Submit
Then User navigates to HomePage

Scenario: Verify the Dashboard
Given User is on login page
When User Logins into OrangeHRM website
Then User clicks on Performance Tab



