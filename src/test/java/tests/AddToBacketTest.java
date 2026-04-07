package tests;

import elements.ProductCard;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import pages.BucketPage;
import pages.LoginPage;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddToBacketTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private BucketPage bucketPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        bucketPage = new BucketPage(driver);
    }

    @Test
    @DisplayName("Успешный вход в систему")
    void loginTest() {
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(driver.getCurrentUrl().contains("/inventory.html"));
    }

    @Test
    @DisplayName("У всех карточек есть цена")
    void addToBucketTest() {
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(mainPage.allCardsHavePrice());
    }

    @Test
    @DisplayName("Карточка в корзине")
    void findCardsTest() {
        String productTitle = "Sauce Labs Backpack";

        //логин
        loginPage.login("standard_user", "secret_sauce");

        //нахождение товара по названию, добавление его в корзину и проверка, что кнопка добавления
        //изменилась на - удалить из корзины
        ProductCard product = mainPage.findProductByTitle(productTitle);
        product.addToBucket();
        assertTrue(product.isRemoveButton(), "Кнопка не изменилась на Remove");

        //проверка, что перешли в корзину
        mainPage.goToBucket();
        assertTrue(driver.getCurrentUrl().contains("/cart.html"), "Не удалось перейти в корзину");

        //проверка, что товар лехит в корзине по названию
        assertTrue(bucketPage.isProductInCart(productTitle));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
