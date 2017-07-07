Feature: Login Action

Scenario: Successful Login with Valid Credentials
	Given Open insider web
	When User Navigate to LogIn Page
	And User enters UserName and Password
	Then Message displayed Login Successfully

Scenario: Successful City Change
	When User change city from the city Dropdown
	Then Selected language should be visible

Scenario: Successful LogOut
	When User LogOut from the Application
	Then Message displayed LogOut Successfully
	
	