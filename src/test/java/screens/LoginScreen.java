package screens;

import driver.Screen;
import entities.User;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginScreen extends Screen {
    public LoginScreen() {
        super();
    }

    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'Username')]")
    private WebElement usernameInput;

    @AndroidFindBy(xpath = "//android.widget.EditText[contains(@resource-id,'Password')]")
    private WebElement passwordInput;

    @AndroidFindBy(accessibility = "Show password")
    private WebElement showPasswordButton;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id,'Confirm')]")
    private WebElement loginButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id,'Error')]")
    private WebElement errorMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'выполнен')]")
    private WebElement loggedInMessage;

    public LoginScreen doLogin(User user) {
        wait.until(ExpectedConditions.visibilityOf(usernameInput)).clear();
        usernameInput.sendKeys(user.getUsername());
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).clear();
        passwordInput.sendKeys(user.getPassword());
        wait.until(ExpectedConditions.visibilityOf(loginButton)).click();

        return this;
    }

    public LoginScreen enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public String getPassword() {
        return passwordInput.getText();
    }

    public String getUsername() {
        return usernameInput.getText();
    }

    public LoginScreen showPassword() {
        wait.until(ExpectedConditions.visibilityOf(showPasswordButton)).click();
        return this;
    }

    public boolean isPasswordVisible() {
        return showPasswordButton.getAttribute("checked").equals("true");
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }

    public boolean isLoginSuccessful() {
        try {
            loggedInMessage.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
