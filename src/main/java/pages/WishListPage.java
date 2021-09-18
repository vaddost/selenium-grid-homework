package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class WishListPage extends BasePage{

    @FindBy(xpath = "//div[contains(@class, 'detail-item-container')]")
    private List<WebElement> wishlistItems;

    @FindBy(xpath = "//h1[contains(text(), 'Wish List')]")
    private WebElement title;

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getTitle(){
        return title;
    }

    public int getItemsCount(){
        return wishlistItems.size();
    }
}
