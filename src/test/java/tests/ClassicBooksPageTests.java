package tests;

import org.testng.annotations.Test;
import pages.ClassicsProductListingPage;
import pages.FictionCategoryPage;
import pages.HomePage;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ClassicBooksPageTests extends BaseTests{
    HomePage homePage;
    FictionCategoryPage fictionCategoryPage;
    ClassicsProductListingPage classicsProductListingPage;
    private static final long TIMEOUT = 60;
    private static final String AUTHOR_NAME = "Elinor Glyn";

    @Test
    public void checkFilteringByAuthorOnProductListingPage(){
        homePage = getHomePage();
        homePage.openFictionCategoryFromDepartmentDropdown();

        fictionCategoryPage = getFictionCategoryPage();
        fictionCategoryPage.waitUntilElementIsVisible(fictionCategoryPage.getCategoriesListElement(), TIMEOUT);
        assertEquals(fictionCategoryPage.getPageTitle(), "Fiction");
        fictionCategoryPage.clickOnClassicsCategory();

        classicsProductListingPage = getClassicsProductListingPage();
        classicsProductListingPage.waitUntilElementIsVisible(classicsProductListingPage
                .getSearchCount(), TIMEOUT);
        classicsProductListingPage.enterValueInSearchByAuthorField(AUTHOR_NAME);
        classicsProductListingPage.clickOnAuthorCheckbox(AUTHOR_NAME);
        classicsProductListingPage.waitUntilElementIsRefreshed(classicsProductListingPage.getSearchCount(), TIMEOUT);
        List<String> booksAuthors = classicsProductListingPage.getProductAuthors();
        boolean filteredAuthorPresent = true;
        for (String authors : booksAuthors){
            if (!authors.contains(AUTHOR_NAME)){
                filteredAuthorPresent = false;
                break;
            }
        }
        assertTrue(filteredAuthorPresent);
    }
}
