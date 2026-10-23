package steps;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class LoginSteps {

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        open("https://www.saucedemo.com/");
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsIn(String username, String password) {
        $("#user-name").setValue(username);
        $("#password").setValue(password);
        $("#login-button").click();
    }

    @Then("the products page is displayed")
    public void theProductsPageIsDisplayed() {
        $(".title").shouldHave(text("Products"));
    }

    @Then("the error message {string} is displayed")
    public void theErrorMessageIsDisplayed(String expectedMessage) {
        $("[data-test='error']").shouldHave(text(expectedMessage));
    }

    @After
    public void tearDown() {
        closeWebDriver();
    }
}

