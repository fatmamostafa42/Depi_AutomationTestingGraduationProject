package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductCardComponent {

    WebDriver driver;
    WebDriverWait wait;
    String productId;
    By cardBy;
    By nameBy;
    By priceBy;
    By addBtnBy;

    public ProductCardComponent(WebDriver driver, String productId) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.productId = productId;

        String name = getProductName(productId);

        this.cardBy = By.xpath("//div[contains(@class,'shelf-item')]" +
                        "[.//p[contains(@class,'shelf-item__title') and text()='" + name + "']]"
        );
        this.nameBy = By.xpath("//div[contains(@class,'shelf-item')]" +
                        "[.//p[contains(@class,'shelf-item__title') and text()='" + name + "']]" +
                        "//p[contains(@class,'shelf-item__title')]"
        );
        this.priceBy = By.xpath(
                "//p[normalize-space()='" + name + "']/following-sibling::div[contains(@class,'shelf-item__price')]//div[@class='val']"
        );

        this.addBtnBy = By.xpath("//div[contains(@class,'shelf-item')]" +
                        "[.//p[contains(@class,'shelf-item__title') and text()='" + name + "']]" +
                        "//*[contains(@class,'shelf-item__buy-btn')]"
        );
    }

    private String getProductName(String productId) {
        switch (productId) {
            case "iphone_12": return "iPhone 12";
            case "samsung_s21": return "Samsung S21";
            default: return productId.replace("_", " ");
        }
    }

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cardBy)).isDisplayed();
    }

    public String getName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nameBy)).getText();
    }

    public String getPrice() {
        WebElement priceEl = wait.until(ExpectedConditions.visibilityOfElementLocated(priceBy));
        String symbol = priceEl.findElement(By.cssSelector("small")).getText();
        String amount = priceEl.findElement(By.cssSelector("b")).getText();
        String decimal = priceEl.findElement(By.cssSelector("span")).getText();
        return symbol + " " + amount + decimal;
    }

    public void addToCart() {
        String name = getName();
        wait.until(ExpectedConditions.elementToBeClickable(addBtnBy)).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.cssSelector(".float-cart .title"), name
        ));
    }

    public void addToFavourites() {
        By favBtn = By.xpath("//div[contains(@class,'shelf-item')]" +
                        "[.//p[contains(@class,'shelf-item__title') and contains(text(),'" + getProductName(productId) + "')]]" +
                        "//button"
        );
        wait.until(ExpectedConditions.elementToBeClickable(favBtn)).click();
    }
}