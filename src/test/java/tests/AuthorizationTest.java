package tests;


import entities.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import services.LoginService;
import utils.DataUtils;
import utils.JsonUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorizationTest extends BaseMobileTest {

    private final LoginService loginService = new LoginService();
    private User[] users;

    @BeforeClass
    public void setUp() {
        String dataPath = TEST_DATA_PATH + "users.data.json";
        users = JsonUtils.getObjectFromJson(dataPath, User[].class);
    }

    @Test(description = "Check valid authorization", dataProvider = "validUsers")
    public void validAuthorizationTest(User user) {
        loginService.loginUser(user);
    }

    @Test(description = "Check invalid authorization", dataProvider = "invalidUsers")
    public void invalidAuthorizationTest(User user) {
        loginService.checkInvalidAuthorization(user);
        loginService.checkPassword(user.getPassword());
    }

    @DataProvider(name = "validUsers")
    public Object[][] validUsers() {
        List<User> validUsers = Arrays.stream(users).filter(User::isValid).collect(Collectors.toList());
        return DataUtils.listToDataProviderArray(validUsers);
    }

    @DataProvider(name = "invalidUsers")
    public Object[][] invalidUsers() {
        List<User> validUsers = Arrays.stream(users).filter(user -> !user.isValid()).collect(Collectors.toList());
        return DataUtils.listToDataProviderArray(validUsers);
    }
}
