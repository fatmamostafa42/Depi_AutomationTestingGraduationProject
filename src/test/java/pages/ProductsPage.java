package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.FooterComponent;
import utilities.NavbarComponent;
import utilities.ProductCardComponent;

import java.time.Duration;
import java.util.List;

public class ProductsPage {

    WebDriver driver;
    WebDriverWait wait;
    NavbarComponent navbarComponent;
    FooterComponent footerComponent;

    By appleFilterBy = By.xpath("//label[.//input[@value='Apple']]/span[@class='checkmark']");
    By samsungFilterBy = By.xpath("//label[.//input[@value='Samsung']]/span[@class='checkmark']");
    By googleFilterBy = By.xpath("//label[.//input[@value='Google']]/span[@class='checkmark']");
    By onePlusFilterBy = By.xpath("//label[.//input[@value='OnePlus']]/span[@class='checkmark']");
    By productsFoundLabelBy = By.cssSelector(".products-found");
    By productCardsBy = By.cssSelector(".shelf-item");
    By sortDropdownBy = By.cssSelector(".shelf-container-header select");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        navbarComponent = new NavbarComponent(driver);
        footerComponent = new FooterComponent(driver);
    }

    public boolean isLoaded() {
        List<By> productPageElements = List.of(
                appleFilterBy,
                samsungFilterBy,
                googleFilterBy,
                onePlusFilterBy,
                productsFoundLabelBy,
                sortDropdownBy
        );
        for (By element : productPageElements) {
            boolean isVisible = wait.until(ExpectedConditions.visibilityOfElementLocated(element)
            ).isDisplayed();

            if (!isVisible) return false;
        }return getProductCount() > 0;
    }

    public ProductsPage filterByVendor(String vendorName) {
        By checkmarkBy = By.xpath("//label[.//input[@value='" + vendorName + "']]/span[@class='checkmark']");

        wait.until(ExpectedConditions.elementToBeClickable(checkmarkBy)).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(productsFoundLabelBy, "Product(s) found"));

        return this;
    }

    public ProductsPage searchForProduct(String productName) {
        navbarComponent.searchForProduct(productName);

        wait.until(ExpectedConditions.textToBePresentInElementLocated(productsFoundLabelBy, "Product(s) found"));

        return this;
    }

    public ProductsPage sortProductsByVisibleText(String option) {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(sortDropdownBy));

        new Select(dropdown).selectByVisibleText(option);

        wait.until(ExpectedConditions.visibilityOfElementLocated(productCardsBy));

        return this;
    }

  // get productCard from ProductCardComponent

    public ProductCardComponent getProductCard(String productId) {
        ProductCardComponent productCard = new ProductCardComponent(driver, productId);
        productCard.isDisplayed();
        return productCard;
    }

    public int getProductCount() {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productCardsBy));
        return driver.findElements(productCardsBy).size();
    }

    public String getProductsFoundText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productsFoundLabelBy)).getText();
    }



}