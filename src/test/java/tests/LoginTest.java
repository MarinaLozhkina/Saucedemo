package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.UserFactory;

import static enums.DepartmentNaming.PRODUCTS;
import static org.testng.Assert.*;

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
        loginPage
                .open()
                .login(UserFactory.withAdminPermission());
        assertTrue(productsPage.titleIsDisplayed());
        assertEquals(productsPage.getTitle(), PRODUCTS.getDisplayName());
    }

    @DataProvider(name = "incorrectLoginData")
    public Object[][] loginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"123", "456", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "incorrectLoginData")
    public void incorrectLogin(String user, String pass, String errorMsg) {
        loginPage
                .open()
                .fillLoginInput(user)
                .fillPasswordInput(pass)
                .clickSubmitBtn();
        assertEquals(loginPage.getErrorMsg(), errorMsg);
    }
}