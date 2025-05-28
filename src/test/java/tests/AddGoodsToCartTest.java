package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;
import static user.UserFactory.withAdminPermission;

public class AddGoodsToCartTest extends BaseTest {
    @Epic("Модуль логина интернет-магазина")
    @Feature("Юридические лица")
    @Story("STG")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Marina Lozhkina lozhkina.marina86@gmail.com")
    @TmsLink("blabla")
    @Issue("2")
    @Test(description = "Проверяем, что товары добавлены в корзину")
    public void cartCheck() {
        loginPage
                .open()
                .login(withAdminPermission());
        productsPage
                .isOpen()
                .addToCart(1)
                .addToCart(3)
                .openCart();
        assertTrue(cartPage.getProductsNames().contains("Sauce Labs Onesie"));
        assertEquals(cartPage.getProductsNames().size(), 2);
        assertFalse(cartPage.getProductsNames().isEmpty());
    }
}