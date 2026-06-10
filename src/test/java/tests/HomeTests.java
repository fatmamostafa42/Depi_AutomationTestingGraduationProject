package tests;

import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTests extends BaseTest {

    @Test
    public void validateHomePageFixedSectionsAndProductsAreDisplayed() {
        Assert.assertTrue(homePage.isLoadedForGuest(), "Home page main sections are not displayed");
        Assert.assertTrue(navbarComponent.isDisplayedForGuest(), "Navbar is not displayed for guest user");
        Assert.assertTrue(footerComponent.isDisplayed(), "Footer is not displayed");
        Assert.assertTrue(heroSectionComponent.isDisplayed(), "Hero section is not displayed");
        Assert.assertTrue(promoBannerComponent.isDisplayed(), "Promo banner is not displayed");
        Assert.assertTrue(homePage.getProductCount() > 0, "Products are not displayed");
        Assert.assertTrue(homePage.getProductsFoundText().contains("Product(s) found"), "Products count label is not displayed");
    }
}
