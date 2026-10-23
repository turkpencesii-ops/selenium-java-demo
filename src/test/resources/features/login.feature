Feature: Login on Sauce Demo

  Scenario: Successful login with valid credentials
    Given the user is on the login page
    When the user logs in with username "standard_user" and password "secret_sauce"
    Then the products page is displayed

  Scenario: Unsuccessful login with invalid credentials
    Given the user is on the login page
    When the user logs in with username "locked_out_user" and password "secret_sauce"
    Then the error message "Epic sadface: Sorry, this user has been locked out." is displayed