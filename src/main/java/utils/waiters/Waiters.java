package utils.waiters;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiters {

    private WebDriver driver;
    public Waiters(WebDriver driver) {
        this.driver = driver;
    }
    public boolean waitForCondition(ExpectedCondition condition) {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
            webDriverWait.until(condition);
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    public boolean waitElementVisible(WebElement element) {
        return waitForCondition(ExpectedConditions.visibilityOf(element));
    }

    public boolean waitElementClickable(WebElement element) {
        return waitForCondition(ExpectedConditions.elementToBeClickable(element));
    }
}
