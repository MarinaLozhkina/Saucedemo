package tests;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

public class AddGoodsToCartTest extends BaseTest {
    @Test()
    public void cartCheck () {
        loginPage.open();
        loginPage.login(user, password);
        productsPage.isOpen();
        productsPage.addToCart(1);
        productsPage.addToCart(3);
        productsPage.openCart();
        assertTrue(cartPage.getProductsNames().contains("Sauce Labs Onesie"));
        assertEquals(cartPage.getProductsNames().size(),2);
        assertFalse(cartPage.getProductsNames().isEmpty());
    }
}