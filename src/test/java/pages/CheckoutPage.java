package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CheckoutPage {
    WebDriver driver;
    WebDriverWait wait;



    By checkoutHeaderBy = By.xpath("//h2//a[normalize-space()='StackDemo']");
    By shippingAddressTitleBy = By.xpath("//*[normalize-space()='Shipping Address']");
    By firstNameInputBy = By.id("firstNameInput");
    By lastNameInputBy = By.id("lastNameInput");
    By addressInputBy = By.id("addressLine1Input");
    By stateInputBy = By.id("provinceInput");
    By postalCodeInputBy = By.id("postCodeInput");
    By submitButtonBy = By.id("checkout-shipping-continue");
    By orderSummaryTitleBy = By.xpath("//h3[normalize-space()='Order Summary']");
    By itemCountBy = By.xpath("//h3[contains(normalize-space(),'item(s)')]");
    By summaryProductNameBy = By.cssSelector(".product-title");
    By summaryProductVendorBy = By.cssSelector(".product-option");
    By summaryTotalBy = By.cssSelector(".cart-priceItem-value");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isLoaded() {
        List<By> checkoutPageElements = List.of(
                checkoutHeaderBy,
                shippingAddressTitleBy,
                firstNameInputBy,
                lastNameInputBy,
                addressInputBy,
                stateInputBy,
                postalCodeInputBy,
                submitButtonBy,
                orderSummaryTitleBy,
                itemCountBy,
                summaryProductNameBy,
                summaryTotalBy
        );

        for (By element : checkoutPageElements) {
            boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();

            if (!isVisible) {
                return false;
            }
        }return true;
    }

    public String getSummaryProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(summaryProductNameBy)).getText();
    }

    public String getSummaryProductVendor() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(summaryProductVendorBy)).getText();
    }

    public String getSummaryTotal() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(summaryTotalBy)).getText();
    }

    public CheckoutPage enterShippingAddress(String firstName, String lastName, String address, String state, String postalCode) {
        WebElement firstNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInputBy));
        firstNameElement.sendKeys(firstName);

        WebElement lastNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameInputBy));
        lastNameElement.sendKeys(lastName);

        WebElement addressElement = wait.until(ExpectedConditions.visibilityOfElementLocated(addressInputBy));
        addressElement.sendKeys(address);

        WebElement stateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(stateInputBy));
        stateElement.sendKeys(state);

        WebElement postalCodeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeInputBy));
        postalCodeElement.sendKeys(postalCode);
        return this;
    }

    public ConfirmationPage submitOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButtonBy)).click();
        wait.until(ExpectedConditions.urlContains("/confirmation"));
        return new ConfirmationPage(driver);
    }
}
