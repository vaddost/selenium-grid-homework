package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class ClassicsProductListingPage extends BasePage{

    @FindBy(xpath = "//input[contains(@placeholder, 'Search by Author')]")
    private WebElement searchByAuthorFilterInput;

    @FindBy(xpath = "//label[contains(@class, 'checkbox')]")
    private List<WebElement> checkboxesValues;

    @FindBy (xpath = "//div[contains(@class, 'search-count')]")
    private WebElement searchCount;

    @FindBy (xpath = "//span[contains(@class, 'authors')]")
    private List<WebElement> authorsProductLabel;

    @FindBy(xpath = "//a[contains(@class, 'product-anchor')]")
    private List<WebElement> productLinks;

    public ClassicsProductListingPage(WebDriver driver) {
        super(driver);
    }

    public void enterValueInSearchByAuthorField(final String author){
        searchByAuthorFilterInput.clear();
        searchByAuthorFilterInput.sendKeys(author);
    }

    public void clickOnAuthorCheckbox(final String author){
        for (var checkboxLabel : checkboxesValues){
            if (checkboxLabel.getAttribute("data-ref").contains(author)){
                checkboxLabel.click();
                break;
            }
        }
    }

    public WebElement getSearchCount(){
        return searchCount;
    }

    public List<String> getProductAuthors(){
        return authorsProductLabel.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clickOnFirstProduct(){
        if (!productLinks.isEmpty()){
            productLinks.get(0).click();
        }
    }
}
