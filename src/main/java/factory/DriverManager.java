package factory;

import driver.SeleniumDriver;

public class DriverManager {

    private static final ThreadLocal<SeleniumDriver> defaultDriver = new ThreadLocal<>();

    public static SeleniumDriver getMobileDriver() {
        if (null == defaultDriver.get()) {
            defaultDriver.set(new SeleniumDriver(MobileSystemType.ANDROID));
        }
        return defaultDriver.get();
    }

    public static void closeDefaultDriver() {
        SeleniumDriver current = defaultDriver.get();
        if (current == null) {
            return;
        }
        try {
            current.close();
            current.quit();
        } finally {
            defaultDriver.set(null);
        }
    }
}
