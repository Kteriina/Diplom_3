import praktikum.burger.client.User;
import praktikum.burger.client.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.burger.page_object.LoginPage;
import praktikum.burger.page_object.MainPage;
import praktikum.burger.page_object.RegisterPage;
import praktikum.burger.util.WebDriverUtil;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@RunWith(Parameterized.class)
public class RegistrationTest {

    private WebDriver driver;
    private String driverType;
    private User user;
    public static String accessToken;

    String name = randomAlphanumeric(4, 8);
    String email = randomAlphanumeric(6, 10) + "@yandex.ru";
    String password = randomAlphanumeric(10, 20);
    String passwordFailed = randomAlphanumeric(0, 5);


    public RegistrationTest(String driverType) {
        this.driverType = driverType;
    }

    @Before
    public void startUp() {
        driver = WebDriverUtil.initializeDriver(driverType);
        WebDriverUtil.navigateToUrl(driver, "https://stellarburgers.nomoreparties.site/");
    }

    @Parameterized.Parameters(name = "Результаты проверок браузера: {0}")
    public static Object[][] getDataDriver() {
        return new Object[][]{
                {"chromedriver"},
                {"yandexdriver"},
        };
    }


    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрайии")
    public void successfulRegistrationTest() {
        user = new User(name, email, password);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegisterButton();
        RegisterPage registerPage = new RegisterPage(driver);
        String name = randomAlphanumeric(4, 8);
        String email = randomAlphanumeric(6, 10) + "@yandex.ru";;
        String password = randomAlphanumeric(6, 10);
        registerPage.registration(name, email, password);
        loginPage.waitForLoadEntrance();
        accessToken = UserClient.checkRequestAuthLogin(user);

        System.out.println("Access Token: " + accessToken);
    }

    @Test
    @DisplayName("Неуспешная регистрация пользователя.")
    @Description("Проверяем неуспешную регистрацию пользователя при вводе пароля меньше 6 символов, и появление сообщения 'Некорректный пароль'.")
    public void failedPasswordRegistrationTest() {
        user = new User(name, email, password);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegisterButton();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForLoadRegisterPage();
        registerPage.registration(user.getName(), user.getEmail(), passwordFailed);

        Assert.assertTrue("Текст об ошибке отсутствует", registerPage.checkInvalidPassword());
    }


    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();

        if (accessToken != null) {
            UserClient.deleteUser(accessToken);
        } else {
            System.out.println("AccessToken is null, cannot delete user.");
        }
    }
}
