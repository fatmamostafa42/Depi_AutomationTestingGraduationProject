package tests;

import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ProductCardComponent;

public class CartTests extends BaseTest {

    @Test
    public void validateProductCanBeAddedToCart() {

        ProductCardComponent productCard = new ProductCardComponent(driver, PRODUCT_ID);

        productCard.addToCart();

        Assert.assertTrue(cartPage.isProductAdded(),
                "Cart product details are not displayed");
        Assert.assertEquals(cartPage.getCartQuantity(), "1",
                "Cart quantity is not correct");
        Assert.assertEquals(cartPage.getProductName(), PRODUCT_NAME,
                "Cart product name is not correct");
        Assert.assertTrue(cartPage.getProductDetails().contains(PRODUCT_VENDOR),
                "Cart product vendor is not correct");
        Assert.assertEquals(cartPage.getSubtotal(), PRODUCT_PRICE,
                "Cart subtotal is not correct");
    }
}