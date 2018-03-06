
Feature: Check all main futures of Google


  Scenario: User can make authorization
    Given User go to login page
    When User login with email: 'genchevskiy.test@gmail.com' and password: 's.g19021992'
    Then User is at account page


  Scenario: Search for Selenium Webdriver
    Given User go to search page
    When User search for 'Selenium WebDriver'
    Then Number of results is '10'
    Then All results contains text 'Selenium'


  Scenario: User can write the message with attachment
    Given User go to login page
    When User login with email: 'genchevskiy.test@gmail.com' and password: 's.g19021992'
    Then User is at account page
    When User go to Gmail page of user 'genchevskiy.test@gmail.com'
    Then User is at gmail page of user 'genchevskiy.test@gmail.com'
    When User write message to 'genchevskiy.test@gmail.com', with subject 'Test Message', with body 'Hello Savva' and file 'src/main/groovy/com/example/data/HelloWorld.txt'
    When Refreshing the page
    Then Message with number '1' have subject 'Test Message'
    Then Message with number '1' contains text 'Hello Savva'