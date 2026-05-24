package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ConfirmationPage {
    WebDriver driver;
    WebDriverWait wait;

    By checkoutHeaderBy = By.xpath("//h2//a[normalize-space()='StackDemo']");
    By confirmationMessageBy = By.xpath("//*[contains(normalize-space(),'Your Order has been successfully placed.')]");
    By orderNumberBy = By.cssSelector("strong");
    By downloadReceiptTextBy = By.xpath("//*[contains(normalize-space(),'Download order receipt')]");
    By continueShoppingButtonBy = By.xpath("//button[contains(normalize-space(),'Continue Shopping')]");
    By orderSummaryTitleBy = By.xpath("//h3[normalize-space()='Order Summary']");
    By itemCountBy = By.xpath("//h3[contains(normalize-space(),'item(s)')]");
    By summaryProductNameBy = By.cssSelector(".product-title");
    By summaryProductVendorBy = By.cssSelector(".product-option");
    By summaryTotalBy = By.cssSelector(".cart-priceItem-value");

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains("/confirmation"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessageBy));
    }

    public boolean isLoaded() {
        List<By> confirmationPageElements = List.of(
                checkoutHeaderBy,
                confirmationMessageBy,
                orderNumberBy,
                downloadReceiptTextBy,
                continueShoppingButtonBy,
                orderSummaryTitleBy,
                itemCountBy,
                summaryProductNameBy,
                summaryTotalBy
        );

        for (By element : confirmationPageElements) {
            boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(element)).isDisplayed();
            if (!isVisible) {
                return false;
            }
        }return true;
    }

    public String getOrderNumber() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumberBy)).getText();
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
}
