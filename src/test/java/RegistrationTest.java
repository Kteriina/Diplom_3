import praktikum.burger.client.User;
import praktikum.burger.client.UserClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import praktikum.burger.pageobject.LoginPage;
import praktikum.burger.pageobject.MainPage;
import praktikum.burger.pageobject.RegisterPage;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

public class RegistrationTest extends BaseTest {

    User user;

    @Override
    public void setUp() {

        driver = getWebDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(BASE_URL);

        name = randomAlphanumeric(4, 8);
        email = randomAlphanumeric(6, 10) + "@yandex.ru";
        password = randomAlphanumeric(10, 20);

        user = new User(name, email, password);

    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрайии")
    public void successfulRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegisterButton();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.registration(name, email, password);
        loginPage.waitForLoadEntrance();
        accessToken = UserClient.checkRequestAuthLogin(user);

        System.out.println("Access Token: " + accessToken);
    }

    @Test
    @DisplayName("Неуспешная регистрация пользователя.")
    @Description("Проверяем неуспешную регистрацию пользователя при вводе пароля меньше 6 символов, и появление сообщения 'Некорректный пароль'.")
    public void failedPasswordRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegisterButton();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.waitForLoadRegisterPage();
        String passwordFailed = randomAlphanumeric(1, 5);
        registerPage.registration(name, email, passwordFailed);

        Assert.assertTrue("Текст об ошибке отсутствует", registerPage.checkInvalidPassword());
        accessToken = UserClient.checkRequestAuthLogin(user);

        System.out.println("Access Token: " + accessToken);
    }


}
