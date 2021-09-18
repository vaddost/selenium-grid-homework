package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FictionCategoryPage extends BasePage{
    @FindBy(xpath = "//h1[contains(@class, 'merch-title')]")
    private WebElement categoryPageTitle;

    @FindBy(xpath = "//ul[contains(@class, 'list-menu')]")
    private WebElement categoriesList;

    @FindBy(xpath = "//ul[contains(@class, 'list-menu')]//a[contains(@href, '/classics')]")
    private WebElement classicsCategoryLink;

    public FictionCategoryPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle(){
        return categoryPageTitle.getText();
    }

    public void clickOnClassicsCategory(){
        classicsCategoryLink.click();
    }

    public WebElement getCategoriesListElement(){
        return categoriesList;
    }
}
