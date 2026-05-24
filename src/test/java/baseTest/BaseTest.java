package baseTest;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.*;
import utilities.*;

public class BaseTest {

    protected static WebDriver driver;

    protected HomePage homePage;
    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;
    protected ConfirmationPage confirmationPage;

    protected NavbarComponent navbarComponent;
    protected FooterComponent footerComponent;
    protected HeroSectionComponent heroSectionComponent;
    protected PromoBannerComponent promoBannerComponent;

    protected String DEMO_USERNAME = "demouser";
    protected String DEMO_PASSWORD = "testingisfun99";
    protected String PRODUCT_NAME = "iPhone 12";
    protected String PRODUCT_ID = "iphone_12";
    protected String PRODUCT_VENDOR = "Apple";
    protected String PRODUCT_PRICE = "$ 799.00";

    @BeforeSuite(alwaysRun = true)
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod(alwaysRun = true)
    public void goToHomePage() {
        driver.get("https://bstackdemo.com/");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.clear();");
        js.executeScript("window.sessionStorage.clear();");
        driver.manage().deleteAllCookies();

        driver.get("https://bstackdemo.com/");

        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        navbarComponent = homePage.getNavbarComponent();
        footerComponent = homePage.getFooterComponent();
        heroSectionComponent = homePage.getHeroSectionComponent();
        promoBannerComponent = homePage.getPromoBannerComponent();
    }

    @AfterSuite(alwaysRun = true)
    public void teardown() {
        {
            driver.quit();
        }
    }
}