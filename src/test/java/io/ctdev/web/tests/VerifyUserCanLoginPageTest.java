package io.ctdev.web.tests;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.driver.WebDriverSingleton;
import io.ctdev.framework.model.Customer;
import io.ctdev.framework.pages.login.LoginPage;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.ctdev.framework.driver.WebDriverSingleton.getDriver;

public class VerifyUserCanLoginPageTest {

    private final WebDriver driver = getDriver();
    private Customer customer;
    private LoginPage loginPage;

    @BeforeClass
    public void beforeActions() {
        customer = Customer.newBuilder().withName("andrewotroh@gmail.com").withPassword("testAUTO123").build();
        loginPage = new LoginPage(driver);
        driver.get(TestConfig.cfg.baseUrl());
        WebDriverWait wait = new WebDriverWait(driver, 5);
    }

    @Test
    @Description("Verify user can login")
    public void verifyLogIn() {
        loginPage.logInAsCustomer(customer);

        Assert.assertTrue(loginPage.isAccountIconPresent(), "Customer Icon is present");
    }

    @AfterClass
    public void closeDriver() {
        WebDriverSingleton.closeDriver();
    }
}