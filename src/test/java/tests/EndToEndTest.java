package tests;

import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ProductCardComponent;

public class EndToEndTest extends BaseTest {

    @Test
    public void validateFullUserJourney() {

        //  1. Home Page
        Assert.assertTrue(homePage.isLoadedForGuest(), "Home page main sections are not displayed");
        Assert.assertTrue(navbarComponent.isDisplayedForGuest(), "Navbar is not displayed for guest user");
        Assert.assertTrue(footerComponent.isDisplayed(), "Footer is not displayed");
        Assert.assertTrue(heroSectionComponent.isDisplayed(), "Hero section is not displayed");
        Assert.assertTrue(promoBannerComponent.isDisplayed(), "Promo banner is not displayed");
        Assert.assertTrue(homePage.getProductCount() > 0, "Products are not displayed");
        Assert.assertTrue(homePage.getProductsFoundText().contains("Product(s) found"), "Products count label is not displayed");

        // 2. Login
        loginPage = navbarComponent.openLoginPage();
        Assert.assertTrue(loginPage.isLoaded(), "Login page is not loaded");
        homePage = loginPage.login(DEMO_USERNAME, DEMO_PASSWORD);
        navbarComponent = homePage.getNavbarComponent();
        Assert.assertTrue(navbarComponent.isDisplayedForLoggedInUser(), "Logged in navbar is not displayed");
        Assert.assertEquals(navbarComponent.getLoggedInUsername(), DEMO_USERNAME, "Logged in username is not correct");

        //  3. Products
        Assert.assertTrue(productsPage.isLoaded(), "Products page is not loaded");
        productsPage.filterByVendor(PRODUCT_VENDOR).searchForProduct(PRODUCT_NAME)
        .sortProductsByVisibleText("Lowest to highest");

        ProductCardComponent productCard = productsPage.getProductCard(PRODUCT_ID);
        Assert.assertTrue(productCard.isDisplayed(), "Product card is not displayed");
        Assert.assertEquals(productCard.getName(), PRODUCT_NAME, "Product name is not correct");
        Assert.assertEquals(productCard.getPrice(), PRODUCT_PRICE, "Product price is not correct");
        Assert.assertTrue(productsPage.getProductsFoundText().contains("Product(s) found"), "Products count label is not updated");

        //  4. Cart
        productCard.addToCart();
        Assert.assertTrue(cartPage.isProductAdded(), "Product is not added to cart");
        Assert.assertEquals(cartPage.getCartQuantity(), "1", "Cart quantity is not correct");
        Assert.assertEquals(cartPage.getProductName(), PRODUCT_NAME,
                "Cart product name is not correct");
        Assert.assertTrue(cartPage.getProductDetails().contains(PRODUCT_VENDOR),
                "Cart product vendor is not correct");
        Assert.assertEquals(cartPage.getSubtotal(), PRODUCT_PRICE,
                "Cart subtotal is not correct");

        //  5. Checkout
        checkoutPage = cartPage.checkout();
        Assert.assertTrue(checkoutPage.isLoaded(), "Checkout page is not loaded");
        Assert.assertEquals(checkoutPage.getSummaryProductName(), PRODUCT_NAME, "Checkout product name is not correct");
        Assert.assertEquals(checkoutPage.getSummaryProductVendor(), PRODUCT_VENDOR, "Checkout product vendor is not correct");
        Assert.assertEquals(
                checkoutPage.getSummaryTotal().replace(" ", ""),
                PRODUCT_PRICE.replace(" ", ""),
                "Checkout total is not correct"
        );
        //  6. Confirmation
        confirmationPage = checkoutPage
                .enterShippingAddress("Demo", "User", "123 Test Street", "Cairo", "11511")
                .submitOrder();

        Assert.assertTrue(confirmationPage.isLoaded(), "Confirmation page is not loaded");
        Assert.assertFalse(confirmationPage.getOrderNumber().isEmpty(), "Order number is not generated");
        Assert.assertEquals(confirmationPage.getSummaryProductName(), PRODUCT_NAME, "Confirmation product name is not correct");
        Assert.assertEquals(confirmationPage.getSummaryProductVendor(), PRODUCT_VENDOR, "Confirmation product vendor is not correct");
        Assert.assertEquals(
                confirmationPage.getSummaryTotal().replace(" ", ""),
                PRODUCT_PRICE.replace(" ", ""),
                "Confirmation total is not correct"  );
    }
}