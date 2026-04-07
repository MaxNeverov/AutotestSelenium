package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    @Given("открыта страница логина")
    public void openLoginPage() {
        driver = Hooks.getDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @When("пользователь вводит логин {string}")
    public void enterUsername(String username) {
        loginPage.setUsername(username);
    }

    @When("пользователь вводит пароль {string}")
    public void enterPassword(String password) {
        loginPage.setPassword(password);
    }

    @When("нажимает кнопку Login")
    public void clickLogin() {
        loginPage.clickLogin();
    }

    @Then("пользователь попадает на главную страницу")
    public void verifyDashboard() {
        assertTrue(driver.getCurrentUrl().contains("/inventory.html"));
    }

}
