package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;
import java.util.List;

public class NavbarComponent {

    WebDriver driver;
    WebDriverWait wait;

    By logoLinkBy = By.cssSelector(".Navbar_logo__26S5Y");
    By favouritesLinkBy = By.id("favourites");
    By ordersLinkBy = By.id("orders");
    By offersLinkBy = By.id("offers");
    By searchInputBy = By.cssSelector("input[placeholder='Search']");
    By searchButtonBy = By.xpath("//button[normalize-space()='Search']");
    By signInLinkBy = By.cssSelector("#signin");
    By loggedInUsernameBy = By.cssSelector(".username");
    By logoutLinkBy = By.cssSelector("span#signin.logout-link");

    public NavbarComponent(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isDisplayedForGuest() {
        List<By> guestNavbarElements = List.of(
                logoLinkBy,
                favouritesLinkBy,
                ordersLinkBy,
                offersLinkBy,
                searchInputBy,
                searchButtonBy,
                signInLinkBy
        );

        for (By element : guestNavbarElements) {
            boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
            if (!isVisible) {
                return false;
            }
        }return true;
    }

    public boolean isDisplayedForLoggedInUser() {

        List<By> loggedInNavbarElements = List.of(
                logoLinkBy,
                favouritesLinkBy,
                ordersLinkBy,
                offersLinkBy,
                searchInputBy,
                searchButtonBy,
                loggedInUsernameBy,
                logoutLinkBy
        );

        for (By element : loggedInNavbarElements) {
            boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
            if (!isVisible) {
                return false;
            }
        }return true;
    }

    public LoginPage openLoginPage() {
        driver.get("https://bstackdemo.com/signin");
        wait.until(ExpectedConditions.urlContains("/signin"));
        return new LoginPage(driver);
    }

    public ProductsPage searchForProduct(String productName) {

        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputBy));
        input.clear();
        input.sendKeys(productName);
        wait.until(ExpectedConditions.elementToBeClickable(searchButtonBy)).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".products-found"), "Product(s) found"));
        return new ProductsPage(driver);
    }

    public void openFavourites() {
        wait.until(ExpectedConditions.elementToBeClickable(favouritesLinkBy)).click();
        wait.until(ExpectedConditions.urlContains("/favourites"));
    }

    public void openOrders() {
        wait.until(ExpectedConditions.elementToBeClickable(ordersLinkBy)).click();
        wait.until(ExpectedConditions.urlContains("/orders"));
    }

    public void openOffers() {
        wait.until(ExpectedConditions.elementToBeClickable(offersLinkBy)).click();

        wait.until(ExpectedConditions.urlContains("/offers"));
    }

    public String getLoggedInUsername() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInUsernameBy)).getText();
    }
}
