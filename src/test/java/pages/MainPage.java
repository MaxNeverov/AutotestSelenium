package pages;

import elements.ProductCard;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class MainPage extends BasePage {

    @FindBy(xpath = "//div[@data-test='inventory-item']")
    private List<WebElement> productCardRoots;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductCard> getProductCards() {
        return productCardRoots.stream()
                .map(ProductCard::new)
                .collect(Collectors.toList());
    }

    // Найти карточку по названию
    public ProductCard findProductByTitle(String title) {
        return getProductCards().stream()
                .filter(card -> card.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        "Карточка с названием '" + title + "' не найдена"));
    }

    // Проверить, что у всех карточек есть цена
    public boolean allCardsHavePrice() {
        return getProductCards().stream()
                .allMatch(ProductCard::hasPrice);
    }

}
