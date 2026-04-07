package steps;

import elements.ProductCard;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.BucketPage;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductSteps {

    private WebDriver driver;
    private MainPage mainPage;
    private BucketPage bucketPage;

    public ProductSteps() {
        this.driver = Hooks.getDriver();
        this.mainPage = new MainPage(driver);
        this.bucketPage = new BucketPage(driver);
    }

    @When("пользователь добавляет товар {string} в корзину")
    public void addToBacket(String productName) {
        ProductCard product = mainPage.findProductByTitle(productName);
        product.addToBucket();
        assertTrue(product.isRemoveButton(), "Кнопка не изменилась на Remove");
    }

    @Then("товар {string} отображается в корзине")
    public void checkInBacket(String productName) {
        mainPage.goToBucket();
        System.out.println("Текущий URL после перехода: " + driver.getCurrentUrl());
        assertTrue(driver.getCurrentUrl().contains("/cart.html"), "Не удалось перейти в корзину");
        assertTrue(bucketPage.isProductInCart(productName));
    }
}
