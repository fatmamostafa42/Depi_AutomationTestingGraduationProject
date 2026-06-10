package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    By loginFormBy = By.cssSelector("form");

    // Dropdown containers
    By usernameContainerBy = By.id("username");
    By passwordContainerBy = By.id("password");

    By loginButtonBy = By.id("login-btn");


    // Username options -  dropdown list
    By demoUserOptionBy = By.xpath("//div[contains(@class,'option') and contains(text(),'demouser')]");
    By imageNotLoadingUserOptionBy = By.xpath("//div[contains(@class,'option') and contains(text(),'image_not_loading_user')]");
    By existingOrdersUserOptionBy = By.xpath("//div[contains(@class,'option') and contains(text(),'existing_orders_user')]");
    By favUserOptionBy = By.xpath("//div[contains(@class,'option') and contains(text(),'fav_user')]");
    By lockedUserOptionBy = By.xpath("//div[contains(@class,'option') and contains(text(),'locked_user')]");

    By passwordOptionBy = By.xpath("//div[contains(@class,'option') and contains(text(),'testingisfun99')]");
    By logoutLinkBy = By.cssSelector("span#signin.logout-link");

    public LoginPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginFormBy));
    }

    public boolean isLoaded() {

        List<By> loginPageElements = List.of(
                loginFormBy,
                usernameContainerBy,
                passwordContainerBy,
                loginButtonBy
        );

        for (By element : loginPageElements) {

            boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(element)
            ).isDisplayed();

            if (!isVisible) {
                return false;
            }
        }return true;
    }
    public boolean areUsernameOptionsDisplayed() {

        wait.until(ExpectedConditions.elementToBeClickable(usernameContainerBy)).click();
        List<By> usernameOptions = List.of(
                demoUserOptionBy,
                imageNotLoadingUserOptionBy,
                existingOrdersUserOptionBy,
                favUserOptionBy,
                lockedUserOptionBy
        );

        for (By element : usernameOptions) {
            boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(element)
            ).isDisplayed();

            if (!isVisible) return false;
        }
        driver.findElement(usernameContainerBy).click();
        return true;
    }
    public boolean isPasswordOptionDisplayed() {

        wait.until(ExpectedConditions.elementToBeClickable(passwordContainerBy)).click();

        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordOptionBy)).isDisplayed();
    }
    public HomePage login(String username, String password) {

        //  Username
        wait.until(ExpectedConditions.elementToBeClickable(usernameContainerBy)).click();

        By usernameOption = By.xpath("//div[contains(@class,'option') and contains(text(),'" + username + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(usernameOption)).click();

        //  Password
        wait.until(ExpectedConditions.elementToBeClickable(passwordContainerBy)).click();

        By passwordOption = By.xpath("//div[contains(@class,'option') and contains(text(),'" + password + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(passwordOption)).click();

        // ===== Login =====
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonBy)).click();
        wait.until(ExpectedConditions.urlContains("signin=true"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(logoutLinkBy));

        return new HomePage(driver);
    }
}