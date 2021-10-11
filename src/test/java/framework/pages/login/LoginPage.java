package framework.pages.login;

import framework.config.TestConfig;
import framework.model.Customer;
import framework.pages.AbstractPage;
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

    public LoginPage clickOnLoginIcon() {
        loginLabel.click();
        return this;
    }

    public LoginPage setEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public LoginPage setPassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage clickOnLoginButton() {
        loginButton.click();
        return this;
    }

    public boolean isAccountIconPresent() {
        return accountIcon.isDisplayed();
    }

    public LoginPage logInAsCustomer(Customer customer) {
        clickOnLoginIcon();
        setEmail(customer.getEmail());
        setPassword(customer.getPassword());
        clickOnLoginButton();
        return this;
    }
}
