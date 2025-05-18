package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.UserFactory;
import utils.AllureUtils;

import static org.testng.Assert.*;
import static utils.AllureUtils.takeScreenshot;

public class LoginTest extends BaseTest {
    @Epic("Модуль логина интернет-магазина")
    @Feature("Юридические лица")
    @Story("STG")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Marina Lozhkina lozhkina.marina86@gmail.com")
    @TmsLink("blabla")
    @Flaky
    @Issue("2")
    @Test(description = "Проверка авторизации")
    public void correctLogin() {
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());

        assertTrue(productsPage.titleIsDisplayed());
        takeScreenshot(driver);
        assertEquals(productsPage.getTitle(), "Products");

        productsPage.isOpen();
        productsPage.addToCart(1);
        productsPage.addToCart(3);
        productsPage.openCart();
        assertTrue(cartPage.getProductsNames().contains("Sauce Labs Onesie"));
        assertEquals(cartPage.getProductsNames().size(), 2);
        assertFalse(cartPage.getProductsNames().isEmpty());
    }

    @DataProvider(name = "incorrectLoginData")
    public Object[][] loginData () {
        return new Object[][] {
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"123", "456", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider =  "incorrectLoginData")
    public void incorrectLogin(String user, String pass, String errorMsg) {
        loginPage.open();
        loginPage.fillLoginInput(user);
        loginPage.fillPasswordInput(pass);
        loginPage.clickSubmitBtn();
        assertEquals(loginPage.getErrorMsg(), errorMsg);
       }
}
