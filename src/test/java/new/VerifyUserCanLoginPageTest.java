import framework.config.TestConfig;
import framework.driver.WebDriverSingleton;
import framework.model.Customer;
import framework.pages.login.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static framework.driver.WebDriverSingleton.getDriver;

public class VerifyUserCanLoginPageTest {

    private final WebDriver driver = getDriver();
    private Customer customer;
    private LoginPage loginPage;

    @BeforeClass
    public void beforeActions() {
        customer = Customer.newBuilder().withName("test@gmail.com").withPassword("testAUTO123").build();
        loginPage = new LoginPage(driver);
        driver.get(TestConfig.cfg.baseUrl());
        WebDriverWait wait = new WebDriverWait(driver, 5);
    }

    @Test
    public void verifyLogIn() {
        loginPage.logInAsCustomer(customer);
    }

    @AfterClass
    public void closeDriver() {
        WebDriverSingleton.closeDriver();
    }
}