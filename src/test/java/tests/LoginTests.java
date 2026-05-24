package tests;

import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void validateDemoUserCanLoginSuccessfully() {

        loginPage = navbarComponent.openLoginPage();

        Assert.assertTrue(loginPage.isLoaded(), "Login page is not loaded");

        Assert.assertTrue(loginPage.areUsernameOptionsDisplayed(), "Username dropdown options are not displayed");

        Assert.assertTrue(loginPage.isPasswordOptionDisplayed(), "Password dropdown option is not displayed");

        homePage = loginPage.login(DEMO_USERNAME, DEMO_PASSWORD);
        navbarComponent = homePage.getNavbarComponent();

        Assert.assertTrue(navbarComponent.isDisplayedForLoggedInUser(), "Logged in navbar is not displayed");

        Assert.assertEquals(navbarComponent.getLoggedInUsername(), DEMO_USERNAME, "Logged in username is not correct");
    }
}