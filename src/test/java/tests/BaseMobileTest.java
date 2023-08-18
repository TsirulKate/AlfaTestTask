package tests;

import driver.SeleniumDriver;
import factory.DriverManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class BaseMobileTest {
    private static SeleniumDriver driver;

    protected static final String TEST_DATA_PATH = "src/test/resources/data/";

    @BeforeClass(alwaysRun = true, description = "Class Level Setup")
    public void setupAppium() {
        driver = DriverManager.getMobileDriver();
    }

    @AfterClass(alwaysRun = true, description = "Mobile Class Level TearDown")
    public void closeAppium() {
        DriverManager.closeDefaultDriver();
    }
}
