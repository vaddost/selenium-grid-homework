package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage{
    private static final long TIMEOUT = 30;
    @FindBy(xpath = "//div[contains(@class, 'search-count')]")
    private WebElement itemsCount;

    @FindBy(xpath = "//button[contains(@class, 'wishlist-button')]")
    private List<WebElement> wishListButtons;

    @FindBy(xpath = "//div[contains(@class, 'toast-body')]")
    private WebElement itemAddedToWishlistAlert;

    @FindBy(xpath = "//div[contains(@class, 'toast-content-module_message')]")
    private WebElement itemAddedToWishlistAlertMessage;

    @FindBy(xpath = "//a[contains(@class, 'top-nav-module_wishlist-button')]")
    private WebElement wishListButtonInHeader;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getItemsCount() {
        return itemsCount;
    }

    public void clickOnWishListIconForFirstProduct(){
        if (!wishListButtons.isEmpty()){
            wishListButtons.get(0).click();
        }
    }

    public boolean isItemAddedToWishListAlertDisplayed(){
        try {
            waitUntilElementIsVisible(itemAddedToWishlistAlert, TIMEOUT);
        } catch (TimeoutException e){
            return false;
        }
        return true;
    }

    public String getWishlistAlertText(){
        return itemAddedToWishlistAlertMessage.getText();
    }

    public WebElement getWishListAlert(){
        return itemAddedToWishlistAlert;
    }

    public void clickOnWishlistButtonFromHeader(){
        wishListButtonInHeader.click();
    }
}
