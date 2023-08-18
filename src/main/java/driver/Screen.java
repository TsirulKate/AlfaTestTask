package driver;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import factory.DriverManager;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Screen {

    public SeleniumDriver driver;

    protected WebDriverWait wait;

    protected Screen() {
        driver = DriverManager.getMobileDriver();
        this.wait = new WebDriverWait(driver, 20);
        PageFactory.initElements(new AppiumFieldDecorator(driver.getWebDriver()), this);
    }
}
