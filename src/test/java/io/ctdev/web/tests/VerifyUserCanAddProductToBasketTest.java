package io.ctdev.web.tests;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.driver.WebDriverSingleton;
import io.ctdev.framework.model.Customer;
import io.ctdev.framework.pages.login.LoginPage;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;
import static java.lang.String.format;

public class VerifyUserCanAddProductToBasketTest {

    private final WebDriver driver = getDriver();
    private Customer customer;
    private LoginPage loginPage;

    private final String productName = "URBAN JUNGLE LED";

    @BeforeClass
    public void beforeActions() {
        customer = Customer.newBuilder().withName("andrewotroh@gmail.com").withPassword("testAUTO123").build();
        loginPage = new LoginPage(driver);
        driver.get(TestConfig.cfg.baseUrl());
    }

    @Test
    @Description("Verify user can login")
    public void verifyLogIn() {
        loginPage.logInAsCustomer(customer);
        loginPage.waitForSomething(120);

        Assert.assertTrue(loginPage.isAccountIconPresent(),
                "Customer Icon should be present");
    }

    @Test(dependsOnMethods = "verifyLogIn")
    @Description("Verify User Can Find Product")
    public void verifyUserCanFindProduct() {
        loginPage.searchProduct(productName);
        String actualProductName = loginPage.getProductName();

        Assert.assertTrue(actualProductName.contains(productName),
                format("'%s' should be present", productName));
    }

    @Test(dependsOnMethods = "verifyUserCanFindProduct")
    @Description("Verify User Can Add Product")
    public void verifyUserCanAddProduct() {
        String expectedNotification = "Товар був доданий у Кошик для покупок";

        loginPage.addProductToBasket();
        String actualNotificationText = loginPage.getTextFromNotification();

        Assert.assertTrue(actualNotificationText.contains(expectedNotification),
                format("'%s' should be present", expectedNotification));
    }

    @Test(dependsOnMethods = "verifyUserCanAddProduct")
    @Description("Verify Product is present in the basket")
    public void verifyProductIsPresentInTheBasket() {
        loginPage.clickOnBasketButton();
        String actualProductName = loginPage.getProductNameFromBasket();

        Assert.assertTrue(actualProductName.contains(productName),
                format("'%s' should be present", productName));
    }

    @AfterClass
    public void closeDriver() {
        WebDriverSingleton.closeDriver();
    }
}