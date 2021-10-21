package io.ctdev.framework.pages.login;

import io.ctdev.framework.config.TestConfig;
import io.ctdev.framework.model.Customer;
import io.ctdev.framework.pages.AbstractPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {

    private WebDriverWait webDriverWait;

    @FindBy(className = "ico-login")
    private WebElement loginLabel;

    @FindBy(id = "Email")
    private WebElement emailField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[contains(@class,'login-button')]")
    private WebElement loginButton;

    @FindBy(className = "ico-account")
    private WebElement accountIcon;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.webDriverWait = new WebDriverWait(driver, TIME_OUT);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void openPage() {
        driver.get(TestConfig.cfg.baseUrl() + "#/login");
    }

    @Step("Click on login icon")
    public LoginPage clickOnLoginIcon() {
        loginLabel.click();
        return this;
    }

    @Step("set '{0}' email")
    public LoginPage setEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    @Step("set '{0}' password")
    public LoginPage setPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    @Step("Click on login button")
    public LoginPage clickOnLoginButton() {
        loginButton.click();
        return this;
    }

    @Step("Is account icon present?")
    public boolean isAccountIconPresent() {
        return accountIcon.isDisplayed();
    }

    @Step("Log in as customer")
    public LoginPage logInAsCustomer(Customer customer) {
        clickOnLoginIcon();
        setEmail(customer.getEmail());
        setPassword(customer.getPassword());
        clickOnLoginButton();
        return this;
    }
}
