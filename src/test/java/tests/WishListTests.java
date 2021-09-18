package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import pages.WishListPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class WishListTests extends BaseTests{

    private static final long TIMEOUT = 30;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private WishListPage wishListPage;

    @Test
    public void checkUserCanAddProductToWishListFromSearchPage(){
        String searchPhrase = "iphone";

        homePage = getHomePage();
        homePage.searchByPhrase(searchPhrase);

        searchResultsPage = getSearchResultsPage();
        searchResultsPage.waitUntilElementIsVisible(searchResultsPage.getItemsCount(), TIMEOUT);
        searchResultsPage.clickOnWishListIconForFirstProduct();
        assertTrue(searchResultsPage.isItemAddedToWishListAlertDisplayed());
        assertEquals(searchResultsPage.getWishlistAlertText(), "Item added to Wish List");
        searchResultsPage.waitUntilElementIsNotVisible(searchResultsPage.getWishListAlert(), TIMEOUT);
        searchResultsPage.clickOnWishlistButtonFromHeader();

        wishListPage = getWishListPage();
        wishListPage.waitUntilPageLoadIsCompleted(TIMEOUT);
        wishListPage.waitUntilElementIsVisible(wishListPage.getTitle(), TIMEOUT);
        assertEquals(wishListPage.getItemsCount(), 1);
    }
}
