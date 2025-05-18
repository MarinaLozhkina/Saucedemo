package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test(enabled = true)
    public void correctLogin() {
        loginPage.open();
        loginPage.login(user, password);

        assertTrue(productsPage.titleIsDisplayed());
        assertEquals(productsPage.getTitle(), "Products");
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
        loginPage.login(user, pass);
        assertEquals(loginPage.getErrorMsg(), errorMsg);
       }
}
