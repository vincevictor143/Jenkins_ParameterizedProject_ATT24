Feature: Google Search Feature

Scenario: User searches with keyword on Google
Given I open Google search page
When I search for "Java"
Then I should see results related to "Java"
