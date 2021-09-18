package tests;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import pages.*;
import utils.CapabilityFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTests {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    private CapabilityFactory capabilityFactory = new CapabilityFactory();

    private static final String SITE_URL = "https://www.takealot.com/";

    @BeforeMethod
    @Parameters(value = {"browser"})
    public void setUp(@Optional("firefox") String browser) throws MalformedURLException{
        driver.set(new RemoteWebDriver(new URL("http://192.168.1.102:4444/wd/hub"),
                capabilityFactory.getCapabilities(browser)));
        getDriver().get(SITE_URL);
        getDriver().manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        getDriver().close();
    }

    @AfterClass
    void terminate(){
        driver.remove();
    }

    public WebDriver getDriver(){
        return driver.get();
    }

    public HomePage getHomePage(){
        HomePage homePage = new HomePage(getDriver());
        homePage.waitUntilPageLoadIsCompleted(30);
        if (homePage.isModalPresent()){
            homePage.closeModal();
        }
        homePage.closeCookieBanner();
        return homePage;
    }

    public FictionCategoryPage getFictionCategoryPage(){
        return new FictionCategoryPage(getDriver());
    }

    public ClassicsProductListingPage getClassicsProductListingPage(){
        return new ClassicsProductListingPage(getDriver());
    }

    public ProductDetailsPage getProductDetailsPage(){
        return new ProductDetailsPage(getDriver());
    }

    public SearchResultsPage getSearchResultsPage(){
        return new SearchResultsPage(getDriver());
    }

    public WishListPage getWishListPage(){
        return new WishListPage(getDriver());
    }
}
