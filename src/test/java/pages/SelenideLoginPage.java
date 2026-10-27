package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideLoginPage {

    private static final String URL = "https://www.saucedemo.com/";

    private final SelenideElement usernameInput = $("[data-test='username']");
    private final SelenideElement passwordInput = $("[data-test='password']");
    private final SelenideElement loginButton   = $("[data-test='login-button']");
    private final SelenideElement errorMessage  = $("[data-test='error']");

    public void openPage() {
        open(URL);
    }

    public void login(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        loginButton.click();
    }

    public SelenideElement errorMessage() {
        return errorMessage;
    }
}