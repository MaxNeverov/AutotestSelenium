package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductCard {

    private WebElement root;

    // Локаторы относительно корня карточки
    private static final String TITLE_CSS = "div[data-test='inventory-item-name']";
    private static final String PRICE_CSS = "div[data-test='inventory-item-price']";
    private static final String ADD_TO_BUCKET_CSS = "button[data-test='add-to-cart-sauce-labs-backpack']";
    private static final String REMOVE_TO_BUCKET_CSS = "button[data-test='remove-sauce-labs-backpack']";

    public ProductCard(WebElement root) {
        this.root = root;
    }

    public String getTitle() {
        return root.findElement(By.cssSelector(TITLE_CSS)).getText();
    }

    public String getPrice() {
        return root.findElement(By.cssSelector(PRICE_CSS)).getText();
    }

    public void addToBucket() {
        root.findElement(By.cssSelector(ADD_TO_BUCKET_CSS)).click();
    }

    public boolean hasPrice() {
        List<WebElement> prices = root.findElements(By.cssSelector(PRICE_CSS));
        return !prices.isEmpty() && !prices.get(0).getText().isEmpty();
    }

    public boolean isRemoveButton() {
        return root.findElement(By.cssSelector(REMOVE_TO_BUCKET_CSS)).isDisplayed();
    }
}
