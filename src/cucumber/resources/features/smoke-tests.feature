

Feature: User can login

  Scenario: User make positive authorization
    Given User go to login page
    When User login with email: 'genchevskiy.test@gmail.com' and password: 's.g19021992'
    Then User is at account page