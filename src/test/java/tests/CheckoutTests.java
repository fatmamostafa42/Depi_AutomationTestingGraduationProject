package tests;

import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {

    @Test
    public void validateDemoUserCanCompleteCheckoutSuccessfully() {

        checkoutPage = cartPage.checkout();

        Assert.assertTrue(checkoutPage.isLoaded(), "Checkout page is not loaded");
        Assert.assertEquals(checkoutPage.getSummaryProductName(), PRODUCT_NAME, "Checkout product name is not correct");
        Assert.assertEquals(checkoutPage.getSummaryProductVendor(), PRODUCT_VENDOR, "Checkout product vendor is not correct");
        Assert.assertEquals(
                checkoutPage.getSummaryTotal().replace(" ", ""),
                PRODUCT_PRICE.replace(" ", ""),
                "Checkout total is not correct"
        );


        confirmationPage = checkoutPage.enterShippingAddress("Demo", "User", "123 Test Street", "Cairo", "11511")
                .submitOrder();

        Assert.assertTrue(confirmationPage.isLoaded(), "Confirmation page is not displayed");
        Assert.assertFalse(confirmationPage.getOrderNumber().isEmpty(), "Order number is not generated");
        Assert.assertEquals(confirmationPage.getSummaryProductName(), PRODUCT_NAME, "Confirmation product name is not correct");
        Assert.assertEquals(confirmationPage.getSummaryProductVendor(), PRODUCT_VENDOR, "Confirmation product vendor is not correct");
        Assert.assertEquals(
                confirmationPage.getSummaryTotal().replace(" ", ""),
                PRODUCT_PRICE.replace(" ", ""),
                "Confirmation total is not correct"
        );

    }
}
