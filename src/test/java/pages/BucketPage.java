package pages;

import elements.ProductCard;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class BucketPage extends BasePage {

    @FindBy(xpath = "//button[@data-test='continue-shopping']")
    private WebElement buttonContinueShopping;

    @FindBy(xpath = "//button[@data-test='checkout']")
    private WebElement buttonCheckout;

    @FindBy(xpath = "//div[@data-test='inventory-item']")
    private List<WebElement> productCardRoots;

    public BucketPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductCard> getProductCards() {
        return productCardRoots.stream()
                .map(ProductCard::new)
                .collect(Collectors.toList());
    }

    public boolean isProductInCart(String title) {
        return getProductCards().stream()
                .anyMatch(card -> card.getTitle().equals(title));
    }
}
