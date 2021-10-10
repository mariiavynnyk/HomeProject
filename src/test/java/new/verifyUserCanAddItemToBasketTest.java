import framework.driver.WebDriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static framework.driver.WebDriverSingleton.getDriver;

public class verifyUserCanAddItemToBasketTest {

    WebDriver driver;

    String expectedTitle = "BUTLERS";

    @BeforeClass
    public void beforeActions() {
        driver = getDriver();
        driver.get("https://butlers.ua");
    }

    @Test
    public void verifyTitle() {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Title isn't match");

    }

    @Test(dependsOnMethods = "verifyTitle")
    public void searchProduct() {
        WebElement searchField = driver.findElement(By.xpath("//*[@aria-label='Search store']"));
        WebElement searchButton = driver.findElement(By.xpath("//*[@id='small-search-box-form']/input[@type='submit']"));
        searchField.sendKeys("10217772");
        searchButton.click();
    }

    @AfterClass
    public void closeDriver() {
        WebDriverSingleton.closeDriver();
    }
}