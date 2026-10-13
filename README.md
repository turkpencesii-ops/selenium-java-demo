# Selenium Java Demo

UI test automation for saucedemo.com using Selenium 4, Java 21, TestNG and Maven, built with the Page Object Model.

## Tests
| Test | Scenario |
|---|---|
| validLogin | Standard user logs in, inventory page is shown |
| lockedOutUser | Locked user gets an error message |
| invalidPassword | Wrong password shows a validation error |

## Run

    mvn test

## Structure

    src/test/java/
      pages/LoginPage.java   - Page Object: locators + actions
      tests/LoginTest.java   - TestNG tests
