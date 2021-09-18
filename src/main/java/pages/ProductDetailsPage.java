package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductDetailsPage extends BasePage {

    private static final long TIMEOUT = 30;
    @FindBy(xpath = "//div[contains(@class, 'buybox-actions')]//a[contains(@class, 'add-to-cart-button')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//button[contains(@class, 'checkout-now')]")
    private WebElement goToCartButton;

    @FindBy(xpath = "//div[contains(@class, 'drawer-screen-module_drawer-screen')]")
    private WebElement cartModal;

    @FindBy(xpath = "//div[contains(@class, 'product-horizontal-item')]")
    private List<WebElement> productItemsOnCartModal;

    @FindBy(xpath = "//a[contains(@class,'drawer-screen-module_close')]")
    private WebElement closeModalButton;

    @FindBy(xpath = "//div[@class='badge-count']")
    private WebElement itemsInCartCount;

    @FindBy(xpath = "//div[contains(@class, 'pdp-main-panel')]")
    private WebElement pdpMainGrid;

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddToCartButton(){
        addToCartButton.click();
    }

    public boolean isCartModalPresent(){
        try{
            waitUntilElementIsVisible(cartModal, TIMEOUT);
        } catch(TimeoutException e){
            return false;
        }
        return true;
    }

    public boolean isProductPresentOnCartModal(){
        return !productItemsOnCartModal.isEmpty();
    }

    public void closeCartModal(){
        closeModalButton.click();
    }

    public boolean isGoToCartButtonPresent(){
        try{
            waitUntilElementIsVisible(goToCartButton, TIMEOUT);
        } catch (TimeoutException e){
            return false;
        }
        return true;
    }

    public String getGoToCartButtonText(){
        return goToCartButton.getText();
    }

    public String getItemsInCartCount(){
        return itemsInCartCount.getText();
    }

    public WebElement getMainGrid(){
        return pdpMainGrid;
    }
}
