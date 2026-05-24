package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    By cartPanelBy = By.cssSelector(".float-cart");
    By cartCloseButtonBy = By.cssSelector(".float-cart__close-btn");
    By cartQuantityBy = By.cssSelector(".bag__quantity");
    By emptyCartMessageBy = By.cssSelector(".shelf-empty");
    By cartProductNameBy = By.cssSelector(".float-cart .title");
    By cartProductDetailsBy = By.cssSelector(".float-cart .desc");
    By cartProductPriceBy = By.cssSelector(".float-cart .shelf-item__price");
    By decreaseQuantityButtonBy = By.xpath("//div[contains(@class,'float-cart')]//button[normalize-space()='-']");
    By increaseQuantityButtonBy = By.xpath("//div[contains(@class,'float-cart')]//button[normalize-space()='+']");
    By subtotalBy = By.cssSelector(".sub-price__val");
    By checkoutButtonBy = By.cssSelector(".buy-btn");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isEmptyCartDisplayed() {
        List<By> emptyCartElements = List.of(
                cartPanelBy,
                cartQuantityBy,
                emptyCartMessageBy,
                subtotalBy
        );

        for (By element : emptyCartElements) {
            boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(element)
            ).isDisplayed();

            if (!isVisible) {
                return false;
            }
        }

        return true;
    }

    public boolean isProductAdded() {
        List<By> cartProductElements = List.of(
                cartPanelBy,
                cartCloseButtonBy,
                cartQuantityBy,
                cartProductNameBy,
                cartProductDetailsBy,
                cartProductPriceBy,
                decreaseQuantityButtonBy,
                increaseQuantityButtonBy,
                subtotalBy,
                checkoutButtonBy
        );

        for (By element : cartProductElements) {
            boolean isVisible = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(element)
            ).isDisplayed();

            if (!isVisible) {
                return false;
            }
        }return true;
    }

    public String getCartQuantity() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartQuantityBy)).getText();
    }

    public String getProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartProductNameBy)).getText();
    }

    public String getProductDetails() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartProductDetailsBy)).getText();
    }

    public String getSubtotal() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(subtotalBy)).getText();
    }

    public CheckoutPage checkout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButtonBy)).click();
        wait.until(ExpectedConditions.urlContains("/checkout"));
        return new CheckoutPage(driver);
    }
}
