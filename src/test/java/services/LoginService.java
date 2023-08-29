package services;

import entities.User;
import io.qameta.allure.Step;
import org.testng.Assert;
import screens.LoginScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.EqualUtils;
import utils.StringUtils;

public class LoginService {
    private static final int MAXIMUM_LENGTH = 50;
    private static final String CORRECT_LOGIN_PATTERN = "\\s?[a-zA-Z.,'_//-]*\\s?";
    private static final String DEFAULT_USERNAME = "Логин";
    private static final String DEFAULT_PASSWORD = "Пароль";
    private static final String INCORRECT_DATA_MESSAGE = "";
    private static final String INVALID_DATA_MESSAGE = "Введены неверные данные";
    private static final String EXCEPT_DATA_MESSAGE = "";

    @Step("Login to the app as {user.username}, {user.password}")
    public void loginUser(User user) {
        LoginScreen loginScreen = new LoginScreen().doLogin(user);
        Assert.assertTrue(loginScreen.isLoginSuccessful(), "Login wasn't successful.");
    }

    @Step("Try to login to the app as {user.username}, {user.password}")
    public void checkInvalidAuthorization(User user) {
        LoginScreen loginScreen = new LoginScreen().doLogin(user);
        Assert.assertFalse(loginScreen.isLoginSuccessful(), "Login was successful with incorrect data.");
        String actualErrorMessage = loginScreen.getErrorMessage();
        String login = user.getUsername();
        String password = user.getPassword();
        boolean isCorrect;
        if (isLoginValid(login) && isPasswordValid(password)) {
            isCorrect = EqualUtils.equalFields("Message for unauthorized user with correct data", actualErrorMessage, INCORRECT_DATA_MESSAGE);
        } else if (!isLengthCorrect(login) || !isLengthCorrect(password)) {
            isCorrect = EqualUtils.equalFields("Error message", actualErrorMessage, EXCEPT_DATA_MESSAGE);
            isCorrect &= EqualUtils.equalFields("Login with correct length ", loginScreen.getUsername(),
                    isLengthCorrect(login) ? login : login.substring(0, MAXIMUM_LENGTH));
            isCorrect &= EqualUtils.equalFields("Password with correct length ", loginScreen.getPassword(),
                    isLengthCorrect(password) ? password : password.substring(0, MAXIMUM_LENGTH));
        } else {
            isCorrect = EqualUtils.equalFields("Error message", actualErrorMessage, INVALID_DATA_MESSAGE);
        }

        if (!isCorrect) {
            Assert.fail("Something went wrong. See DIFFERENT PARAMETERS for more details");
        }
    }

    @Step("Check password showing/hiding logic")
    public void checkPassword(String password) {
        LoginScreen loginScreen = new LoginScreen().enterPassword(password);
        boolean isCorrect = EqualUtils.equalFalse("Password is hidden", loginScreen.isPasswordVisible());
        isCorrect &= EqualUtils.equalFields("Password", loginScreen.showPassword().getPassword(), StringUtils.isStringEmpty(password) ? DEFAULT_PASSWORD : password);
        isCorrect &= EqualUtils.equalTrue("Password is visible", loginScreen.isPasswordVisible());
        isCorrect &= EqualUtils.equalFalse("Password is hidden again", loginScreen.showPassword().isPasswordVisible());

        if (!isCorrect) {
            Assert.fail("Something went wrong. See DIFFERENT PARAMETERS for more details");
        }
    }

    private boolean isPasswordValid(String password) {
        return !StringUtils.isStringEmpty(password) && password.length() <= MAXIMUM_LENGTH;
    }

    private boolean isLoginValid(String login) {
        return !StringUtils.isStringEmpty(login) && login.length() <= MAXIMUM_LENGTH && login.matches(CORRECT_LOGIN_PATTERN);
    }

    private boolean isLengthCorrect(String value) {
        return value.length() <= MAXIMUM_LENGTH;
    }
}
