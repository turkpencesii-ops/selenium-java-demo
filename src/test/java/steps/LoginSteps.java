package steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.SelenideLoginPage;
import pages.SelenideProductsPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class LoginSteps {

    private final SelenideLoginPage loginPage = new SelenideLoginPage();
    private final SelenideProductsPage productsPage = new SelenideProductsPage();

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        loginPage.openPage();
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsIn(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("the products page is displayed")
    public void theProductsPageIsDisplayed() {
        productsPage.title().shouldHave(text("Products"));
    }

    @Then("the error message {string} is displayed")
    public void theErrorMessageIsDisplayed(String expectedMessage) {
        loginPage.errorMessage().shouldHave(text(expectedMessage));
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }
}