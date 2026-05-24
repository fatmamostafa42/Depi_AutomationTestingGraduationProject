package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.FooterComponent;
import utilities.HeroSectionComponent;
import utilities.NavbarComponent;
import utilities.PromoBannerComponent;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;


    protected NavbarComponent navbarComponent;
    protected FooterComponent footerComponent;
    protected HeroSectionComponent heroSectionComponent;
    protected PromoBannerComponent promoBannerComponent;


    By vendorsTitleBy = By.xpath("//h4[normalize-space()='Vendors:']");
    By productsTitleBy = By.xpath("//*[normalize-space()='Products']");
    By productsFoundLabelBy = By.cssSelector(".products-found");
    By productCardsBy = By.cssSelector(".shelf-item");


    public HomePage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        navbarComponent = new NavbarComponent(driver);
        footerComponent = new FooterComponent(driver);
        heroSectionComponent = new HeroSectionComponent(driver);
        promoBannerComponent = new PromoBannerComponent(driver);
    }


    public int getProductCount() {

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productCardsBy)
        );

        return driver.findElements(productCardsBy).size();
    }

    public String getProductsFoundText() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(productsFoundLabelBy)
        ).getText();
    }


    public boolean areMainSectionsVisible() {

        boolean vendorsVisible = wait.until(
                ExpectedConditions.visibilityOfElementLocated(vendorsTitleBy)
        ).isDisplayed();

        boolean productsVisible = wait.until(
                ExpectedConditions.visibilityOfElementLocated(productsTitleBy)
        ).isDisplayed();

        boolean productsFoundVisible = wait.until(
                ExpectedConditions.visibilityOfElementLocated(productsFoundLabelBy)
        ).isDisplayed();

        return vendorsVisible
                && productsVisible
                && productsFoundVisible;
    }

    public NavbarComponent getNavbarComponent() {

        return navbarComponent;
    }

    public FooterComponent getFooterComponent() {

        return footerComponent;
    }

    public HeroSectionComponent getHeroSectionComponent() {

        return heroSectionComponent;
    }

    public PromoBannerComponent getPromoBannerComponent() {

        return promoBannerComponent;
    }

    public boolean isLoadedForGuest() {

        return getNavbarComponent().isDisplayedForGuest()
                && getHeroSectionComponent().isDisplayed()
                && areMainSectionsVisible()
                && getProductCount() > 0
                && getFooterComponent().isDisplayed()
                && getPromoBannerComponent().isDisplayed();
    }
}
