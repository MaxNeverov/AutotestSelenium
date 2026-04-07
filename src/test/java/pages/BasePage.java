package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(xpath = "//a[@data-test='shopping-cart-link']")
    private WebElement bucketIcon;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void goToBucket(){
        //wait.until(ExpectedConditions.visibilityOf(this.bucketIcon)).click();
        driver.get("https://www.saucedemo.com/cart.html");
        wait.until(ExpectedConditions.urlContains("/cart.html"));
    }
}
