package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;


import static java.util.concurrent.TimeUnit.SECONDS;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitUntilPageLoadIsCompleted(long timeout){
        WebDriverWait waiter = new WebDriverWait(driver, timeout);
        waiter.until(
                webDriver ->
                        ((JavascriptExecutor)webDriver).executeScript("return document.readyState;").
                                equals("complete")
        );
    }

    public void waitUntilElementIsVisible(WebElement element, long timeout){
        WebDriverWait waiter = new WebDriverWait(driver, timeout);
        waiter.until(ExpectedConditions.visibilityOf(element));
    }

    public void fluentWaitUntilElementIsVisible(WebElement element, long timeout){
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilElementIsRefreshed(WebElement element, long timeout){
        new WebDriverWait(driver, timeout).until(
                ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element))
        );
    }

    public void switchToNextWindow(){
        String parentWindow = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();
        driver.close();

        for (var window : windows){
            if (!window.equals(parentWindow)){
                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void waitUntilNumberOfWindowsEquals(int number, long timeout){
        new WebDriverWait(driver, timeout).until(
                ExpectedConditions.numberOfWindowsToBe(number)
        );
    }

    public void waitUntilElementIsNotVisible(WebElement element, long timeout){
        new WebDriverWait(driver, timeout).until(
                ExpectedConditions.invisibilityOf(element)
        );
    }

    public void implicitWait(long timeout){
        driver.manage().timeouts().implicitlyWait(timeout, SECONDS);
    }

}
