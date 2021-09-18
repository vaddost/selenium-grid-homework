package tests;

import org.testng.annotations.Test;
import pages.ClassicsProductListingPage;
import pages.FictionCategoryPage;
import pages.HomePage;
import pages.ProductDetailsPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductDetailsPageTests extends BaseTests{

    private static final long TIMEOUT = 30;

    private HomePage homePage;
    private ClassicsProductListingPage classicsPage;
    private FictionCategoryPage fictionCategoryPage;
    private ProductDetailsPage productDetailsPage;

    @Test
    public void checkThatUserCanAddProductToTheCart(){
        homePage = getHomePage();
        homePage.openFictionCategoryFromDepartmentDropdown();

        fictionCategoryPage = getFictionCategoryPage();
        fictionCategoryPage.waitUntilElementIsVisible(fictionCategoryPage.getCategoriesListElement(), TIMEOUT);
        fictionCategoryPage.clickOnClassicsCategory();

        classicsPage = getClassicsProductListingPage();
        classicsPage.waitUntilElementIsVisible(classicsPage.getSearchCount(), TIMEOUT);
        classicsPage.clickOnFirstProduct();
        classicsPage.waitUntilNumberOfWindowsEquals(2, TIMEOUT);
        classicsPage.switchToNextWindow();

        productDetailsPage = getProductDetailsPage();
        productDetailsPage.fluentWaitUntilElementIsVisible(productDetailsPage.getMainGrid(), TIMEOUT);
        productDetailsPage.clickAddToCartButton();
        productDetailsPage.implicitWait(TIMEOUT);
        assertTrue(productDetailsPage.isCartModalPresent(), "Cart modal is not opened");
        assertTrue(productDetailsPage.isProductPresentOnCartModal(), "Cart modal is empty");

        productDetailsPage.closeCartModal();
        assertTrue(productDetailsPage.isGoToCartButtonPresent(), "Go to Cart button is absent");
        assertEquals(productDetailsPage.getGoToCartButtonText(), "Go to Cart");

        assertEquals(productDetailsPage.getItemsInCartCount(), "1");
    }
}
