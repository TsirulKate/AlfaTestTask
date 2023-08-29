package factory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import utils.FileUtils;
import utils.PropertyReader;

public class AndroidFactory extends MobileFactory {

    @Override
    public WebDriver setUp() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        Properties prop = PropertyReader.getInstance().getProperties("src/main/resources/local.properties");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("capabilities.deviceName"));
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("capabilities.automationName"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.getProperty("capabilities.platformName"));
        capabilities.setCapability(MobileCapabilityType.APP, FileUtils.getAbsolutePath(prop.getProperty("capabilities.app")));

        AndroidDriver<MobileElement> driver = null;
        try {
            driver = new AndroidDriver<>(new URL(prop.getProperty("appium_host")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
