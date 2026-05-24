package tests;

import baseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ProductCardComponent;

public class ProductTests extends BaseTest {

    @Test
    public void validateProductFiltersSearchSortAndProductCard() {

        Assert.assertTrue(productsPage.isLoaded(), "Products page is not loaded");

        productsPage
                .filterByVendor(PRODUCT_VENDOR)
                .searchForProduct(PRODUCT_NAME)
                .sortProductsByVisibleText("Lowest to highest");

        ProductCardComponent productCard = productsPage.getProductCard(PRODUCT_ID);

        Assert.assertTrue(productCard.isDisplayed(), "Product card is not displayed");

        Assert.assertEquals(productCard.getName(), PRODUCT_NAME, "Product name is not correct");

        Assert.assertEquals(productCard.getPrice(), PRODUCT_PRICE, "Product price is not correct");

        Assert.assertTrue(productsPage.getProductsFoundText().contains("Product(s) found"), "Products count label is not updated");
    }
}