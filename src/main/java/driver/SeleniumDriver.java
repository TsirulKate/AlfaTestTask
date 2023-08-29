package driver;

import factory.AndroidFactory;
import factory.MobileSystemType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SeleniumDriver implements WebDriver {

    private final WebDriver driver;

    public static final long DEFAULT_IMPLICIT_WAIT_TIMEOUT = 5;

    public SeleniumDriver(MobileSystemType mobileSystemType) {
        switch (mobileSystemType) {
            case ANDROID:
                driver = new AndroidFactory().setUp();
                break;

            default:
                throw new RuntimeException("not implemented");
        }
        driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_WAIT_TIMEOUT, TimeUnit.SECONDS);
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    @Override
    public void get(String url) {
        this.driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return this.driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public <T extends WebElement> List<T> findElements(By by) {
        return driver.findElements(by);
    }

    @Override
    public <T extends WebElement> T findElement(By by) {
        return this.driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return this.driver.getPageSource();
    }

    @Override
    public void close() {
        if (driver instanceof AndroidDriver) {
            ((AndroidDriver<?>) driver).closeApp();
        } else {
            ((IOSDriver<?>) driver).closeApp();
        }
    }

    @Override
    public void quit() {
        this.driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return this.driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return this.driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return this.driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return this.driver.navigate();
    }

    @Override
    public Options manage() {
        return this.driver.manage();
    }
}
