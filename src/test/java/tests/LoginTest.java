package tests;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends TestBase {

    private LoginPage loginPage;

    @BeforeMethod
    public void openLoginPage() {
        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @Test
    public void validLogin() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(loginPage.isInventoryDisplayed(), "Inventory page not shown");
    }

    @Test
    public void invalidPassword() {
        loginPage.login("standard_user", "wrong_password");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }

    @Test
    public void lockedOutUser() {
        loginPage.login("locked_out_user", "secret_sauce");
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"));
    }


}
