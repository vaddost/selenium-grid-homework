package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    private static final int TIMEOUT = 30;

    @FindBy(xpath = "//div[contains(@class, 'modal')]//button[contains(@class, 'close')]")
    private WebElement closeModalButton;

    @FindBy(xpath = "//button[contains(@class, 'cookies-banner-module_dismiss-button')]")
    private WebElement gotItButton;

    @FindBy(xpath = "//ul[contains(@class, 'department-categories')]//span[contains(text(), 'Books')]/..")
    private WebElement booksAndCoursesDepartmentDropdownLink;

    @FindBy(xpath = "//ul[contains(@class, 'department-flyout-module_content')]//a[contains(@href, '/fiction')]")
    private WebElement fictionCategoryDepartmentFlyoutLink;

    @FindBy(xpath = "//input[@name='search']")
    private WebElement searchField;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void closeModal(){
        closeModalButton.click();
    }

    public WebElement getCloseModalButton(){
        return closeModalButton;
    }

    public void closeCookieBanner(){
        gotItButton.click();
    }

    public boolean isModalPresent(){
        try{
            waitUntilElementIsVisible(closeModalButton, TIMEOUT);
        } catch (TimeoutException e){
            return false;
        }
        return true;
    }

    public void openFictionCategoryFromDepartmentDropdown(){
        Actions actions = new Actions(driver);
        actions.moveToElement(booksAndCoursesDepartmentDropdownLink).build().perform();
        waitUntilElementIsVisible(fictionCategoryDepartmentFlyoutLink, TIMEOUT);
        fictionCategoryDepartmentFlyoutLink.click();
    }

    public void searchByPhrase(final String phrase){
        searchField.sendKeys(phrase, Keys.RETURN);
    }
}
